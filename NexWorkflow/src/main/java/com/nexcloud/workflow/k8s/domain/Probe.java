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
package com.nexcloud.workflow.k8s.domain;
public class Probe {
	private Probe httpGet;
	
	private String path;
	
	private Object port;
	
	private String scheme;
	
	private Integer initialDelaySeconds;
	
	private Integer timeoutSeconds;
	
	private Integer periodSeconds;
	
	private Integer successThreshold;
	
	private Integer failureThreshold;

	public Probe getHttpGet() {
		if( httpGet == null )
			httpGet = new Probe();
		return httpGet;
	}

	public void setHttpGet(Probe httpGet) {
		this.httpGet = httpGet;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Object getPort() {
		return port;
	}

	public void setPort(Object port) {
		this.port = port;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public Integer getInitialDelaySeconds() {
		return initialDelaySeconds;
	}

	public void setInitialDelaySeconds(Integer initialDelaySeconds) {
		this.initialDelaySeconds = initialDelaySeconds;
	}

	public Integer getTimeoutSeconds() {
		return timeoutSeconds;
	}

	public void setTimeoutSeconds(Integer timeoutSeconds) {
		this.timeoutSeconds = timeoutSeconds;
	}

	public Integer getPeriodSeconds() {
		return periodSeconds;
	}

	public void setPeriodSeconds(Integer periodSeconds) {
		this.periodSeconds = periodSeconds;
	}

	public Integer getSuccessThreshold() {
		return successThreshold;
	}

	public void setSuccessThreshold(Integer successThreshold) {
		this.successThreshold = successThreshold;
	}

	public Integer getFailureThreshold() {
		return failureThreshold;
	}

	public void setFailureThreshold(Integer failureThreshold) {
		this.failureThreshold = failureThreshold;
	}
}
