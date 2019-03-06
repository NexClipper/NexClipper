package com.nexcloud.workflow.mesos.domain.framework;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Frameworks {

	List<Framework> frameworks;

	public List<Framework> getFrameworks() {
		if(frameworks == null)
			frameworks = new ArrayList<Framework>();
		return frameworks;
	}

	public void setFrameworks(List<Framework> frameworks) {
		this.frameworks = frameworks;
	}
}