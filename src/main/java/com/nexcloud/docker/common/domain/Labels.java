package com.nexcloud.docker.common.domain;

import com.google.gson.annotations.SerializedName;

public class Labels {
	@SerializedName("com.example.vendor")
	private String vendor;
	
	@SerializedName("com.example.license")
	private String license;
	
	@SerializedName("com.example.version")
	private String version;
	
	private String MESOS_TASK_ID;

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getMESOS_TASK_ID() {
		return MESOS_TASK_ID;
	}

	public void setMESOS_TASK_ID(String mESOS_TASK_ID) {
		MESOS_TASK_ID = mESOS_TASK_ID;
	}

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Labels *****\n");		
		sb.append("vendor        = " +getVendor()+"\n");
		sb.append("license       = " +getLicense()+"\n");
		sb.append("version       = " +getVersion()+"\n");
		sb.append("MESOS_TASK_ID = " +getMESOS_TASK_ID()+"\n");
		return sb.toString();
	}
}