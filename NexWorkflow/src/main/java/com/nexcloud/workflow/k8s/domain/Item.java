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
	
	private Metadata involvedObject;
	
	private String reason;
	
	private String message;
	
	private Source source;
	
	private String firstTimestamp;
	
	private String lastTimestamp;
	
	private Integer cout;
	
	private String eventTime;
	
	private String reportingComponent;
	
	private String reportingInstance;

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
	
	public Metadata getInvolvedObject() {
		if( involvedObject == null )
			involvedObject = new Metadata();
		return involvedObject;
	}

	public void setInvolvedObject(Metadata involvedObject) {
		this.involvedObject = involvedObject;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Source getSource() {
		if( source == null )
			source = new Source();
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getFirstTimestamp() {
		return firstTimestamp;
	}

	public void setFirstTimestamp(String firstTimestamp) {
		this.firstTimestamp = firstTimestamp;
	}

	public String getLastTimestamp() {
		return lastTimestamp;
	}

	public void setLastTimestamp(String lastTimestamp) {
		this.lastTimestamp = lastTimestamp;
	}

	public Integer getCout() {
		return cout;
	}

	public void setCout(Integer cout) {
		this.cout = cout;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getReportingComponent() {
		return reportingComponent;
	}

	public void setReportingComponent(String reportingComponent) {
		this.reportingComponent = reportingComponent;
	}

	public String getReportingInstance() {
		return reportingInstance;
	}

	public void setReportingInstance(String reportingInstance) {
		this.reportingInstance = reportingInstance;
	}
}
