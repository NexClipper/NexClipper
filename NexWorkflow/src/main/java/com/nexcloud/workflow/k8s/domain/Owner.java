package com.nexcloud.workflow.k8s.domain;

public class Owner {
	private String apiVersion;
	
	private String kind;
	
	private String name;
	
	private String uid;
	
	private Boolean controller;
	
	private Boolean blockOwnerDeletion;

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Boolean getController() {
		return controller;
	}

	public void setController(Boolean controller) {
		this.controller = controller;
	}

	public Boolean getBlockOwnerDeletion() {
		return blockOwnerDeletion;
	}

	public void setBlockOwnerDeletion(Boolean blockOwnerDeletion) {
		this.blockOwnerDeletion = blockOwnerDeletion;
	}
}
