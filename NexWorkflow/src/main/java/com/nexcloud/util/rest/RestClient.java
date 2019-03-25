package com.nexcloud.util.rest;
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

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status.Family;

import com.nexcloud.util.Util;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;


public class RestClient 
{
	public static enum Method {
		GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
	} 

	/**
	 * 
	 * @param restURL
	 * @param method
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @//throws CoreException 
	 */
	public static <E, T> RestResponse<E> request( String restURL, Method method, T request, Class<E> classOfResponse ) throws ClientHandlerException, UniformInterfaceException
	{
        // Rest Client
		Client client = Client.create();
        client.setConnectTimeout(1000);
        client.setReadTimeout(2000);

        WebResource webResource = client.resource( restURL );
        webResource.type( MediaType.APPLICATION_JSON_TYPE );
        
		// Request JSON
        String reqJSON = request == null ? "" : Util.beanToJson( request );
        //System.out.println( "RestClient req uri ["+method.name()+"] => " + webResource.getURI() );
        //System.out.println( "RestClient req json => " + reqJSON );

        // Method Type
        ClientResponse clientResponse = null;
        if( method == Method.GET )
        	clientResponse = webResource.type( MediaType.APPLICATION_JSON_TYPE ).get( ClientResponse.class );
        else if( method == Method.POST )
        	clientResponse = webResource.type( MediaType.APPLICATION_JSON_TYPE ).post( ClientResponse.class, reqJSON );
        else if( method == Method.PUT )
        	clientResponse = webResource.type( MediaType.APPLICATION_JSON_TYPE ).put( ClientResponse.class, reqJSON );
        else if( method == Method.DELETE )
        	clientResponse = webResource.type( MediaType.APPLICATION_JSON_TYPE ).delete( ClientResponse.class, reqJSON  );

        // Response JSON
        String resJSON = null;
        if( clientResponse.getStatus() != 204 && clientResponse.getStatus() != 202 )
        {
        	if( clientResponse.getStatus() == 201 )
        	{
        		if( method == Method.POST )
        		{
		        	resJSON = clientResponse.getEntity( String.class );
			        //System.out.println( "RestClient res json => " + resJSON );
        		}else if( method == Method.PUT){
        			resJSON = clientResponse.getEntity( String.class );
			        //System.out.println( "RestClient res json => " + resJSON );
        		}
        	}
        	else
        	{
        		resJSON = clientResponse.getEntity( String.class );
        		if( resJSON.startsWith("[") )
        		{
        			resJSON  = "{\"list\":"+resJSON+"}";
        		}
		        //System.out.println( "RestClient res json => " + resJSON );
        	}
        }

        /**
         * SUCCESS OR FAIL
         */
        /*
        System.out.println( "*** Status Code ***********************************************************************" );
        System.out.println( "##############################"+clientResponse.getStatus());
        System.out.println( "*** Status Code ***********************************************************************" );
        */
        if( clientResponse.getResponseStatus().getFamily() != null && clientResponse.getResponseStatus().getFamily() == Family.SUCCESSFUL )
            return new RestResponse<E>( clientResponse, Util.JsonTobean( resJSON, classOfResponse ) );
        else if( clientResponse.getStatus() == 303 )
            return new RestResponse<E>( clientResponse, Util.JsonTobean( resJSON, classOfResponse ) );
        else if( clientResponse.getStatus() == 204 || clientResponse.getStatus() == 404 )
            return new RestResponse<E>( clientResponse, Util.JsonTobean( resJSON, classOfResponse ) );
        else if( clientResponse.getStatus() < 400 )
            return new RestResponse<E>( clientResponse, Util.JsonTobean( resJSON, classOfResponse ) );
        else
        {
        	System.out.println( "*** ERROR START ***********************************************************************" );
        	System.out.println(restURL);
        	System.out.println(reqJSON);
        	System.out.println( "clientResponse.getStatus() : " + clientResponse.getStatus() );
        	System.out.println( "clientResponse.getResponseStatus().getFamily() : " + clientResponse.getResponseStatus().getFamily() );
        	System.out.println( resJSON );
        	System.out.println( "*** ERROR END   ***********************************************************************" );
        	
        	
        	// reprocessing ( token validation exception ) 
        	if( clientResponse.getStatus() == 401 )
        		return reprocessing( restURL, method, request, classOfResponse);
        	else
        		return new RestResponse<E>( clientResponse, Util.JsonTobean( resJSON, classOfResponse ) );
        }
	}
	
