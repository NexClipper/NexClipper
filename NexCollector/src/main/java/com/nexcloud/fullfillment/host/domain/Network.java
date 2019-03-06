package com.nexcloud.fullfillment.host.domain;

import java.util.ArrayList;
import java.util.List;

public class Network {
	private Integer inbound;
	
	private Integer outbound;
	
	private List<NetInterface> ifaces;

	public Integer getInbound() {
		return inbound;
	}

	public void setInbound(Integer inbound) {
		this.inbound = inbound;
	}

	public Integer getOutbound() {
		return outbound;
	}

	public void setOutbound(Integer outbound) {
		this.outbound = outbound;
	}

	public List<NetInterface> getIfaces() {
		if( ifaces == null )
			ifaces = new ArrayList<NetInterface>();
		return ifaces;
	}

	public void setIfaces(List<NetInterface> ifaces) {
		this.ifaces = ifaces;
	}
}

