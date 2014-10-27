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

import java.io.File;
import java.net.CookieManager;
import java.net.CookieStore;

import com.brianjmelton.apcs.api.BasicCookieStoreSerializer;
import com.brianjmelton.apcs.api.StubPersistenceExceptionHandler;
import com.brianjmelton.apcs.vo.SerializableCookie;

/**
 * A turn-key implementation of {@link CookieStore}
 * 
 * @author brianmelton
 * 
 */
public class DefaultPersistentCookieStore extends PersistentCookieStore {

	/**
	 * Constructs a new instance of DefaultPersistsCookieStore.
	 * 
	 * @param pathToCookieFile
	 *            the path to the {@link SerializableCookie} storage file you
	 *            would like to use. If this file doesn't exist it will be
	 *            created.
	 */
	public DefaultPersistentCookieStore(File pathToCookieFile) {
		super(new CookieManager().getCookieStore(),
				new BasicCookieStoreSerializer(pathToCookieFile),
				new StubPersistenceExceptionHandler(),
				"DefaultPersistentCookieStore", false, true);
	}
}
