package com.nexcloud.rdb.mapper.mysql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexcloud.rdb.domain.mysql.Metric;

@Repository("MetricMapper")
public interface MetricRepository {
	public List<Metric> getMetricList();
}
