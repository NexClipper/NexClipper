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
function SimpleList () {
	this.data;
	this.area;
	this.fields;
	this.event;
}
SimpleList.prototype.data = function(data){
	this.data = data;
	return this;
}
SimpleList.prototype.area = function(area){
	this.area = area;
	return this;
}
SimpleList.prototype.fields = function(fields){
	this.fields = fields;
	return this;
}
SimpleList.prototype.event = function(event){
	this.event = event;
	return this;
}
SimpleList.prototype.drawTable = function () {
	var html = '<table class="table m-table">';
	html += '<colgroup>';
	html += this.drawCol(this.fields);
	html += '</colgroup>';
	html += '<thead><tr>';
	html += this.drawTh(this.fields);
	html += '</tr></thead>';
	html += '<tbody><tr>';
	html += this.drawTd(this.fields);
	html += '</tr></tbody>';
	html += '</table>';
	return html;
}
SimpleList.prototype.drawCol = function (fields) {
	var html = '';
	fields.forEach(function(field) {
		html += '<col>';
	})
	return html;
}
SimpleList.prototype.drawTh = function (fields) {
	var html = '';
	fields.forEach(function(field) {
		html += '<th class="text-left">' + field + '</th>';
	})
	return html;
}
SimpleList.prototype.drawTd = function (fields) {
	var html = '';
	this.data.forEach(function(item){
		html += '<tr>';
		fields.forEach (function (field){
			html += '<td class="text-left">'+item[field]+'</td>';
		})
		html += '</tr>';
		
	})
	return html;
}
SimpleList.prototype.draw = function(){
	$("#" + this.area).append(this.drawTable(this.fields));
}