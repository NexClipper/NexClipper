package com.nexcloud.workflow.incident.domain;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.nexcloud.db.domain.Rule;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Assurance {
	private Map<String, Double> nodeCPU;
	
	private Map<String, String> taskIP;
	
	private Map<String, String> nodeIP;
	
	private List<Rule> rules;

	public Map<String, Double> getNodeCPU() {
		return nodeCPU;
	}

	public void setNodeCPU(Map<String, Double> nodeCPU) {
		this.nodeCPU = nodeCPU;
	}

	public Map<String, String> getTaskIP() {
		return taskIP;
	}

	public void setTaskIP(Map<String, String> taskIP) {
		this.taskIP = taskIP;
	}

	public Map<String, String> getNodeIP() {
		return nodeIP;
	}

	public void setNodeIP(Map<String, String> nodeIP) {
		this.nodeIP = nodeIP;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
}

