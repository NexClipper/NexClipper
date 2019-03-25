package com.nexcloud.fullfillment.k8s.domain;
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
