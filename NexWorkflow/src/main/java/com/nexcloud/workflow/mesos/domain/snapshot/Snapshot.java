package com.nexcloud.workflow.mesos.domain.snapshot;

import com.google.gson.annotations.SerializedName;

public class Snapshot {
	@SerializedName("master/tasks_error")
	private Double masterQtasks_error;
	
	@SerializedName("master/tasks_failed")
	private Double masterQtasks_failed;
	
	@SerializedName("master/tasks_finished")
	private Double masterQtasks_finished;
	
	@SerializedName("master/tasks_killed")
	private Double masterQtasks_killed;
	
	@SerializedName("master/tasks_lost")
	private Double masterQtasks_lost;
	
	@SerializedName("master/slave_registrations")
	private Double masterQslave_registrations;
	
	@SerializedName("master/slave_removals")
	private Double masterQslave_removals;
	
	@SerializedName("master/slave_reregistrations")
	private Double masterQslave_reregistrations;
	
	@SerializedName("master/slave_unreachable_scheduled")
	private Double masterQslave_unreachable_scheduled;
	
	@SerializedName("master/slave_unreachable_canceled")
	private Double masterQslave_unreachable_canceled;
	
	@SerializedName("master/slave_unreachable_completed")
	private Double masterQslave_unreachable_completed;
	
	@SerializedName("master/slaves_inactive")
	private Double masterQslaves_inactive;
	
	@SerializedName("master/slaves_disconnected")
	private Double masterQslaves_disconnected;
	
	@SerializedName("master/slaves_connected")
	private Double masterQslaves_connected;
	
	@SerializedName("master/slaves_active")
	private Double masterQslaves_active;
	
	@SerializedName("system/cpus_total")
	private Double systemQcpus_total;
	
	@SerializedName("system/mem_total_bytes")
	private Double systemQmem_total_bytes;
	
	@SerializedName("system/mem_free_bytes")
	private Double systemQmem_free_bytes;
	
	@SerializedName("system/load_15min")
	private Double systemQload_15min;
	
	@SerializedName("system/load_5min")
	private Double systemQload_5min;
	
	@SerializedName("system/load_1min")
	private Double systemQload_1min;
	
	@SerializedName("master/uptime_secs")
	private Double masterQuptime_secs;
	
	@SerializedName("master/tasks_killing")
	private Double masterQtasks_killing;
	
	@SerializedName("master/tasks_running")
	private Double masterQtasks_running;
	
	@SerializedName("master/tasks_staging")
	private Double masterQtasks_staging;
	
	@SerializedName("master/tasks_starting")
	private Double masterQtasks_starting;
	
	@SerializedName("master/tasks_unreachable")
	private Double masterQtasks_unreachable;

	public Double getMasterQtasks_error() {
		return masterQtasks_error;
	}

	public void setMasterQtasks_error(Double masterQtasks_error) {
		this.masterQtasks_error = masterQtasks_error;
	}

	public Double getMasterQtasks_failed() {
		return masterQtasks_failed;
	}

	public void setMasterQtasks_failed(Double masterQtasks_failed) {
		this.masterQtasks_failed = masterQtasks_failed;
	}

	public Double getMasterQtasks_finished() {
		return masterQtasks_finished;
	}

	public void setMasterQtasks_finished(Double masterQtasks_finished) {
		this.masterQtasks_finished = masterQtasks_finished;
	}

	public Double getMasterQtasks_killed() {
		return masterQtasks_killed;
	}

	public void setMasterQtasks_killed(Double masterQtasks_killed) {
		this.masterQtasks_killed = masterQtasks_killed;
	}

	public Double getMasterQtasks_lost() {
		return masterQtasks_lost;
	}

	public void setMasterQtasks_lost(Double masterQtasks_lost) {
		this.masterQtasks_lost = masterQtasks_lost;
	}

	public Double getMasterQslave_registrations() {
		return masterQslave_registrations;
	}

	public void setMasterQslave_registrations(Double masterQslave_registrations) {
		this.masterQslave_registrations = masterQslave_registrations;
	}

	public Double getMasterQslave_removals() {
		return masterQslave_removals;
	}

	public void setMasterQslave_removals(Double masterQslave_removals) {
		this.masterQslave_removals = masterQslave_removals;
	}

	public Double getMasterQslave_reregistrations() {
		return masterQslave_reregistrations;
	}

	public void setMasterQslave_reregistrations(Double masterQslave_reregistrations) {
		this.masterQslave_reregistrations = masterQslave_reregistrations;
	}

	public Double getMasterQslave_unreachable_scheduled() {
		return masterQslave_unreachable_scheduled;
	}

	public void setMasterQslave_unreachable_scheduled(Double masterQslave_unreachable_scheduled) {
		this.masterQslave_unreachable_scheduled = masterQslave_unreachable_scheduled;
	}

	public Double getMasterQslave_unreachable_canceled() {
		return masterQslave_unreachable_canceled;
	}

	public void setMasterQslave_unreachable_canceled(Double masterQslave_unreachable_canceled) {
		this.masterQslave_unreachable_canceled = masterQslave_unreachable_canceled;
	}

	public Double getMasterQslave_unreachable_completed() {
		return masterQslave_unreachable_completed;
	}

	public void setMasterQslave_unreachable_completed(Double masterQslave_unreachable_completed) {
		this.masterQslave_unreachable_completed = masterQslave_unreachable_completed;
	}

