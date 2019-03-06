package com.nexcloud.workflow.k8s.domain;

import java.util.ArrayList;
import java.util.List;

public class Item {
	private Metadata metadata;
	
	private List<Condition> conditions;
	
	private Condition condition;
	
	private List<Subset> subsets;
	
	private Subset subset;
	
	private Status status;
	
	private Spec spec;
	
	private String type;
	
	private Resource resource;

	public Metadata getMetadata() {
		if( metadata == null )
			metadata = new Metadata();
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<Condition> getConditions() {
		if( conditions == null )
			conditions = new ArrayList<Condition>();
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public Condition getCondition() {
		if( condition == null )
			condition = new Condition();
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public List<Subset> getSubsets() {
		if( subsets == null )
			subsets = new ArrayList<Subset>();
		return subsets;
	}

	public void setSubsets(List<Subset> subsets) {
		this.subsets = subsets;
	}

	public Subset getSubset() {
		if( subset == null )
			subset = new Subset();
		return subset;
	}

	public void setSubset(Subset subset) {
		this.subset = subset;
	}

	public Status getStatus() {
		if( status == null )
			status = new Status();
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Spec getSpec() {
		if( spec == null )
			spec = new Spec();
		return spec;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Resource getResource() {
		if( resource == null )
			resource = new Resource();
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
