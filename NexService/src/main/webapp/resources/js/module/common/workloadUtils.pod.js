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
function drawPodList (data) {
	var tableData = [];
	data.forEach(function (item){
		var m = {
			key : item.metadata.name + ":" + item.metadata.namespace,
			name : item.metadata.name,
			hostIp : item.status.hostIP,
			namespace : item.metadata.namespace,
			kind : item.metadata.ownerReferences[0].kind,
			cpu : Number(item.resource.used_percent.cpu),
			memory : Number(item.resource.used_percent.memory),
			status : item.status.phase
		}
		tableData.push(m);
	})
	var columns = [
		{ 
			field : "key", 
			title : "#", 
			width: 50,
			sortable: false,
			selector: {class: 'm-checkbox--solid m-checkbox--brand'}
		},
		{ field : "status", title : "", width : 20,
			template: function (row) {
				var color = "danger";
				if ("Running" == row.status)
					color = "success";
				return "<span class='m-badge m-badge--"+color+" m-badge--wide'></span>";
			}
		},
		{ 
			field : "name", 
			title : "Name", 
			width : 400,
			template : function(row){
				return "<a href='/kubernetes/pod/"+row.name+"/detail'><span style='cursor:pointer;' class='font-weight-bold m--font-brand'>" + row.name + "</span></a>";
			}
		},
		{ field : "hostIp", title : "Host Ip", width : 100},
		{ field : "namespace", title : "Namespace", width : 100},
		{ field : "kind", title : "Kind", width : 100},
		{ field : "cpu", title : "CPU(%)", width : 100,
			template: function (row) {
				return '<div class="m-table__progress"><div class="m-table__progress-sm progress m-progress--sm"> <div class="m-table__progress-bar progress-bar bg-brand" role="progressbar" aria-valuenow="' + row.cpu + '" aria-valuemin="0" aria-valuemax="100" style="width:' + row.cpu + '%;"></div></div><span class="m-table__stats">' + row.cpu + '</span> </div>';
			}
		},
		{ field : "memory", title : "Memory(%)", width : 100,
			template: function (row) {
				return '<div class="m-table__progress"><div class="m-table__progress-sm progress m-progress--sm"> <div class="m-table__progress-bar progress-bar bg-brand" role="progressbar" aria-valuenow="' + row.memory + '" aria-valuemin="0" aria-valuemax="100" style="width:' + row.memory + '%;"></div></div><span class="m-table__stats">' + row.memory + '</span> </div>';
			}
		}
	];
	new MDT().area("podListTableArea").columns(columns).data(tableData).event(mdtEvent).makeSearch().draw();
	$('input:checkbox[type=checkbox]:eq(1)').prop("checked", true);
	new Picker().refreshFunction(timeRefresh).draw();
}
function searchCheckPod() {
	var podList = [];
	$('input:checkbox[type=checkbox]:checked').each(function () {
		if( this.value != "on" ) {
			var appNameAndNamespace = this.value;
			var appName = appNameAndNamespace.split(":")[0];
			var thisNs = appNameAndNamespace.split(":")[1];
    		podList.push(appName);
		}
	});
	return podList;
}
function mdtEvent() {
	$('#podListTableArea').on('click', 'input:checkbox[type=checkbox]', function() {
		drawChart(searchCheckPod());
	});
}