package com.nexcloud.fullfillment.docker.domain.container;

import java.util.ArrayList;
import java.util.List;

public class ProcessTop {
	private List<List<String>> Processes;
	private List<String> Titles;
	
	public List<List<String>> getProcesses() {
		if( Processes == null )
			Processes = new ArrayList<List<String>>();
		return Processes;
	}
	
	public void setProcesses(List<List<String>> processes) {
		Processes = processes;
	}
	
	public List<String> getTitles() {
		if( Titles == null )
			Titles = new ArrayList<String>();
		return Titles;
	}
	
	public void setTitles(List<String> titles) {
		Titles = titles;
	}
}