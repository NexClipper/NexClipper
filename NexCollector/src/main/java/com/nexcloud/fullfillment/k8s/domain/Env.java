package com.nexcloud.fullfillment.k8s.domain;

public class Env {
	private String name;
	
	private String value;
	
	private Value valueFrom;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Value getValueFrom() {
		if( valueFrom == null )
			valueFrom = new Value();
		return valueFrom;
	}

	public void setValueFrom(Value valueFrom) {
		this.valueFrom = valueFrom;
	}
}
