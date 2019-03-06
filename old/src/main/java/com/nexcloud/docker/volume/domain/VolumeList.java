package com.nexcloud.docker.volume.domain;

import java.util.ArrayList;

public class VolumeList {
	ArrayList<Volume> Volumes;

	public ArrayList<Volume> getVolumes() {
		return Volumes;
	}

	public void setVolumes(ArrayList<Volume> volumes) {
		Volumes = volumes;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Options *****\n");
		sb.append("Volumes     = " + getVolumes() + "\n");
		return sb.toString();
	}
}
