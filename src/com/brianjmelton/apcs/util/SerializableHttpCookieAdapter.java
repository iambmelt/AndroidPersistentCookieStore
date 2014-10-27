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

package com.brianjmelton.apcs.util;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import com.brianjmelton.apcs.vo.SerializableCookie;

/**
 * Utility methods for de/serializing {@link HttpCookie}
 * 
 * @author brianmelton
 * @since July 2014
 */
public class SerializableHttpCookieAdapter {

	public static HttpCookie deserialize(SerializableCookie sCookie) {
		if (null == sCookie) {
			throw new IllegalArgumentException(
					"Cannot deserialize a null Cookie");
		}
		HttpCookie realCookie = new HttpCookie(sCookie.getName(),
				sCookie.getValue());

		realCookie.setDiscard(sCookie.getDiscard());
		realCookie.setSecure(sCookie.isSecure());
		realCookie.setMaxAge(sCookie.getMaxAge());
		realCookie.setVersion(sCookie.getVersion());
		realCookie.setComment(sCookie.getComment());
		realCookie.setCommentURL(sCookie.getCommentURL());
		realCookie.setDomain(sCookie.getDomain());
		realCookie.setPath(sCookie.getPath());
		realCookie.setPortlist(sCookie.getPortlist());

		return realCookie;
	}

	/**
	 * Transformer method for derserializing {@link SerializableCookie}s into
	 * {@link HttpCookie}s
	 * 
	 * @param cookiesIn
	 * @return
	 */
	public static List<HttpCookie> deserialize(
			List<SerializableCookie> cookiesIn) {
		List<HttpCookie> cookiesOut = new ArrayList<HttpCookie>();

		for (SerializableCookie serializedCookie : cookiesIn) {
			cookiesOut.add(deserialize(serializedCookie));
		}

		return cookiesOut;
	}

	public static List<SerializableCookie> serialize(List<HttpCookie> cookiesIn) {
		List<SerializableCookie> cookiesOut = new ArrayList<SerializableCookie>();
		for (HttpCookie cc : cookiesIn) {
			cookiesOut.add(new SerializableCookie(cc));
		}
		return cookiesOut;
	}

}
