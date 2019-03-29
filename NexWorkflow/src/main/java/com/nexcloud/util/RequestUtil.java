/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.nexcloud.util;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;




public class RequestUtil extends HttpServletRequestWrapper {


	HashMap<String, Object> params;
	@SuppressWarnings("unchecked")
	public RequestUtil(HttpServletRequest request) {
		super(request);
		
		this.params = new HashMap<String, Object>(request.getParameterMap());
	
	}


	public String getParameter(String name){
		String returnValue = null;
		
		String[] paramArray = getParameterValues(name);
		
		if (paramArray != null && paramArray.length > 0){
		
			returnValue = paramArray[0];
		
		}
		
		return returnValue;
	}
	
	
	@SuppressWarnings("unchecked")
	
	public Map getParameterMap() {
		return Collections.unmodifiableMap(params);
	
	}
	
	
	
	@SuppressWarnings("unchecked")
	
	public Enumeration getParameterNames() {
	
		return Collections.enumeration(params.keySet());
	
	}
	
	
	public String[] getParameterValues(String name) {
	
		String[] result = null;
		
		String[] temp = (String[])params.get(name);
		
		if (temp != null){
		
			result = new String[temp.length];
			
			System.arraycopy(temp, 0, result, 0, temp.length);
		
		}
		
		return result;
	
	}
	
	
	
	public void setParameter(String name, String value){
	
		String[] oneParam = {value};
		
		setParameter(name, oneParam);
	
	}
	
	
	public void setParameter(String name, String[] value){
	
		params.put(name, value);
	}

}

