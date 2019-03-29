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

	public String getCpuUsed (String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsed(startTime, time, limit);
	}
	public String getCpuUsedPercent (String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercent(startTime, time, limit);
	}
	public String getCpuLimit (String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLimit(startTime, time, limit);
	}
	public String getCpuRequest (String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuRequest(startTime, time, limit);
	}
	
	public String getMemoryUsed (String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsed(startTime, time, limit);
	}
	public String getMemoryUsedPercent (String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsedPercent(startTime, time, limit);
	}
	public String getMemoryLimit (String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryLimit(startTime, time, limit);
	}
	public String getMemoryRequest (String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryRequest(startTime, time, limit);
	}
	public String getCpuUsedByPod (String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsed(pod, startTime, time, limit);
	}
	public String getCpuUsedPercentByPod (String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercent(pod, startTime, time, limit);
	}
	public String getCpuLimitByPod (String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLimit(pod, startTime, time, limit);
	}
	public String getCpuRequestByPod (String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuRequest(pod, startTime, time, limit);
	}
	
	public String getMemoryUsedByPod (String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsed(pod, startTime, time, limit);
	}
	public String getMemoryUsedPercentByPod (String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsedPercent(pod, startTime, time, limit);
	}
	public String getMemoryLimitByPod (String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryLimit(pod, startTime, time, limit);
	}
	public String getMemoryRequestByPod (String pod, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryRequest(pod, startTime, time, limit);
	}
}
