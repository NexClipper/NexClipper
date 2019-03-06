function BasicLine () {
	this.chart;
	this.option = {
		chart: {
			type: 'line'
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
			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
	    },
		tooltip: {
			formatter: function() {
				return '<b>'+ this.series.name +'</b><br/>'+
				Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
				Highcharts.numberFormat(this.y, 2);
        	}
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
	return dateTime.getTime();
}