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
function SimpleLine () {
	this.chart;
	this.option = {
		colors: [],
		chart: {
			type: 'line'
		},
		title: {
			text: null
		},
		xAxis: {
			type: null,
			lineWidth: 0,
			labels: {
				enabled: false
			}
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		yAxis: {
			max : 100,
			gridLineWidth: 0,
			title: {
				text: null
			},
			labels: {
				enabled: false
			}
		},
		tooltip: {
			enabled: false
		},
		legend: {
			enabled: false
		},
		plotOptions: {
			line: {
				animation: false
			}
		},
		series: []
	};
	this.area;
	this.event;
}
SimpleLine.prototype.option = function(option){
	this.option = option;
	return this;
}
SimpleLine.prototype.colors = function(colors){
	this.option.colors = [colors];
	return this;
}
SimpleLine.prototype.appendColors = function(colors){
	this.option.colors.push(colors);
	return this;
}
SimpleLine.prototype.data = function(data, key){
	var d = [];
	data.forEach(function (item) {
		d.push([item["time"], item[key]]);
	})
	this.option.series = [{
		name: 'Temperatures',
		data: d
	}]
	return this;
}
SimpleLine.prototype.appendData = function(data, key){
	var d = [];
	data.forEach(function (item) {
		d.push([item["time"], item[key]]);
	})
	this.chart.addSeries({
		data: d
	})
	return this;
}
SimpleLine.prototype.area = function(area){
	this.area = area;
	return this;
}
SimpleLine.prototype.event = function(event){
	this.event = event;
	return this;
}
SimpleLine.prototype.draw = function(){
	this.chart = Highcharts.chart(this.area, this.option);
	return this;
}