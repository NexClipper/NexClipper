package com.nexcloud.workflow.docker.domain.container;

import java.util.List;

public class Config {
	private List<String> Env;
	
	public List<String> getEnv() {
		return Env;
	}

	public void setEnv(List<String> env) {
		Env = env;
	}
}