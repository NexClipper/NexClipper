package com.nexcloud.util.rest;

import com.nexcloud.util.Util;
import com.nexcloud.util.rest.RestClient.Method;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;



public class CallManager
{
	private static CallManager 				thisObj 							= null;
	
	/**
	 * token Validation
	 */
	private String							token								= null;
	
	private String							uid									= null;
	
	private String							scretKey							= null;
	
	
	public CallManager( String uid, String scretKey )
	{
		this.uid		= uid;
		this.scretKey	= scretKey;
	}
	
	public CallManager( )
	{
		
	}
	
	public synchronized static CallManager getInstance( String uid, String scretKey ){
		if ( thisObj == null ){
			//System.out.println("CallManager getInstance 실행!!!!!!!!!!!!!!!");
			try {
				thisObj = new CallManager( uid, scretKey );
			}catch(IndexOutOfBoundsException e){
				System.out.println("CallManager Class getInstance IndexOutOfBoundsException Error = " +e);
				e.printStackTrace();
			}catch(NullPointerException  e){
				System.out.println("CallManager Class getInstance NullPointerException Error = " +e);
				e.printStackTrace();
			} catch(Exception e) {
				System.out.println("CallManager Class getInstance Exception Error = " + e);
				e.printStackTrace();
			}	
		}
		/*
		else
		{
			thisObj = new CallManager( uid, scretKey );
		}
		*/
		
		return thisObj; 		
	}
	
	public synchronized static CallManager getInstance( ){
		if ( thisObj == null ){
			//System.out.println("CallManager getInstance 실행!!!!!!!!!!!!!!!");
			try {
				thisObj = new CallManager( );
			}catch(IndexOutOfBoundsException e){
				System.out.println("CallManager Class getInstance IndexOutOfBoundsException Error = " +e);
				e.printStackTrace();
			}catch(NullPointerException  e){
				System.out.println("CallManager Class getInstance NullPointerException Error = " +e);
				e.printStackTrace();
			} catch(Exception e) {
				System.out.println("CallManager Class getInstance Exception Error = " + e);
				e.printStackTrace();
			}	
		}	
		
		return thisObj; 		
	}
	 

	/**
	 * 
	 * @param method
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @
	 */
	public <E, T> RestResponse<E> request( String restURL, Method method, T request, Class<E> classOfResponse )
	{
		if( token == null || "".equals(token) )
		{
			this.authToken();
		}
		//System.out.println(" Token ::: " + getToken() );
		RestResponse<E> restResponse = RestClient.request( "token="+getToken(), restURL, method, request, classOfResponse );
        
        return restResponse;
	}
	
	/**
	 * 
	 * @param method
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @
	 */
	public <E, T> RestResponse<E> request( String token, String restURL, Method method, T request, Class<E> classOfResponse )
	{
		RestResponse<E> restResponse = RestClient.request( token, restURL, method, request, classOfResponse );
        
        return restResponse;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public void authToken()
	{
		long exp	= Util.addDate(3,0,0,0,0,0);
		try{
			JWSSigner signer = new MACSigner(this.scretKey);
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
							.claim("email", this.uid)
			                .claim("aud", this.scretKey)
			                .claim("uid", this.uid)
			                .claim("exp",exp)
			                .claim("email_verified", true)
			                .build();
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
			signedJWT.sign(signer);
			 
			String jwtString = signedJWT.serialize();
			this.setToken(jwtString);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

























