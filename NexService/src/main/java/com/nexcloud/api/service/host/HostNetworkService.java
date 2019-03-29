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
	
	public String getNetworkRxbyte(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkRxbyte(hostIp, interfaceId, startTime, time, limit);
	}

	public String getNetworkRxdrop(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkRxdrop(hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkRxerror(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkRxerror(hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkRxoverrun(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkRxoverrun(hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkRxpacket(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkRxpacket(hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxbyte(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxbyte(hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxdrop(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxdrop(hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxerror(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxerror(hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxoverrun(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxoverrun(hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxpacket(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxpacket(hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxcarrier(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxcarrier(hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkTxcollision(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkTxcollision(hostIp, interfaceId, startTime, time, limit);
	}
	
	public String getNetworkSpeed(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return adapterManager.adapter().getNetworkSpeed(hostIp, interfaceId, startTime, time, limit);
	}
}
