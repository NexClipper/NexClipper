package com.nexcloud.docker;

import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nexcloud.docker.collector.Container;
import com.nexcloud.docker.collector.ContainerListThread;

@SpringBootApplication
public class ContainerApplication {
	
	public static void startMonitorThread() {
		// Container main information
		Timer containInfoTimer		= new Timer(false);
		Container container 		= new Container();
		
		// Container List & detail information
		Timer containerListTimer 	= new Timer(false);
		ContainerListThread containerList = new ContainerListThread();

		// Run every 10 seconds
		containInfoTimer.scheduleAtFixedRate(container, 0, 1000*10);
		containerListTimer.scheduleAtFixedRate(containerList, 0, 1000*10);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ContainerApplication.class, args);
		startMonitorThread();
	}
}