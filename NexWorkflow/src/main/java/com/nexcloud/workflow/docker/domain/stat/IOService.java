package com.nexcloud.workflow.docker.domain.stat;

public class IOService {
	private Integer major;
	private Integer minor;
	private String op;
	private Long value;
	 
	public Integer getMajor() {
		return major;
	}
	
	public void setMajor(Integer major) {
		this.major = major;
	}
	
	public Integer getMinor() {
		return minor;
	}
	
	public void setMinor(Integer minor) {
		this.minor = minor;
	}
	
	public String getOp() {
		return op;
	}
	
	public void setOp(String op) {
		this.op = op;
	}
	
	public Long getValue() {
		return value;
	}
	
	public void setValue(Long value) {
		this.value = value;
	}
}
