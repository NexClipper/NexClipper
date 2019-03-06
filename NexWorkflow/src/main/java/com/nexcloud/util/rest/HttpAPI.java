package com.nexcloud.util.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status.Family;

import com.nexcloud.util.Util;
import com.nexcloud.util.rest.RestClient.Method;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;


public class HttpAPI 
{
	public static synchronized void write( String endpoint, String msg) throws IOException
	{
		URL url = new URL(endpoint);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	    conn.setUseCaches(false);
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "text/plain"); 
	    try {
	    	if( msg != null && "".equals(msg))
	    	{
		        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		        wr.write(msg);
		        wr.flush();
		        wr.close();
	    	}
	    	
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	        String inputLine = null;
	        while((inputLine = br.readLine()) != null){
				System.out.println(inputLine);
			}
			
			br.close();

	    } catch (Exception e) {
	        e.printStackTrace();
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
	public static synchronized void request( String restURL, MediaType type, Method method,  String reqJSON ) throws ClientHandlerException, UniformInterfaceException
	{
        // Rest Client
		Client client = Client.create();
        client.setConnectTimeout(1000);
        client.setReadTimeout(2000);

        WebResource webResource = client.resource( restURL );
        webResource.type( type );

        // Method Type
        ClientResponse clientResponse = null;
        if( method == Method.GET )
        	clientResponse = webResource.type( type ).get( ClientResponse.class );
        else if( method == Method.POST )
        	clientResponse = webResource.type( type ).post( ClientResponse.class, reqJSON );
        else if( method == Method.PUT )
        	clientResponse = webResource.type( type ).put( ClientResponse.class, reqJSON );
        else if( method == Method.DELETE )
        	clientResponse = webResource.type( type ).delete( ClientResponse.class, reqJSON  );

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
        {
        	
        }
        else if( clientResponse.getStatus() == 303 )
        {
        	
        }
        else if( clientResponse.getStatus() == 204 || clientResponse.getStatus() == 404 )
        {
        	
        }
        else if( clientResponse.getStatus() < 400 )
        {
        	
        }
        else
        {
        	System.out.println( "*** ERROR START ***********************************************************************" );
        	System.out.println(restURL);
        	System.out.println(reqJSON);
        	System.out.println( "clientResponse.getStatus() : " + clientResponse.getStatus() );
        	System.out.println( "clientResponse.getResponseStatus().getFamily() : " + clientResponse.getResponseStatus().getFamily() );
        	System.out.println( resJSON );
        	System.out.println( "*** ERROR END   ***********************************************************************" );
        }
	}
}
