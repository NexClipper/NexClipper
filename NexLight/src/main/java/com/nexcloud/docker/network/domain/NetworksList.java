package com.nexcloud.docker.network.domain;

import java.util.ArrayList;

public class NetworksList {

	private ArrayList<Network> networks;

	public ArrayList<Network> getNetworks() {
		return networks;
	}

	public void setNetworks(ArrayList<Network> networks) {
		this.networks = networks;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** List Networks *****\n");
		sb.append("Networks  = " + getNetworks() + "\n");
		return sb.toString();
	}
}
