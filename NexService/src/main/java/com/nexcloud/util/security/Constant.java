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
package com.nexcloud.util.security;
/**
 * 공통 데이터를 관리하는 Static Enum 클래스
 */
public enum Constant {
	INSTANT;

	public static final String RESULT = "result";
	public static final String RESULT_DATA = "resultdata";

	// 프로젝트명
	public static final String CONTEXT_ROOT = "nexcloud";

	// 로그인
	public static final String USER = "USER";
	public static final String LOGIN = "login";
	public static final String COMMON = "common";
	public static final String LOGIN_COOKIE = "loginCookie";
	public static final int COOKIE_LIMIT_TIME = 60 * 60 * 24 * 30;
	public static final int LOGOUT_TIME = 1000 * 60 * 20;

	public static enum ROLE_TYPE {
		ROLE_USER, ROLE_ADMIN
	};
}