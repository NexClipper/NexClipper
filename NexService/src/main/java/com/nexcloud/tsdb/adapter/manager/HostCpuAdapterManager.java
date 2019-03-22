package com.nexcloud.tsdb.adapter.manager;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostCpuAdapter;
import com.nexcloud.tsdb.client.adapter.influx.HostCpuInfluxAdapter;
import com.nexcloud.tsdb.client.adapter.prometheus.HostCpuPrometheusAdapter;

@Component
public class HostCpuAdapterManager {
	@Value("${tsdb}") private String DB;
	@Autowired private HostCpuInfluxAdapter influxAdapter; 
	@Autowired private HostCpuPrometheusAdapter prometheusAdapter;

	public HostCpuAdapter adapter() {
		if (DB.equals("influx")) return influxAdapter;
		else if (DB.equals("prometheus")) return prometheusAdapter;
		else {
			System.out.println("err");
			return null;
		}
	}
}
