function Scatter () {
	this.chart;
	this.area;
	this.event;
	this.option = {
		chart: {
			type: 'scatter',
			zoomType: 'xy'
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
		yAxis: {
			min:0,
			max:100,
			title: {
				text:'CPU'
			},
			plotLines: [{
				color: 'red',
				dashStyle: 'dot',
				width: 2,
				value: 80,
				zIndex: 3
			}]
		},
		xAxis: { 
			min:0,
			max:100,
			title: {
				text:'Memory'
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
			headerFormat: '<b>{series.name}</b><br>',
			pointFormat: 'Cpu {point.y}% <br> Memory {point.x}%'
		},
		legend: false,
		colors: ['#f4516c'],
		series: []
	}
}
Scatter.prototype.option = function(option){
	this.option = option;
	return this;
}
Scatter.prototype.area = function(area){
	this.area = area;
	return this;
}
Scatter.prototype.event = function(event){
	this.event = event;
	return this;
}
Scatter.prototype.data = function(data){
	this.option.series = data;
	return this;
}
Scatter.prototype.draw = function(){
	this.chart = Highcharts.chart(this.area, this.option);
	return this;
}