package com.nexcloud.util.consts;

public interface REDIS {

	interface HASH {
		final String HOST 			= "host";
		final String DOCKER 		= "docker";
		final String HOST_STATUS	= "host_status";
		final String AGENT_STATUS 	= "agent_status";
		final String K8S 			= "k8s";
	}
	
	interface KEY {
		final String LIST 			= "list";
		final String SECRET 		= "secret";
		final String ENDPOINT 		= "endpoint";
		final String CONTAINER 		= "container";
		final String CLUSTER 		= "cluster";
		final String VERSION 		= "version";
		final String STATEFULSET 	= "statefulset";
		final String DEPLOYMENT 	= "deployment";
		final String POD 			= "pod";
		final String SERVICE 		= "service";
		final String DAEMONSET 		= "daemonset";
		final String NODE 			= "node";
		final String NAMESPACE 		= "namespace";
		final String REPLICASET 	= "replicaset";
		final String COMPONENT_STATUS 	= "component_status";
	}
}
