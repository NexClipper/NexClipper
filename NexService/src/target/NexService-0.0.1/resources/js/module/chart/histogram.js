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
function Histogram () {
	this.data;
	this.key;
	this.area;
	this.event;
}
Histogram.prototype.data = function(data, key){
	this.data = data;
	this.key = key;
	return this;
}
Histogram.prototype.area = function(area){
	this.area = area;
	return this;
}
Histogram.prototype.event = function(event){
	this.event = event;
	return this;
}
Histogram.prototype.draw = function(){
	var categories = [];
	var key_normal = this.key+"_normal";
	var key_warning = this.key+"_warning";
	var key_danger = this.key+"_danger";
	var columnNormal = [key_normal];
	var columnWarning = [key_warning];
	var columnDanger = [key_danger];
	var ipList = [];
	this.data.sort(function(a, b) {return b[this.key] - a[this.key];}).forEach(function(item, idx){
		if (idx < 5) {
			if (item[key] <60) {
				columnNormal.push(item[key]);
				columnWarning.push(0);
				columnDanger.push(0);
			} else if(item[key] < 80){
				columnNormal.push(0);
				columnWarning.push(item[key]);
				columnDanger.push(0);
			} else if (item[key] >= 80){
				columnNormal.push(0);
				columnWarning.push(0);
				columnDanger.push(item[key]);
			}
				if (key == "disk")
					categories.push(item.name + "\n[" + item.mountName + "]");
				else
					categories.push(item.name);
			ipList.push(item.ip);
		}
	})
	bb.generate({
		axis: {
			rotated: true,
			x: {
				show : false
			},
			y : {
				max : 90,
				min : 10
			}
		},
		data: {
			columns: [columnNormal,columnWarning,columnDanger],
			types: {
				[key_normal]: "bar",
				[key_warning]: "bar",
				[key_danger]: "bar"
			},
			groups: [
				[
					key_normal,key_warning,key_danger
				]
			],
			labels : {
				format : function (value, xx, idx) {
					if (value != 0)
						return categories[idx];
					else 
						return null;
				}
			},
			onclick : function(d,x,w,r){
				fnHostDetail(ipList[d.index]);
			}
		},
		grid : {
			y : {
				show : false,
				lines : [
					{value : 60, class : "histogramLine60"},
					{value : 80, class : "histogramLine80"}
				]
			}
		},
		bar: {
			radius: {
				ratio: 0.5
			}
		},
		color: {
			pattern: [
				"#00c5dc","#ffb822","#f4516c"
			]
		},
		legend : {
			show : false
		},
		tooltip: {
			grouped: false
		},
		bindto: "#" + this.area
	});
	d3.selectAll("g.bb-chart-texts text")[0].forEach(function(t){
		if (t.innerHTML != "")
			d3.select(t).style("fill", "black").attr("x", "10");
	});
}