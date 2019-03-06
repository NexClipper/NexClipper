package com.nexcloud.db.domain;

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
