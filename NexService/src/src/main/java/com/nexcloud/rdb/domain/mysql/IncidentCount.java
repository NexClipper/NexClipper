package com.nexcloud.rdb.domain.mysql;

public class IncidentCount{
	private String targetSystem;
	private String targetIp;
	private String target;
	private Long countValue;
	public String getTargetSystem() {
		return targetSystem;
	}
	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}
	public String getTargetIp() {
		return targetIp;
	}
	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Long getCountValue() {
		return countValue;
	}
	public void setCountValue(Long countValue) {
		this.countValue = countValue;
	}
	public IncidentCount(String targetSystem, String targetIp, String target, Long countValue) {
		super();
		this.targetSystem = targetSystem;
		this.targetIp = targetIp;
		this.target = target;
		this.countValue = countValue;
	}
}