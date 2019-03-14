package com.nexcloud.util.response;


public class Error implements Response {
	/** 
	 * 
	 * Error Code[20000~29999]
	 * 
	 * 20400 : Bad Request
	 * 20401 : Unauthorized
	 * 20403 : Forbidden
	 * 20404 : Not Found
	 * 20500 : Internal Error / Server Error
	 * 
	 * 21001 : MalformedURLException
	 * 21002 : IOException
	 *  
	 * ETC..
	 *  
	**/	
		
	private int errorCode;
	// error mesage
	private String errorMessage;
	// error detail
	private String errorDetail;
	

	
	
	public Error(int errorCode, String errorMessage, String errorDetail) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorDetail = errorDetail;
	}




	public int getErrorCode() {
		return errorCode;
	}




	public String getErrorMessage() {
		return errorMessage;
	}




	public String getErrorDetail() {
		return errorDetail;
	}
}
