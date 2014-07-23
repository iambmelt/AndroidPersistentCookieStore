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

package com.brianjmelton.apcs.vo;

import java.io.Serializable;
import java.net.HttpCookie;

/**
 * Like a cookie, but non-final and allows for serialization.
 * 
 * @author brianmelton
 * @since July 2014
 * 
 */
public class SerializableCookie implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean discard, secure, expired;
    private long maxAge;
    private int version;
    private String comment, commentURL, domain, name, path, portlist, value;

    public SerializableCookie(final HttpCookie cookie) {
        this.discard = cookie.getDiscard();
        this.secure = cookie.getSecure();
        this.expired = cookie.hasExpired();
        this.maxAge = cookie.getMaxAge();
        this.version = cookie.getVersion();
        this.comment = cookie.getComment();
        this.commentURL = cookie.getCommentURL();
        this.domain = cookie.getDomain();
        this.name = cookie.getName();
        this.path = cookie.getPath();
        this.portlist = cookie.getPortlist();
        this.value = cookie.getValue();
    }

    /**
     * @return the discard
     */
    public boolean getDiscard() {
        return discard;
    }

    /**
     * @param discard
     *            the discard to set
     */
    public void setDiscard(boolean discard) {
        this.discard = discard;
    }

    /**
     * @return the secure
     */
    public boolean isSecure() {
        return secure;
    }

    /**
     * @param secure
     *            the secure to set
     */
    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    /**
     * @return the expired
     */
    public boolean isExpired() {
        return expired;
    }

    /**
     * @param expired
     *            the expired to set
     */
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    /**
     * @return the maxAge
     */
    public long getMaxAge() {
        return maxAge;
    }

    /**
     * @param maxAge
     *            the maxAge to set
     */
    public void setMaxAge(long maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the commentURL
     */
    public String getCommentURL() {
        return commentURL;
    }

    /**
     * @param commentURL
     *            the commentURL to set
     */
    public void setCommentURL(String commentURL) {
        this.commentURL = commentURL;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain
     *            the domain to set
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     *            the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the portlist
     */
    public String getPortlist() {
        return portlist;
    }

    /**
     * @param portlist
     *            the portlist to set
     */
    public void setPortlist(String portlist) {
        this.portlist = portlist;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
