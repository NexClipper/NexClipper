package com.nexcloud.docker.container.inspect.domain;

public class State {
	private String Status;
	private Boolean Running;
	private Boolean Paused;
	private Boolean Restarting;
	private Boolean OOMKilled;
	private Boolean Dead;
	private Integer Pid;
	private Integer ExitCode;
	private String Error;
	private String StartedAt;
	private String FinishedAt;

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Boolean getRunning() {
		return Running;
	}

	public void setRunning(Boolean running) {
		Running = running;
	}

	public Boolean getPaused() {
		return Paused;
	}

	public void setPaused(Boolean paused) {
		Paused = paused;
	}

	public Boolean getRestarting() {
		return Restarting;
	}

	public void setRestaring(Boolean restarting) {
		Restarting = restarting;
	}

	public Boolean getOOMKilled() {
		return OOMKilled;
	}

	public void setOOMKilled(Boolean oOMKilled) {
		OOMKilled = oOMKilled;
	}

	public Boolean getDead() {
		return Dead;
	}

	public void setDead(Boolean dead) {
		Dead = dead;
	}

	public Integer getPid() {
		return Pid;
	}

	public void setPid(Integer pid) {
		Pid = pid;
	}

	public Integer getExitCode() {
		return ExitCode;
	}

	public void setExitCode(Integer exitCode) {
		ExitCode = exitCode;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}

	public String getStartedAt() {
		return StartedAt;
	}

	public void setStartedAt(String startedAt) {
		StartedAt = startedAt;
	}

	public String getFinishedAt() {
		return FinishedAt;
	}

	public void setFinishedAt(String finishedAt) {
		FinishedAt = finishedAt;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** State *****\n");
		sb.append("Status     = " + getStatus() + "\n");
		sb.append("Running    = " + getRunning() + "\n");
		sb.append("Paused     = " + getPaused() + "\n");
		sb.append("Restarting = " + getRestarting() + "\n");
		sb.append("OOMKilled  = " + getOOMKilled() + "\n");
		sb.append("Dead       = " + getDead() + "\n");
		sb.append("Pid        = " + getPid() + "\n");
		sb.append("ExitCode   = " + getExitCode() + "\n");
		sb.append("Error      = " + getError() + "\n");
		sb.append("StartedAt  = " + getStartedAt() + "\n");
		sb.append("FinishedAt = " + getFinishedAt() + "\n");
		return sb.toString();
	}
}