package com.nexcloud.fullfillment.k8s.domain;

import java.util.ArrayList;
import java.util.List;

public class Image {
	private List<String> names;
	
	private Double sizeBytes;

	public List<String> getNames() {
		if( names == null )
			names = new ArrayList<String>();
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public Double getSizeBytes() {
		return sizeBytes;
	}

	public void setSizeBytes(Double sizeBytes) {
		this.sizeBytes = sizeBytes;
	}
}
