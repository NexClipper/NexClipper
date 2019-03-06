package com.nexcloud.workflow.mesos.domain.common;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Resources {

	private Double disk = 0d;
	
	private Double per_disk = 0d;
	
	private Double mem = 0d;
	
	private Double per_mem = 0d;
	
	private Double gpus = 0d;
	
	private Double per_gpus = 0d;
	
	private Double cpus = 0d;
	
	private Double per_cpus = 0d;
	
	private Double max_value = 0d;

	public Double getDisk() {
		return disk;
	}

	public void setDisk(Double disk) {
		this.disk = disk;
	}

	public Double getPer_disk() {
		return per_disk;
	}

	public void setPer_disk(Double per_disk) {
		this.per_disk = per_disk;
	}

	public Double getMem() {
		return mem;
	}

	public void setMem(Double mem) {
		this.mem = mem;
	}

	public Double getPer_mem() {
		return per_mem;
	}

	public void setPer_mem(Double per_mem) {
		this.per_mem = per_mem;
	}

	public Double getGpus() {
		return gpus;
	}

	public void setGpus(Double gpus) {
		this.gpus = gpus;
	}

	public Double getPer_gpus() {
		return per_gpus;
	}

	public void setPer_gpus(Double per_gpus) {
		this.per_gpus = per_gpus;
	}

	public Double getCpus() {
		return cpus;
	}

	public void setCpus(Double cpus) {
		this.cpus = cpus;
	}

	public Double getPer_cpus() {
		return per_cpus;
	}

	public void setPer_cpus(Double per_cpus) {
		this.per_cpus = per_cpus;
	}

	public Double getMax_value() {
		return max_value;
	}

	public void setMax_value(Double max_value) {
		this.max_value = max_value;
	}
}
