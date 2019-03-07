package com.nexcloud.docker.container.list.domain;

import java.util.List;

import com.nexcloud.docker.common.domain.HostConfig;
import com.nexcloud.docker.common.domain.Labels;
import com.nexcloud.docker.common.domain.Mounts;

public class Container {
	private String Id;
	private List<String> Names;
	private String Image;
	private String ImageID;
	private String Command;
	private Long Created;
	private List<Ports> Ports;
	private Labels Labels;
	private String State;
	private String Status;
	private HostConfig HostConfig;
	private NetworkSettings NetworkSettings;
	private List<Mounts> Mounts;

	public Float cpuPercent;
	public Float memPercent;
	public Long  used_mem;
	public Long  limit_mem;
	public Long block_io_read;
	public Long block_io_write;
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public List<String> getNames() {
		return Names;
	}

	public void setNames(List<String> names) {
		Names = names;
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

	public List<Ports> getPorts() {
		return Ports;
	}

	public void setPorts(List<Ports> ports) {
		Ports = ports;
	}

	public Labels getLabels() {
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** List Container *****\n");
		sb.append("Id              = " + getId() + "\n");
		sb.append("Names           = " + getNames() + "\n");
		sb.append("Image           = " + getImage() + "\n");
		sb.append("ImageID         = " + getImageID() + "\n");
		sb.append("Command         = " + getCommand() + "\n");
		sb.append("Created         = " + getCreated() + "\n");
		sb.append("Ports           = " + getPorts() + "\n");
		sb.append("Labels          = " + getLabels() + "\n");
		sb.append("State           = " + getState() + "\n");
		sb.append("Status          = " + getStatus() + "\n");
		sb.append("HostConfig      = " + getHostConfig() + "\n");
		sb.append("NetworkSettings = " + getNetworkSettings() + "\n");
		sb.append("Mounts          = " + getMounts() + "\n");
		sb.append("cpuPercent      = " + getCpuPercent() + "\n");
		sb.append("memPercent      = " + getMemPercent() + "\n");
		return sb.toString();
	}
}