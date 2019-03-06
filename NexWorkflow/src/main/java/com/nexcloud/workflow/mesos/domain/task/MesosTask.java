package com.nexcloud.workflow.mesos.domain.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nexcloud.workflow.mesos.domain.common.Resources;
import com.nexcloud.workflow.mesos.domain.common.Statistics;

public class MesosTask {

	private String node_ip;
	private String container_id;
	private String executor_id; // Task ID
	private String framework_id;
	private String source;
	private Statistics statistics;
	private String state;
	
	private Resources resources;
	
	/**
	 * Allocate Resource
	 */
	private Resources allocateResource;
	
	/**
	 * 사용중인 Resource
	 */
	private Resources usedResource;
	
	/**
    * Statuses - comepleted tasks (failed, killed, lost 등)
    * Mesos에 직접 접근하여 Statuses를 수집하기 위해 추가한 필드
    * 추후 Collecter 에서 해당 필드를 수집하는것으로 변경해야 함
    */
	private List<Map<String, Object>> statuses;
	private String id;
	private String time;
	private String name;
	private String role;
	private String slave_id;
	private String executor_name;
	private String task_name;

	public String getExecutor_name() {
		return executor_name;
	}

	public void setExecutor_name(String executor_name) {
		this.executor_name = executor_name;
	}

	public String getNode_ip() {
		return node_ip;
	}

	public void setNode_ip(String node_ip) {
		this.node_ip = node_ip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContainer_id() {
		return container_id;
	}

	public void setContainer_id(String container_id) {
		this.container_id = container_id;
	}

	public String getExecutor_id() {
		return executor_id;
	}

	public void setExecutor_id(String executor_id) {
		this.executor_id = executor_id;
	}

	public String getFramework_id() {
		return framework_id;
	}

	public void setFramework_id(String framework_id) {
		this.framework_id = framework_id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Statistics getStatistics() {
		if( statistics == null )
			statistics = new Statistics();
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
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

	public List<Map<String, Object>> getStatuses() {
		if( statuses == null )
			statuses = new ArrayList<Map<String, Object>>();
		return statuses;
	}

	public void setStatuses(List<Map<String, Object>> statuses) {
		this.statuses = statuses;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSlave_id() {
		return slave_id;
	}

	public void setSlave_id(String slave_id) {
		this.slave_id = slave_id;
	}

	public Resources getResources() {
		if( resources == null )
			resources = new Resources();
		
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
}
