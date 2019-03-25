package com.nexcloud.db.domain;
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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Agent {
	private Integer agent_id;
	
	private String agent_key;
	
	private Long agent_expire_time;
	
	private Integer group_number;

	public Integer getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}

	public String getAgent_key() {
		return agent_key;
	}

	public void setAgent_key(String agent_key) {
		this.agent_key = agent_key;
	}

	public Long getAgent_expire_time() {
		return agent_expire_time;
	}

	public void setAgent_expire_time(Long agent_expire_time) {
		this.agent_expire_time = agent_expire_time;
	}

	public Integer getGroup_number() {
		return group_number;
	}

	public void setGroup_number(Integer group_number) {
		this.group_number = group_number;
	}
}