	/**
	 * 
	 * @param restURL
	 * @param method
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @//throws CoreException
	 */
	public static <E, T> RestResponse<E> request( String token, String restURL, Method method, T request, Class<E> classOfResponse ) throws ClientHandlerException, UniformInterfaceException
	{
        // Rest Client
		Client client = Client.create();
        client.setConnectTimeout(180*1000);
        client.setReadTimeout(180*1000);

        WebResource webResource = client.resource( restURL );
        webResource.type( MediaType.APPLICATION_JSON_TYPE );
        
		// Request JSON
        String reqJSON = request == null ? "" : Util.beanToJson( request );
        //System.out.println( "RestClient req uri ["+method.name()+"] => " + webResource.getURI() );
        //System.out.println( "RestClient req json => " + reqJSON );

        // Method Type
        ClientResponse clientResponse = null;
        
        if( method == Method.GET )
        	clientResponse = webResource.header("Authorization", token).type( MediaType.APPLICATION_JSON_TYPE ).get( ClientResponse.class );
        else if( method == Method.POST )
        	clientResponse = webResource.header("Authorization", token).type( MediaType.APPLICATION_JSON_TYPE ).post( ClientResponse.class, reqJSON );
        else if( method == Method.PUT )
        	clientResponse = webResource.header("Authorization", token).type( MediaType.APPLICATION_JSON_TYPE ).put( ClientResponse.class, reqJSON );
        else if( method == Method.DELETE )
        	clientResponse = webResource.header("Authorization", token).type( MediaType.APPLICATION_JSON_TYPE ).delete( ClientResponse.class, reqJSON  );

        // Response JSON
        String resJSON = null;
        if( clientResponse.getStatus() != 204 && clientResponse.getStatus() != 202 )
        {
        	if( clientResponse.getStatus() == 201 )
        	{
        		if( method == Method.POST )
        		{
		        	resJSON = clientResponse.getEntity( String.class );
			        //System.out.println( "RestClient res json => " + resJSON );
        		}else if( method == Method.PUT){
        			resJSON = clientResponse.getEntity( String.class );
			        //System.out.println( "RestClient res json => " + resJSON );
        		}
        	}
        	else
        	{
        		resJSON = clientResponse.getEntity( String.class );
        		if( resJSON.startsWith("[") )
        		{
        			resJSON  = "{\"list\":"+resJSON+"}";
        		}
		        //System.out.println( "RestClient res json => " + resJSON );
        	}
        }

        /**
         * SUCCESS OR FAIL
         */
        /*
        System.out.println( "*** Status Code ***********************************************************************" );
        System.out.println( "##############################"+clientResponse.getStatus());
        System.out.println( "*** Status Code ***********************************************************************" );
        */
        if( clientResponse.getResponseStatus().getFamily() != null && clientResponse.getResponseStatus().getFamily() == Family.SUCCESSFUL )
            return new RestResponse<E>( clientResponse, Util.JsonTobean( resJSON, classOfResponse ) );
        else if( clientResponse.getStatus() == 303 )
            return new RestResponse<E>( clientResponse, Util.JsonTobean( resJSON, classOfResponse ) );
        else if( clientResponse.getStatus() == 204 || clientResponse.getStatus() == 404 )
            return new RestResponse<E>( clientResponse, Util.JsonTobean( resJSON, classOfResponse ) );
        else if( clientResponse.getStatus() < 400 )
            return new RestResponse<E>( clientResponse, Util.JsonTobean( resJSON, classOfResponse ) );
        else
        {
        	System.out.println( "*** ERROR START ***********************************************************************" );
        	System.out.println(restURL);
        	System.out.println(reqJSON);
        	System.out.println( "clientResponse.getStatus() : " + clientResponse.getStatus() );
        	System.out.println( "clientResponse.getResponseStatus().getFamily() : " + clientResponse.getResponseStatus().getFamily() );
        	System.out.println( resJSON );
        	System.out.println( "*** ERROR END   ***********************************************************************" );
        	
        	
        	// reprocessing ( token validation exception ) 
        	if( clientResponse.getStatus() == 401 )
        		return reprocessing( restURL, method, request, classOfResponse);
        	else
        		return new RestResponse<E>( clientResponse, Util.JsonTobean( resJSON, classOfResponse ) );
        }
	}
	
	
	public static <E, T> RestResponse<E> reprocessing(String restURL, Method method, T request, Class<E> classOfResponse)
	{
		CallManager.getInstance().setToken("");
		return CallManager.getInstance().request(restURL, method, request, classOfResponse);
	}
}

























