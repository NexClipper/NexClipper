package com.nexcloud.fullfillment.docker.domain.stat;

import java.util.List;

public class BlockIO {
	private List<IOService> io_service_bytes_recursive;

	public List<IOService> getIo_service_bytes_recursive() {
		return io_service_bytes_recursive;
	}

	public void setIo_service_bytes_recursive(List<IOService> io_service_bytes_recursive) {
		this.io_service_bytes_recursive = io_service_bytes_recursive;
	}
}