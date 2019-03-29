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
