package com.nexcloud.util;
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
public class Const {
		
	/**
	 * Redis key
	 */
	public static final String MESOS				= "mesos";
	
	public static final String MARATHON				= "marathon";
	
	public static final String DCOS					= "dcos";
	
	
	/**
	 * Redis field
	 */
	public static final String MASTER				= "master";
	
	public static final String NODE					= "node";
	
	public static final String TASK					= "%s_%s";
	
	public static final String NODES				= "nodes";
	
	public static final String TASKS				= "tasks";
	
	public static final String APPS					= "apps";
	
	public static final String FRAMEWORK			= "framework";
	
	public static final String DEPLOYMENT			= "deployment";
	

	
	/**
	 * Session Key
	 */
	public static final String USER_SESSION_KEY 	= "USER_INFO";
	
	/**
	 * HTTP Response Code
	 */
	public static final int HTTP_OK					= 200;
}

