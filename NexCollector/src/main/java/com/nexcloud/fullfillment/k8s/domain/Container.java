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
package com.nexcloud.fullfillment.k8s.domain;
import java.util.ArrayList;
import java.util.List;

public class Container {
	private List<String> containers;
	
	private String name;
	
	private String image;
	
	private List<Port> ports;
	
	private List<Env> env;
	
	private Resource resources;
	
	private List<Volume> volumeMounts; 
	
	private String terminationMessagePath;
	
	private String terminationMessagePolicy;
	
	private String imagePullPolicy;
	
	private List<String> command;
	
	private List<String> args;
	
	private Probe livenessProbe;
	
	private Probe readinessProbe;
	
	private String type;
	
	// Status of the condition, one of True, False, Unknown.
	private String status;
	
	private String lastHeartbeatTime;
	
	private String lastTransitionTime;
	
	private String reason;
	
	private String message;
	
	public List<String> getContainers() {
		if( containers == null )
			containers = new ArrayList<String>();
		return containers;
	}

	public void setContainers(List<String> containers) {
		this.containers = containers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Port> getPorts() {
		if( ports == null )
			ports = new ArrayList<Port>();
		
		return ports;
	}

	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}

	public List<Env> getEnv() {
		if( env == null )
			env = new ArrayList<Env>();
		return env;
	}

	public void setEnv(List<Env> env) {
		this.env = env;
	}

	public Resource getResources() {
		if( resources == null )
			resources = new Resource();
		return resources;
	}

	public void setResources(Resource resources) {
		this.resources = resources;
	}

	public List<Volume> getVolumeMounts() {
		if( volumeMounts == null )
			volumeMounts = new ArrayList<Volume>();
		return volumeMounts;
	}

	public void setVolumeMounts(List<Volume> volumeMounts) {
		this.volumeMounts = volumeMounts;
	}

	public String getTerminationMessagePath() {
		return terminationMessagePath;
	}

	public void setTerminationMessagePath(String terminationMessagePath) {
		this.terminationMessagePath = terminationMessagePath;
	}

	public String getTerminationMessagePolicy() {
		return terminationMessagePolicy;
	}

	public void setTerminationMessagePolicy(String terminationMessagePolicy) {
		this.terminationMessagePolicy = terminationMessagePolicy;
	}

	public String getImagePullPolicy() {
		return imagePullPolicy;
	}

	public void setImagePullPolicy(String imagePullPolicy) {
		this.imagePullPolicy = imagePullPolicy;
	}

	public List<String> getCommand() {
		if( command == null )
			command = new ArrayList<String>();
		return command;
	}

	public void setCommand(List<String> command) {
		this.command = command;
	}

	public List<String> getArgs() {
		if( args == null )
			args = new ArrayList<String>();
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = args;
	}

	public Probe getLivenessProbe() {
		if( livenessProbe == null )
			livenessProbe = new Probe();
		return livenessProbe;
	}

	public void setLivenessProbe(Probe livenessProbe) {
		this.livenessProbe = livenessProbe;
	}

	public Probe getReadinessProbe() {
		if( readinessProbe == null )
			readinessProbe = new Probe();
		return readinessProbe;
	}

	public void setReadinessProbe(Probe readinessProbe) {
		this.readinessProbe = readinessProbe;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastHeartbeatTime() {
		return lastHeartbeatTime;
	}

	public void setLastHeartbeatTime(String lastHeartbeatTime) {
		this.lastHeartbeatTime = lastHeartbeatTime;
	}

	public String getLastTransitionTime() {
		return lastTransitionTime;
	}

	public void setLastTransitionTime(String lastTransitionTime) {
		this.lastTransitionTime = lastTransitionTime;
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
}
