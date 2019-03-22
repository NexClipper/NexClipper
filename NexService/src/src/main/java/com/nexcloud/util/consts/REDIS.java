package com.nexcloud.util.consts;
/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
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
