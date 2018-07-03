package com.nexcloud.docker.container.stats.domain;

import java.util.ArrayList;
import java.util.List;

public class History {
	private List<Resource> allocates;
	
	private List<Resource> useds;
	

	public List<Resource> getAllocates() {
		if( allocates == null )
			allocates = new ArrayList<Resource>();
		return allocates;
	}

	public void setAllocates(List<Resource> allocates) {
		this.allocates = allocates;
	}

	public List<Resource> getUseds() {
		if( useds == null )
			useds = new ArrayList<Resource>();
		
		return useds;
	}

	public void setUseds(List<Resource> useds) {
		this.useds = useds;
	}
}