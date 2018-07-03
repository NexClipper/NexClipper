package com.nexcloud.docker.container.stats.domain;

public class NetworkStats {
	private String ifname;
	private Long rx_bytes;
	private Long rx_packets;
	private Long rx_errors;
	private Long rx_dropped;
	private Long tx_bytes;
	private Long tx_packets;
	private Long tx_errors;
	private Long tx_dropped;

	public String getIfname() {
		return ifname;
	}

	public void setIfname(String ifname) {
		this.ifname = ifname;
	}

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

	@Override 
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append("***** NetworkStats *****\n");
		sb.append("rx_bytes   = "+getRx_bytes()+"\n");
		sb.append("rx_packets = "+getRx_packets()+"\n");
		sb.append("rx_errors  = "+getRx_errors()+"\n");
		sb.append("rx_dropped = "+getRx_dropped()+"\n");
		sb.append("tx_bytes   = "+getTx_bytes()+"\n");
		sb.append("tx_packets = "+getTx_packets()+"\n");
		sb.append("tx_errors  = "+getTx_errors()+"\n");
		sb.append("tx_dropped = "+getTx_dropped()+"\n");		
		return sb.toString();
	}
}