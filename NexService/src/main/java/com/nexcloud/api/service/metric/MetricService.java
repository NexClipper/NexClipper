package com.nexcloud.api.service.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexcloud.rdb.mapper.mysql.MetricRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class MetricService{
	@Autowired private MetricRepository metricRepository;
	
	public String getMetricList(){
		return Mysql.resultToResponse(metricRepository.getMetricList());
	}
}
