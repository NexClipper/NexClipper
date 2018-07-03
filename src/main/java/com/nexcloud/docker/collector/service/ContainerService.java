package com.nexcloud.docker.collector.service;

import com.nexcloud.docker.resource.ResourceLoader;
import com.nexcloud.docker.util.Util;

public class ContainerService {

	public void getContainerSystemInfo(){
		// Get system data
		String uri = String.format(Util.uri_system_info);
		String data = Util.procDockerApi(uri);
		
		// Set system data
		ResourceLoader.getInstance().setResource("system_info", data);	
	}
}