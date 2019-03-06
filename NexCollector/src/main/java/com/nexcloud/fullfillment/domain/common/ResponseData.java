package com.nexcloud.fullfillment.domain.common;

public class ResponseData {
	private Integer status;
	
	private String message;
	
	private String container_id;
	
	private String run;
	
	private String target;
	
	private String type;
	
	private String gaguage;
	
	private Header header;

	private String body;
	
	private Long timestamp;

	public Header getHeader() {
		if(header == null)
			header = new Header();
		
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContainer_id() {
		return container_id;
	}

	public void setContainer_id(String container_id) {
		this.container_id = container_id;
	}

	public String getRun() {
		return run;
	}

	public void setRun(String run) {
		this.run = run;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGaguage() {
		return gaguage;
	}

	public void setGaguage(String gaguage) {
		this.gaguage = gaguage;
	}
}

