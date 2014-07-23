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

import com.brianjmelton.apcs.exception.PersistenceException;

/**
 * This interface specifies callbacks to be implemented by those who wish to
 * provide their own Exception handler
 * 
 * @author brianmelton
 * @since July 2014
 * 
 */
public interface PersistenceExceptionHandler {

    /**
     * Called when a cookie persist operation fails.
     * 
     * @param e
     *            The wrapped Throwable which caused this PersistenceException.
     */
    public void onPersistFailure(PersistenceException e);

    /**
     * Called when a cookie restore operation fails.
     * 
     * @param e
     *            The wrapped Throwable which caused this PersistenceException.
     */
    public void onRestoreFailure(PersistenceException e);

}
