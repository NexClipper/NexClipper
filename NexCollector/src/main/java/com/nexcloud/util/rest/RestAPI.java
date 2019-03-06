package com.nexcloud.util.rest;

import com.nexcloud.util.rest.RestClient.Method;

public class RestAPI
{
	
	private static RestAPI 				thisObj 							= null;
	private static CallManager			callManager							= null;
	
	public RestAPI()
	{
		callManager = CallManager.getInstance(  );
	}

	public RestAPI( String uid, String scretKey )
	{
		
		callManager = CallManager.getInstance( uid, scretKey );
		
		
		/** TEST START **/
		//callManager.setSubject_token("test");
		/** TEST END ***/ 
	}
	
	public synchronized static RestAPI getInstance( String uid, String scretKey){
		if ( thisObj == null ){
			//System.out.println("RestAPI getInstance 실행!!!!!!!!!!!!!!!");
			try {
				thisObj = new RestAPI( uid, scretKey);
			}catch(IndexOutOfBoundsException e){
				System.out.println("RestAPI Class getInstance IndexOutOfBoundsException Error = " +e);
				e.printStackTrace();
			}catch(NullPointerException  e){
				System.out.println("RestAPI Class getInstance NullPointerException Error = " +e);
				e.printStackTrace();
			} catch(Exception e) {
				System.out.println("RestAPI Class getInstance Exception Error = " + e);
				e.printStackTrace();
			}	
		}
		else
		{
			callManager = CallManager.getInstance( uid, scretKey );
		}
		
		return thisObj; 		
	}
	
	public synchronized static RestAPI getInstance(){
		if ( thisObj == null ){
			//System.out.println("RestAPI getInstance 실행!!!!!!!!!!!!!!!");
			try {
				thisObj = new RestAPI();
			}catch(IndexOutOfBoundsException e){
				System.out.println("RestAPI Class getInstance IndexOutOfBoundsException Error = " +e);
				e.printStackTrace();
			}catch(NullPointerException  e){
				System.out.println("RestAPI Class getInstance NullPointerException Error = " +e);
				e.printStackTrace();
			} catch(Exception e) {
				System.out.println("RestAPI Class getInstance Exception Error = " + e);
				e.printStackTrace();
			}	
		}
		else
		{
			callManager = CallManager.getInstance(  );
		}
		
		return thisObj; 		
	}

	/**
	 * 
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @
	 */
	public <E, T> RestResponse<E> get( String url, Class<E> classOfResponse )
	{
		
		return callManager.request( url, Method.GET, null, classOfResponse );
	}
	
	/**
	 * 
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @
	 */
	public <E, T> RestResponse<E> get( String token, String url, Class<E> classOfResponse )
	{
		
		return callManager.request( token, url, Method.GET, null, classOfResponse );
	}

	/**
	 * 
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @
	 */
	public <E, T> RestResponse<E> post( String url, T request, Class<E> classOfResponse ) 
	{
		return callManager.request( url, Method.POST, request, classOfResponse );
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @
	 */
	public <E, T> RestResponse<E> post( String token, String url, T request, Class<E> classOfResponse ) 
	{
		return callManager.request( token, url, Method.POST, request, classOfResponse );
	}
	
	
	/**
	 * 
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @
	 */
	public <E, T> RestResponse<E> put( String url, T request, Class<E> classOfResponse ) 
	{
		return callManager.request( url, Method.PUT, request, classOfResponse );
	}
	
	/**
	 * 
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @
	 */
	public <E, T> RestResponse<E> put( String token, String url, T request, Class<E> classOfResponse ) 
	{
		return callManager.request( token, url, Method.PUT, request, classOfResponse );
	}

	/**
	 * 
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @
	 */
	public <E, T> RestResponse<E> delete( String url, Class<E> classOfResponse ) 
	{
		return callManager.request( url, Method.DELETE, null, classOfResponse );
	}
	
	/**
	 * 
	 * @param request
	 * @param classOfResponse
	 * @return
	 * @
	 */
	public <E, T> RestResponse<E> delete( String token, String url, Class<E> classOfResponse ) 
	{
		return callManager.request( token, url, Method.DELETE, null, classOfResponse );
	}
	
	/**
	 * 
	 * @param restURL
	 * @param map
	 * @param request
	 * @param classOfResponse
	 * @return
	 */
	public <E, T> RestResponse<E> delete( String url, T request, Class<E> classOfResponse ) 
	{
		return callManager.request( url, Method.DELETE, request, classOfResponse );
	}
	
	/**
	 * 
	 * @param restURL
	 * @param map
	 * @param request
	 * @param classOfResponse
	 * @return
	 */
	public <E, T> RestResponse<E> delete( String token, String url, T request, Class<E> classOfResponse ) 
	{
		return callManager.request( token, url, Method.DELETE, request, classOfResponse );
	}
}

























