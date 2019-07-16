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
function getServicesColumns (workload, checkColumn) {
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
			} else
				r = '-';
			return r;
		}
	});
	result.push({ field : "clusterIP", title : "clusterIP", width : 100});

	result.push(	{ 
		field : "ports", 
		title : "Internal Endpoint", 
		width : 200,
		template : function(row){
			var ports = row.ports;
			var r = '';
			if (typeof ports != "undefined") {
				ports.forEach(function(port){
					r += '<span class="badge badge-secondary">' + row.name + '.' + row.namespace + ':' + port.port + '</span>'
				})
			}
			if (typeof row.publicEndpoints != "undefined") {
				var publicEndpoints = JSON.parse(row.publicEndpoints);
				publicEndpoints.forEach(function(port){
					r += '<span class="badge badge-secondary">' + row.name + '.' + row.namespace + ':' + port.port + '</span>'
				})
			}
			return r;
		}
	});
	result.push({ field : "creationTimestamp", title : "Creation Timestamp", width : 150});
	return result;
}