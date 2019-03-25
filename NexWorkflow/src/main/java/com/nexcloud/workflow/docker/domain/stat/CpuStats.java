package com.nexcloud.workflow.docker.domain.stat;
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

public class CpuStats {
	private CpuUsage cpu_usage;
	private Long system_cpu_usage;
	
	public CpuUsage getCpu_usage() {
		return cpu_usage;
	}
	
	public void setCpu_usage(CpuUsage cpu_usage) {
		this.cpu_usage = cpu_usage;
	}
	
	public Long getSystem_cpu_usage() {
		return system_cpu_usage;
	}
	
	public void setSystem_cpu_usage(Long system_cpu_usage) {
		this.system_cpu_usage = system_cpu_usage;
	}

}
