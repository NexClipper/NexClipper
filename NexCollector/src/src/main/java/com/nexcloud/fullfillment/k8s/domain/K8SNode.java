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

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class K8SNode {
	private Map<String, String> map;

	public Map<String, String> getMap() {
		if( map == null )
			map	= new HashMap<String, String>();
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	private String machineID;
	
	private String systemUUID;
	
	private String bootID;
	
	private String kernelVersion;
	
	private String osImage;
	
	private String containerRuntimeVersion;
	
	private String kubeletVersion;
	
	private String kubeProxyVersion;
	
	private String operatingSystem;
	
	private String architecture;
	
	@SerializedName("beta.kubernetes.io/os")
	private String beta_kubernetes_io_os;

	public String getMachineID() {
		return machineID;
	}

	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}

	public String getSystemUUID() {
		return systemUUID;
	}

	public void setSystemUUID(String systemUUID) {
		this.systemUUID = systemUUID;
	}

	public String getBootID() {
		return bootID;
	}

	public void setBootID(String bootID) {
		this.bootID = bootID;
	}

	public String getKernelVersion() {
		return kernelVersion;
	}

	public void setKernelVersion(String kernelVersion) {
		this.kernelVersion = kernelVersion;
	}

	public String getOsImage() {
		return osImage;
	}

	public void setOsImage(String osImage) {
		this.osImage = osImage;
	}

	public String getContainerRuntimeVersion() {
		return containerRuntimeVersion;
	}

	public void setContainerRuntimeVersion(String containerRuntimeVersion) {
		this.containerRuntimeVersion = containerRuntimeVersion;
	}

	public String getKubeletVersion() {
		return kubeletVersion;
	}

	public void setKubeletVersion(String kubeletVersion) {
		this.kubeletVersion = kubeletVersion;
	}

	public String getKubeProxyVersion() {
		return kubeProxyVersion;
	}

	public void setKubeProxyVersion(String kubeProxyVersion) {
		this.kubeProxyVersion = kubeProxyVersion;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	public String getBeta_kubernetes_io_os() {
		return beta_kubernetes_io_os;
	}

	public void setBeta_kubernetes_io_os(String beta_kubernetes_io_os) {
		this.beta_kubernetes_io_os = beta_kubernetes_io_os;
	}
}
