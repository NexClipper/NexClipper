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
package com.nexcloud.rdb.domain.mysql;
public class IncidentCount{
	private String targetSystem;
	private String targetIp;
	private String target;
	private Long countValue;
	public String getTargetSystem() {
		return targetSystem;
	}
	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}
	public String getTargetIp() {
		return targetIp;
	}
	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Long getCountValue() {
		return countValue;
	}
	public void setCountValue(Long countValue) {
		this.countValue = countValue;
	}
	public IncidentCount(String targetSystem, String targetIp, String target, Long countValue) {
		super();
		this.targetSystem = targetSystem;
		this.targetIp = targetIp;
		this.target = target;
		this.countValue = countValue;
	}
}