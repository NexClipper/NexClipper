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

	public String getCpuUsed (String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsed(startTime, time, limit);
	}
	public String getCpuUsedPercent (String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercent(startTime, time, limit);
	}
	public String getCpuAllocate (String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuAllocate(startTime, time, limit);
	}
	public String getCpuTotal (String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuTotal(startTime, time, limit);
	}
	
	public String getMemoryUsed (String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsed(startTime, time, limit);
	}
	public String getMemoryUsedPercent (String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsedPercent(startTime, time, limit);
	}
	public String getMemoryAllocate (String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryAllocate(startTime, time, limit);
	}
	public String getMemoryTotal (String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryTotal(startTime, time, limit);
	}
	
	public String getPodAllocate (String startTime, String time, int limit) {
		return adapterManager.adapter().getPodAllocate(startTime, time, limit);
	}
	public String getPodTotal (String startTime, String time, int limit) {
		return adapterManager.adapter().getPodTotal(startTime, time, limit);
	}
	
	// by node
	

	public String getCpuUsedByNode (String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsed(node, startTime, time, limit);
	}
	public String getCpuUsedPercentByNode (String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercent(node, startTime, time, limit);
	}
	public String getCpuAllocateByNode (String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuAllocate(node, startTime, time, limit);
	}
	public String getCpuTotalByNode (String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuTotal(node, startTime, time, limit);
	}
	
	public String getMemoryUsedByNode (String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsed(node, startTime, time, limit);
	}
	public String getMemoryUsedPercentByNode (String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsedPercent(node, startTime, time, limit);
	}
	public String getMemoryAllocateByNode (String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryAllocate(node, startTime, time, limit);
	}
	public String getMemoryTotalByNode (String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryTotal(node, startTime, time, limit);
	}
	
	public String getPodAllocateByNode (String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getPodAllocate(node, startTime, time, limit);
	}
	public String getPodTotalByNode (String node, String startTime, String time, int limit) {
		return adapterManager.adapter().getPodTotal(node, startTime, time, limit);
	}
	
}
