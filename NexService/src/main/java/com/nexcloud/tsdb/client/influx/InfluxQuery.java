package com.nexcloud.tsdb.client.influx;
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
