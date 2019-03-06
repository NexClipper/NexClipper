package com.nexcloud.workflow.host.domain;

public class SwapMemory {
	private Long free;
	
	private Long pageIn;
	
	private Long pageOut;
	
	private Long total;
	
	private Long used;
	
	private Double free_per;
	
	private Double used_per;

	public Long getFree() {
		return free;
	}

	public void setFree(Long free) {
		this.free = free;
	}

	public Long getPageIn() {
		return pageIn;
	}

	public void setPageIn(Long pageIn) {
		this.pageIn = pageIn;
	}

	public Long getPageOut() {
		return pageOut;
	}

	public void setPageOut(Long pageOut) {
		this.pageOut = pageOut;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getUsed() {
		return used;
	}

	public void setUsed(Long used) {
		this.used = used;
	}

	public Double getFree_per() {
		return free_per;
	}

	public void setFree_per(Double free_per) {
		this.free_per = free_per;
	}

	public Double getUsed_per() {
		return used_per;
	}

	public void setUsed_per(Double used_per) {
		this.used_per = used_per;
	}
}

