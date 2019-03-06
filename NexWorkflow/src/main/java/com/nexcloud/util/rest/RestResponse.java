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















