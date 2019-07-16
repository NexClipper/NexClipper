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
function Namespace () {
	this.area;
	this.thisNs;
	this.thisWorkload;
}

Namespace.prototype.area = function(area) {
	this.area = area;
	return this;
}
Namespace.prototype.thisNs = function(thisNs) {
	/*console.log(thisNs);
	if (thisNs == "all")
		this.thisNs = getCookie("thisNs");
	else {
		this.thisNs = thisNs;
	}
	document.cookie = "thisNs=" + thisNs;*/
	this.thisNs = thisNs;
	return this;
}
Namespace.prototype.thisWorkload = function(thisWorkload) {
	this.thisWorkload = thisWorkload;
	return this;
}
Namespace.prototype.draw = function() {
	var _this = this;
	new Client().url("/api/v1/kubernetes/namespace/snapshot").callback(function(data){
		var r = '<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">' + _this.thisNs + '</button>';
		r += '<div class="dropdown-menu">';
		r += '<a class="dropdown-item" href="/kubernetes/' + _this.thisWorkload + '" style = "color: #ffffff;">All</a>';
		data.items.forEach(function(item){
			r += '<a class="dropdown-item" href="/kubernetes/' + _this.thisWorkload + '?namespace=' + item.metadata.name + '" style = "color: #ffffff;">' + item.metadata.name + '</a>';
		})
		r += '</div>';
		$("#" + _this.area).empty().append(r);
	}).get();
}

function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
function getPodListByNamespace(arr, ns) {
	var result = [];
	if (ns == "all") result = arr;
	else {
		arr.forEach(function(item){
			if (item.metadata.namespace == ns)
				result.push(item);
		}) 
	}
	return result;
}
function getArrayByNamespace (arr, ns) {
	var result = [];
	if (ns == "all") result = arr.items;
	else {
		arr.items.forEach(function(item){
			if (item.metadata.namespace == ns)
				result.push(item);
		}) 
	}
	return result;
}
function getColumns (workload, checkColumn) {
	var result = [];
	if (checkColumn == "check")
		result.push({ 
			field : "key", 
			title : "#", 
			width: 50,
			sortable: false,
			selector: {class: 'm-checkbox--solid m-checkbox--brand m-optionbox'}
		});
	result.push(	{ field : "status", title : "", width : 20,
		template: function (row) {
			var color = "danger";
			if ("Running" == row.status)
				color = "success";
			return "<span class='m-badge m-badge--"+color+" m-badge--wide'></span>";
		}
	});
	result.push({ 
		field : "name", 
		title : "Name", 
		width : 300,
		template : function(row){
			return "<a href='/kubernetes/"+workload+"/"+row.name+"/detail?namespace=" + row.namespace + "'><span style='cursor:pointer;' class='font-weight-bold m--font-brand'>" + row.name + "</span></a>";
		}
	});
	result.push(	{ 
		field : "label", 
		title : "Label", 
		width : 300,
		template : function(row){
			var labels = row.label;
			var r = '';
			if (typeof labels != "undefined") {
				Object.keys(labels).forEach(function(key){
					r += '<span class="badge badge-secondary">' + key + ':' + labels[key] + '</span>'
				})	
			} else {
					r += '<span class="badge badge-secondary">-</span>'
			}
			return r;
		}
	});
	result.push({ field : "pod", title : "Pod", width : 50});
	result.push({ field : "creationTimestamp", title : "Creation Timestamp", width : 150});
	result.push({ 
		field : "image", 
		title : "Image", 
		width : 300,
		template : function(row){
			var images = row.image;
			var r = '';
			images.forEach(function(image){
				r += image.image + '<br>';
			})
			return r;
		}
	});
	return result;
}
function drawChart (podList) {
	drawInitChart("cpu", podList);
	drawInitChart("memory", podList);
}
function drawInitChart(resource, podList) {
	var podName = podList[0];
	new Client().url("/api/v1/kubernetes/pod/" + podName + "/" + resource + "/used/percent?startTime="+START_TIME+"&time=" + TIME + "&limit=1000")
	.callback(
		function(data, podName){
			
			var chart;
			if (resource == "memory") chart = new BasicLine().area(resource + "ChartArea").setYAxisMax(100).data(data, resource + "UsedPercent", podName).draw();
			else chart = new BasicLine().area(resource + "ChartArea").data(data, resource + "UsedPercent", podName).draw();
			drawAppendChart(resource, podList, chart);
		}
	).bindData(podName).get();
}

function drawLabels (labels) {
	if (typeof labels == "undefined") return;
	var html = '';
	Object.keys(labels).forEach(function(key){
		html += '<span class="badge badge-secondary"> ' + textCut(key + ':' + labels[key]) + '</span><br>';
	})
	$("#labelsArea").empty().append(html);
}
function drawSelector (labels) {
	if (typeof labels == "undefined") return;
	var html = '';
	Object.keys(labels).forEach(function(key){
		html += '<span class="badge badge-secondary"> ' + textCut(key + ':' + labels[key]) + '</span><br>';
	})
	$("#selectorArea").empty().append(html);
}

