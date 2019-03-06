package com.nexcloud.fullfillment.k8s.domain;

public class Volume {
	private String name;
	
	private String mountPath;
	
	private Boolean readOnly;
	
	private Path hostPath;
	
	private Path secret;
	
	private Configmap configMap;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Path getHostPath() {
		if( hostPath == null )
			hostPath = new Path();
		
		return hostPath;
	}

	public void setHostPath(Path hostPath) {
		this.hostPath = hostPath;
	}

	public Path getSecret() {
		if( secret == null )
			secret = new Path();
		return secret;
	}

	public void setSecret(Path secret) {
		this.secret = secret;
	}

	public String getMountPath() {
		return mountPath;
	}

	public void setMountPath(String mountPath) {
		this.mountPath = mountPath;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Configmap getConfigMap() {
		if( configMap == null )
			configMap = new Configmap();
		return configMap;
	}

	public void setConfigMap(Configmap configMap) {
		this.configMap = configMap;
	}
}
