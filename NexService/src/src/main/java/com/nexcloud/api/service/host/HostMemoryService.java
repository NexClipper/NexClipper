package com.nexcloud.api.service.host;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexcloud.tsdb.adapter.manager.HostMemoryAdapterManager;
@Service
public class HostMemoryService {

	@Autowired private HostMemoryAdapterManager adapterManager;
	
	public String getActualFreeMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getActualFreeMemory(hostIp, startTime, time, limit);
	}
	
	public String getActualUsedMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getActualUsedMemory(hostIp, startTime, time, limit);
	}
	
	public String getFreeMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getFreeMemory(hostIp, startTime, time, limit);
	}
	
	public String getFreePercentMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getFreePercentMemory(hostIp, startTime, time, limit);
	}
	
	public String getUsedMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getUsedMemory(hostIp, startTime, time, limit);
	}
	
	public String getUsedPercentMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getUsedPercentMemory(hostIp, startTime, time, limit);
	}
	
	public String getTotalMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getTotalMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapFreeMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapFreeMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapFreePercentMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapFreePercentMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapUsedMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapUsedMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapUsedPercentMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapUsedPercentMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapTotalMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapTotalMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapPagein(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapPagein(hostIp, startTime, time, limit);
	}
	
	public String getSwapPageout(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapPageout(hostIp, startTime, time, limit);
	}
}
