package com.nexcloud.docker.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexcloud.docker.collector.service.ContainerListThreadService;
import com.nexcloud.docker.container.list.domain.Container;
import com.nexcloud.docker.resource.ResourceLoader;
import com.nexcloud.docker.util.Util;

public class ContainerListThread extends TimerTask {
	private static final Logger logger = LoggerFactory.getLogger(ContainerListThread.class);
	
	public ContainerListThread() {
		super();
	}
	
	@Override
	public void run() {		
		int container_cnt=0, sleep_cnt=0;
		String result = Util.procDockerApi(Util.uri_container_list);
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		ArrayList<Container> containers  = gson.fromJson(result, new TypeToken<ArrayList<Container>>(){}.getType());
		
		if (containers == null) {
			return;
		}
		else {
			container_cnt = containers.size();
		}
		
		Runnable[] runnable = new Runnable[container_cnt];
		Thread[] threads = new Thread[container_cnt];
		
		for(int i=0; i<container_cnt; i++) {
			runnable[i] = new ContainerListThreadService(containers.get(i));
			threads[i] = new Thread(runnable[i]);
			threads[i].start();
		}
		
		while(true) {
			try {
				sleep_cnt += 1;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(container_cnt <= ContainerListThreadService.getThread_cnt()) {
				//logger.info("---------->> Thread end....");
				List<String> data = ContainerListThreadService.con_list;
				ResourceLoader.getInstance().removeResource("con_list");
				ResourceLoader.getInstance().setResource("con_list", data);
								
				ContainerListThreadService.setThread_cnt(0);
				ContainerListThreadService.con_list= new ArrayList<String>();
				break;
			}

			// Terminate thread after 7 seconds
			if (sleep_cnt >= 7) {
				//logger.info("---------->> container_cnt: " +container_cnt + ", " + "thr_con_count: " +ContainerListThreadService.getThread_cnt());
				List<String> data = ContainerListThreadService.con_list;
				ResourceLoader.getInstance().removeResource("con_list");
				ResourceLoader.getInstance().setResource("con_list", data);
								
				ContainerListThreadService.setThread_cnt(0);
				ContainerListThreadService.con_list= new ArrayList<String>();
				break;
			}
		}
	}
}