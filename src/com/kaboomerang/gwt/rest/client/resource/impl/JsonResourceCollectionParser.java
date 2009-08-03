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
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A JSON implementation of a ResourceCollectionParser. Parses a JSON array into
 * individual JSON objects using the given ResourceFactory to build and return
 * a Collection of Resources.
 * 
 * @author Jon Crosby
 */
public class JsonResourceCollectionParser implements ResourceCollectionParser {

    public Collection parse(String text, ResourceFactory factory) {
        JSONArray json = (JSONArray) JSONParser.parse(text);
        Collection resources = new ArrayList();
            
        for (int i = 0; i < json.size(); i++) {
            Resource resource = factory.create();
            resource.populateFromRepresentation(json.get(i).isObject().toString());
            resources.add(resource);
        }
        
        return resources;
    }

}
