package com.nexcloud.api.service.k8s;

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
