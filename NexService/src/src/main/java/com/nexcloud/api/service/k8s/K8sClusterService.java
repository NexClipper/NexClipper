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

import com.nexcloud.tsdb.adapter.manager.K8sClusterAdapterManager;

@Service
public class K8sClusterService {
	@Autowired private K8sClusterAdapterManager adapterManager;
	public String getCpuTotal (String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuTotal(startTime, time, limit);
	}
	public String getCpuUsed (String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsed(startTime, time, limit);
	}
	public String getCpuUsedPercent (String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercent(startTime, time, limit);
	}
	public String getMemoryTotal (String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryTotal(startTime, time, limit);
	}
	public String getMemoryUsed (String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsed(startTime, time, limit);
	}
	public String getMemoryUsedPercent (String startTime, String time, int limit) {
		return adapterManager.adapter().getMemoryUsedPercent(startTime, time, limit);
	}
	public String getPodTotal (String startTime, String time, int limit) {
		return adapterManager.adapter().getPodTotal(startTime, time, limit);
	}
	public String getPodUsed (String startTime, String time, int limit) {
		return adapterManager.adapter().getPodUsed(startTime, time, limit);
	}
	public String getPodUsedPercent (String startTime, String time, int limit) {
		return adapterManager.adapter().getPodUsedPercent(startTime, time, limit);
	}
}
