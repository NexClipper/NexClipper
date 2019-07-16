package com.nexcloud.rdb.domain.mysql;

public class Cluster {
	private int clusterId;
	private String clusterName;
	private String description;
	private char clusterStatus;
	public Cluster() {
		// TODO Auto-generated constructor stub
	}
	public int getClusterId() {
		return clusterId;
	}
	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public char getClusterStatus() {
		return clusterStatus;
	}
	public void setClusterStatus(char clusterStatus) {
		this.clusterStatus = clusterStatus;
	}
	public Cluster(int clusterId, String clusterName, String description, char clusterStatus) {
		super();
		this.clusterId = clusterId;
		this.clusterName = clusterName;
		this.description = description;
		this.clusterStatus = clusterStatus;
	}
}
