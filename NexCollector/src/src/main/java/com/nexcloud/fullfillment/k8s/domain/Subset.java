package com.nexcloud.fullfillment.k8s.domain;
/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

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
