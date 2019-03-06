package com.nexcloud.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;
import com.nexcloud.scheduler.host.status.HostStatus;

@Component
@EnableScheduling
public class Scheduler {
	
	@Autowired 
	private HostStatus hostStatus;
	
	@Bean
	public TaskScheduler taskScheduler() {
	    return new ConcurrentTaskScheduler();
	}

	@Scheduled(fixedDelay = 30000)
	public void statusCheck() { 
		hostStatus.statusCheck();
	} 
}
