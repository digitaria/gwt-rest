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

import com.kaboomerang.gwt.rest.client.resource.Resource;
import com.kaboomerang.gwt.rest.client.resource.ResourceCollectionParser;
import com.kaboomerang.gwt.rest.client.resource.ResourceFactory;
import com.kaboomerang.gwt.rest.client.uri.UriBuilder;
import com.kaboomerang.gwt.rest.client.callback.CollectionResponseCallback;
import com.kaboomerang.gwt.rest.client.callback.ResourceCreationResponseCallback;
import com.kaboomerang.gwt.rest.client.callback.ResourceResponseCallback;
import com.kaboomerang.gwt.rest.client.callback.StatusResponseCallback;
import com.kaboomerang.gwt.rest.client.handler.CollectionResponseHandler;
import com.kaboomerang.gwt.rest.client.handler.ResourceResponseHandler;
import com.kaboomerang.gwt.rest.client.handler.StatusResponseHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;

/**
 * Implements the generic functionality required by a ResourceCollection.
 * 
 * @author Jon Crosby
 */
public abstract class AbstractResourceCollection {
    /** Base URL for this collection of resources */
    protected String url;
    
    /** Collection Parser for the representation format delivered by the factory */
    protected ResourceCollectionParser parser;
    
    /** Factory used to create all resources returned by this collection */
    protected ResourceFactory factory;
    
    /** Provides URIs for the various REST operations */
    protected UriBuilder uriBuilder;
    
    public void find(int id, ResourceResponseHandler handler) {
        RequestBuilder builder = buildGet(id);
        ResourceResponseCallback requestCallback = new ResourceResponseCallback(factory, handler);
         
        try {
            Request response = builder.sendRequest(null, requestCallback);
        } catch (RequestException e) {
            handler.onError(null, e);
        }
    }
    
    public void findAll(CollectionResponseHandler handler) {
        RequestBuilder builder = buildGet();
        CollectionResponseCallback requestCallback = new CollectionResponseCallback(factory, handler, parser);
        
        try {
            Request response = builder.sendRequest(null, requestCallback);
        } catch (RequestException e) {
            handler.onError(null, e);
        }
    }
    
    public void create(Resource resource, ResourceResponseHandler handler) {
        RequestBuilder builder = buildPost();
        ResourceResponseCallback requestCallback = new ResourceCreationResponseCallback(factory, handler);
        
        try {
            Request response = builder.sendRequest(resource.toRepresentation(), requestCallback);
        } catch (RequestException e) {
            handler.onError(null, e);
        }
        
    }
    
    public void update(Resource resource, StatusResponseHandler handler) {
        RequestBuilder builder = buildPut(resource.getId());
        StatusResponseCallback requestCallback = new StatusResponseCallback(handler);
        
        try {
            Request response = builder.sendRequest(resource.toRepresentation(), requestCallback);
        } catch (RequestException e) {
            handler.onError(null, e);
        }
    }
    
    public void delete(int id, StatusResponseHandler handler) {
        RequestBuilder builder = buildDelete(id);
        StatusResponseCallback requestCallback = new StatusResponseCallback(handler);
        
        try {
            Request response = builder.sendRequest(null, requestCallback);
        } catch (RequestException e) {
            handler.onError(null, e);
        }
    }
    
    /** Builds GET request for a collection of resources */
    protected RequestBuilder buildGet() {
        RequestBuilder builder = new RequestBuilder(
                RequestBuilder.GET,
                uriBuilder.buildCollectionGetUri(url));
        declareAccept(builder);
        declareContentType(builder);
        return builder;
    }
    
    /** Builds a GET request for a single resource */
    protected RequestBuilder buildGet(int id) {
        RequestBuilder builder = new RequestBuilder(
                RequestBuilder.GET,
                uriBuilder.buildGetUri(url, id));
        declareAccept(builder);
        declareContentType(builder);
        return builder;
    }
    
    /** Builds a POST for resource creation */
    protected RequestBuilder buildPost() {
        RequestBuilder builder = new RequestBuilder(
                RequestBuilder.POST,
                uriBuilder.buildPostUri(url));
        declareAccept(builder);
        declareContentType(builder);
        return builder;
    }
    
    /**
     * Fakes a PUT over POST to update a resource. This tunneling is
     * necessary due to browser constraints.
     */
    protected RequestBuilder buildPut(int id) {
        RequestBuilder builder = new RequestBuilder(
                RequestBuilder.POST, // WebKit
                uriBuilder.buildPutUri(url, id));
        declareAccept(builder);
        declareContentType(builder);
        return builder;
    }
    
    /**
     * Fakes a DELETE request for a resource. As with PUT, this tunneling is
     * necessary due to browser constraints.
     */
    protected RequestBuilder buildDelete(int id) {
        RequestBuilder builder = new RequestBuilder(
                RequestBuilder.POST, // WebKit
                uriBuilder.buildDeleteUri(url, id));
        declareAccept(builder);
        declareContentType(builder);
        return builder;
    }
    
    protected abstract void declareAccept(RequestBuilder builder);
    
    protected abstract void declareContentType(RequestBuilder builder);
}
