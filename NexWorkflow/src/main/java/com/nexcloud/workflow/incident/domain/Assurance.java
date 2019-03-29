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

