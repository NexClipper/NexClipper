package com.nexcloud.docker.container.stats.domain;

public class Resource {
	private Float per_cpu;
	private Float used_cpu;
	private Float limit_cpu;
	private Float per_mem;
	private Long used_mem;
	private Long limit_mem;
	private Long timestamp;
	private String interface_name;
	private Long rx_bytes;
	private Long rx_packets;
	private Long rx_errors;
	private Long rx_dropped;
	private Long tx_bytes;
	private Long tx_packets;
	private Long tx_errors;
	private Long tx_dropped;
	private Long block_io_read;
	private Long block_io_write;
	
	public Float getPer_cpu() {
		return per_cpu;
	}

	public void setPer_cpu(Float per_cpu) {
		this.per_cpu = per_cpu;
	}

	public Float getUsed_cpu() {
		return used_cpu;
	}

	public void setUsed_cpu(Float used_cpu) {
		this.used_cpu = used_cpu;
	}

	public Float getLimit_cpu() {
		return limit_cpu;
	}

	public void setLimit_cpu(Float limit_cpu) {
		this.limit_cpu = limit_cpu;
	}

	public Float getPer_mem() {
		return per_mem;
	}

	public void setPer_mem(Float per_mem) {
		this.per_mem = per_mem;
	}

	public Long getUsed_mem() {
		return used_mem;
	}

	public void setUsed_mem(Long used_mem) {
		this.used_mem = used_mem;
	}

	public Long getLimit_mem() {
		return limit_mem;
	}

	public void setLimit_mem(Long limit_mem) {
		this.limit_mem = limit_mem;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getInterface_name() {
		return interface_name;
	}

	public void setInterface_name(String interface_name) {
		this.interface_name = interface_name;
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

	public Long getBlock_io_read() {
		return block_io_read;
	}

	public void setBlock_io_read(Long block_io_read) {
		this.block_io_read = block_io_read;
	}

	public Long getBlock_io_write() {
		return block_io_write;
	}

	public void setBlock_io_write(Long block_io_write) {
		this.block_io_write = block_io_write;
	}	
}
