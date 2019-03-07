package com.nexcloud.docker.collector;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexcloud.docker.collector.service.ContainerService;

public class Container extends TimerTask {
	private static final Logger logger = LoggerFactory.getLogger(Container.class);
	private ContainerService containerService;
	
	public Container() {
		super();
		this.containerService = new ContainerService();
	}
	
	@Override
	public void run() {
		logger.info("###### Container Start");
		containerService.getContainerSystemInfo();
		
		try {
			TimeUnit.MICROSECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}