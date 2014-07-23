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

package com.brianjmelton.apcs.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.brianjmelton.apcs.exception.PersistenceException;
import com.brianjmelton.apcs.vo.SerializableCookie;

/**
 * A no-frills Object serializer for Cookies.
 * 
 * @author brianmelton
 * @since July 2014
 */
public class BasicCookieStoreSerializer implements Persister {

    private final File cookieFile;

    /**
     * Constructs a new BasicCookieStoreSerializer
     * 
     * @param pathToCookieFile
     *            the path to the cookie file you would like to create. This
     *            file may or may not exist beforehand.
     */
    public BasicCookieStoreSerializer(File pathToCookieFile) {
        this.cookieFile = pathToCookieFile;
    }

    @Override
    public void persist(Map<URI, List<SerializableCookie>> cookies)
            throws PersistenceException {
        try {
            OutputStream fout = new FileOutputStream(cookieFile);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(cookies);
            IOUtils.closeQuietly(oos);
        } catch (Throwable t) {
            throw new PersistenceException(t);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<URI, List<SerializableCookie>> restore()
            throws PersistenceException {
        try {
            Map<URI, List<SerializableCookie>> map = null;
            if (cookieFile.exists()) {
                InputStream in = new FileInputStream(cookieFile);
                ObjectInputStream ois = new ObjectInputStream(in);
                map = (Map<URI, List<SerializableCookie>>) ois.readObject();
                IOUtils.closeQuietly(ois);
            }
            return map;
        } catch (Throwable t) {
            throw new PersistenceException(t);
        }
    }

}
