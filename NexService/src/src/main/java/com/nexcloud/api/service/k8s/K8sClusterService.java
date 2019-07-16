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

import com.nexcloud.tsdb.adapter.manager.K8sClusterAdapterManager;

@Service
public class K8sClusterService {
	@Autowired private K8sClusterAdapterManager adapterManager;
	public String getCpuTotal (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuTotal(clusterId, startTime, time, limit);
	}
	public String getCpuUsed (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsed(clusterId, startTime, time, limit);
	}
	public String getCpuUsedPercent (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercent(clusterId, startTime, time, limit);
	}
	public String getMemoryTotal (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryTotal(clusterId, startTime, time, limit);
	}
	public String getMemoryUsed (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsed(clusterId, startTime, time, limit);
	}
	public String getMemoryUsedPercent (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsedPercent(clusterId, startTime, time, limit);
	}
	public String getPodTotal (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getPodTotal(clusterId, startTime, time, limit);
	}
	public String getPodUsed (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getPodUsed(clusterId, startTime, time, limit);
	}
	public String getPodUsedPercent (String clusterId, String startTime, String time, int limit) {
		return adapterManager.adapter().getPodUsedPercent(clusterId, startTime, time, limit);
	}
}
