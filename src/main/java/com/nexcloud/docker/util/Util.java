package com.nexcloud.docker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
	private static final Logger logger = LoggerFactory.getLogger(Util.class);
	
	// local test
	public static String unix_socket = "curl --unix-socket /var/run/docker.sock ";
	public static String api_version = "v1.29";
	
	// azure test
	//public static String unix_socket = "curl http://104.41.134.199:4000";

	//public static String uri_container_list = "/containers/json";
	public static String uri_container_list = "/containers/json?all=true";
	public static String uri_container_inspect = "/containers/%s/json";
	public static String uri_container_process = "/containers/%s/top";
	public static String uri_container_log = "/containers/%s/logs?stderr=1";
	public static String uri_container_stat = "/containers/%s/stats?stream=false";
	
	public static String uri_images_list = "/images/json";
	public static String uri_images_inspect = "/images/%s/json";
	
	public static String uri_networks_list = "/networks";
	public static String uri_networks_inspect = "/networks/%s";
	
	public static String uri_volumes_list = "/volumes";
	public static String uri_volumes_inspect = "/volumes/%s";
	
	public static String uri_system_info = "/info";
	public static String uri_system_version = "/version";
	
	public static String procDockerApi(String uri) {
		Command cmd = new Command();
		String docker_command = String.format("%s http:/%s%s", unix_socket, api_version, uri);
		//String docker_command = String.format("%s%s", unix_socket, uri);
		logger.info(docker_command);
		
		return cmd.execCommand(docker_command);
	}
}