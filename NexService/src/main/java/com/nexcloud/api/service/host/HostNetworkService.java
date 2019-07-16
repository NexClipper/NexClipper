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

import com.nexcloud.tsdb.adapter.manager.HostNetworkAdapterManager;
@Service
public class HostNetworkService {

	@Autowired private HostNetworkAdapterManager adapterManager;
	
	public String getNetworkRxbyte(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkRxbyte(clusterId, hostIp, interfaceId, startTime, time, limit);
	}

	public String getNetworkRxdrop(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkRxdrop(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkRxerror(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkRxerror(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkRxoverrun(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkRxoverrun(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkRxpacket(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkRxpacket(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxbyte(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxbyte(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxdrop(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxdrop(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxerror(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxerror(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxoverrun(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxoverrun(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxpacket(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxpacket(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxcarrier(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxcarrier(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxcollision(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxcollision(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkSpeed(String clusterId, String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkSpeed(clusterId, hostIp, interfaceId, startTime, time, limit);
	}
}
