package com.nexcloud.util.response;

public class Success implements Response{
	// 200 : success 
	private int responseCode; 
	// Message
	private String responseMessage;
	// [mysql, influx, redis] or [metrics, array, redis type, json]
	private String responseType;
	// data
	private String responseBody;
	
	
	public Success(int responseCode, String responseMessage, String responseType,
			String responseBody) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.responseType = responseType;
		this.responseBody = responseBody;
	}


	public int getResponseCode() {
		return responseCode;
	}


	public String getResponseMessage() {
		return responseMessage;
	}


	public String getResponseType() {
		return responseType;
	}


	public String getResponseBody() {
		return responseBody;
	}
}
