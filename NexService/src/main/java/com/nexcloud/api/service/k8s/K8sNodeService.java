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
package com.nexcloud.api.service.k8s;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexcloud.tsdb.adapter.manager.K8sNodeAdapterManager;

@Service
public class K8sNodeService {
	@Autowired private K8sNodeAdapterManager adapterManager;

	public String getCpuUsed (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsed(clusterId, startTime, time, limit);
	}
	public String getCpuUsedPercent (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercent(clusterId, startTime, time, limit);
	}
	public String getCpuAllocate (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuAllocate(clusterId, startTime, time, limit);
	}
	public String getCpuTotal (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuTotal(clusterId, startTime, time, limit);
	}
	
	public String getMemoryUsed (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsed(clusterId, startTime, time, limit);
	}
	public String getMemoryUsedPercent (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsedPercent(clusterId, startTime, time, limit);
	}
	public String getMemoryAllocate (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryAllocate(clusterId, startTime, time, limit);
	}
	public String getMemoryTotal (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryTotal(clusterId, startTime, time, limit);
	}
	
	public String getPodAllocate (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getPodAllocate(clusterId, startTime, time, limit);
	}
	public String getPodTotal (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getPodTotal(clusterId, startTime, time, limit);
	}
	
	// by node
	

	public String getCpuUsedByNode (String clusterId, String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsed(clusterId, node, startTime, time, limit);
	}
	public String getCpuUsedPercentByNode (String clusterId, String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercent(clusterId, node, startTime, time, limit);
	}
	public String getCpuAllocateByNode (String clusterId, String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuAllocate(clusterId, node, startTime, time, limit);
	}
	public String getCpuTotalByNode (String clusterId, String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuTotal(clusterId, node, startTime, time, limit);
	}
	
	public String getMemoryUsedByNode (String clusterId, String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsed(clusterId, node, startTime, time, limit);
	}
	public String getMemoryUsedPercentByNode (String clusterId, String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsedPercent(clusterId, node, startTime, time, limit);
	}
	public String getMemoryAllocateByNode (String clusterId, String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryAllocate(clusterId, node, startTime, time, limit);
	}
	public String getMemoryTotalByNode (String clusterId, String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryTotal(clusterId, node, startTime, time, limit);
	}
	
	public String getPodAllocateByNode (String clusterId, String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getPodAllocate(clusterId, node, startTime, time, limit);
	}
	public String getPodTotalByNode (String clusterId, String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getPodTotal(clusterId, node, startTime, time, limit);
	}
	
}
