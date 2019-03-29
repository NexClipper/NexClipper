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
package com.nexcloud.workflow.host.domain;
public class NetInterface {
	private String name;
	
	private Long rxBytes;
	
	private Long rxDropped;
	
	private Long rxErrors;
	
	private Long rxPackets;
	
	private Long rxOverruns;
	
	private Long speed;
	
	private Long txBytes;
	
	private Long txCarrier;
	
	private Long txCollisions;
	
	private Long txDropped;
	
	private Long txErrors;
	
	private Long txOverruns;
	
	private Long txPackets;
	
	private String address;
	
	private String macAddr;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRxBytes() {
		return rxBytes;
	}

	public void setRxBytes(Long rxBytes) {
		this.rxBytes = rxBytes;
	}

	public Long getRxDropped() {
		return rxDropped;
	}

	public void setRxDropped(Long rxDropped) {
		this.rxDropped = rxDropped;
	}

	public Long getRxErrors() {
		return rxErrors;
	}

	public void setRxErrors(Long rxErrors) {
		this.rxErrors = rxErrors;
	}

	public Long getRxPackets() {
		return rxPackets;
	}

	public void setRxPackets(Long rxPackets) {
		this.rxPackets = rxPackets;
	}

	public Long getRxOverruns() {
		return rxOverruns;
	}

	public void setRxOverruns(Long rxOverruns) {
		this.rxOverruns = rxOverruns;
	}

	public Long getSpeed() {
		return speed;
	}

	public void setSpeed(Long speed) {
		this.speed = speed;
	}

	public Long getTxBytes() {
		return txBytes;
	}

	public void setTxBytes(Long txBytes) {
		this.txBytes = txBytes;
	}

	public Long getTxCarrier() {
		return txCarrier;
	}

	public void setTxCarrier(Long txCarrier) {
		this.txCarrier = txCarrier;
	}

	public Long getTxCollisions() {
		return txCollisions;
	}

	public void setTxCollisions(Long txCollisions) {
		this.txCollisions = txCollisions;
	}

	public Long getTxDropped() {
		return txDropped;
	}

	public void setTxDropped(Long txDropped) {
		this.txDropped = txDropped;
	}

	public Long getTxErrors() {
		return txErrors;
	}

	public void setTxErrors(Long txErrors) {
		this.txErrors = txErrors;
	}

	public Long getTxOverruns() {
		return txOverruns;
	}

	public void setTxOverruns(Long txOverruns) {
		this.txOverruns = txOverruns;
	}

	public Long getTxPackets() {
		return txPackets;
	}

	public void setTxPackets(Long txPackets) {
		this.txPackets = txPackets;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}
}

