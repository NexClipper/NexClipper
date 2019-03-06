package com.nexcloud.workflow.mesos.domain.common;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiskStatistics {

	private Double limit_bytes = 0d;
	
	private Double used_bytes = 0d;

	public Double getLimit_bytes() {
		return limit_bytes;
	}

	public void setLimit_bytes(Double limit_bytes) {
		this.limit_bytes = limit_bytes;
	}

	public Double getUsed_bytes() {
		return used_bytes;
	}

	public void setUsed_bytes(Double used_bytes) {
		this.used_bytes = used_bytes;
	}
}

