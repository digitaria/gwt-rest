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
package com.kaboomerang.gwt.rest.client.resource;

import com.kaboomerang.gwt.rest.client.handler.CollectionResponseHandler;
import com.kaboomerang.gwt.rest.client.handler.ResourceResponseHandler;
import com.kaboomerang.gwt.rest.client.handler.StatusResponseHandler;

/**
 * The central interface for managing a collection of Resources of a particular
 * type. Does not currently manage a cache, but future implementations will
 * likely store a map of the data in order to minimize HTTP traffic.
 * 
 * All operations are asynchronous and use their respective handlers to deliver
 * a success or error response.
 * 
 * See RailsJsonResourceCollection for an example.
 * 
 * @author Jon Crosby
 */
public interface ResourceCollection {
    /** Requests a collection of resources */
    void findAll(CollectionResponseHandler handler);
    
    /** Requests an individual resource */
    void find(int id, ResourceResponseHandler handler);
    
    /** Creates a resource */
    void create(Resource resource, ResourceResponseHandler handler);
    
    /** Updates a resource */
    void update(Resource resource, StatusResponseHandler handler);
    
    /** Deletes a resource */
    void delete(int id, StatusResponseHandler handler);
}
