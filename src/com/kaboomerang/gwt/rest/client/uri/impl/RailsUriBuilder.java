/*
 *  Copyright 2007, Jon Crosby. All rights reserved.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package com.kaboomerang.gwt.rest.client.uri.impl;

import com.kaboomerang.gwt.rest.client.uri.UriBuilder;

/**
 * Rails-style URI Builder implementation.
 * 
 * @author Jon Crosby
 */
public class RailsUriBuilder implements UriBuilder {

    public String buildGetUri(String baseUri, int id) {
        return baseUri + "/" + id + ".gwt";
    }

    public String buildCollectionGetUri(String baseUri) {
        return baseUri + ".gwt";
    }

    public String buildPostUri(String baseUri) {
        return baseUri + ".gwt";
    }

    public String buildPutUri(String baseUri, int id) {
        return baseUri + "/" + id + ".gwt" + "?_method=put";
    }

    public String buildDeleteUri(String baseUri, int id) {
        return baseUri + "/" + id + ".gwt" + "?_method=delete";
    }

}
