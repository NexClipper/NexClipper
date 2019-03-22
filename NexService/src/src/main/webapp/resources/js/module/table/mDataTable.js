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
function MDT () {
	this.area;
	this.table;
	this.options = {
		data : {
			type : 'local',
			source : [],
			pageSize : 5
		},
		layout : {
			theme : "default",
			"class" : "",
			scroll : false,
			footer : false
		},
		sortable: true,
		pagination: true,
		toolbar: {
			items: {
				// pagination
				pagination: {
					// page size select
					pageSizeSelect: [5,10]
				}
			}
		},
		columns : []
	};
	this.event;
}
MDT.prototype.area = function (area) {
	this.area = area;
	return this;
}
MDT.prototype.pageSize = function (pageSize) {
	this.options.data.pageSize = pageSize;
	return this;
}
MDT.prototype.columns = function (columns) {
	var c = [];
	columns.forEach(function(column){
		var m = {
			textAlign : 'center'
		};
		Object.keys(column).forEach(function(key){
			m[key] = column[key];
		})
		c.push(m)
	})
	this.options.columns = c;
	return this;
}
MDT.prototype.data = function (data) {
	this.options.data.source = data;
	return this;
}
MDT.prototype.event = function (event) {
	this.event = event;
	return this;
}
MDT.prototype.draw = function () {
	this.event();
	this.table = $("#" + this.area).mDatatable(this.options);
}