package com.nexcloud.workflow.host.domain;

public class CPU {
	private Integer core;
	
	private String load1;
	
	private String load5;
	
	private String load15;
	
	private Integer cpu_total;
	
	private Double cpu_per;
	
	private Double  cpu_idle_per;
	
	private Double  cpu_irq_per;
	
	private Double  cpu_nice_per;
	
	private Double  cpu_sorfirq_per;
	
	private Double  cpu_stolen_per;
	
	private Double  cpu_sys_per;
	
	private Double  cpu_user_per;
	
	private Double  cpu_wait_per;

	public Integer getCore() {
		return core;
	}

	public void setCore(Integer core) {
		this.core = core;
	}

	public String getLoad1() {
		return load1;
	}

	public void setLoad1(String load1) {
		this.load1 = load1;
	}

	public String getLoad5() {
		return load5;
	}

	public void setLoad5(String load5) {
		this.load5 = load5;
	}

	public String getLoad15() {
		return load15;
	}

	public void setLoad15(String load15) {
		this.load15 = load15;
	}

	public Integer getCpu_total() {
		return cpu_total;
	}

	public void setCpu_total(Integer cpu_total) {
		this.cpu_total = cpu_total;
	}

	public Double getCpu_per() {
		return cpu_per;
	}

	public void setCpu_per(Double cpu_per) {
		this.cpu_per = cpu_per;
	}

	public Double getCpu_idle_per() {
		return cpu_idle_per;
	}

	public void setCpu_idle_per(Double cpu_idle_per) {
		this.cpu_idle_per = cpu_idle_per;
	}

	public Double getCpu_irq_per() {
		return cpu_irq_per;
	}

	public void setCpu_irq_per(Double cpu_irq_per) {
		this.cpu_irq_per = cpu_irq_per;
	}

	public Double getCpu_nice_per() {
		return cpu_nice_per;
	}

	public void setCpu_nice_per(Double cpu_nice_per) {
		this.cpu_nice_per = cpu_nice_per;
	}

	public Double getCpu_sorfirq_per() {
		return cpu_sorfirq_per;
	}

	public void setCpu_sorfirq_per(Double cpu_sorfirq_per) {
		this.cpu_sorfirq_per = cpu_sorfirq_per;
	}

	public Double getCpu_stolen_per() {
		return cpu_stolen_per;
	}

	public void setCpu_stolen_per(Double cpu_stolen_per) {
		this.cpu_stolen_per = cpu_stolen_per;
	}

	public Double getCpu_sys_per() {
		return cpu_sys_per;
	}

	public void setCpu_sys_per(Double cpu_sys_per) {
		this.cpu_sys_per = cpu_sys_per;
	}

	public Double getCpu_user_per() {
		return cpu_user_per;
	}

	public void setCpu_user_per(Double cpu_user_per) {
		this.cpu_user_per = cpu_user_per;
	}

	public Double getCpu_wait_per() {
		return cpu_wait_per;
	}

	public void setCpu_wait_per(Double cpu_wait_per) {
		this.cpu_wait_per = cpu_wait_per;
	}
}

