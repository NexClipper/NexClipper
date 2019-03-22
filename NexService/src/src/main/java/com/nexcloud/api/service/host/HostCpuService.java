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

import com.nexcloud.tsdb.adapter.manager.HostCpuAdapterManager;

@Service
public class HostCpuService {
	@Autowired private HostCpuAdapterManager adapterManager;

	public String getCpuUserPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUserPercentByHostIp(hostIp, startTime, time, limit);
	}
	public String getCpuUsedPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuIdlePercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuIdlePercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuIrqPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuIrqPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuNicePercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuNicePercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuSorfirqPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuSorfirqPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuStolenPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuStolenPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuSysPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuSysPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuWaitPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuWaitPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuTotalCoreByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuTotalCoreByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuLoad1ByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLoad1ByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuLoad5ByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLoad5ByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuLoad15ByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLoad15ByHostIp(hostIp, startTime, time, limit);
	}
}
