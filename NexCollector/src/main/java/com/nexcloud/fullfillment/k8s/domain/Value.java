package com.nexcloud.fullfillment.k8s.domain;

public class Value {
	private Value fieldRef;
	
	private String apiVersion;
	
	private String fieldPath;

	public Value getFieldRef() {
		if( fieldRef == null )
			fieldRef = new Value();
		return fieldRef;
	}

	public void setFieldRef(Value fieldRef) {
		this.fieldRef = fieldRef;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getFieldPath() {
		return fieldPath;
	}

	public void setFieldPath(String fieldPath) {
		this.fieldPath = fieldPath;
	}
}
