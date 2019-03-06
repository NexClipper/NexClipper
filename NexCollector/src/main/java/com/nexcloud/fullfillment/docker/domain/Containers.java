package com.nexcloud.fullfillment.docker.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nexcloud.fullfillment.docker.domain.container.Labels;
import com.nexcloud.fullfillment.docker.domain.container.Network;
import com.nexcloud.fullfillment.docker.domain.container.ProcessTop;
import com.nexcloud.fullfillment.docker.domain.stat.NetworkStats;

//import com.nexcloud.agent.common.domain.HostConfig;
//import com.nexcloud.agent.common.domain.Labels;
//import com.nexcloud.agent.common.domain.Mounts;

public class Containers {

	private String Id;
	private List<String> Names;
	private String Type;
	private String Image;
	private String ImageID;
	private String Command;
	private Long Created;
	//private List<Ports> Ports;
	private Labels Labels;
	private String State;
	private String Status;
	//private HostConfig HostConfig;
	//private NetworkSettings NetworkSettings;
	//private List<Mounts> Mounts;
	private Float cpuPercent;
	private Float memPercent;
	private Long  used_mem;
	private Long  limit_mem;
	private Long block_io_read;
	private Long block_io_write;
	private Map<String, NetworkStats> networks;
	
	private ProcessTop process;
	private Network network;
	private List<String> env;
	private List<String> volume;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public List<String> getNames() {
		if( Names == null )
			Names = new ArrayList<String>();
		return Names;
	}

	public void setNames(List<String> names) {
		Names = names;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getImageID() {
		return ImageID;
	}

	public void setImageID(String imageID) {
		ImageID = imageID;
	}

	public String getCommand() {
		return Command;
	}

	public void setCommand(String command) {
		Command = command;
	}

	public Long getCreated() {
		return Created;
	}

	public void setCreated(Long created) {
		Created = created;
	}

	/*
	public List<Ports> getPorts() {
		return Ports;
	}

	public void setPorts(List<Ports> ports) {
		Ports = ports;
	}
	*/
	
	public Labels getLabels() {
		if( Labels == null )
			Labels = new Labels();
		return Labels;
	}

	public void setLabels(Labels labels) {
		Labels = labels;
	}
	
	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	/*
	public HostConfig getHostConfig() {
		return HostConfig;
	}

	public void setHostConfig(HostConfig hostConfig) {
		HostConfig = hostConfig;
	}

	public NetworkSettings getNetworkSettings() {
		return NetworkSettings;
	}

	public void setNetworkSettings(NetworkSettings networkSettings) {
		NetworkSettings = networkSettings;
	}
	
	public List<Mounts> getMounts() {
		return Mounts;
	}

	public void setMounts(List<Mounts> mounts) {
		Mounts = mounts;
	}
	*/
			
	public Float getCpuPercent() {
		return cpuPercent;
	}

	public void setCpuPercent(Float cpuPercent) {
		this.cpuPercent = cpuPercent;
	}

	public Float getMemPercent() {
		return memPercent;
	}

	public void setMemPercent(Float memPercent) {
		this.memPercent = memPercent;
	}
	
	public Long getUsed_mem() {
		return used_mem;
	}

	public void setUsed_mem(Long used_mem) {
		this.used_mem = used_mem;
	}

	public Long getLimit_mem() {
		return limit_mem;
	}

	public void setLimit_mem(Long limit_mem) {
		this.limit_mem = limit_mem;
	}

	public Long getBlock_io_read() {
		return block_io_read;
	}

	public void setBlock_io_read(Long block_io_read) {
		this.block_io_read = block_io_read;
	}

	public Long getBlock_io_write() {
		return block_io_write;
	}

	public void setBlock_io_write(Long block_io_write) {
		this.block_io_write = block_io_write;
	}

	public Map<String, NetworkStats> getNetworks() {
		if( networks == null )
			networks = new HashMap<String, NetworkStats>();
		return networks;
	}

	public void setNetworks(Map<String, NetworkStats> networks) {
		this.networks = networks;
	}
	
	public ProcessTop getProcess() {
		if( process == null )
			process = new ProcessTop();
		return process;
	}

	public void setProcess(ProcessTop process) {
		
		
		this.process = process;
	}

	public Network getNetwork() {
		if( network == null )
			network = new Network();
			
		return network;
	}

	public void setNetwork(Network network) {
		
		this.network = network;
	}

	public List<String> getEnv() {
		if( env == null )
			env = new ArrayList<String>();
		return env;
	}

	public void setEnv(List<String> env) {
		this.env = env;
	}

	public List<String> getVolume() {
		if( volume == null )
			volume = new ArrayList<String>();
		return volume;
	}

	public void setVolume(List<String> volume) {
		this.volume = volume;
	}

}