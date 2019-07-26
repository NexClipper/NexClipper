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
// https://jsfiddle.net/0n2Lt1m7/4/
function SolidGauge () {
	this.chart;
	this.area;
	this.event;
	this.option = {
		chart: {
			type: 'solidgauge',
		},
		title: null,
		pane: {
			center: ['50%', '50%'],
			size: '100%',
			startAngle: -110,
			endAngle: 110,
			background: {
				innerRadius: '90%',
				outerRadius: '100%',
				shape: 'arc'
			}
		},
		tooltip: {
			valueSuffix: ''
		},
		yAxis: {
			min: 0,
			max: 100,
			stops: [
				[0.1, '#00c5dc'], // green
				[0.4, '#ffb822'], // yellow
				[0.7, '#f4516c'] // red
			],
			lineWidth: 0,
			minorTickInterval: null,
			tickAmount: 2,
			title: {
				y: -70
			},
			labels: {
				y: 16
			}
		},
		plotOptions: {
			solidgauge: {
				dataLabels: {
					y: 5,
					borderWidth: 0,
					useHTML: true
				}
			}
		},credits: {
			enabled: false
		},
		exporting: {
			enabled: false    
		},
		series: []
	}
}
SolidGauge.prototype.option = function(option){
	this.option = option;
	return this;
}
SolidGauge.prototype.colors = function(colors){
	this.option.colors = [colors];
	return this;
}
SolidGauge.prototype.data = function(data, title){
	this.option.series = [{
		name: title,
		data: [Number(data.toFixed(0))],
		dataLabels: {
			format: '<div style="text-align:center"><span style="font-size:25px;color:black">{y}</span><br/>' +
			'<span style="font-size:12px;color:silver">' + title + '</span></div>'
		}
	}]
	return this;
}
SolidGauge.prototype.area = function(area){
	this.area = area;
	return this;
}
SolidGauge.prototype.event = function(event){
	this.event = event;
	return this;
}
SolidGauge.prototype.draw = function(){
	$("#" + this.area).empty();
	this.chart = Highcharts.chart(this.area, this.option);
	return this;
}

