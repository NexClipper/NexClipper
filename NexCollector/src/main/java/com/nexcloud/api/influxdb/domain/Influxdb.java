package com.nexcloud.api.influxdb.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Influxdb {
	private List<Series> series;

	public List<Series> getSeries() {
		if( series == null )
			series = new ArrayList<Series>();
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}
}

