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