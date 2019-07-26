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
function StatusList () {
	this.data;
	this.area;
	this.fields;
	this.event;
}
StatusList.prototype.data = function(data){
	this.data = data;
	return this;
}
StatusList.prototype.area = function(area){
	this.area = area;
	return this;
}
StatusList.prototype.fields = function(fields){
	this.fields = fields;
	return this;
}
StatusList.prototype.event = function(event){
	this.event = event;
	return this;
}
StatusList.prototype.draw = function(){
	var _this = this;
	var html = "";
	this.data.forEach(function(item){
		var color;
		if(item.status.toLowerCase() == "ACTIVE".toLowerCase()) color = "success";
		else color = "danger";
		
		html += "<div class='m-widget4__item'><div class='m-widget4__ext'>";
		html += "<span class='m-badge m-badge--"+color+" m-badge--wide'></span></div>";
		
		_this.fields.forEach (function (field){
			if (field == "status")
				html += "<div class='m-widget4__info'><strong class='m--font-bold m--font-"+color+"'>"+item[field]+"</strong></div>";
			else
				html += "<div class='m-widget4__info'><strong>"+item[field]+"</strong></div>";
		})
		
		html += "</div>";
	})
	$("#" + this.area).append(html);
}