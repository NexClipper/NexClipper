package com.nexcloud.workflow.mesos.domain.node;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.nexcloud.workflow.mesos.domain.common.Resources;
import com.nexcloud.workflow.mesos.domain.task.MesosTask;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MesosNode {

	private String id;
	private String hostname;
	private String pid;
	private Double registered_time;
	private Double reregistered_time;
	private Resources resources;		//total resources
	private Resources used_resources;	//allocated resources
	private Boolean active;
	private String version;
	private List<String> capabilities; 
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
	
	private Double cpus_limit				= 0.0d;
	private Double cpus_nr_periods			= 0.0d;
	private Double cpus_nr_throttled		= 0.0d;
	private Double cpus_system_time_secs	= 0.0d;
	private Double cpus_throttled_time_secs	= 0.0d;
	private Double cpus_user_time_secs		= 0.0d;
	private Double disk_limit_bytes 		= 0d; // allocated disk
	private Double disk_used_bytes			= 0d; // used disk
	private Double mem_limit_bytes			= 0d; // allocated memory
	private Double mem_rss_bytes			= 0d; // used memory
	
	private String ip;
	
	/**
	 * Total Resource
	 */
	private Resources totalResource;
	
	/**
	 * Allocate Resource
	 */
	private Resources allocateResource;
	
	/**
	 * 사용중인 Resource
	 */
	private Resources usedResource;
	
	private List<MesosTask> list;
	
	private String host_name;

	public Double getCpus_limit() {
		return cpus_limit;
	}

	public void setCpus_limit(Double cpus_limit) {
		this.cpus_limit = cpus_limit;
	}

	public Double getCpus_nr_periods() {
		return cpus_nr_periods;
	}

	public void setCpus_nr_periods(Double cpus_nr_periods) {
		this.cpus_nr_periods = cpus_nr_periods;
	}

	public Double getCpus_nr_throttled() {
		return cpus_nr_throttled;
	}

	public void setCpus_nr_throttled(Double cpus_nr_throttled) {
		this.cpus_nr_throttled = cpus_nr_throttled;
	}

	public Double getCpus_system_time_secs() {
		return cpus_system_time_secs;
	}

	public void setCpus_system_time_secs(Double cpus_system_time_secs) {
		this.cpus_system_time_secs = cpus_system_time_secs;
	}

	public Double getCpus_throttled_time_secs() {
		return cpus_throttled_time_secs;
	}

	public void setCpus_throttled_time_secs(Double cpus_throttled_time_secs) {
		this.cpus_throttled_time_secs = cpus_throttled_time_secs;
	}

	public Double getCpus_user_time_secs() {
		return cpus_user_time_secs;
	}

	public void setCpus_user_time_secs(Double cpus_user_time_secs) {
		this.cpus_user_time_secs = cpus_user_time_secs;
	}

	public Double getDisk_limit_bytes() {
		return disk_limit_bytes;
	}

	public void setDisk_limit_bytes(Double disk_limit_bytes) {
		this.disk_limit_bytes = disk_limit_bytes;
	}

	public Double getDisk_used_bytes() {
		return disk_used_bytes;
	}

	public void setDisk_used_bytes(Double disk_used_bytes) {
		this.disk_used_bytes = disk_used_bytes;
	}

	public Double getMem_limit_bytes() {
		return mem_limit_bytes;
	}

	public void setMem_limit_bytes(Double mem_limit_bytes) {
		this.mem_limit_bytes = mem_limit_bytes;
	}

	public Double getMem_rss_bytes() {
		return mem_rss_bytes;
	}

	public void setMem_rss_bytes(Double mem_rss_bytes) {
		this.mem_rss_bytes = mem_rss_bytes;
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

	private List<String> framework_ids;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Double getRegistered_time() {
		return registered_time;
	}

	public void setRegistered_time(Double registered_time) {
		this.registered_time = registered_time;
	}

	public Double getReregistered_time() {
		return reregistered_time;
	}

	public void setReregistered_time(Double reregistered_time) {
		this.reregistered_time = reregistered_time;
	}

	public Resources getResources() {
		if( resources == null )
			resources = new Resources();
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

	public Resources getUsed_resources() {
		if( used_resources == null )
			used_resources = new Resources();
		
		return used_resources;
	}

	public void setUsed_resources(Resources used_resources) {
		this.used_resources = used_resources;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<String> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<String> capabilities) {
		this.capabilities = capabilities;
	}

	public List<String> getFramework_ids() {
		return framework_ids;
	}

	public void setFramework_ids(List<String> framework_ids) {
		this.framework_ids = framework_ids;
	}

	public List<MesosTask> getList() {
		if( list == null )
			list = new ArrayList<MesosTask>();
		return list;
	}

	public void setList(List<MesosTask> list) {
		this.list = list;
	}

	public Resources getTotalResource() {
		if( totalResource == null )
			totalResource = new Resources();
		return totalResource;
	}

	public void setTotalResource(Resources totalResource) {
		this.totalResource = totalResource;
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
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
}
