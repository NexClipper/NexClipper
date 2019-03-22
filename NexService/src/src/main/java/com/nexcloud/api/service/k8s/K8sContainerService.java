package com.nexcloud.api.service.k8s;
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
import com.nexcloud.tsdb.adapter.manager.K8sContainerAdapterManager;

@Service
public class K8sContainerService {
	@Autowired private K8sContainerAdapterManager adapterManager;

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
	
	// by container
	

	public String getCpuUsedByContainer (String container, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsed(container, startTime, time, limit);
	}
	public String getCpuUsedPercentByContainer (String container, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercent(container, startTime, time, limit);
	}
	public String getCpuLimitByContainer (String container, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLimit(container, startTime, time, limit);
	}
	public String getCpuRequestByContainer (String container, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuRequest(container, startTime, time, limit);
	}
	public String getMemoryUsedByContainer (String container, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsed(container, startTime, time, limit);
	}
	public String getMemoryUsedPercentByContainer (String container, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsedPercent(container, startTime, time, limit);
	}
	public String getMemoryLimitByContainer (String container, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryLimit(container, startTime, time, limit);
	}
	public String getMemoryRequestByContainer (String container, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryRequest(container, startTime, time, limit);
	}
	
}
