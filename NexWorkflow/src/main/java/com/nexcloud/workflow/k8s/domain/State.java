package com.nexcloud.workflow.k8s.domain;

public class State {
	private State running;
	
	private String message;
	
	private State waiting;
	
	private State terminated;
	
	private String startedAt;
	
	private String containerID;
	
	private Integer exitCode;
	
	private String finishedAt;
	
	private String reason;
	
	private Integer signal;
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public State getTerminated() {
		if( terminated == null )
			terminated = new State();
		return terminated;
	}

	public void setTerminated(State terminated) {
		this.terminated = terminated;
	}

	public State getRunning() {
		if( running == null )
			running = new State();
		return running;
	}

	public void setRunning(State running) {
		this.running = running;
	}

	public String getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}

	public State getWaiting() {
		if( waiting == null )
			waiting = new State();
		return waiting;
	}

	public void setWaiting(State waiting) {
		this.waiting = waiting;
	}

	public String getContainerID() {
		return containerID;
	}

	public void setContainerID(String containerID) {
		this.containerID = containerID;
	}

	public Integer getExitCode() {
		return exitCode;
	}

	public void setExitCode(Integer exitCode) {
		this.exitCode = exitCode;
	}

	public String getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(String finishedAt) {
		this.finishedAt = finishedAt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getSignal() {
		return signal;
	}

	public void setSignal(Integer signal) {
		this.signal = signal;
	}
}