	public Double getMasterQslaves_inactive() {
		return masterQslaves_inactive;
	}

	public void setMasterQslaves_inactive(Double masterQslaves_inactive) {
		this.masterQslaves_inactive = masterQslaves_inactive;
	}

	public Double getMasterQslaves_disconnected() {
		return masterQslaves_disconnected;
	}

	public void setMasterQslaves_disconnected(Double masterQslaves_disconnected) {
		this.masterQslaves_disconnected = masterQslaves_disconnected;
	}

	public Double getMasterQslaves_connected() {
		return masterQslaves_connected;
	}

	public void setMasterQslaves_connected(Double masterQslaves_connected) {
		this.masterQslaves_connected = masterQslaves_connected;
	}

	public Double getMasterQslaves_active() {
		return masterQslaves_active;
	}

	public void setMasterQslaves_active(Double masterQslaves_active) {
		this.masterQslaves_active = masterQslaves_active;
	}

	public Double getSystemQcpus_total() {
		return systemQcpus_total;
	}

	public void setSystemQcpus_total(Double systemQcpus_total) {
		this.systemQcpus_total = systemQcpus_total;
	}

	public Double getSystemQmem_total_bytes() {
		return systemQmem_total_bytes;
	}

	public void setSystemQmem_total_bytes(Double systemQmem_total_bytes) {
		this.systemQmem_total_bytes = systemQmem_total_bytes;
	}

	public Double getSystemQmem_free_bytes() {
		return systemQmem_free_bytes;
	}

	public void setSystemQmem_free_bytes(Double systemQmem_free_bytes) {
		this.systemQmem_free_bytes = systemQmem_free_bytes;
	}

	public Double getSystemQload_15min() {
		return systemQload_15min;
	}

	public void setSystemQload_15min(Double systemQload_15min) {
		this.systemQload_15min = systemQload_15min;
	}

	public Double getSystemQload_5min() {
		return systemQload_5min;
	}

	public void setSystemQload_5min(Double systemQload_5min) {
		this.systemQload_5min = systemQload_5min;
	}

	public Double getSystemQload_1min() {
		return systemQload_1min;
	}

	public void setSystemQload_1min(Double systemQload_1min) {
		this.systemQload_1min = systemQload_1min;
	}

	public Double getMasterQuptime_secs() {
		return masterQuptime_secs;
	}

	public void setMasterQuptime_secs(Double masterQuptime_secs) {
		this.masterQuptime_secs = masterQuptime_secs;
	}

	public Double getMasterQtasks_killing() {
		return masterQtasks_killing;
	}

	public void setMasterQtasks_killing(Double masterQtasks_killing) {
		this.masterQtasks_killing = masterQtasks_killing;
	}

	public Double getMasterQtasks_running() {
		return masterQtasks_running;
	}

	public void setMasterQtasks_running(Double masterQtasks_running) {
		this.masterQtasks_running = masterQtasks_running;
	}

	public Double getMasterQtasks_staging() {
		return masterQtasks_staging;
	}

	public void setMasterQtasks_staging(Double masterQtasks_staging) {
		this.masterQtasks_staging = masterQtasks_staging;
	}

	public Double getMasterQtasks_starting() {
		return masterQtasks_starting;
	}

	public void setMasterQtasks_starting(Double masterQtasks_starting) {
		this.masterQtasks_starting = masterQtasks_starting;
	}

	public Double getMasterQtasks_unreachable() {
		return masterQtasks_unreachable;
	}

	public void setMasterQtasks_unreachable(Double masterQtasks_unreachable) {
		this.masterQtasks_unreachable = masterQtasks_unreachable;
	}

	@Override
	public String toString() {
		return "Snapshot [masterQtasks_error=" + masterQtasks_error + ", masterQtasks_failed=" + masterQtasks_failed
				+ ", masterQtasks_finished=" + masterQtasks_finished + ", masterQtasks_killed=" + masterQtasks_killed
				+ ", masterQtasks_lost=" + masterQtasks_lost + ", masterQslave_registrations="
				+ masterQslave_registrations + ", masterQslave_removals=" + masterQslave_removals
				+ ", masterQslave_reregistrations=" + masterQslave_reregistrations
				+ ", masterQslave_unreachable_scheduled=" + masterQslave_unreachable_scheduled
				+ ", masterQslave_unreachable_canceled=" + masterQslave_unreachable_canceled
				+ ", masterQslave_unreachable_completed=" + masterQslave_unreachable_completed
				+ ", masterQslaves_inactive=" + masterQslaves_inactive + ", masterQslaves_disconnected="
				+ masterQslaves_disconnected + ", masterQslaves_connected=" + masterQslaves_connected
				+ ", masterQslaves_active=" + masterQslaves_active + ", systemQcpus_total=" + systemQcpus_total
				+ ", systemQmem_total_bytes=" + systemQmem_total_bytes + ", systemQmem_free_bytes="
				+ systemQmem_free_bytes + ", systemQload_15min=" + systemQload_15min + ", systemQload_5min="
				+ systemQload_5min + ", systemQload_1min=" + systemQload_1min + ", masterQuptime_secs="
				+ masterQuptime_secs + ", masterQtasks_killing=" + masterQtasks_killing + ", masterQtasks_running="
				+ masterQtasks_running + ", masterQtasks_staging=" + masterQtasks_staging + ", masterQtasks_starting="
				+ masterQtasks_starting + ", masterQtasks_unreachable=" + masterQtasks_unreachable + "]";
	}
}
