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
	this.initData;
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
	this.initData = data;
	this.options.data.source = this.initData;
	return this;
}
MDT.prototype.event = function (event) {
	this.event = event;
	return this;
}

MDT.prototype.event = function (event) {
	this.event = event;
	return this;
}

MDT.prototype.searchEvent = function () {
	var _this = this;
	$("#" + _this.area + "SearchBtn").click(function(){
		_this.search();
	})
	$("#" + _this.area + "SearchInput").keydown(function(key){
		if(key.keyCode == 13)
			_this.search();
	})
}
MDT.prototype.search = function () {
	var _this = this;
	var searchResult = []; 
	var searchText = $("#" + _this.area + "SearchInput").val();
	_this.initData.forEach(function(item){
		var searchCheck = false;
		Object.keys(item).forEach(function(key){
			if (typeof item[key] == "string" && item[key].indexOf(searchText) != -1) searchCheck = true;
		})
		if (searchCheck) searchResult.push(item);
	})
	_this.searchData(searchResult).searchDraw();
	_this.searchEvent();
	if (typeof _this.event != "undefined")
		_this.event();
}
MDT.prototype.searchData = function (data) {
	this.options.data.source = data;
	return this;
}
MDT.prototype.searchDraw = function () {
	$("#" + this.area).mDatatable("destroy");
	this.table = $("#" + this.area).mDatatable(this.options);
}
MDT.prototype.appendData = function (data) {
	var _this = this;
	data.forEach(function(d){
		_this.options.data.source.push(d);
	})
	$("#" + this.area).mDatatable("destroy");
	this.table = $("#" + this.area).mDatatable(this.options);
	return this;
}
MDT.prototype.getData = function () {
	return this.options.data.source;
}
MDT.prototype.setData = function (data) {
	return this.options.data.source = data;
}
MDT.prototype.refresh = function () {
	$("#" + this.area).mDatatable("destroy");
	this.table = $("#" + this.area).mDatatable(this.options);
	return this;
}
MDT.prototype.makeSearch = function () {
	var searchHtml = '';
	searchHtml +='<div class="row"><div class="col-lg-8"></div><div class="col-lg-4">';
	searchHtml +='<div class="form-group m-form__group">';
	searchHtml +='	<div class="input-group">';
	searchHtml +='		<input type="text" class="form-control" placeholder="Search for..." aria-label="Search for..." aria-describedby="basic-addon2" id="' + this.area + 'SearchInput">';
	searchHtml +='		<div class="input-group-append">';
	searchHtml +='			<button class="btn btn-success" id = "' + this.area + 'SearchBtn" type="button">Search</button>';
	searchHtml +='		</div>';
	searchHtml +='	</div>';
	searchHtml +='</div>';
	searchHtml +='</div></div>';
	$("#" + this.area).append(searchHtml);
	this.searchEvent();
	return this;
}
MDT.prototype.draw = function () {
	this.event();
	$("#" + this.area).mDatatable("destroy");
	this.table = $("#" + this.area).mDatatable(this.options);
	return this;
}
