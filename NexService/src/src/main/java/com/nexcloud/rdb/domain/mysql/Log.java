package com.nexcloud.rdb.domain.mysql;
/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
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
