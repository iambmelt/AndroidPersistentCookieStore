/*
 * Copyright 2014 Brian Melton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brianjmelton.apcs;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.brianjmelton.apcs.api.PersistenceExceptionHandler;
import com.brianjmelton.apcs.api.Persister;
import com.brianjmelton.apcs.exception.PersistenceException;
import com.brianjmelton.apcs.util.SerializableHttpCookieAdapter;
import com.brianjmelton.apcs.vo.SerializableCookie;

/**
 * Thread-safe Binder to an instance of {@link CookieStore}, delegates to a
 * {@link Persister} for write/delete actions
 * 
 * @author brianmelton
 * 
 */
public class PersistentCookieStore implements CookieStore {

	private final CookieStore store;
	private final Persister persister;
	private final PersistenceExceptionHandler exceptionHandler;
	private final String LOG_TAG;
	private final boolean logVerbose, logErrors;

	protected PersistentCookieStore(CookieStore store, Persister persister,
			PersistenceExceptionHandler exceptionHandler, String LOG_TAG,
			boolean logVerbose, boolean logErrors) {
		this.store = store;
		this.persister = persister;
		this.exceptionHandler = exceptionHandler;
		this.LOG_TAG = LOG_TAG;
		this.logVerbose = logVerbose;
		this.logErrors = logErrors;
		restore();
	}

	/**
	 * Builder-pattern Object creator to stop icky telescoping-constructor calls
	 * 
	 */
	public static class Builder {

		private CookieStore store;
		private Persister persister;
		private PersistenceExceptionHandler exceptionHandler;
		private String logTag;
		private boolean logVerbose, logErrors;

		/**
		 * The in-memory CookieStore this PersistentCookieStore will bind to in
		 * order to replicate
		 * 
		 * @param cookiestore
		 * @return a reference to this Builder
		 */
		public Builder useCookieStore(CookieStore cookiestore) {
			if (null == cookiestore) {
				throw new IllegalArgumentException(
						"Cookiestore cannot be null.");
			}
			this.store = cookiestore;
			return this;
		}

		/**
		 * The {@link Persister} this {@link CookieStore} subclass should
		 * delegate to for the actual persisting
		 * 
		 * @param persister
		 * @return a reference to this Builder
		 */
		public Builder usePersister(Persister persister) {
			if (null == persister) {
				throw new IllegalArgumentException("Persister cannot be null");
			}
			this.persister = persister;
			return this;
		}

		/**
		 * If logging is enabled, log statements will be logged using the
		 * supplied tag.
		 * 
		 * @param logTag
		 *            the log tag to use
		 * @return a reference to this Builder
		 */
		public Builder logWithTag(String logTag) {
			if (null == logTag) {
				this.logTag = "PersistentCookieStore";
			}
			this.logTag = logTag;
			return this;
		}

		/**
		 * The {@link PersistenceExceptionHandler} to which {@link Exception}s
		 * will be thrown.
		 * 
		 * @param exceptionHandler
		 * @return a reference to this Builder
		 */
		public Builder throwTo(PersistenceExceptionHandler exceptionHandler) {
			if (null == exceptionHandler) {
				throw new IllegalArgumentException(
						"ExceptionHandler cannot be null");
			}
			this.exceptionHandler = exceptionHandler;
			return this;
		}

		/**
		 * Log CookieStore actions, verbosely. Mostly boring things.
		 * 
		 * @return a reference to this Builder
		 */
		public Builder logVerbose() {
			this.logVerbose = true;
			this.logErrors = true;
			return this;
		}

		/**
		 * Log errors, {@link Exception}s, etc. General problem things.
		 * 
		 * @return a reference to this Builder
		 */
		public Builder logErrors() {
			this.logErrors = true;
			return this;
		}

