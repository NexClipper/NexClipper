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

import com.nexcloud.tsdb.adapter.manager.HostDiskAdapterManager;

@Service
public class HostDiskService {
	@Autowired private HostDiskAdapterManager adapterManager;
	
	public String getDiskFreeByte(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskFreeByte(clusterId, hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskUsagePercent(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskUsagePercent(clusterId, hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskReadbyte(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskReadbyte(clusterId, hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskWritebyte(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskWritebyte(clusterId, hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskTotal(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskTotal(clusterId, hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskUsedbyte(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskUsedbyte(clusterId, hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskReads(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskReads(clusterId, hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskWrites(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskWrites(clusterId, hostIp, mountName, startTime, time, limit);
	}
}
