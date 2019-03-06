package com.nexcloud.db.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
	private Integer idx;
	
	// 대상 Type
	// host, node, task, service, framework...
	private String type;
	
	// Node  or host ip 
	private String ip;
	
	// task 정보
	private String id;
	
	// 장애 등급
	// critical, warning
	private String grade;
	
	// 장애내용
	private String contents;
	
	// 내용
	private String memo;
	
	private String check_yn;
	
	private String regdt;

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCheck_yn() {
		return check_yn;
	}

	public void setCheck_yn(String check_yn) {
		this.check_yn = check_yn;
	}

	public String getRegdt() {
		return regdt;
	}

	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
}
