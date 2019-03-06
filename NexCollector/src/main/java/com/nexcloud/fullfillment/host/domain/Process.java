package com.nexcloud.fullfillment.host.domain;

import java.util.Map;

public class Process {
	private Long pid;
	
	private String name;
	
	private Double cpu_usage;
	
	private Long cpu_sys;
	
	private Long cpu_total;
	
	private Long cpu_user;
	
	private Long mem_size;
	
	private Long mem_resident;
	
	private Long mem_rss;
	
	private Long mem_share;
	
	private Long mem_vsize;
	
	private Long majorFaults;
	
	private Long minorFaults;
	
	private Long pageFaults;
	
	private Map<String, Object>map;
	
	private String[] args;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCpu_usage() {
		return cpu_usage;
	}

	public void setCpu_usage(Double cpu_usage) {
		this.cpu_usage = cpu_usage;
	}

	public Long getCpu_sys() {
		return cpu_sys;
	}

	public void setCpu_sys(Long cpu_sys) {
		this.cpu_sys = cpu_sys;
	}

	public Long getCpu_total() {
		return cpu_total;
	}

	public void setCpu_total(Long cpu_total) {
		this.cpu_total = cpu_total;
	}

	public Long getCpu_user() {
		return cpu_user;
	}

	public void setCpu_user(Long cpu_user) {
		this.cpu_user = cpu_user;
	}

	public Long getMem_size() {
		return mem_size;
	}

	public void setMem_size(Long mem_size) {
		this.mem_size = mem_size;
	}

	public Long getMem_resident() {
		return mem_resident;
	}

	public void setMem_resident(Long mem_resident) {
		this.mem_resident = mem_resident;
	}

	public Long getMem_rss() {
		return mem_rss;
	}

	public void setMem_rss(Long mem_rss) {
		this.mem_rss = mem_rss;
	}

	public Long getMem_share() {
		return mem_share;
	}

	public void setMem_share(Long mem_share) {
		this.mem_share = mem_share;
	}

	public Long getMem_vsize() {
		return mem_vsize;
	}

	public void setMem_vsize(Long mem_vsize) {
		this.mem_vsize = mem_vsize;
	}

	public Long getMajorFaults() {
		return majorFaults;
	}

	public void setMajorFaults(Long majorFaults) {
		this.majorFaults = majorFaults;
	}

	public Long getMinorFaults() {
		return minorFaults;
	}

	public void setMinorFaults(Long minorFaults) {
		this.minorFaults = minorFaults;
	}

	public Long getPageFaults() {
		return pageFaults;
	}

	public void setPageFaults(Long pageFaults) {
		this.pageFaults = pageFaults;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	@Override
	public String toString() {
		return "Process [name=" + name + ", cpu_usage=" + cpu_usage + ", cpu_sys=" + cpu_sys + ", cpu_total="
				+ cpu_total + ", cpu_user=" + cpu_user + ", mem_size=" + mem_size + ", mem_resident=" + mem_resident
				+ ", mem_rss=" + mem_rss + ", mem_share=" + mem_share + ", mem_vsize=" + mem_vsize + ", majorFaults="
				+ majorFaults + ", minorFaults=" + minorFaults + ", pageFaults=" + pageFaults + ", map=" + map + "]";
	}
}

