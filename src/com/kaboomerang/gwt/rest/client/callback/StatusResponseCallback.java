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
package com.kaboomerang.gwt.rest.client.callback;

import com.kaboomerang.gwt.rest.client.handler.StatusResponseHandler;
import com.kaboomerang.gwt.rest.client.resource.ResourceFactory;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

/**
 * Implements GWT's RequestCallback interface, passing a status for successful
 * requests or an error for failed requests to a StatusResponseHandler.
 * 
 * @author Jon Crosby
 */
public class StatusResponseCallback
        extends AbstractResponseCallback implements RequestCallback {
    protected ResourceFactory factory;
    protected StatusResponseHandler callback;
    
    public StatusResponseCallback(StatusResponseHandler handler) {
        this.callback = handler;
    }
    
    /** Callback for GWT's request builder upon receiving a response. */
    public void onResponseReceived(Request request, Response response) {
        if (response.getStatusCode() != expectedResponseCode) {
            onError(request, null);
        } else {
            callback.onSuccess();
        }
    }
    
    /** Callback for GWT's request builder upon encountering an error condition */
    public void onError(Request request, Throwable exception) {
        callback.onError(request, exception);
    }
}
