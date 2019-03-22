package com.nexcloud.util.consts;
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
public interface HTTP {

	interface CODE {
		final int OK 							= 200;
		final int MALFORMED_URL_EXCEPTION 		= 20000;
		final int IO_EXCEPTION 					= 20001;
		final int EXCEPTION						= 20002;
	}

	interface MESSAGE {
		final String OK 						= "OK";
		final String MALFORMED_URL_EXCEPTION 	= "MalformedURLException";
		final String IO_EXCEPTION				= "IOException";
		final String EXCEPTION					= "Exception";
	}
}
