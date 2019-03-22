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
function Tilemap() {
	this.area;
	this.titleX = '';
	this.titleY = '';
	this.data;
	this.option = {
		chart: {
			zoomType: 'x',
			animation: false,
			type:'area',
		},
		title: {
			text: ''
		},
		subtitle: {
			text: ''
		},
		navigation: {
			buttonOptions: {
		    	enabled: false
			}
		},
		xAxis: {
			min:0,
			max:100,
			title: {
				text: this.titleX
			},
			plotLines: [{
				color: 'red',
				dashStyle: 'dot',
				width: 2,
				value: 80,
				zIndex: 3
			}]
		},
		yAxis: { 
			min:0,
			title: {
				text: this.titleY
			},
			plotLines: [{
				color: 'red',
				dashStyle: 'dot',
				width: 2,
				value: 80,
				zIndex: 3
			}]
		},
		tooltip: {
			formatter: function() {
		    	return eventTaskMap.get(this.x+"_"+this.y) +'</b><br/>';
			}
		},
		legend: false,
		colors: ['#f4516c'],
		series: []
	}
}
Tilemap.prototype.option = function(option){
	this.option = option;
	return this;
}
Tilemap.prototype.area = function(area){
	this.area = area;
	return this;
}
Tilemap.prototype.event = function(event){
	this.event = event;
	return this;
}
Tilemap.prototype.data = function(data){
	this.data = data;
	return this;
}
Tilemap.prototype.draw = function(){
	this.chart = Highcharts.chart(this.area, this.option);
	return this;
}