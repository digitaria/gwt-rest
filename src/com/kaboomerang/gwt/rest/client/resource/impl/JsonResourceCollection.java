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
package com.kaboomerang.gwt.rest.client.resource.impl;

import com.kaboomerang.gwt.rest.client.resource.ResourceCollection;
import com.kaboomerang.gwt.rest.client.resource.ResourceFactory;
import com.google.gwt.http.client.RequestBuilder;

/**
 * Configures an AbstractResourceCollection with JSON specific headers and a
 * JSON implementation of a ResourceCollectionParser.
 * 
 * Needs to be extended to configure the proper URI builder for a given style
 * of implementation.
 * 
 * @author Jon Crosby
 */
public abstract class JsonResourceCollection
        extends AbstractResourceCollection implements ResourceCollection {
    
    public JsonResourceCollection(String url, ResourceFactory factory) {
        this.url = url;
        this.factory = factory;
        this.parser = new JsonResourceCollectionParser();
    }
    
    protected void declareAccept(RequestBuilder builder) {
        builder.setHeader("Accept","application/json");
    }
    
    protected void declareContentType(RequestBuilder builder) {
        builder.setHeader("Content-Type", "application/json");
    }

}
