package com.nexcloud.util;

/**
 * Created by Kim Bumsu on 2016-02-09.
 */
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
	
	
	/**
	 * Redis field
	 */
	public static final String MASTER				= "master";
	
	public static final String NODE					= "node";
		
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
	
	public static final String DEPLOYMENT			= "deployment";
	
	public static final String SNAPSHOT				= "snapshot";
	
	public static final String LIST					= "list";
	
	public static final String NODE_LIST			= "node_list";
	
	public static final String AGENT_STATUS			= "agent_status";
	
	public static final String K8SAPI				= "k8sapi";
	
	
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
	
	
	/**
	 * Elasticsearch Range Timestamp format
	 */
	public static final String EPOCH_MILLIS			= "epoch_millis";
	
	
	/**
	 * HTTP Response Code
	 */
	public static final int OK						= 200;
	
	public static final int CREATED					= 201;
	
	public static final int ACCEPTED				= 202;

	public static final int NO_CONTENT				= 204;
	
	public static final int MOVED_PERMANENTLY		= 301;
	
	public static final int SEE_OTHER				= 303;
	
	public static final int NOT_MODIFIED			= 304;
	
	public static final int TEMPORARY_REDIRECT		= 307;
		
	public static final int BAD_REQUEST				= 400;
	
	public static final int UNAUTHORIZED			= 401;
	
	public static final int FORBIDDEN				= 403;
	
	public static final int NOT_FOUND				= 404;
	
	public static final int NOT_ACCEPTABLE			= 406;
	
	public static final int CONFLICT				= 409;
	
	public static final int GONE					= 410;
	
	public static final int PRECONDITION_FAILED		= 412;
	
	public static final int UNSUPPORTED_MEDIA_TYPE	= 415;
	
	public static final int INTERNAL_SERVER_ERROR	= 500;
	
	public static final int SERVICE_UNAVAILABLE		= 503;
	
}

