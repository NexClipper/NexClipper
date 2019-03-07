package com.nexcloud.docker.container.process.domain;

import java.util.List;

public class ContainerProcess {
	private List<String> Titles;
	private List<List<String>> Processes;

	public List<String> getTitles() {
		return Titles;
	}

	public void setTitles(List<String> titles) {
		this.Titles = titles;
	}
	
	public List<List<String>> getProcesses() {
		return Processes;
	}

	public void setProcesses(List<List<String>> processes) {
		this.Processes = processes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Container Process *****\n");		
		sb.append("Titles    = "+getTitles()+"\n");
		sb.append("Processes = "+getProcesses()+"\n");
		return sb.toString();
	}
}
