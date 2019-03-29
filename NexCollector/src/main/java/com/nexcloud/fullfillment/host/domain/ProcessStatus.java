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
package com.nexcloud.fullfillment.host.domain;
public class ProcessStatus {
	private Integer total;
	
	private Integer sleeping;
	
	private Integer running;
	
	private Integer stopped;
	
	private Integer zombie;
	
	private Integer idle;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getSleeping() {
		return sleeping;
	}

	public void setSleeping(Integer sleeping) {
		this.sleeping = sleeping;
	}

	public Integer getRunning() {
		return running;
	}

	public void setRunning(Integer running) {
		this.running = running;
	}

	public Integer getStopped() {
		return stopped;
	}

	public void setStopped(Integer stopped) {
		this.stopped = stopped;
	}

	public Integer getZombie() {
		return zombie;
	}

	public void setZombie(Integer zombie) {
		this.zombie = zombie;
	}

	public Integer getIdle() {
		return idle;
	}

	public void setIdle(Integer idle) {
		this.idle = idle;
	}
}

