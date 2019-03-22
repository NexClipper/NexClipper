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
function BasicLine () {
	this.chart;
	this.option = {
		chart: {
			type: 'line'
		},
		time: {
			useUTC: false
		},
		title: {
			text: ''
		},
		xAxis: {
			type: 'datetime',
			dateTimeLabelFormats: { second: '%Y-%m-%d %H:%M:%S' }
		},
		navigation: {
			buttonOptions: {
				enabled: false
			}
		},
		yAxis: { 
			title: {
				text: ''
			},
			min : 0,
			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
	    },
		tooltip: {
			headerFormat: '<b>{series.name}</b><br/>',
			pointFormat: '{point.x:%Y-%m-%d %H:%M:%S}<br/>{point.y:.2f}'
		},
		legend: {
			align: 'center'
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
BasicLine.prototype.option = function(option){
	this.option = option;
	return this;
}
BasicLine.prototype.colors = function(colors){
	this.option.colors = [colors];
	return this;
}
BasicLine.prototype.setYAxisMax = function(max){
	this.option.yAxis.max = max;
	return this;
}
BasicLine.prototype.appendColors = function(colors){
	this.option.colors.push(colors);
	return this;
}
BasicLine.prototype.data = function(data, key, legend){
	var d = [];
	var dataFormat = this.dateFormat;
	data.forEach(function (item) {
		d.push([dataFormat(item["time"]), item[key]]);
	})
	this.option.series = [{ type: 'line', name: legend, data: d }]
	return this;
}
BasicLine.prototype.appendData = function(data, key, legend){
	var d = [];
	var dataFormat = this.dateFormat;
	data.forEach(function (item) {
		d.push([dataFormat(item["time"]), item[key]]);
	})
	this.chart.addSeries({ type: 'line', name: legend, data: d })
	return this;
}
BasicLine.prototype.area = function(area){
	this.area = area;
	return this;
}
BasicLine.prototype.event = function(event){
	this.event = event;
	return this;
}
BasicLine.prototype.draw = function(){
	this.chart = Highcharts.chart(this.area, this.option);
	return this;
}
BasicLine.prototype.dateFormat = function(date){
	var dateTime = new Date(date.split("Z")[0]);
	var offset = new Date().getTimezoneOffset();
	dateTime.setHours(dateTime.getHours() - (offset / 60 ));
	var d = dateTime.getTime();
	return d;
}