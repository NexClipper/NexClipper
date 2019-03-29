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
package com.nexcloud.fullfillment.k8s.domain;
import com.google.gson.annotations.SerializedName;

public class Selector {
	@SerializedName("k8s-app")
	private String k8s_app;
	
	@SerializedName("pod-template-hash")
	private String pod_template_hash;
	
	private String version;
	
	private Selector matchLabels;
	
	private String alertmanager;
	
	private String app;

	public String getPod_template_hash() {
		return pod_template_hash;
	}

	public void setPod_template_hash(String pod_template_hash) {
		this.pod_template_hash = pod_template_hash;
	}

	public String getAlertmanager() {
		return alertmanager;
	}

	public void setAlertmanager(String alertmanager) {
		this.alertmanager = alertmanager;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getK8s_app() {
		return k8s_app;
	}

	public void setK8s_app(String k8s_app) {
		this.k8s_app = k8s_app;
	}

	public Selector getMatchLabels() {
		if( matchLabels == null )
			matchLabels = new Selector();
		
		return matchLabels;
	}

	public void setMatchLabels(Selector matchLabels) {
		this.matchLabels = matchLabels;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
