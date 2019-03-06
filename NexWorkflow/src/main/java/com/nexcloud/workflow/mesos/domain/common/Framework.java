package com.nexcloud.workflow.mesos.domain.common;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Framework {

	private String id;
	private String name;
	private String pid;
	private Resources used_resources;
	private Resources offered_resources;
	private List<String> capabilities;
	private String hostname;
	private String webui_url;
	private Boolean active;
	private Boolean connected;
	private Boolean recovered;
	private Integer TASK_STAGING;
	private Integer TASK_STARTING;
	private Integer TASK_RUNNING;
	private Integer TASK_KILLING;
	private Integer TASK_FINISHED;
	private Integer TASK_KILLED;
	private Integer TASK_FAILED;
	private Integer TASK_LOST;
	private Integer TASK_ERROR;
	private Integer TASK_UNREACHABLE;
	private List<String> slave_ids;

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

	public Resources getUsed_resources() {
		return used_resources;
	}

	public void setUsed_resources(Resources used_resources) {
		this.used_resources = used_resources;
	}

	public Resources getOffered_resources() {
		return offered_resources;
	}

	public void setOffered_resources(Resources offered_resources) {
		this.offered_resources = offered_resources;
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

	public Integer getTASK_STAGING() {
		return TASK_STAGING;
	}

	public void setTASK_STAGING(Integer tASK_STAGING) {
		TASK_STAGING = tASK_STAGING;
	}

	public Integer getTASK_STARTING() {
		return TASK_STARTING;
	}

	public void setTASK_STARTING(Integer tASK_STARTING) {
		TASK_STARTING = tASK_STARTING;
	}

	public Integer getTASK_RUNNING() {
		return TASK_RUNNING;
	}

	public void setTASK_RUNNING(Integer tASK_RUNNING) {
		TASK_RUNNING = tASK_RUNNING;
	}

	public Integer getTASK_KILLING() {
		return TASK_KILLING;
	}

	public void setTASK_KILLING(Integer tASK_KILLING) {
		TASK_KILLING = tASK_KILLING;
	}

	public Integer getTASK_FINISHED() {
		return TASK_FINISHED;
	}

	public void setTASK_FINISHED(Integer tASK_FINISHED) {
		TASK_FINISHED = tASK_FINISHED;
	}

	public Integer getTASK_KILLED() {
		return TASK_KILLED;
	}

	public void setTASK_KILLED(Integer tASK_KILLED) {
		TASK_KILLED = tASK_KILLED;
	}

	public Integer getTASK_FAILED() {
		return TASK_FAILED;
	}

	public void setTASK_FAILED(Integer tASK_FAILED) {
		TASK_FAILED = tASK_FAILED;
	}

	public Integer getTASK_LOST() {
		return TASK_LOST;
	}

	public void setTASK_LOST(Integer tASK_LOST) {
		TASK_LOST = tASK_LOST;
	}

	public Integer getTASK_ERROR() {
		return TASK_ERROR;
	}

	public void setTASK_ERROR(Integer tASK_ERROR) {
		TASK_ERROR = tASK_ERROR;
	}

	public Integer getTASK_UNREACHABLE() {
		return TASK_UNREACHABLE;
	}

	public void setTASK_UNREACHABLE(Integer tASK_UNREACHABLE) {
		TASK_UNREACHABLE = tASK_UNREACHABLE;
	}

	public List<String> getSlave_ids() {
		return slave_ids;
	}

	public void setSlave_ids(List<String> slave_ids) {
		this.slave_ids = slave_ids;
	}

}
