package com.nexcloud.docker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
	private static final Logger logger = LoggerFactory.getLogger(Util.class);
	
	// local test
	public static final String UNIX_SOCKET = "curl --unix-socket /var/run/docker.sock ";
	public static final String API_VERSION = "v1.29";
	
	// azure test
	//public static final String UNIX_SOCKET = "curl http://104.41.134.199:4000";

	//public static final String URI_CONTAINER_LIST = "/containers/json";
	public static final String URI_CONTAINER_LIST = "/containers/json?all=true";
	public static final String URI_CONTAINER_INSPECT = "/containers/%s/json";
	public static final String URI_CONTAINER_PROCESS = "/containers/%s/top";
	public static final String URI_CONTAINER_LOG = "/containers/%s/logs?stderr=1";
	public static final String URI_CONTAINER_STAT = "/containers/%s/stats?stream=false";
	
	public static final String URI_IMAGES_LIST = "/images/json";
	public static final String URI_IMAGES_INSPECT = "/images/%s/json";
	
	public static final String URI_NETWORKS_LIST = "/networks";
	public static final String URI_NETWORKS_INSPECT = "/networks/%s";
	
	public static final String URI_VOLUMES_LIST = "/volumes";
	public static final String URI_VOLUMES_INSPECT = "/volumes/%s";
	
	public static final String URI_SYSTEM_INFO = "/info";
	public static final String URI_SYSTEM_VERSION = "/version";
	
	public static String procDockerApi(String uri) {
		Command cmd = new Command();
		String dockerCommand = String.format("%s http:/%s%s", UNIX_SOCKET, API_VERSION, uri);
		//String docker_command = String.format("%s%s", unix_socket, uri);
		logger.info(dockerCommand);
		
		return cmd.execCommand(dockerCommand);
	}
}