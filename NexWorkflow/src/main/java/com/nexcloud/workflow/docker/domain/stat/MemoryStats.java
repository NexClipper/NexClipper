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

public class MemoryStats {
	private Long usage;
	private Long max_usage;
	private Long limit;
	private Float mem_usage;

	public Long getUsage() {
		return usage;
	}

	public void setUsage(Long usage) {
		this.usage = usage;
	}

	public Long getMax_usage() {
		return max_usage;
	}

	public void setMax_usage(Long max_usage) {
		this.max_usage = max_usage;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}
	
	public Float getMem_usage() {
		return (float)this.usage/this.limit*100;
	}

	public void setMem_usage(Float mem_usage) {
		this.mem_usage = mem_usage;
	}
}
