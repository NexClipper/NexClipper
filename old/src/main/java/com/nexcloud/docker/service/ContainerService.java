package com.nexcloud.docker.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nexcloud.docker.container.list.domain.Container;
import com.nexcloud.docker.container.stats.domain.History;
import com.nexcloud.docker.container.stats.domain.Resource;
import com.nexcloud.docker.resource.ResourceLoader;
import com.nexcloud.docker.util.Util;

@Service
public class ContainerService {
	private static final Logger logger = LoggerFactory.getLogger(ContainerService.class);
	private static final int log_cnt = 10000;
	
	public String getContainerInspect(String id) {
		String uri = String.format(Util.URI_CONTAINER_INSPECT, id);
		return Util.procDockerApi(uri);
	}
	
	public String getContainerProcess(String id) {
		String uri = String.format(Util.URI_CONTAINER_PROCESS, id);
		return Util.procDockerApi(uri);
	}
	
	public String getContainerLog(String container_id) throws IOException {
		List<String> res = new ArrayList<String>();
		String command = String.format("/var/lib/docker/containers/%s/%s-json.log", container_id, container_id);
		File file = new File(command);
		int counter = 0;
		
		try{
			ReversedLinesFileReader object = new ReversedLinesFileReader(file);
			while(counter < log_cnt) {
				String str_line = object.readLine();
				if (str_line == null) {
					break;
				}
				
				res.add(str_line);
				counter++;
			}
			Collections.reverse(res);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return res.toString();
	}
	
	public ModelMap getContainerLogHistory(String container_id, String time ) throws IOException{
		ModelMap model = new ModelMap();
		List<String> res = new ArrayList<String>();
		String command = String.format("/var/lib/docker/containers/%s/%s-json.log", container_id, container_id);
		File file = new File(command);
		int counter = 0;
		
		try{
			ReversedLinesFileReader object = new ReversedLinesFileReader(file);
			while(counter < log_cnt) {
				String str_line = object.readLine();
				if (str_line == null) {
					break;
				}
				
				if (str_line.contains(time)) {
					break;
				}
				
				res.add(str_line);
				counter++;
			}
			Collections.reverse(res);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		model.put("result", "SUCCESS");
		model.put("log", res);

		return model;
	}
	
	public ModelMap getStatHistory(String container_id, String time ) {
		ModelMap model = new ModelMap();		
		History history = (History)ResourceLoader.getInstance().getResource("history_"+container_id);

		List<Resource> resources  = new ArrayList<Resource>();
		
		if( time == null ) {
			for( Resource  resource : history.getUseds() )
				resources.add(resource);
		}
		else {
			if (history != null) {
				for (Resource resource : history.getUseds()) {
					if (resource.getTimestamp() > Long.parseLong(time)) {
						resources.add(resource);
					}
				}
			}
		}
		
		model.put("result", "SUCCESS");
		model.put("list", resources);
		
		return model;
	}
	
	public String getContainerList(HttpServletRequest request) {
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		List<Container> result = new ArrayList<Container>();
		List<Container> containers = (ArrayList<Container>)ResourceLoader.getInstance().getResource("con_list");
		Iterator iterator = containers.iterator();
		
		if (request.getParameter("name").equals("") 
				&& request.getParameter("status").equals("")
				&& request.getParameter("searchTxt").equals("")) {
			
			while (iterator.hasNext()) {
				Container con = gson.fromJson((String)iterator.next(), Container.class);
				result.add(con);
			}
			
			return gson.toJson(result);
		}
        
        while (iterator.hasNext()) {
    		Container con = gson.fromJson((String)iterator.next(), Container.class);
    		
    		if(!request.getParameter("name").equals("") && con.getNames().toString().contains(request.getParameter("name"))) {
    			result.add(con);
			}
    		
    		if (con.getLabels().getMESOS_TASK_ID() != null) {
	    		if(!request.getParameter("name").equals("") && con.getLabels().getMESOS_TASK_ID().contains(request.getParameter("name"))) {
	    			result.add(con);
				}
    		}
    		
    		if(!request.getParameter("status").equals("") && con.getState().equals(request.getParameter("status"))) {
    			result.add(con);
			}
    		
    		else if(!request.getParameter("searchTxt").equals("") && con.getId().contains(request.getParameter("searchTxt"))) {
				result.add(con);
			}
    		
    		else if(!request.getParameter("searchTxt").equals("") && con.getNames().toString().contains(request.getParameter("searchTxt"))) {
				result.add(con);
			}
        }
        
        return gson.toJson(result);
	}
}