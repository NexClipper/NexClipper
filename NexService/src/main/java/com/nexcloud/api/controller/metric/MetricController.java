package com.nexcloud.api.controller.metric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nexcloud.api.service.metric.MetricService;
@RestController
@RequestMapping(value = "/api/v1/metric")
public class MetricController {
	static final Logger logger = LoggerFactory.getLogger(MetricController.class);

	@Autowired private MetricService metricService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getMetric() {
		return metricService.getMetricList();
	}
}
