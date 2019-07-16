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
package com.nexcloud.tsdb.client.adapter.prometheus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostDockerAdapter;
import com.nexcloud.tsdb.client.prometheus.PrometheusClient;

@Component
public class HostDockerPrometheusAdapter implements HostDockerAdapter {
	@Autowired private PrometheusClient prometheusClient;

	@Override
	public String getDockerCpuUsedByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}

	@Override
	public String getDockerCpuUsedByTaskId(String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerMemoryAllocateByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerMemoryUsedByteByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}

	@Override
	public String getDockerMemoryUsedByteByTaskId(String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerMemoryUsedPercentByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}

	@Override
	public String getDockerMemoryUsedPercentByTaskId(String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerDiskioReadByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}

	@Override
	public String getDockerDiskioReadByTaskId(String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerDiskioWriteByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}

	@Override
	public String getDockerDiskioWriteByTaskId(String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkRxbyteByContainerId (String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkRxdropByContainerId (String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkRxerrorByContainerId (String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkRxpacketByContainerId (String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkTxbyteByContainerId (String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkTxdropByContainerId (String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkTxerrorByContainerId (String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkTxpacketByContainerId (String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkRxbyteByTaskId (String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkRxdropByTaskId (String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkRxerrorByTaskId (String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkRxpacketByTaskId (String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkTxbyteByTaskId (String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkTxdropByTaskId (String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkTxerrorByTaskId (String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDockerNetworkTxpacketByTaskId (String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return null;
	}
}
