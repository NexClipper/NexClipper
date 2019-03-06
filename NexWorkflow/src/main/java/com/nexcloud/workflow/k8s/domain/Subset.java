package com.nexcloud.workflow.k8s.domain;

import java.util.ArrayList;
import java.util.List;

public class Subset {
	private List<Address> addresses;
	
	private Address address;
	
	private List<Address> notReadyAddresses;
	
	private Address notReadyAddress;
	
	private List<Port> ports;
	
	private Port port;

	public List<Address> getAddresses() {
		if( addresses == null )
			addresses = new ArrayList<Address>();
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address getAddress() {
		if( address == null )
			address = new Address();
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Port> getPorts() {
		if( ports == null )
			ports = new ArrayList<Port>();
		return ports;
	}

	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}

	public Port getPort() {
		if( port == null )
			port = new Port();
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}

	public List<Address> getNotReadyAddresses() {
		if( notReadyAddresses == null )
			notReadyAddresses = new ArrayList<Address>();
		return notReadyAddresses;
	}

	public void setNotReadyAddresses(List<Address> notReadyAddresses) {
		this.notReadyAddresses = notReadyAddresses;
	}

	public Address getNotReadyAddress() {
		if( notReadyAddress == null )
			notReadyAddress = new Address();
		return notReadyAddress;
	}

	public void setNotReadyAddress(Address notReadyAddress) {
		this.notReadyAddress = notReadyAddress;
	}
}
