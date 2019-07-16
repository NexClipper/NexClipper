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

import com.nexcloud.tsdb.adapter.HostDiskAdapter;
import com.nexcloud.tsdb.client.prometheus.PrometheusClient;

@Component
public class HostDiskPrometheusAdapter implements HostDiskAdapter {
	@Autowired private PrometheusClient prometheusClient;

	@Override
	public String getDiskFreeByte(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}

	@Override
	public String getDiskUsagePercent(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskReadbyte(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskWritebyte(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskTotal(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskUsedbyte(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskReads(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskWrites(String clusterId, String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
}
