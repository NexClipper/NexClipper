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
package com.nexcloud.api.service.host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexcloud.tsdb.adapter.manager.HostCpuAdapterManager;

@Service
public class HostCpuService {
	@Autowired private HostCpuAdapterManager adapterManager;

	public String getCpuUserPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUserPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	public String getCpuUsedPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	public String getCpuIdlePercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuIdlePercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	public String getCpuIrqPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuIrqPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	public String getCpuNicePercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuNicePercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	public String getCpuSorfirqPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuSorfirqPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	public String getCpuStolenPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuStolenPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	public String getCpuSysPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuSysPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	public String getCpuWaitPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuWaitPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	public String getCpuTotalCoreByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuTotalCoreByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	public String getCpuLoad1ByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLoad1ByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	public String getCpuLoad5ByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLoad5ByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	public String getCpuLoad15ByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLoad15ByHostIp(clusterId, hostIp, startTime, time, limit);
	}
}
