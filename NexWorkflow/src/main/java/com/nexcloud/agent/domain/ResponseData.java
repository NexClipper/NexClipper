package com.nexcloud.agent.domain;

/**
 * Response Data
 *  header	- agent_key, metric_type(cluster, mesos_node, dcos)
 *  body	- metric data
 * @author Nelson Kim
 *
 */
public class ResponseData {

	private Header header;
	private String container_id;
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

	public String getContainer_id() {
		return container_id;
	}

	public void setContainer_id(String container_id) {
		this.container_id = container_id;
	}
}
