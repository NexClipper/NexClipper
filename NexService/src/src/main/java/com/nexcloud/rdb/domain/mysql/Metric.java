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
package com.nexcloud.rdb.domain.mysql;
import java.sql.Timestamp;

public class Metric {
	private int idx;
	private String targetSystem;
	private String target;
	private String dataSource;
	private String table;
	private String metric;
	private String alias;
	private String math;
	private String groupBy;
	private String newEngine;
	private Timestamp regdt;
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
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
	public String getNewEngine() {
		return newEngine;
	}
	public void setNewEngine(String newEngine) {
		this.newEngine = newEngine;
	}
	public Timestamp getRegdt() {
		return regdt;
	}
	public void setRegdt(Timestamp regdt) {
		this.regdt = regdt;
	}
	public Metric(int idx, String targetSystem, String target, String dataSource, String table, String metric,
			String alias, String math, String groupBy, String newEngine, Timestamp regdt) {
		super();
		this.idx = idx;
		this.targetSystem = targetSystem;
		this.target = target;
		this.dataSource = dataSource;
		this.table = table;
		this.metric = metric;
		this.alias = alias;
		this.math = math;
		this.groupBy = groupBy;
		this.newEngine = newEngine;
		this.regdt = regdt;
	}
}
