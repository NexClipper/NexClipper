package com.nexcloud.rdb.domain.mysql;

import java.sql.Timestamp;

public class Log {
	private int idx;
	private String hostIp;
	private String containerId;
	private String stream;
	private String log;
	private String time;
	private Timestamp regdt;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Timestamp getRegdt() {
		return regdt;
	}
	public void setRegdt(Timestamp regdt) {
		this.regdt = regdt;
	}
	public Log(int idx, String hostIp, String containerId, String stream, String log, String time, Timestamp regdt) {
		super();
		this.idx = idx;
		this.hostIp = hostIp;
		this.containerId = containerId;
		this.stream = stream;
		this.log = log;
		this.time = time;
		this.regdt = regdt;
	}
}
