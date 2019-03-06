package com.nexcloud.docker.collector.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nexcloud.docker.container.list.domain.Container;
import com.nexcloud.docker.container.stats.domain.ContainerStat;
import com.nexcloud.docker.container.stats.domain.History;
import com.nexcloud.docker.container.stats.domain.IOService;
import com.nexcloud.docker.container.stats.domain.NetworkStats;
import com.nexcloud.docker.container.stats.domain.Resource;
import com.nexcloud.docker.resource.ResourceLoader;
import com.nexcloud.docker.util.Util;

public class ContainerListThreadService implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(ContainerListThreadService.class);
	private Container con;

	public static int thread_cnt;
	public static List<String> con_list = new ArrayList<String>();
	public static final int CHART_COUNT = 360;
	
	public ContainerListThreadService(Container con) {
		this.con = con;
	}

	public synchronized static int getThread_cnt() {
		return thread_cnt;
	}
	
	public synchronized static void setThread_cnt() {
		thread_cnt = getThread_cnt() + 1;
	}
	
	public synchronized static void setThread_cnt(int thread_cnt) {
		ContainerListThreadService.thread_cnt = thread_cnt;
	}

	public synchronized static List<String> getCon_list() {
		return con_list;
	}

	public synchronized static void setCon_list(List<String> con_list) {
		ContainerListThreadService.con_list = con_list;
	}
	
	public synchronized static void addCon_list(Container con) {
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		con_list.add(gson.toJson(con));
	}
	
	@Override
	public synchronized void run() {
		if (con.getState().equals("running")) {
			ContainerStat conStat = getContainerStat(Util.URI_CONTAINER_STAT, con.getId());
			con.setCpuPercent(calculateCPUPercent(conStat));
			con.setMemPercent(calculateMEMPercent(conStat));
			con.setUsed_mem(conStat.getMemory_stats().getUsage());
			con.setLimit_mem(conStat.getMemory_stats().getLimit());
			con.setBlock_io_read(getBlockIORead(conStat));
			con.setBlock_io_write(getBlockIOWrite(conStat));
					
			History history	= (History)ResourceLoader.getInstance().getResource("history_"+con.getId());
			if( history == null )
				history = new History();
			
			List<Resource> resources		= history.getUseds();
			//logger.info("###### "+con.getId()+"####Resource Count["+resources.size()+"]");
			
			if( resources.size() >= CHART_COUNT )
				resources.remove(0);
			
			Resource	resource			= new Resource();
			
			// Set resource stat
			resource.setPer_cpu(con.getCpuPercent());
			resource.setPer_mem(con.getMemPercent());
			resource.setUsed_mem(con.getUsed_mem());
			resource.setLimit_mem(con.getLimit_mem());
			resource.setTimestamp(new Date().getTime());
			resource.setBlock_io_read(con.getBlock_io_read());
			resource.setBlock_io_write(con.getBlock_io_write());
		
			if (conStat.getNetworks() != null) {
				for( String key : conStat.getNetworks().keySet() ){
					NetworkStats net	= conStat.getNetworks().get(key);
					
					resource.setInterface_name(key);
					
					if( resources.size() > 0 ) {
						Resource rs	= (Resource)ResourceLoader.getInstance().getResource("network_"+con.getId());
						
						resource.setRx_bytes(net.getRx_bytes() - rs.getRx_bytes());
						resource.setRx_packets(net.getRx_packets() - rs.getRx_packets());
						resource.setRx_errors(net.getRx_errors() - rs.getRx_errors());
						resource.setRx_dropped(net.getRx_dropped() - rs.getRx_dropped());
						
						resource.setTx_bytes(net.getTx_bytes() - rs.getTx_bytes());
						resource.setTx_packets(net.getTx_packets() - rs.getTx_packets());
						resource.setTx_errors(net.getRx_errors() - rs.getTx_errors());
						resource.setTx_dropped(net.getTx_dropped() - rs.getRx_dropped());
					}
					
					// Stores previous network statistics data in memory
					Resource	netResource		= new Resource();
					
					netResource.setRx_bytes(net.getRx_bytes());
					netResource.setRx_packets(net.getRx_packets());
					netResource.setRx_errors(net.getRx_errors());
					netResource.setRx_dropped(net.getRx_dropped());
					
					netResource.setTx_bytes(net.getTx_bytes());
					netResource.setTx_packets(net.getTx_packets());
					netResource.setTx_errors(net.getRx_errors());
					netResource.setTx_dropped(net.getTx_dropped());
					
					ResourceLoader.getInstance().setResource("network_"+con.getId(), netResource);
		        }
			}
	
			resources.add(resource);
			ResourceLoader.getInstance().setResource("history_"+con.getId(), history);
		}
		
		setThread_cnt();
		addCon_list(con);
	}

	public ContainerStat getContainerStat(String api_name, String id) {
		String uri = String.format(Util.URI_CONTAINER_STAT, id);
		String result = Util.procDockerApi(uri);

		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		ContainerStat con = gson.fromJson(result, ContainerStat.class);
		return con;
	}

	/*
	 * Refer: https://stackoverflow.com/questions/30271942/get-docker-container-cpu-usage-as-percentage
	 */
	public float calculateCPUPercent(ContainerStat con) {
		float cpuPercent = 0.0f;
		long cpuDelta = con.cpu_stats.cpu_usage.total_usage - con.precpu_stats.cpu_usage.total_usage;
		long systemDelta = con.cpu_stats.system_cpu_usage - con.precpu_stats.system_cpu_usage;
		int cpuCount = getCpuCount(con);
		
		cpuPercent = (float) cpuDelta / (float) systemDelta * cpuCount * 100;
		return Float.parseFloat(String.format("%.2f", cpuPercent));
	}

	public int getCpuCount(ContainerStat con) {
		int i=0, count=0;
		
		for(i=0; i<con.cpu_stats.cpu_usage.percpu_usage.size(); i++) {
			if (con.cpu_stats.cpu_usage.percpu_usage.get(i) > 0) {
				count += 1;
			}
		}
		return count;
	}
	
	public float calculateMEMPercent(ContainerStat con) {
		float memPercent = 0.0f;
		memPercent = (float) con.getMemory_stats().usage / (float) con.getMemory_stats().limit * 100;		
		return Float.parseFloat(String.format("%.2f", memPercent));
	}
	
	public long getBlockIORead(ContainerStat con) {
		long block_io_read=0;
		
		for (IOService io: con.getBlkio_stats().getIo_service_bytes_recursive()) {
			if(io.getOp().equals("Read")) {
				block_io_read += io.getValue();
			}
		}
		return block_io_read;
	}
	
	public long getBlockIOWrite(ContainerStat con) {
		long block_io_write=0;
		
		for (IOService io: con.getBlkio_stats().getIo_service_bytes_recursive()) {
			if(io.getOp().equals("Write")) {
				block_io_write += io.getValue();
			}
		}
		return block_io_write;
	}
}