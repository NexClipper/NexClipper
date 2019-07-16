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
function Donut () {
	this.colors;
	this.data;
	this.area;
	this.event;
}
Donut.prototype.colors = function(colors){
	this.colors = [colors];
	return this;
}
Donut.prototype.data = function(data){
	this.data = data;
	return this;
}
Donut.prototype.area = function(area){
	this.area = area;
	return this;
}
Donut.prototype.event = function(event){
	this.event = event;
	return this;
}
Donut.prototype.draw = function(){
	new Morris.Donut({
		element: this.area,
		data: this.data,
		colors: this.colors,
		formatter: function(y,data) {
			return "";
		},
		resize:true
	});
}