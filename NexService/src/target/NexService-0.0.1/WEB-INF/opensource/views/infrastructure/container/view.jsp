<!-- 
  Copyright 2019 NexCloud Co.,Ltd.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<div class="m-subheader">
	<div class="d-flex align-items-center">
		<div class="mr-auto">
			<h3 class="m-subheader__title">
				Container
			</h3>
		</div>
		<span class="m-subheader__daterange" id="m_dashboard_daterangepicker">
			<span class="m-subheader__daterange-label">
				<span class="m-subheader__daterange-title"></span>
				<span class="m-subheader__daterange-date m--font-brand"></span>
			</span>
			<a href="#" class="btn btn-sm btn-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill">
				<i class="la la-angle-down"></i>
			</a>
		</span>
	</div>
</div>
<div class="m-content">
	<div class="row">
		<div class="col-lg-6">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">CPU</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="cpuChartArea" style="height: 250px;"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Memory</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="memoryChartArea" style="height: 250px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Container List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="containerListTableArea"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script type="text/javascript">
var START_TIME = "1h";
var TIME = "5s";
function timeRefresh(startTime, time) {
	START_TIME = startTime;
	TIME = time;
	drawChart(searchCheckContainer());
}
function drawChart (containerList) {
	drawInitChart("cpu", containerList, "cpu_used_percent");
	drawInitChart("memory", containerList, "mem_used_percent");
}
function drawInitChart(resource, containerList, field) {
	var containerName = containerList[0];
	if (typeof containerName == "undefined")
		return;
	new Client().url("/api/v1/host/" + containerName.split(":")[0] + "/docker/container/" + containerName.split(":")[1] + "/" + resource + "/usage?startTime="+START_TIME+"&time=" + TIME + "&limit=1000")
	.callback(
		function(data, containerName){
			var chart;
			if (resource == "memory")
				chart = new BasicLine().area(resource + "ChartArea").setYAxisMax(100).data(data, field, containerName.split(":")[2]).draw();
			else chart = new BasicLine().area(resource + "ChartArea").data(data, field, containerName.split(":")[2]).draw();			
			drawAppendChart(resource, containerList, chart, field);
		}
	).bindData(containerName).get();
}
function drawAppendChart(resource, containerList, chart, field) {
	containerList.forEach(function(container, idx){
		if (idx != 0) {
			new Client().url("/api/v1/host/" + container.split(":")[0] + "/docker/container/" + container.split(":")[1] + "/" + resource + "/usage?startTime="+START_TIME+"&time=" + TIME + "&limit=1000")
			.callback(
				function(data, containerName){
					chart.appendData(data, field, containerName);
				}
			).bindData(container.split(":")[2]).get();
		}
	});
}
function searchCheckContainer() {
	var containerList = [];
	$('input:checkbox[type=checkbox]:checked').each(function () {
		if( this.value != "on" ) {
			containerList.push(this.value);
		}
	});
	return containerList;
}
function mdtEvent() {
	$('#containerListTableArea').on('click', 'input:checkbox[type=checkbox]', function() {
		drawChart(searchCheckContainer());
	});
}
function drawContainerList (data) {
	var tableData = [];
	Object.keys(data).forEach(function(key){
		data[key].containers.forEach(function(container){
			if (container.Type == "Kubernetes") {
				if (container.Labels["io.kubernetes.docker.type"] == "container") {
					var m = {
						key : key + ":" + container.Id + ":" + container.Labels["io.kubernetes.pod.name"],
						id : container.Id,
						name : container.Labels["io.kubernetes.pod.name"],
						hostIp : key,
						image : container.Image,
						state : container.State,
						cpu : Number(container.cpuPercent),
						mem : Number(container.memPercent),
						diskRead : Number(container.block_io_read),
						diskWrite : Number(container.block_io_write),
						upTime : container.Status
					}
					tableData.push(m);
				}
			} else console.log(container);
		})
	});
	var columns = [
		{ 
			field : "key", 
			title : "#", 
			width: 50,
			sortable: false,
			selector: {class: 'm-checkbox--solid m-checkbox--brand'}
		},
		{ 
			field : "name", 
			title : "Name", 
			width : 400,
			template : function(row){
				return "<a href='/infrastructure/"+row.hostIp+"/container/"+row.id+"/detail'><span style='cursor:pointer;' class='font-weight-bold m--font-brand'>" + row.name + "</span></a>";
			}
		},
		{ field : "hostIp", title : "Host Ip", width : 100},
		{ field : "image", title : "Image", width : 100},
		{ field : "state", title : "Status", width : 100,
			template: function (row) {
				var color = "danger";
				if ("running" == row.state)
					color = "success";
				return "<span class='m-badge m-badge--"+color+" m-badge--wide'></span>&nbsp&nbsp<strong>"+row.state+"</strong>";
			}
		},
		{ field : "cpu", title : "CPU (%)", width : 100,
			template: function (row) {
				return '<div class="m-table__progress"><div class="m-table__progress-sm progress m-progress--sm"> <div class="m-table__progress-bar progress-bar bg-brand" role="progressbar" aria-valuenow="' + row.cpu + '" aria-valuemin="0" aria-valuemax="100" style="width:' + row.cpu + '%;"></div></div><span class="m-table__stats">' + row.cpu + '</span> </div>';
			}
		},
		{ field : "mem", title : "Memory (%)", width : 100,
			template: function (row) {
				return '<div class="m-table__progress"><div class="m-table__progress-sm progress m-progress--sm"> <div class="m-table__progress-bar progress-bar bg-brand" role="progressbar" aria-valuenow="' + row.mem + '" aria-valuemin="0" aria-valuemax="100" style="width:' + row.mem + '%;"></div></div><span class="m-table__stats">' + row.mem + '</span> </div>';
			}
		},
		{ field : "diskRead", title : "Disk Read", width : 100,
			template: function (row) {
				return '<div class="m-table__progress"><div class="m-table__progress-sm progress m-progress--sm"> <div class="m-table__progress-bar progress-bar bg-brand" role="progressbar" aria-valuenow="' + row.diskRead + '" aria-valuemin="0" aria-valuemax="100" style="width:' + row.diskRead + '%;"></div></div><span class="m-table__stats">' + row.diskRead + '</span> </div>';
			}
		},
		{ field : "diskWrite", title : "Disk Write", width : 100,
			template: function (row) {
				return '<div class="m-table__progress"><div class="m-table__progress-sm progress m-progress--sm"> <div class="m-table__progress-bar progress-bar bg-brand" role="progressbar" aria-valuenow="' + row.diskWrite + '" aria-valuemin="0" aria-valuemax="100" style="width:' + row.diskWrite + '%;"></div></div><span class="m-table__stats">' + row.diskWrite + '</span> </div>';
			}
		},
		{ field : "upTime", title : "Up Time", width : 100}
	];
	new MDT().area("containerListTableArea").columns(columns).data(tableData).event(mdtEvent).makeSearch().draw();
	$('input:checkbox[type=checkbox]:eq(1)').prop("checked", true);
	new Picker().refreshFunction(timeRefresh).draw();
}
new Client().url("/api/v1/host/docker/snapshot").callback(drawContainerList).get();
</script>
