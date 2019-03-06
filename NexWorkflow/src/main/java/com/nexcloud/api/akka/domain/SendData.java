package com.nexcloud.api.akka.domain;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SendData {
	private List<String> jsons;
	
	private String json;
	
	private String mysql_datasource_url;
	
	private String mysql_username;
	
	private String mysql_password;
	
	private String redis_host;
	
	private String redis_password;
	
	private String redis_port;
	
	private String kafka_zookeeper;
	
	private String kafka_host;
	
	private String kafka_port;
	
	private String kafka_topic;

	private String influxdb_endpoint;
	
	private String influxdb_datasource;
	
	private String influxdb_mesos_cluster_table;

	private String influxdb_mesos_node_table;
	
	private String influxdb_mesos_task_table;
	
	private String influxdb_mesos_task_status_table;
	
	private String influxdb_mesos_network_table;
	
	private String influxdb_disk_table;
	
	private String influxdb_http_table;
	
	private String influxdb_table;
	
	private ConsumerRecords<String, String> records; 

	public List<String> getJsons() {
		return jsons;
	}

	public void setJsons(List<String> jsons) {
		this.jsons = jsons;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getMysql_datasource_url() {
		return mysql_datasource_url;
	}

	public void setMysql_datasource_url(String mysql_datasource_url) {
		this.mysql_datasource_url = mysql_datasource_url;
	}

	public String getMysql_username() {
		return mysql_username;
	}

	public void setMysql_username(String mysql_username) {
		this.mysql_username = mysql_username;
	}

	public String getMysql_password() {
		return mysql_password;
	}

	public void setMysql_password(String mysql_password) {
		this.mysql_password = mysql_password;
	}

	public String getRedis_host() {
		return redis_host;
	}

	public void setRedis_host(String redis_host) {
		this.redis_host = redis_host;
	}

	public String getRedis_password() {
		return redis_password;
	}

	public void setRedis_password(String redis_password) {
		this.redis_password = redis_password;
	}

	public String getRedis_port() {
		return redis_port;
	}

	public void setRedis_port(String redis_port) {
		this.redis_port = redis_port;
	}

	public String getKafka_zookeeper() {
		return kafka_zookeeper;
	}

	public void setKafka_zookeeper(String kafka_zookeeper) {
		this.kafka_zookeeper = kafka_zookeeper;
	}

	public String getKafka_host() {
		return kafka_host;
	}

	public void setKafka_host(String kafka_host) {
		this.kafka_host = kafka_host;
	}

	public String getKafka_port() {
		return kafka_port;
	}

	public void setKafka_port(String kafka_port) {
		this.kafka_port = kafka_port;
	}

	public String getKafka_topic() {
		return kafka_topic;
	}

	public void setKafka_topic(String kafka_topic) {
		this.kafka_topic = kafka_topic;
	}

	public String getInfluxdb_endpoint() {
		return influxdb_endpoint;
	}

	public void setInfluxdb_endpoint(String influxdb_endpoint) {
		this.influxdb_endpoint = influxdb_endpoint;
	}

	public String getInfluxdb_datasource() {
		return influxdb_datasource;
	}

	public void setInfluxdb_datasource(String influxdb_datasource) {
		this.influxdb_datasource = influxdb_datasource;
	}

	public String getInfluxdb_mesos_cluster_table() {
		return influxdb_mesos_cluster_table;
	}

	public void setInfluxdb_mesos_cluster_table(String influxdb_mesos_cluster_table) {
		this.influxdb_mesos_cluster_table = influxdb_mesos_cluster_table;
	}

	public String getInfluxdb_mesos_node_table() {
		return influxdb_mesos_node_table;
	}

	public void setInfluxdb_mesos_node_table(String influxdb_mesos_node_table) {
		this.influxdb_mesos_node_table = influxdb_mesos_node_table;
	}

	public String getInfluxdb_mesos_task_table() {
		return influxdb_mesos_task_table;
	}

	public void setInfluxdb_mesos_task_table(String influxdb_mesos_task_table) {
		this.influxdb_mesos_task_table = influxdb_mesos_task_table;
	}

	public String getInfluxdb_mesos_task_status_table() {
		return influxdb_mesos_task_status_table;
	}

	public void setInfluxdb_mesos_task_status_table(String influxdb_mesos_task_status_table) {
		this.influxdb_mesos_task_status_table = influxdb_mesos_task_status_table;
	}

	public String getInfluxdb_mesos_network_table() {
		return influxdb_mesos_network_table;
	}

	public void setInfluxdb_mesos_network_table(String influxdb_mesos_network_table) {
		this.influxdb_mesos_network_table = influxdb_mesos_network_table;
	}

	public String getInfluxdb_disk_table() {
		return influxdb_disk_table;
	}

	public void setInfluxdb_disk_table(String influxdb_disk_table) {
		this.influxdb_disk_table = influxdb_disk_table;
	}

	public String getInfluxdb_http_table() {
		return influxdb_http_table;
	}

	public void setInfluxdb_http_table(String influxdb_http_table) {
		this.influxdb_http_table = influxdb_http_table;
	}

	public String getInfluxdb_table() {
		return influxdb_table;
	}

	public void setInfluxdb_table(String influxdb_table) {
		this.influxdb_table = influxdb_table;
	}

	public ConsumerRecords<String, String> getRecords() {
		return records;
	}

	public void setRecords(ConsumerRecords<String, String> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "SendData [jsons=" + jsons + ", json=" + json + ", mysql_datasource_url=" + mysql_datasource_url
				+ ", mysql_username=" + mysql_username + ", mysql_password=" + mysql_password + ", redis_host="
				+ redis_host + ", redis_password=" + redis_password + ", redis_port=" + redis_port
				+ ", kafka_zookeeper=" + kafka_zookeeper + ", kafka_host=" + kafka_host + ", kafka_port=" + kafka_port
				+ ", kafka_topic=" + kafka_topic + ", influxdb_endpoint=" + influxdb_endpoint + ", influxdb_datasource="
				+ influxdb_datasource + ", influxdb_mesos_cluster_table=" + influxdb_mesos_cluster_table
				+ ", influxdb_mesos_node_table=" + influxdb_mesos_node_table + ", influxdb_mesos_task_table="
				+ influxdb_mesos_task_table + ", influxdb_mesos_task_status_table=" + influxdb_mesos_task_status_table
				+ ", influxdb_mesos_network_table=" + influxdb_mesos_network_table + ", influxdb_disk_table="
				+ influxdb_disk_table + ", influxdb_http_table=" + influxdb_http_table + ", influxdb_table="
				+ influxdb_table + "]";
	}
}
