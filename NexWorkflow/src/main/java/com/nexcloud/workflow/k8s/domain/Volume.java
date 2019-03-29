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
public class Volume {
	private String name;
	
	private String mountPath;
	
	private Boolean readOnly;
	
	private Path hostPath;
	
	private Path secret;
	
	private Configmap configMap;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Path getHostPath() {
		if( hostPath == null )
			hostPath = new Path();
		
		return hostPath;
	}

	public void setHostPath(Path hostPath) {
		this.hostPath = hostPath;
	}

	public Path getSecret() {
		if( secret == null )
			secret = new Path();
		return secret;
	}

	public void setSecret(Path secret) {
		this.secret = secret;
	}

	public String getMountPath() {
		return mountPath;
	}

	public void setMountPath(String mountPath) {
		this.mountPath = mountPath;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Configmap getConfigMap() {
		if( configMap == null )
			configMap = new Configmap();
		return configMap;
	}

	public void setConfigMap(Configmap configMap) {
		this.configMap = configMap;
	}
}
