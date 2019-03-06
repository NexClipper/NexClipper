package com.nexcloud.workflow.docker.domain;

import java.util.Map;

import com.nexcloud.workflow.docker.domain.stat.BlockIO;
import com.nexcloud.workflow.docker.domain.stat.CpuStats;
import com.nexcloud.workflow.docker.domain.stat.MemoryStats;
import com.nexcloud.workflow.docker.domain.stat.NetworkStats;

public class ContainerStat {
	private String read;
	private String preread;
	private BlockIO blkio_stats; 
	private CpuStats cpu_stats;
	private CpuStats precpu_stats;
	private MemoryStats memory_stats;
	private Map<String, NetworkStats> networks;

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getPreread() {
		return preread;
	}

	public void setPreread(String preread) {
		this.preread = preread;
	}

	public BlockIO getBlkio_stats() {
		return blkio_stats;
	}

	public void setBlkio_stats(BlockIO blkio_stats) {
		this.blkio_stats = blkio_stats;
	}

	public CpuStats getCpu_stats() {
		return cpu_stats;
	}

	public void setCpu_stats(CpuStats cpu_stats) {
		this.cpu_stats = cpu_stats;
	}

	public CpuStats getPrecpu_stats() {
		return precpu_stats;
	}

	public void setPrecpu_stats(CpuStats precpu_stats) {
		this.precpu_stats = precpu_stats;
	}

	public MemoryStats getMemory_stats() {
		return memory_stats;
	}

	public void setMemory_stats(MemoryStats memory_stats) {
		this.memory_stats = memory_stats;
	}

	public Map<String, NetworkStats> getNetworks() {
		return networks;
	}

	public void setNetworks(Map<String, NetworkStats> networks) {
		this.networks = networks;
	}
}
