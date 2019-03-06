package com.nexcloud.rdb.domain.mysql;

import java.sql.Timestamp;

public class Rule {
	private int idx;
	private String targetSystem;
	private String target;
	private String severity;
	private String type;
	private String scaleType;
	private String dataSource;
	private String table;
	private String metric;
	private String math;
	private String groupBy;
	private String condition;
	private String message;
	private String status;
	private String newEngine;
	private String notify;
	private String slackToken;
	private String slackChannel;
	private Timestamp regdt;
	public Rule() {
		// TODO Auto-generated constructor stub
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTargetSystem() {
		return targetSystem;
	}
	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getScaleType() {
		return scaleType;
	}
	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getMetric() {
		return metric;
	}
	public void setMetric(String metric) {
		this.metric = metric;
	}
	public String getMath() {
		return math;
	}
	public void setMath(String math) {
		this.math = math;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNewEngine() {
		return newEngine;
	}
	public void setNewEngine(String newEngine) {
		this.newEngine = newEngine;
	}
	public String getNotify() {
		return notify;
	}
	public void setNotify(String notify) {
		this.notify = notify;
	}
	public String getSlackToken() {
		return slackToken;
	}
	public void setSlackToken(String slackToken) {
		this.slackToken = slackToken;
	}
	public String getSlackChannel() {
		return slackChannel;
	}
	public void setSlackChannel(String slackChannel) {
		this.slackChannel = slackChannel;
	}
	
	public Timestamp getRegdt() {
		return regdt;
	}
	public void setRegdt(Timestamp regdt) {
		this.regdt = regdt;
	}
	public Rule(int idx, String targetSystem, String target, String severity, String type, String scaleType,
			String dataSource, String table, String metric, String math, String groupBy, String condition,
			String message, String status, String newEngine, String notify, String slackToken, String slackChannel,
			Timestamp regdt) {
		super();
		this.idx = idx;
		this.targetSystem = targetSystem;
		this.target = target;
		this.severity = severity;
		this.type = type;
		this.scaleType = scaleType;
		this.dataSource = dataSource;
		this.table = table;
		this.metric = metric;
		this.math = math;
		this.groupBy = groupBy;
		this.condition = condition;
		this.message = message;
		this.status = status;
		this.newEngine = newEngine;
		this.notify = notify;
		this.slackToken = slackToken;
		this.slackChannel = slackChannel;
		this.regdt = regdt;
	}
}
