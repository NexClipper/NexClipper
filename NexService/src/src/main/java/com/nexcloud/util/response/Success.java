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
