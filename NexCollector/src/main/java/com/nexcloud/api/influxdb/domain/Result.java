package com.nexcloud.api.influxdb.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
	private List<Influxdb> results;

	public List<Influxdb> getResults() {
		if( results == null )
			results = new ArrayList<Influxdb>();
		return results;
	}

	public void setResults(List<Influxdb> results) {
		this.results = results;
	}
}

