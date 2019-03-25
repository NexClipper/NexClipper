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

public class K8SAPI {
	private K8SMain daemonset;
	
	private K8SMain deployment;
	
	private K8SMain endpoint;
	
	private K8SMain namespace;
	
	private K8SMain node;
	
	private K8SMain pod;
	
	private K8SMain container;
	
	private K8SMain replicaset;
	
	private K8SMain secret;
	
	private K8SMain service;
	
	private K8SMain statefulset;
	
	private K8SCluster k8sCluster;
	
	private Version version;
	
	private K8SMain componentstatuses;

	public K8SMain getComponentstatuses() {
		if( componentstatuses == null )
			componentstatuses = new K8SMain();
		
		return componentstatuses;
	}

	public void setComponentstatuses(K8SMain componentstatuses) {
		this.componentstatuses = componentstatuses;
	}

	public K8SMain getDaemonset() {
		if( daemonset == null )
			daemonset = new K8SMain();
		return daemonset;
	}

	public void setDaemonset(K8SMain daemonset) {
		this.daemonset = daemonset;
	}

	public K8SMain getDeployment() {
		if( deployment == null )
			deployment = new K8SMain();
		return deployment;
	}

	public void setDeployment(K8SMain deployment) {
		this.deployment = deployment;
	}

	public K8SMain getEndpoint() {
		if( endpoint == null )
			endpoint = new K8SMain();
		return endpoint;
	}

	public void setEndpoint(K8SMain endpoint) {
		this.endpoint = endpoint;
	}

	public K8SMain getNamespace() {
		if( namespace == null )
			namespace = new K8SMain();
		return namespace;
	}

	public void setNamespace(K8SMain namespace) {
		this.namespace = namespace;
	}

	public K8SMain getNode() {
		if( node == null )
			node = new K8SMain();
		return node;
	}

	public void setNode(K8SMain node) {
		this.node = node;
	}

	public K8SMain getPod() {
		if( pod == null )
			pod = new K8SMain();
		return pod;
	}

	public void setPod(K8SMain pod) {
		this.pod = pod;
	}

	public K8SMain getContainer() {
		if( container == null )
			container = new K8SMain();
		return container;
	}

	public void setContainer(K8SMain container) {
		this.container = container;
	}

	public K8SMain getReplicaset() {
		if( replicaset == null )
			replicaset = new K8SMain();
		return replicaset;
	}

	public void setReplicaset(K8SMain replicaset) {
		this.replicaset = replicaset;
	}

	public K8SMain getSecret() {
		if( secret == null )
			secret = new K8SMain();
		return secret;
	}

	public void setSecret(K8SMain secret) {
		this.secret = secret;
	}

	public K8SMain getService() {
		if( service == null )
			service = new K8SMain();
		return service;
	}

	public void setService(K8SMain service) {
		this.service = service;
	}

	public K8SMain getStatefulset() {
		if( statefulset == null )
			statefulset = new K8SMain();
		return statefulset;
	}

	public void setStatefulset(K8SMain statefulset) {
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
	
	public Version getVersion() {
		if( version == null )
			version = new Version();
		
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}
}
