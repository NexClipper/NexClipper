package com.nexcloud.workflow.k8s.domain;

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
