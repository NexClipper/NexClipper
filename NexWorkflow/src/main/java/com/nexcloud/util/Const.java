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
package com.nexcloud.util;
public class Const {
	
	/**
	 * Session Key
	 */
	public static final String USER_SESSION_KEY 	= "USER_INFO";
	
	/**
	 * return 코드
	 */
	public static final String FAIL					= "fail";
	
	public static final String SUCCESS 				= "success";
	
	/**
	 * Redis key
	 */
	public static final String MESOS				= "mesos";
	
	public static final String MARATHON				= "marathon";
	
	public static final String DCOS					= "dcos";
	
	public static final String INCIDENT				= "incident";
	
	public static final String ALL_INCIDENT			= "all_incident";
	
	public static final String CUSTOM_INCIDENT		= "custom_incident";
	
	public static final String ALL_CUSTOM_INCIDENT	= "all_custom_incident";
	
	public static final String AGENT				= "agent";
	
	public static final String HOST					= "host";
	
	public static final String DOCKER				= "docker";
	
	public static final String K8S					= "k8s";
	
	public static final String EMAIL				= "email";
	
	public static final String AGENT_KEY			= "agent_key";
	
	public static final String LOST_INCIDENT		= "lost_incident";
	
	public static final String CLUSTER				= "cluster";
	
	
	/**
	 * Redis field
	 */
	public static final String MASTER				= "master";
	
	public static final String TASK					="%s_%s_%s";
	
	public static final String NODES				= "nodes";
	
	public static final String TASKS				= "tasks";
	
	public static final String APPS					= "apps";
	
	public static final String ASSURANCE_NORMAL		= "normal";
	
	public static final String ASSURANCE_CUSTOM		= "custom";
	
	public static final String NODE_CPUS			= "cpus";
	
	public static final String TASK_RUN_IP			= "task_run_ip";
	
	public static final String NODE_IP_MAPPING		= "node_ip_mapping";
	
	public static final String FRAMEWORK			= "framework";
	
	public static final String SNAPSHOT				= "snapshot";
	
	public static final String LIST					= "list";
	
	public static final String SEND_EMAIL_INFO		= "send_info";
	
	public static final String NODE_LIST			= "node_list";
	
	public static final String AGENT_STATUS			= "agent_status";
	
	public static final String K8SAPI				= "k8sapi";
	
	public static final String NODE					= "node";
	
	public static final String DEPLOYMENT			= "deployment";
	
	public static final String DAEMONSET			= "daemonset";
	
	public static final String ENDPOINT				= "endpoint";
	
	public static final String NAMESPACE			= "namespace";
	
	public static final String POD					= "pod";
	
	public static final String CONTAINER			= "container";
	
	public static final String REPLICASET			= "replicaset";
	
	public static final String SECRET				= "secret";
	
	public static final String SERVICE				= "service";
	
	public static final String STATEFULSET			= "statefulset";
	
	public static final String K8SCLUSTER			= "cluster";
	
	public static final String VERSION				= "version";
	
	public static final String COMPONENT_STATUS		= "component_status";
	
	public static final String EVENT				= "event";
	
	
	/**
	 * Event Type
	 */
	public static final String TYPE_HOST			= "host";
	
	public static final String TYPE_NODE			= "node";
	
	public static final String TYPE_TASK			= "task";
	
	public static final String TYPE_FRAMEWORK		= "framework";
	
	/**
	 * Event Grade
	 */
	public static final String GRADE_CRITICAL		= "Critical";
	
	public static final String GRADE_WARNING		= "Warning";
	
	/**
	 * Toke Split
	 */
	public static final String TABLE_TOKEN			= ",";
	
	public static final String COLUMN_TOKEN			= ",";
	
	public static final String AND_TOKEN			= "and";
	
	public static final String OR_TOKEN				= "or";
	
	public static final String INEQUALITY_TOKEN		= "><=";
	
	public static final String DIVIDE_TOKEN			= "/";
	
	/**
	 * Date format
	 */
	public static final String  DATE_FORMAT			= "yyyy-MM-dd HH:mm:ss";
	
	
	/**
	 * YN flag
	 */
	public static final String SEND_Y				= "Y";
	
	public static final String SEND_N				= "N";
	
	/**
	 * Agent metric type
	 */
	public static final String MESOS_METRIC			= "mesos";
	
	public static final String MESOS_CLUSTER		= "cluster";
	
	/**
	 * Kafka TOPIC
	 */
	public static final String INCIDENT_TOPIC		= "incident_topic";
	
	public static final String HOST_TOPIC			= "host_topic";
	
	public static final String DOCKER_TOPIC			= "docker_topic";
	
	public static final String K8SAPI_TOPIC			= "k8sapi_topic";
	
	public static final String LOG_TOPIC			= "log_topic";
	
	
	/**
	 * Agent Status
	 */
	public static final String ACTIVE				= "ACTIVE";
	
	public static final String INACTIVE				= "INACTIVE";
}

