package com.nexcloud.workflow.mesos.domain.common;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * Task Resource 실사용량
 * @author Nelson Kim
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Statistics { 

	private Double cpus_limit				= 0d;
	private Double cpus_nr_periods			= 0d;
	private Double cpus_nr_throttled		= 0d;
	private Double cpus_system_time_secs	= 0d;
	private Double cpus_throttled_time_secs	= 0d;
	private Double cpus_user_time_secs		= 0d;
	
	private double cpu_total_usage 			= 0d;
	private double cpu_percent	   			= 0d;
	private double cpu_limit				= 0d;
	
	private Double disk_limit_bytes 		= 0d; // allocated disk
	private Double disk_used_bytes			= 0d; // used disk
	private Double mem_limit_bytes			= 0d; // allocated memory
	private Double mem_rss_bytes			= 0d; // used memory
	private List<DiskStatistics> disk_statistics;
	private Double timestamp				= 0d;
	
	public Double getCpus_limit() {
		return cpus_limit;
	}

	public void setCpus_limit(Double cpus_limit) {
		this.cpus_limit = cpus_limit;
	}

	public Double getCpus_nr_periods() {
		return cpus_nr_periods;
	}

	public void setCpus_nr_periods(Double cpus_nr_periods) {
		this.cpus_nr_periods = cpus_nr_periods;
	}

	public Double getCpus_nr_throttled() {
		return cpus_nr_throttled;
	}

	public void setCpus_nr_throttled(Double cpus_nr_throttled) {
		this.cpus_nr_throttled = cpus_nr_throttled;
	}

	public Double getCpus_system_time_secs() {
		return cpus_system_time_secs;
	}

	public void setCpus_system_time_secs(Double cpus_system_time_secs) {
		this.cpus_system_time_secs = cpus_system_time_secs;
	}

	public Double getCpus_throttled_time_secs() {
		return cpus_throttled_time_secs;
	}

	public void setCpus_throttled_time_secs(Double cpus_throttled_time_secs) {
		this.cpus_throttled_time_secs = cpus_throttled_time_secs;
	}

	public Double getCpus_user_time_secs() {
		return cpus_user_time_secs;
	}

	public void setCpus_user_time_secs(Double cpus_user_time_secs) {
		this.cpus_user_time_secs = cpus_user_time_secs;
	}

	public Double getDisk_limit_bytes() {
		return disk_limit_bytes;
	}

	public void setDisk_limit_bytes(Double disk_limit_bytes) {
		this.disk_limit_bytes = disk_limit_bytes;
	}

	public Double getDisk_used_bytes() {
		return disk_used_bytes;
	}

	public void setDisk_used_bytes(Double disk_used_bytes) {
		this.disk_used_bytes = disk_used_bytes;
	}

	public Double getMem_limit_bytes() {
		return mem_limit_bytes;
	}

	public void setMem_limit_bytes(Double mem_limit_bytes) {
		this.mem_limit_bytes = mem_limit_bytes;
	}

	public Double getMem_rss_bytes() {
		return mem_rss_bytes;
	}

	public void setMem_rss_bytes(Double mem_rss_bytes) {
		this.mem_rss_bytes = mem_rss_bytes;
	}

	public List<DiskStatistics> getDisk_statistics() {
		if( disk_statistics == null )
			disk_statistics = new LinkedList<DiskStatistics>();
		
		return disk_statistics;
	}

	public void setDisk_statistics(List<DiskStatistics> disk_statistics) {
		this.disk_statistics = disk_statistics;
	}

	public Double getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Double timestamp) {
		this.timestamp = timestamp;
	}

	public double getCpu_total_usage() {
		return cpu_total_usage;
	}

	public void setCpu_total_usage(double cpu_total_usage) {
		this.cpu_total_usage = cpu_total_usage;
	}

	public double getCpu_percent() {
		return cpu_percent;
	}

	public void setCpu_percent(double cpu_percent) {
		this.cpu_percent = cpu_percent;
	}

	public double getCpu_limit() {
		return cpu_limit;
	}

	public void setCpu_limit(double cpu_limit) {
		this.cpu_limit = cpu_limit;
	}
}
