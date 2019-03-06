package com.nexcloud.util.consts;

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
