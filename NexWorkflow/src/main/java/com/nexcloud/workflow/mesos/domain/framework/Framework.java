package com.nexcloud.workflow.mesos.domain.framework;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.nexcloud.workflow.mesos.domain.common.Resources;
import com.nexcloud.workflow.mesos.domain.task.MesosTask;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Framework {

	private String id;
	private String name;
	private String pid;
	private List<String> capabilities; 
	private String hostname;
	private String webui_url;
	private Boolean active;
	private Boolean connected;
	private Boolean recovered;
	private List<MesosTask> tasks;				//task_running, task_staging, task_starting
	private List<MesosTask> unreachable_tasks;	//task_unreachable
	private List<MesosTask> completed_tasks;		//task_killing, task_finished, task_failed, task_lost
	private List<MesosTask> executors;
	
	private List<Double> max_value;
	
	/**
	 * Allocate Resource
	 */
	private Resources allocateResource;
	
	/**
	 * 사용중인 Resource
	 */
	private Resources usedResource;
	

	public List<MesosTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<MesosTask> tasks) {
		this.tasks = tasks;
	}

	public List<MesosTask> getUnreachable_tasks() {
		return unreachable_tasks;
	}

	public void setUnreachable_tasks(List<MesosTask> unreachable_tasks) {
		this.unreachable_tasks = unreachable_tasks;
	}

	public List<MesosTask> getCompleted_tasks() {
		return completed_tasks;
	}

	public void setCompleted_tasks(List<MesosTask> completed_tasks) {
		this.completed_tasks = completed_tasks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public List<String> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<String> capabilities) {
		this.capabilities = capabilities;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getWebui_url() {
		return webui_url;
	}

	public void setWebui_url(String webui_url) {
		this.webui_url = webui_url;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getConnected() {
		return connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

	public Boolean getRecovered() {
		return recovered;
	}

	public void setRecovered(Boolean recovered) {
		this.recovered = recovered;
	}
	
	public Resources getAllocateResource() {
		if( allocateResource == null )
			allocateResource = new Resources();
		return allocateResource;
	}

	public void setAllocateResource(Resources allocateResource) {
		this.allocateResource = allocateResource;
	}

	public Resources getUsedResource() {
		if( usedResource == null )
			usedResource = new Resources();
		return usedResource;
	}

	public void setUsedResource(Resources usedResource) {
		this.usedResource = usedResource;
	}

	public List<Double> getMax_value() {
		if( max_value == null )
			max_value = new ArrayList<Double>();
		return max_value;
	}

	public void setMax_value(List<Double> max_value) {
		this.max_value = max_value;
	}

	public List<MesosTask> getExecutors() {
		if( executors == null )
			executors = new ArrayList<MesosTask>();
		return executors;
	}

	public void setExecutors(List<MesosTask> executors) {
		this.executors = executors;
	}
}