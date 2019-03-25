package com.nexcloud.fullfillment.docker.domain.stat;
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

import java.util.List;

public class CpuUsage {
	private Long total_usage;
	private List<Long> percpu_usage;
	private Long usage_in_kernelmode;
	private Long usage_in_usermode;
	
	public Long getTotal_usage() {
		return total_usage;
	}
	
	public void setTotal_usage(Long total_usage) {
		this.total_usage = total_usage;
	}
	
	public List<Long> getPercpu_usage() {
		return percpu_usage;
	}
	
	public void setPercpu_usage(List<Long> percpu_usage) {
		this.percpu_usage = percpu_usage;
	}
	
	public Long getUsage_in_kernelmode() {
		return usage_in_kernelmode;
	}
	
	public void setUsage_in_kernelmode(Long usage_in_kernelmode) {
		this.usage_in_kernelmode = usage_in_kernelmode;
	}
	
	public Long getUsage_in_usermode() {
		return usage_in_usermode;
	}
	
	public void setUsage_in_usermode(Long usage_in_usermode) {
		this.usage_in_usermode = usage_in_usermode;
	}
}