		/**
		 * Creates a {@link PersistentCookieStore} with the arguments supplied
		 * to this {@link Builder}
		 * 
		 * @return the constructed {@link PersistentCookieStore}
		 */
		public PersistentCookieStore build() {
			checkInitParams();
			if (logVerbose) {
				Log.v(logTag, "Creating new PersistentCookieStore...");
			}
			return new PersistentCookieStore(store, persister,
					exceptionHandler, logTag, logVerbose, logErrors);
		}

		/**
		 * Verifies that the supplied arguments make sense.
		 */
		private void checkInitParams() {
			String msg;
			if (null == store) {
				msg = "Cookie store ";
			} else if (null == persister) {
				msg = "Persister ";
			} else if (null == exceptionHandler) {
				msg = "ExceptionHandler ";
			} else if (null == logTag) {
				msg = "LOG_TAG ";
			} else {
				return;
			}
			throw new IllegalStateException(msg + "cannot be null.");
		}
	}

	@Override
	public synchronized void add(URI uri, HttpCookie cookie) {
		if (logVerbose) {
			Log.i(LOG_TAG, "add(URI " + uri + ", HttpCookie " + cookie + ")");
		}

		store.add(uri, cookie);

		persist();
	}

	@Override
	public List<HttpCookie> get(URI uri) {
		List<HttpCookie> cookies = store.get(uri);

		if (logVerbose) {
			Log.i(LOG_TAG, "get(URI " + uri + ") - " + cookies);
		}

		return cookies;
	}

	@Override
	public List<HttpCookie> getCookies() {
		List<HttpCookie> cookies = store.getCookies();

		if (logVerbose) {
			Log.i(LOG_TAG, "getCookies() - " + cookies);
		}

		return cookies;
	}

	@Override
	public List<URI> getURIs() {
		List<URI> uris = store.getURIs();

		if (logVerbose) {
			Log.i(LOG_TAG, "getURIs() - " + uris.toString());
		}

		return uris;
	}

	@Override
	public synchronized boolean remove(URI uri, HttpCookie cookie) {
		boolean removed = store.remove(uri, cookie);

		if (logVerbose) {
			Log.i(LOG_TAG, "remove(URI " + uri + ", HttpCookie " + cookie
					+ ") - " + removed);
		}

		if (removed) {
			persist();
		}

		return removed;
	}

	@Override
	public synchronized boolean removeAll() {
		boolean allRemoved = store.removeAll();

		if (logVerbose) {
			Log.i(LOG_TAG, "removeAll() - " + allRemoved);
		}

		if (allRemoved) {
			persist();
		}

		return allRemoved;
	}

	private void persist() {
		try {
			persister.persist(exportCookies());
		} catch (PersistenceException e) {

			if (logErrors) {
				e.printStackTrace();
			}

			exceptionHandler.onPersistFailure(new PersistenceException(e));
		}
	}

	private void restore() {
		try {
			importCookies(persister.restore());
		} catch (PersistenceException e) {

			if (logErrors) {
				e.printStackTrace();
			}

			exceptionHandler.onRestoreFailure(e);
		}
	}

	private void importCookies(
			Map<URI, List<SerializableCookie>> cookiesToRestore) {
		Map<URI, List<HttpCookie>> restoredCookies = new HashMap<URI, List<HttpCookie>>();
		if (null != cookiesToRestore) {
			for (URI uri : cookiesToRestore.keySet()) {
				restoredCookies.put(uri, SerializableHttpCookieAdapter
						.deserialize(cookiesToRestore.get(uri)));
			}
			for (URI uri : restoredCookies.keySet()) {
				for (HttpCookie cookie : restoredCookies.get(uri)) {
					store.add(uri, cookie);
				}
			}
		}

	}

	private Map<URI, List<SerializableCookie>> exportCookies() {
		Map<URI, List<SerializableCookie>> preserializedCookies = new HashMap<URI, List<SerializableCookie>>();
		for (URI uri : store.getURIs()) {
			preserializedCookies.put(uri,
					SerializableHttpCookieAdapter.serialize(store.get(uri)));
		}
		return preserializedCookies;
	}

}
