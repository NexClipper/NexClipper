package com.nexcloud.workflow.k8s.domain;

import java.util.ArrayList;
import java.util.List;

public class ContainerStatus {
	private String name;
	
	private Boolean ready;
	
	private int restartCount;
	
	private String image;
	
	private String imageID;
	
	private String containerID;
	
	private State state;
	
	private State lastState;
	
	private int readyCount;

	private int running;

	private int terminated;

	private int terminated_completed;

	private int terminated_container_cannot_run;

	private int terminated_error;

	private int terminated_oomkilled;

	private int waiting;

	private int waiting_container_creating;

	private int waiting_crash_loop_backoff;

	private int waiting_errimagepull;

	private int waiting_image_pull_backoff;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getReady() {
		return ready;
	}

	public void setReady(Boolean ready) {
		this.ready = ready;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageID() {
		return imageID;
	}

	public void setImageID(String imageID) {
		this.imageID = imageID;
	}

	public String getContainerID() {
		return containerID;
	}

	public void setContainerID(String containerID) {
		this.containerID = containerID;
	}

	public State getState() {
		if( state == null )
			state = new State();
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getLastState() {
		if( lastState == null )
			lastState = new State();
		return lastState;
	}

	public void setLastState(State lastState) {
		this.lastState = lastState;
	}

	public int getReadyCount() {
		return readyCount;
	}

	public void setReadyCount(int readyCount) {
		this.readyCount = readyCount;
	}

	public int getRunning() {
		return running;
	}

	public void setRunning(int running) {
		this.running = running;
	}

	public int getTerminated() {
		return terminated;
	}

	public void setTerminated(int terminated) {
		this.terminated = terminated;
	}

	public int getTerminated_completed() {
		return terminated_completed;
	}

	public void setTerminated_completed(int terminated_completed) {
		this.terminated_completed = terminated_completed;
	}

	public int getTerminated_container_cannot_run() {
		return terminated_container_cannot_run;
	}

	public void setTerminated_container_cannot_run(int terminated_container_cannot_run) {
		this.terminated_container_cannot_run = terminated_container_cannot_run;
	}

	public int getTerminated_error() {
		return terminated_error;
	}

	public void setTerminated_error(int terminated_error) {
		this.terminated_error = terminated_error;
	}

	public int getTerminated_oomkilled() {
		return terminated_oomkilled;
	}

	public void setTerminated_oomkilled(int terminated_oomkilled) {
		this.terminated_oomkilled = terminated_oomkilled;
	}

	public int getWaiting() {
		return waiting;
	}

	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}

	public int getWaiting_container_creating() {
		return waiting_container_creating;
	}

	public void setWaiting_container_creating(int waiting_container_creating) {
		this.waiting_container_creating = waiting_container_creating;
	}

	public int getWaiting_crash_loop_backoff() {
		return waiting_crash_loop_backoff;
	}

	public void setWaiting_crash_loop_backoff(int waiting_crash_loop_backoff) {
		this.waiting_crash_loop_backoff = waiting_crash_loop_backoff;
	}

	public int getWaiting_errimagepull() {
		return waiting_errimagepull;
	}

	public void setWaiting_errimagepull(int waiting_errimagepull) {
		this.waiting_errimagepull = waiting_errimagepull;
	}

	public int getWaiting_image_pull_backoff() {
		return waiting_image_pull_backoff;
	}

	public void setWaiting_image_pull_backoff(int waiting_image_pull_backoff) {
		this.waiting_image_pull_backoff = waiting_image_pull_backoff;
	}

	public void setRestartCount(int restartCount) {
		this.restartCount = restartCount;
	}

	public int getRestartCount() {
		return restartCount;
	}
}
