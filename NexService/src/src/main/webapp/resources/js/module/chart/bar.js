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
function Bar () {
	this.chart;
	this.option = {
		chart: {
			type: 'column'
		},
		title: {
			text: ''
		},
		subtitle: {
			text: ''
		},
		xAxis: {
			type: 'category'
		},
		yAxis: {
			title: {
				text: ''
			}
		},
		legend: {
			enabled: false
		},
		plotOptions: {
		series: {
			borderWidth: 0,
				dataLabels: {
					enabled: true,
					format: '{point.y:.1f}%'
				}
			}
		},
		
		tooltip: {
			headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
			pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
		},
		series : [],
		drilldown : {}
	};
	this.area;
	this.event;
}
Bar.prototype.option = function(option){
	this.option = option;
	return this;
}
Bar.prototype.data = function(data){
	this.option.series = data.series;
	this.option.drilldown = data.drilldown;
	return this;
}
Bar.prototype.appendData = function(data, key, legend){
	var d = [];
	var dataFormat = this.dateFormat;
	data.forEach(function (item) {
		d.push([dataFormat(item["time"]), item[key]]);
	})
	this.chart.addSeries({ type: 'line', name: legend, data: d })
	return this;
}
Bar.prototype.area = function(area){
	this.area = area;
	return this;
}
Bar.prototype.event = function(event){
	this.event = event;
	return this;
}
Bar.prototype.draw = function(){
	this.chart = Highcharts.chart(this.area, this.option);
	return this;
}
