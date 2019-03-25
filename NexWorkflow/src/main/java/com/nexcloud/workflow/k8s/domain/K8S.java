package com.nexcloud.workflow.k8s.domain;
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

import java.util.HashMap;
import java.util.Map;

public class K8S {
	private Map<String, Configmap> configmap;
	
	private Map<String, Daemonset> daemonset;
	
	private Map<String, Deployment> deployment;
	
	private Map<String, Endpoint> endpoint;
	
	private Map<String, Namespace> namespace;
	
	private Map<String, K8SNode> node;
	
	private Map<String, Pod> pod;
	
	private Map<String, Container> container;
	
	private Map<String, Replicaset> replicaset;
	
	private Map<String, Secret> secret;
	
	private Map<String, Service> service;
	
	private Map<String, Statefulset> statefulset;
	
	private K8SCluster k8sCluster;

	public Map<String, Configmap> getConfigmap() {
		if( configmap == null )
			configmap = new HashMap<String, Configmap>();
		return configmap;
	}

	public void setConfigmap(Map<String, Configmap> configmap) {
		this.configmap = configmap;
	}

	public Map<String, Daemonset> getDaemonset() {
		if( daemonset == null )
			daemonset = new HashMap<String, Daemonset>();
		return daemonset;
	}

	public void setDaemonset(Map<String, Daemonset> daemonset) {
		this.daemonset = daemonset;
	}

	public Map<String, Deployment> getDeployment() {
		if( deployment == null )
			deployment = new HashMap<String, Deployment>();
		return deployment;
	}

	public void setDeployment(Map<String, Deployment> deployment) {
		this.deployment = deployment;
	}

	public Map<String, Endpoint> getEndpoint() {
		if( endpoint == null )
			endpoint = new HashMap<String, Endpoint>();
		return endpoint;
	}

	public void setEndpoint(Map<String, Endpoint> endpoint) {
		this.endpoint = endpoint;
	}

	public Map<String, Namespace> getNamespace() {
		if( namespace == null )
			namespace = new HashMap<String, Namespace>();
		return namespace;
	}

	public void setNamespace(Map<String, Namespace> namespace) {
		this.namespace = namespace;
	}

	public Map<String, K8SNode> getNode() {
		if( node == null )
			node = new HashMap<String, K8SNode>();
		return node;
	}

	public void setNode(Map<String, K8SNode> node) {
		this.node = node;
	}

	public Map<String, Pod> getPod() {
		if( pod == null )
			pod = new HashMap<String, Pod>();
		
		return pod;
	}

	public void setPod(Map<String, Pod> pod) {
		this.pod = pod;
	}

	public Map<String, Container> getContainer() {
		if( container == null )
			container = new HashMap<String, Container>();
		
		return container;
	}

	public void setContainer(Map<String, Container> container) {
		this.container = container;
	}

	public Map<String, Replicaset> getReplicaset() {
		if( replicaset == null )
			replicaset = new HashMap<String, Replicaset>();
		return replicaset;
	}

	public void setReplicaset(Map<String, Replicaset> replicaset) {
		this.replicaset = replicaset;
	}

	public Map<String, Secret> getSecret() {
		if( secret == null )
			secret = new HashMap<String, Secret>();
		return secret;
	}

	public void setSecret(Map<String, Secret> secret) {
		this.secret = secret;
	}

	public Map<String, Service> getService() {
		if( service == null )
			service = new HashMap<String, Service>();
		return service;
	}

	public void setService(Map<String, Service> service) {
		this.service = service;
	}

	public Map<String, Statefulset> getStatefulset() {
		if( statefulset == null )
			statefulset = new HashMap<String, Statefulset>();
		return statefulset;
	}

	public void setStatefulset(Map<String, Statefulset> statefulset) {
		this.statefulset = statefulset;
	}

	public K8SCluster getK8sCluster() {
		if( k8sCluster == null )
			k8sCluster = new K8SCluster();
		return k8sCluster;
	}

	public void setK8sCluster(K8SCluster k8sCluster) {
		this.k8sCluster = k8sCluster;
	}
}
