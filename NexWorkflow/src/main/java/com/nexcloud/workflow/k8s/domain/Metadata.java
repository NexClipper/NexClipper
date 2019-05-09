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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metadata {
	private String name;
	
	private String selfLink;
	
	private String creationTimestamp;
	
	private String resourceVersion;
	
	private String uid;
	
	private String namespace;
	
	private String kind;
	
	private String generateName;
	
	private List<Owner> ownerReferences;
	
	private Map<String, String> labels;
	
	private Map<String, String> annotations;
	
	private Integer generation;
	
	private String node_name;
	
	private String node_ip;
	
	private String apiVersion;
	
	private String fieldPath;

	public Map<String, String> getAnnotations() {
		if( annotations == null )
			annotations = new HashMap<String, String>();
		return annotations;
	}

	public void setAnnotations(Map<String, String> annotations) {
		this.annotations = annotations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelfLink() {
		return selfLink;
	}

	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}

	public String getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(String creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public String getResourceVersion() {
		return resourceVersion;
	}

	public void setResourceVersion(String resourceVersion) {
		this.resourceVersion = resourceVersion;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Map<String, String> getLabels() {
		if( labels == null )
			labels = new HashMap<String, String>();
		return labels;
	}

	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getGenerateName() {
		return generateName;
	}

	public void setGenerateName(String generateName) {
		this.generateName = generateName;
	}

	public List<Owner> getOwnerReferences() {
		if( ownerReferences == null )
			ownerReferences = new ArrayList<Owner>();
		return ownerReferences;
	}

	public void setOwnerReferences(List<Owner> ownerReferences) {
		this.ownerReferences = ownerReferences;
	}

	public Integer getGeneration() {
		return generation;
	}

	public void setGeneration(Integer generation) {
		this.generation = generation;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	public String getNode_ip() {
		return node_ip;
	}

	public void setNode_ip(String node_ip) {
		this.node_ip = node_ip;
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
