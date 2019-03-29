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
package com.nexcloud.util.rest;
import com.sun.jersey.api.client.ClientResponse;

public class RestResponse<T> 
{
	protected ClientResponse 						restResponse 				= null;
	protected T 									response					= null;
	
	
	public RestResponse()
	{
		super();		
	}
	
	public RestResponse( ClientResponse restResponse, T classOfResponse )
	{
		this.restResponse = restResponse;
		this.response = classOfResponse;
	} 

	
	public String getHeader( String key )
	{
		if( restResponse != null && restResponse.getHeaders() != null )
			return restResponse.getHeaders().getFirst( key );
		return "";
	}
	
	/**
	 * @return the restResponse
	 */
	public ClientResponse getRestResponse() {
		return restResponse;
	}

	/**
	 * @param restResponse the restResponse to set
	 */
	public void setRestResponse(ClientResponse restResponse) {
		this.restResponse = restResponse;
	}

	/**
	 * @return the response
	 */
	public T getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(T response) {
		this.response = response;
	}
	
	public String getJSON()
	{
		if( restResponse != null )
			return restResponse.getEntity( String.class );
		return "";
	}

	
	
	
}















