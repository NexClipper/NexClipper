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
				[0.1, '#55BF3B'], // green
				[0.5, '#DDDF0D'], // yellow
				[0.9, '#DF5353'] // red
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
	this.chart = Highcharts.chart(this.area, this.option);
	return this;
}

