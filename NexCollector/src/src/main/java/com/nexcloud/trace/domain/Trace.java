package com.nexcloud.trace.domain;
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

public class Trace {
	private Integer agent_id;
	private long 	uuid;
	private String 	service_id;
	private String  src_ip;
	private String  dest_ip;
	private String 	method;
	private String 	uri;
	private String 	status_code;
	private String  status_group;
	private long 	res_time;
	private String 	req_params;
	private String 	req_body;
	private String 	res_data;
	private Integer status_2XX;
	private Integer status_3XX;
	private Integer status_4XX;
	private Integer status_5XX;
	
	public Integer getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}

	public long getUuid() {
		return uuid;
	}

	public void setUuid(long uuid) {
		this.uuid = uuid;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getSrc_ip() {
		return src_ip;
	}

	public void setSrc_ip(String src_ip) {
		this.src_ip = src_ip;
	}

	public String getDest_ip() {
		return dest_ip;
	}

	public void setDest_ip(String dest_ip) {
		this.dest_ip = dest_ip;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public long getRes_time() {
		return res_time;
	}

	public void setRes_time(long res_time) {
		this.res_time = res_time;
	}


	public String getReq_params() {
		return req_params;
	}

	public void setReq_params(String req_params) {
		this.req_params = req_params;
	}

	public String getReq_body() {
		return req_body;
	}

	public void setReq_body(String req_body) {
		this.req_body = req_body;
	}

	public String getRes_data() {
		return res_data;
	}

	public void setRes_data(String res_data) {
		this.res_data = res_data;
	}

	public Integer getStatus_2XX() {
		return status_2XX;
	}

	public void setStatus_2XX(Integer status_2xx) {
		status_2XX = status_2xx;
	}

	public Integer getStatus_3XX() {
		return status_3XX;
	}

	public void setStatus_3XX(Integer status_3xx) {
		status_3XX = status_3xx;
	}

	public Integer getStatus_4XX() {
		return status_4XX;
	}

	public void setStatus_4XX(Integer status_4xx) {
		status_4XX = status_4xx;
	}

	public Integer getStatus_5XX() {
		return status_5XX;
	}

	public void setStatus_5XX(Integer status_5xx) {
		status_5XX = status_5xx;
	}

	public String getStatus_group() {
		return status_group;
	}

	public void setStatus_group(String status_group) {
		this.status_group = status_group;
	}
}
