package com.nexcloud.workflow.host.domain;

public class Memory {
	private Long total;
	
	private Long free;
	
	private Long used;
	
	private Long actual_free;
	
	private Long actual_used;
	
	private Double free_per;
	
	private Double used_per;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getFree() {
		return free;
	}

	public void setFree(Long free) {
		this.free = free;
	}

	public Long getUsed() {
		return used;
	}

	public void setUsed(Long used) {
		this.used = used;
	}

	public Long getActual_free() {
		return actual_free;
	}

	public void setActual_free(Long actual_free) {
		this.actual_free = actual_free;
	}

	public Long getActual_used() {
		return actual_used;
	}

	public void setActual_used(Long actual_used) {
		this.actual_used = actual_used;
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

