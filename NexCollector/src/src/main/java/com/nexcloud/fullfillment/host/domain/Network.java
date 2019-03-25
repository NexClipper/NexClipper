package com.nexcloud.fullfillment.host.domain;
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

