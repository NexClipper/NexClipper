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
package com.nexcloud.fullfillment.docker.domain.stat;

public class IOService {
	private Integer major;
	private Integer minor;
	private String op;
	private Long value;
	 
	public Integer getMajor() {
		return major;
	}
	
	public void setMajor(Integer major) {
		this.major = major;
	}
	
	public Integer getMinor() {
		return minor;
	}
	
	public void setMinor(Integer minor) {
		this.minor = minor;
	}
	
	public String getOp() {
		return op;
	}
	
	public void setOp(String op) {
		this.op = op;
	}
	
	public Long getValue() {
		return value;
	}
	
	public void setValue(Long value) {
		this.value = value;
	}
}
