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
import com.nexcloud.tsdb.adapter.manager.K8sPodAdapterManager;

@Service
public class K8sPodService {
	@Autowired private K8sPodAdapterManager adapterManager;

	public String getCpuUsed (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsed(clusterId, startTime, time, limit);
	}
	public String getCpuUsedPercent (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercent(clusterId, startTime, time, limit);
	}
	public String getCpuLimit (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLimit(clusterId, startTime, time, limit);
	}
	public String getCpuRequest (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuRequest(clusterId, startTime, time, limit);
	}
	
	public String getMemoryUsed (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsed(clusterId, startTime, time, limit);
	}
	public String getMemoryUsedPercent (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsedPercent(clusterId, startTime, time, limit);
	}
	public String getMemoryLimit (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryLimit(clusterId, startTime, time, limit);
	}
	public String getMemoryRequest (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryRequest(clusterId, startTime, time, limit);
	}
	public String getCpuUsedByPod (String clusterId, String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsed(clusterId, pod, startTime, time, limit);
	}
	public String getCpuUsedPercentByPod (String clusterId, String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercent(clusterId, pod, startTime, time, limit);
	}
	public String getCpuLimitByPod (String clusterId, String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLimit(clusterId, pod, startTime, time, limit);
	}
	public String getCpuRequestByPod (String clusterId, String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuRequest(clusterId, pod, startTime, time, limit);
	}
	
	public String getMemoryUsedByPod (String clusterId, String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsed(clusterId, pod, startTime, time, limit);
	}
	public String getMemoryUsedPercentByPod (String clusterId, String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsedPercent(clusterId, pod, startTime, time, limit);
	}
	public String getMemoryLimitByPod (String clusterId, String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryLimit(clusterId, pod, startTime, time, limit);
	}
	public String getMemoryRequestByPod (String clusterId, String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryRequest(clusterId, pod, startTime, time, limit);
	}
}
