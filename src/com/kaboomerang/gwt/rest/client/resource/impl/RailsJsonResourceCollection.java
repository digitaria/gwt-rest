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

import com.kaboomerang.gwt.rest.client.resource.ResourceFactory;
import com.kaboomerang.gwt.rest.client.uri.impl.RailsUriBuilder;

/**
 * Implements a Rails-style JSON Resource Collection by configuring a
 * JSONResourceCollection's URI builder with a Rails-specific implementation.
 * 
 * Example:
 * 
 * ResourceCollection projects = new RailsJsonResourceCollection(
 *      "http://example.com/projects/",
 *       new ResourceFactory() {
 *           public Resource create() {
 *               return (Resource) GWT.create(ProjectResource.class);
 *           }
 *       }
 * );
 * 
 * projects.find(42,
 *      new ResourceResponseHandler() {
 *          public void onSuccess(Resource resource) {
 *              Window.alert("Project name is " + ((ProjectResource) resource).getName());
 *          }
 *
 *          public void onError(Request request, Throwable exception) {
 *              Window.alert("Something went wrong.");
 *          }
 *           
 *      }
 * );
 * 
 * @author Jon Crosby
 */
public class RailsJsonResourceCollection extends JsonResourceCollection {
    public RailsJsonResourceCollection(String url, ResourceFactory factory) {
        super(url, factory);
        this.uriBuilder = new RailsUriBuilder();
    }
}
