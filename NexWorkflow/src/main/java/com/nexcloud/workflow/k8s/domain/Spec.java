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

public class Spec {
	private String podCIDR;
	
	private String externalID;
	
	private String providerID;
	
	private List<Taint> taints;
	
	private List<Volume> volumes;
	
	private List<Container> containers;
	
	private String restartPolicy;
	
	private String dnsPolicy;
	
	private String serviceAccountName;
	
	private String nodeName;
	
	private String serviceAccount;
	
	private Integer terminationGracePeriodSeconds;
	
	private String schedulerName;
	
	private List<Toleration> tolerations;
	
	private List<Port> ports;
	
	private String clusterIP;
	
	private String type;
	
	private String sessionAffinity;
	
	private Selector selector;
	
	private String externalTrafficPolicy;
	
	private Integer replicas;
	
	private Boolean paused;
	
	private Item template;
	
	private K8SNode nodeSelector;
	
	private Strategy strategy;
	
	private Integer revisionHistoryLimit;
	
	private Integer progressDeadlineSeconds;
	
	private Boolean unschedulable;
	
	private List<String> externalIPs;
	
	private String externalName;
	
	private String loadBalancerIP;
	
	public List<String> getExternalIPs() {
		if( externalIPs == null )
			externalIPs = new ArrayList<String>();
		return externalIPs;
	}

	public void setExternalIPs(List<String> externalIPs) {
		this.externalIPs = externalIPs;
	}

	public String getExternalName() {
		return externalName;
	}

	public void setExternalName(String externalName) {
		this.externalName = externalName;
	}

	public String getLoadBalancerIP() {
		return loadBalancerIP;
	}

	public void setLoadBalancerIP(String loadBalancerIP) {
		this.loadBalancerIP = loadBalancerIP;
	}

	public Integer getRevisionHistoryLimit() {
		return revisionHistoryLimit;
	}

	public void setRevisionHistoryLimit(Integer revisionHistoryLimit) {
		this.revisionHistoryLimit = revisionHistoryLimit;
	}

	public Integer getProgressDeadlineSeconds() {
		return progressDeadlineSeconds;
	}

	public void setProgressDeadlineSeconds(Integer progressDeadlineSeconds) {
		this.progressDeadlineSeconds = progressDeadlineSeconds;
	}

	public String getPodCIDR() {
		return podCIDR;
	}

	public void setPodCIDR(String podCIDR) {
		this.podCIDR = podCIDR;
	}

	public String getExternalID() {
		return externalID;
	}

	public void setExternalID(String externalID) {
		this.externalID = externalID;
	}

	public String getProviderID() {
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public List<Taint> getTaints() {
		if( taints == null )
			taints = new ArrayList<Taint>();
		return taints;
	}

	public void setTaints(List<Taint> taints) {
		this.taints = taints;
	}

	public List<Volume> getVolumes() {
		if( volumes == null )
			volumes = new ArrayList<Volume>();
		return volumes;
	}

	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}

	public List<Container> getContainers() {
		if( containers == null )
			containers = new ArrayList<Container>();
		return containers;
	}

	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}

	public String getRestartPolicy() {
		return restartPolicy;
	}

	public void setRestartPolicy(String restartPolicy) {
		this.restartPolicy = restartPolicy;
	}

	public String getDnsPolicy() {
		return dnsPolicy;
	}

	public void setDnsPolicy(String dnsPolicy) {
		this.dnsPolicy = dnsPolicy;
	}

	public String getServiceAccountName() {
		return serviceAccountName;
	}

	public void setServiceAccountName(String serviceAccountName) {
		this.serviceAccountName = serviceAccountName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getServiceAccount() {
		return serviceAccount;
	}

	public void setServiceAccount(String serviceAccount) {
		this.serviceAccount = serviceAccount;
	}

	public Integer getTerminationGracePeriodSeconds() {
		return terminationGracePeriodSeconds;
	}

	public void setTerminationGracePeriodSeconds(Integer terminationGracePeriodSeconds) {
		this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
	}

	public String getSchedulerName() {
		return schedulerName;
	}

	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}

	public List<Toleration> getTolerations() {
		if( tolerations == null )
			tolerations = new ArrayList<Toleration>();
		
		return tolerations;
	}

	public void setTolerations(List<Toleration> tolerations) {
		this.tolerations = tolerations;
	}

	public List<Port> getPorts() {
		if( ports == null )
			ports = new ArrayList<Port>();
		return ports;
	}

	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}

	public String getClusterIP() {
		return clusterIP;
	}

	public void setClusterIP(String clusterIP) {
		this.clusterIP = clusterIP;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSessionAffinity() {
		return sessionAffinity;
	}

	public void setSessionAffinity(String sessionAffinity) {
		this.sessionAffinity = sessionAffinity;
	}

	public Selector getSelector() {
		if( selector == null )
			selector = new Selector();
		return selector;
	}

	public void setSelector(Selector selector) {
		this.selector = selector;
	}

	public String getExternalTrafficPolicy() {
		return externalTrafficPolicy;
	}

	public void setExternalTrafficPolicy(String externalTrafficPolicy) {
		this.externalTrafficPolicy = externalTrafficPolicy;
	}

	public Integer getReplicas() {
		return replicas;
	}

	public void setReplicas(Integer replicas) {
		this.replicas = replicas;
	}

	public Item getTemplate() {
		if( template == null )
			template = new Item();
		return template;
	}

	public void setTemplate(Item template) {
		this.template = template;
	}

	public K8SNode getNodeSelector() {
		if( nodeSelector == null )
			nodeSelector = new K8SNode();
		return nodeSelector;
	}

	public void setNodeSelector(K8SNode nodeSelector) {
		this.nodeSelector = nodeSelector;
	}

	public Strategy getStrategy() {
		if( strategy == null )
			strategy = new Strategy();
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public Boolean getPaused() {
		return paused;
	}

	public void setPaused(Boolean paused) {
		this.paused = paused;
	}

	public Boolean getUnschedulable() {
		return unschedulable;
	}

	public void setUnschedulable(Boolean unschedulable) {
		this.unschedulable = unschedulable;
	}
}
