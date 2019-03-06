package com.nexcloud.tsdb.client.influx;

public class InfluxQuery {
	private StringBuilder queryStr = new StringBuilder();
	
	public InfluxQuery() {
	}
	public InfluxQuery Select (String firstFieldStr, String alias) {
		this.queryStr.delete(0, this.queryStr.length()).append("SELECT " + firstFieldStr + " AS " + alias);
		return this;
	}
	public InfluxQuery Field(String fieldStr, String alias) {
		this.queryStr.append( ", " + fieldStr + " AS " + alias);
		return this;
	}
	public InfluxQuery From(String tableName) {
		this.queryStr.append( " FROM \"" + tableName + "\"");
		return this;
	}
	public InfluxQuery Where(String fieldStr) {
		this.queryStr.append( " WHERE " + fieldStr);
		return this;
	}
	public InfluxQuery Where(String field, String value) {
		this.queryStr.append( " WHERE " + field + "\'" + value + "\'");
		return this;
	}
	public InfluxQuery And(String fieldStr) {
		this.queryStr.append( " AND " + fieldStr);
		return this;
	}
	public InfluxQuery And(String field, String value) {
		this.queryStr.append( " AND " + field + "\'" + value + "\'");
		return this;
	}
	public InfluxQuery GroupBy(String groupByStr) {
		this.queryStr.append( " GROUP By " + groupByStr);
		return this;
	}
	public InfluxQuery OrderBy(String orderByStr) {
		this.queryStr.append( " ORDER By " + orderByStr);
		return this;
	}
	public InfluxQuery Limit(int limit) {
		this.queryStr.append( " LIMIT " + limit);
		return this;
	}
	public String Build() {
		return this.queryStr.toString();
	}
}
