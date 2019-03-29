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
	
	private String timeseries_db;
	
	private String pushgateway_endpoint;
	
	private String broker;
	
	private String rabbitmq_host;
	
	private String rabbitmq_port;
	
	public String getRabbitmq_host() {
		return rabbitmq_host;
	}

	public void setRabbitmq_host(String rabbitmq_host) {
		this.rabbitmq_host = rabbitmq_host;
	}

	public String getRabbitmq_port() {
		return rabbitmq_port;
	}

	public void setRabbitmq_port(String rabbitmq_port) {
		this.rabbitmq_port = rabbitmq_port;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

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

	public String getTimeseries_db() {
		return timeseries_db;
	}

	public void setTimeseries_db(String timeseries_db) {
		this.timeseries_db = timeseries_db;
	}

	public String getPushgateway_endpoint() {
		return pushgateway_endpoint;
	}

	public void setPushgateway_endpoint(String pushgateway_endpoint) {
		this.pushgateway_endpoint = pushgateway_endpoint;
	}

	public ConsumerRecords<String, String> getRecords() {
		return records;
	}

	public void setRecords(ConsumerRecords<String, String> records) {
		this.records = records;
	}
}
