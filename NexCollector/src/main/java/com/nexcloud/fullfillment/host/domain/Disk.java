package com.nexcloud.fullfillment.host.domain;

import java.util.ArrayList;
import java.util.List;

public class Disk {
	private String devName;
	
	private String mountName;
	
	private Long free;
	
	private Long used;
	
	private Long total;
	
	private Double used_per;
	
	private Long readBytes;
	
	private Long writeBytes;
	
	private Long reads;
	
	private Long writes;
	
	private String options;
	
	private String sysType;

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getMountName() {
		return mountName;
	}

	public void setMountName(String mountName) {
		this.mountName = mountName;
	}

	public Long getFree() {
		return free;
	}

	public void setFree(Long free) {
		this.free = free;
	}

	public Long getUsed() {
		return used;
	}

	public void setUsed(Long used) {
		this.used = used;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Double getUsed_per() {
		return used_per;
	}

	public void setUsed_per(Double used_per) {
		this.used_per = used_per;
	}

	public Long getReadBytes() {
		return readBytes;
	}

	public void setReadBytes(Long readBytes) {
		this.readBytes = readBytes;
	}

	public Long getWriteBytes() {
		return writeBytes;
	}

	public void setWriteBytes(Long writeBytes) {
		this.writeBytes = writeBytes;
	}

	public Long getReads() {
		return reads;
	}

	public void setReads(Long reads) {
		this.reads = reads;
	}

	public Long getWrites() {
		return writes;
	}

	public void setWrites(Long writes) {
		this.writes = writes;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getSysType() {
		return sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}
}

