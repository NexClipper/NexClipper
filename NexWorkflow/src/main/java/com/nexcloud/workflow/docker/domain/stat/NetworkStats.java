package com.nexcloud.workflow.docker.domain.stat;
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

public class NetworkStats {
	
	private Long rx_bytes;
	private Long rx_packets;
	private Long rx_errors;
	private Long rx_dropped;
	private Long tx_bytes;
	private Long tx_packets;
	private Long tx_errors;
	private Long tx_dropped;

	public Long getRx_bytes() {
		return rx_bytes;
	}

	public void setRx_bytes(Long rx_bytes) {
		this.rx_bytes = rx_bytes;
	}

	public Long getRx_packets() {
		return rx_packets;
	}

	public void setRx_packets(Long rx_packets) {
		this.rx_packets = rx_packets;
	}

	public Long getRx_errors() {
		return rx_errors;
	}

	public void setRx_errors(Long rx_errors) {
		this.rx_errors = rx_errors;
	}

	public Long getRx_dropped() {
		return rx_dropped;
	}

	public void setRx_dropped(Long rx_dropped) {
		this.rx_dropped = rx_dropped;
	}

	public Long getTx_bytes() {
		return tx_bytes;
	}

	public void setTx_bytes(Long tx_bytes) {
		this.tx_bytes = tx_bytes;
	}

	public Long getTx_packets() {
		return tx_packets;
	}

	public void setTx_packets(Long tx_packets) {
		this.tx_packets = tx_packets;
	}

	public Long getTx_errors() {
		return tx_errors;
	}

	public void setTx_errors(Long tx_errors) {
		this.tx_errors = tx_errors;
	}

	public Long getTx_dropped() {
		return tx_dropped;
	}

	public void setTx_dropped(Long tx_dropped) {
		this.tx_dropped = tx_dropped;
	}
}
