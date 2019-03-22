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

public class Command {
	private int idx;
	private String type;
	private String hostIp;
	private String target;
	private String filename;
	private String gaguage;
	private String containerId;
	private String run;
	private String read;
	private Timestamp regdt;
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getGaguage() {
		return gaguage;
	}

	public void setGaguage(String gaguage) {
		this.gaguage = gaguage;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getRun() {
		return run;
	}

	public void setRun(String run) {
		this.run = run;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public Timestamp getRegdt() {
		return regdt;
	}

	public void setRegdt(Timestamp regdt) {
		this.regdt = regdt;
	}

	public Command(int idx, String type, String hostIp, String target, String filename, String gaguage,
			String containerId, String run, String read, Timestamp regdt) {
		super();
		this.idx = idx;
		this.type = type;
		this.hostIp = hostIp;
		this.target = target;
		this.filename = filename;
		this.gaguage = gaguage;
		this.containerId = containerId;
		this.run = run;
		this.read = read;
		this.regdt = regdt;
	}
}
