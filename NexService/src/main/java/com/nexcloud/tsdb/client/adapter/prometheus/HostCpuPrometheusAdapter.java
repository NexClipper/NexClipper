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

import com.nexcloud.tsdb.adapter.HostCpuAdapter;
import com.nexcloud.tsdb.client.prometheus.PrometheusClient;

@Component
public class HostCpuPrometheusAdapter implements HostCpuAdapter {
	@Autowired private PrometheusClient prometheusClient;

	@Override
	public String getCpuUsedPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return prometheusClient.get("container_cpu_usage_seconds_total");
	}

	@Override
	public String getCpuIdlePercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return prometheusClient.get("container_cpu_system_seconds_total");
	}

	@Override
	public String getCpuIrqPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return prometheusClient.get("container_cpu_usage_seconds_total");
	}

	@Override
	public String getCpuNicePercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		return prometheusClient.get("container_cpu_user_seconds_total");
	}

	@Override
	public String getCpuSorfirqPercentByHostIp(String clusterId, String hostIp, String startTime, String time,
			int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuStolenPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuSysPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuWaitPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCpuTotalCoreByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCpuLoad1ByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCpuLoad5ByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCpuLoad15ByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
//	PrometheusClient prometheusClient = new PrometheusClient();

	@Override
	public String getCpuUserPercentByHostIp(String clusterId, String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
}
