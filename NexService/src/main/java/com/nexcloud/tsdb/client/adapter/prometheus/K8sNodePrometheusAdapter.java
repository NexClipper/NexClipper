package com.nexcloud.tsdb.client.adapter.prometheus;
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
import org.springframework.stereotype.Component;
import com.nexcloud.tsdb.adapter.K8sNodeAdapter;
import com.nexcloud.tsdb.client.prometheus.PrometheusClient;

@Component
public class K8sNodePrometheusAdapter implements K8sNodeAdapter {
	@Autowired private PrometheusClient prometheusClient;

	@Override
	public String getCpuUsed(String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuUsedPercent(String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuAllocate(String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuTotal(String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMemoryUsed(String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMemoryUsedPercent(String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMemoryAllocate(String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMemoryTotal(String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPodAllocate(String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPodTotal(String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuUsed(String node, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuUsedPercent(String node, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuAllocate(String node, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuTotal(String node, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMemoryUsed(String node, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMemoryUsedPercent(String node, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMemoryAllocate(String node, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMemoryTotal(String node, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPodAllocate(String node, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPodTotal(String node, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
}
