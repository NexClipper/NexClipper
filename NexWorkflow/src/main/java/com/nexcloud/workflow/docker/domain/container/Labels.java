package com.nexcloud.workflow.docker.domain.container;

import com.google.gson.annotations.SerializedName;

public class Labels {
	private String MESOS_TASK_ID;
	
	@SerializedName("io.kubernetes.container.name")
	private String kubernetes_container_name;
	
	@SerializedName("io.kubernetes.docker.type")
	private String kubernetes_docker_type;
	
	@SerializedName("io.kubernetes.pod.name")
	private String kubernetes_pod_name;
	
	@SerializedName("io.kubernetes.pod.namespace")
	private String kubernetes_pod_namespace;

	public String getMESOS_TASK_ID() {
		return MESOS_TASK_ID;
	}

	public void setMESOS_TASK_ID(String mESOS_TASK_ID) {
		MESOS_TASK_ID = mESOS_TASK_ID;
	}

	public String getKubernetes_container_name() {
		return kubernetes_container_name;
	}

	public void setKubernetes_container_name(String kubernetes_container_name) {
		this.kubernetes_container_name = kubernetes_container_name;
	}

	public String getKubernetes_docker_type() {
		return kubernetes_docker_type;
	}

	public void setKubernetes_docker_type(String kubernetes_docker_type) {
		this.kubernetes_docker_type = kubernetes_docker_type;
	}

	public String getKubernetes_pod_name() {
		return kubernetes_pod_name;
	}

	public void setKubernetes_pod_name(String kubernetes_pod_name) {
		this.kubernetes_pod_name = kubernetes_pod_name;
	}

	public String getKubernetes_pod_namespace() {
		return kubernetes_pod_namespace;
	}

	public void setKubernetes_pod_namespace(String kubernetes_pod_namespace) {
		this.kubernetes_pod_namespace = kubernetes_pod_namespace;
	}
}