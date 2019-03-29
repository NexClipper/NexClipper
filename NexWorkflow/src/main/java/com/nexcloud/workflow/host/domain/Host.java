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
package com.nexcloud.workflow.host.domain;
import java.util.ArrayList;
import java.util.List;

public class Host {
	private String host_ip;
	
	private String host_name;
	
	private String uptime;
	
	private String description;
	
	private String name;
	
	private String version;
	
	private String arch;
	
	private String pathLevel;
	
	private String vendor;
	
	private String vendorName;
	
	private String vendorVersion;
	
	private Integer process_total;
	
	private Integer container_total;
	
	private CPU cpu;
	
	private List<CPU> cpus;
	
	private Memory mem;
	
	private Disk disk;
	
	private List<Disk> disks;
	
	private SwapMemory swap;
	
	private Network net;
	
	private ProcessStatus processStatus;
	
	private List<Process> process;

	public String getHost_ip() {
		return host_ip;
	}

	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public Integer getProcess_total() {
		return process_total;
	}

	public void setProcess_total(Integer process_total) {
		this.process_total = process_total;
	}

	public Integer getContainer_total() {
		return container_total;
	}

	public void setContainer_total(Integer container_total) {
		this.container_total = container_total;
	}

	public CPU getCpu() {
		if( cpu == null )
			cpu = new CPU();
		
		return cpu;
	}

	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}

	public Memory getMem() {
		if( mem == null )
			mem = new Memory();
		return mem;
	}

	public void setMem(Memory mem) {
		this.mem = mem;
	}

	public Disk getDisk() {
		if( disk == null )
			disk = new Disk();
		
		return disk;
	}

	public void setDisk(Disk disk) {
		this.disk = disk;
	}

	public SwapMemory getSwap() {
		if( swap == null )
			swap = new SwapMemory();
		
		return swap;
	}

	public void setSwap(SwapMemory swap) {
		this.swap = swap;
	}

	public Network getNet() {
		if( net == null )
			net = new Network();
		
		return net;
	}

	public void setNet(Network net) {
		this.net = net;
	}

	public ProcessStatus getProcessStatus() {
		if( processStatus == null )
			processStatus = new ProcessStatus();

		return processStatus;
	}

	public void setProcessStatus(ProcessStatus processStatus) {
		this.processStatus = processStatus;
	}

	public List<Disk> getDisks() {
		if( disks == null )
			disks = new ArrayList<Disk>();
		return disks;
	}

	public void setDisks(List<Disk> disks) {
		this.disks = disks;
	}

	public List<CPU> getCpus() {
		if( cpus == null )
			cpus = new ArrayList<CPU>();
		
		return cpus;
	}

	public void setCpus(List<CPU> cpus) {
		this.cpus = cpus;
	}
	
	public List<Process> getProcess() {
		if( process == null )
			process = new ArrayList<Process>();
		return process;
	}

	public void setProcess(List<Process> process) {
		this.process = process;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	public String getPathLevel() {
		return pathLevel;
	}

	public void setPathLevel(String pathLevel) {
		this.pathLevel = pathLevel;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorVersion() {
		return vendorVersion;
	}

	public void setVendorVersion(String vendorVersion) {
		this.vendorVersion = vendorVersion;
	}
}

