package com.nexcloud.docker.container.stats.domain;

import java.util.Map;

public class ContainerStat {
	public String read;
	public String preread;
	public BlockIO blkio_stats; 
	public CpuStats cpu_stats;
	public CpuStats precpu_stats;
	public MemoryStats memory_stats;
	public Map<String, NetworkStats> networks;

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** ContainerStat *****\n");
		sb.append("read          = " + getRead() + "\n");
		sb.append("preread       = " + getPreread() + "\n");
		sb.append("cpu_stats     = " + getCpu_stats() + "\n");
		sb.append("precpu_stats  = " + getPrecpu_stats() + "\n");
		sb.append("memory_stats  = " + getMemory_stats() + "\n");
		sb.append("networks      = " + getNetworks() + "\n");
		return sb.toString();
	}
}