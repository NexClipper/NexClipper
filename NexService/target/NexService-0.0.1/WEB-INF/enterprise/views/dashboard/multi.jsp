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
<div class="m-content">
	<div class="row">
		<div class="col-lg-12">
			<div class="m-subheader">
				<div class="d-flex align-items-center">
					<div class="mr-auto">
						<h3 class="m-subheader__title">
							Cluster
						</h3>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-2">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">Clusters</a></div>
								</div> 
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="javascript:;" class="btn-link m--font-metal">
											<strong id="clusterTotal">0</strong>
										</a>
									</div>	
								</div>	
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">Controller</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="javascript:;" class="btn-link m--font-metal">
											<strong id="controllerStatus">0</strong>/<strong id="controllerStatusTotal">0</strong>
										</a>
									</div>	
								</div>	
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">ETCD</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="javascript:;" class="btn-link m--font-metal">
											<strong id="etcdStatus">0</strong>/<strong id="etcdStatusTotal">0</strong>
										</a>
									</div>	
								</div>	
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">Scheduler</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="javascript:;" class="btn-link m--font-metal">
											<strong id="schedulerStatus">0</strong>/<strong id="schedulerStatusTotal">0</strong>
										</a>
									</div>	
								</div>	
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">Nodes</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="javascript:;" class="btn-link m--font-metal">
											<strong id="nodeCount">0</strong>/<strong id="nodeCountTotal">0</strong>
										</a>
									</div>	
								</div>	
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">Namespace</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="javascript:;" class="btn-link m--font-metal">
											<strong id="namespaceCount">0</strong>/<strong id="namespaceCountTotal">0</strong>
										</a>
									</div>	
								</div>	
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="m-subheader">
				<div class="d-flex align-items-center">
					<div class="mr-auto">
						<h3 class="m-subheader__title">
							Cluster List
						</h3>
					</div>
				</div>
			</div>
 			<div class="row">
				<div class="col-lg-12">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">List</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14">
								<div id="clusterListTableArea"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">Pods Top CPU (%)</a></div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="height: 200px;" id="topPodCpuChart">
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">Pods Top Memory (%)</a></div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="height: 200px;" id="topPodMemChart">
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">Node Top CPU (%)</a></div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="height: 200px;" id="topCpuChart">
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">Node Top Memory (%)</a></div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="height: 200px;" id="topMemChart">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<link href="/resources/css/billboard/billboard.min.css" rel="stylesheet" >
<style type="text/css">
.histogramLine60 line { stroke: #ffb822; stroke-width: 2; stroke-dasharray: 2,2; }
.histogramLine80 line { stroke: #f4516c; stroke-width: 2; stroke-dasharray: 2,2; }
strong { color: black; font-weight: bold; }
.titleA {color: black; }
.titleA:hover {color: black; }
</style>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script src="/resources/js/lib/billboard/d3.min.js" type="text/javascript"></script>
<script src="/resources/js/lib/billboard/billboard.min.js" type="text/javascript"></script>
<script type="text/javascript">
function drawPod(data) {
	var formatHistogramData = [];
	data.forEach(function(item){
		formatHistogramData.push({
			name : item.metadata.name,
			cpu : item.resource.used_percent.cpu,
			mem : item.resource.used_percent.memory
		});
	})
	drawHistogramChart("topPodCpuChart", formatHistogramData, "cpu");
	drawHistogramChart("topPodMemChart", formatHistogramData, "mem");
}
function drawAgents(data) {
	new Client().url("/api/v1/host/snapshot").callback(drawHost).bindData(data).get();
}

function drawHost (data, agents) {
	drawTopChart(data);
}
function drawTopChart(data) {
	var formatHistogramData = formatHistogramChartData(data);
	drawHistogramChart("topCpuChart", formatHistogramData, "cpu");
	drawHistogramChart("topMemChart", formatHistogramData, "mem");
}
function drawHistogramChart (chartArea, chartData, key){
	var categories = [];
	var key_normal = key+"_normal";
	var key_warning = key+"_warning";
	var key_danger = key+"_danger";
	var columnNormal = [key_normal];
	var columnWarning = [key_warning];
	var columnDanger = [key_danger];
	chartData.sort(function(a, b) {return b[key] - a[key];}).forEach(function(item, idx){
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
		bindto: "#" + chartArea
	});
	d3.selectAll("g.bb-chart-texts text").style("fill", "black").attr("x", "10");
}
function formatHistogramChartData (data) {
	var result = [];
	data.forEach(function(item){
		var cpuPer = 0;
		var memPer = 0;
		var diskPer = 0;
		var name = item.host_name;
		var mountName;
		cpuPer = item.cpu.cpu_per;
		memPer = item.mem.used_per;
		
		item.disks.forEach(function(disk, idx){
			if (idx == 0) {
				diskPer = disk.used_per;
				mountName = disk.mountName;
			}
			else if (diskPer < disk.used_per) {
				diskPer = disk.used_per;
				mountName = disk.mountName;
			}
		});
		diskPer = parseFloat(diskPer.toFixed(2));
		var m = {
			ip : item.host_ip, 
			name : name,
			mountName : mountName,
			cpu : cpuPer,
			mem : memPer,
			disk : diskPer
		}
		//if(item.status == "ACTIVE")
		result.push(m);
	});
	return result;
}
function drawCluster (data) {
	$("#clusterTotal").text(data.length);
	$("#controllerStatusTotal").text(data.length);
	$("#etcdStatusTotal").text(data.length);
	$("#schedulerStatusTotal").text(data.length);
	var table = drawTableInit(data);
	var hostList = [];
	var podList = [];
	data.forEach(function (cluster){
		new Client().url("/api/v1/kubernetes/cluster/snapshot").bindData(
			{ 
				cluster : cluster,
				table : table
			}
		).setClusterId(cluster.clusterId).callback(drawClusterStatus).get();

		var result = new Client().url("/api/v1/host/snapshot").setClusterId(cluster.clusterId).asyncFalse().get();
		if (typeof result != "undefined")
			hostList = hostList.concat(result);
		result = new Client().url("/api/v1/kubernetes/pod/snapshot").setClusterId(cluster.clusterId).asyncFalse().get();
		if (typeof result != "undefined")
			podList = podList.concat(result.items);
		new Client().url("/api/v1/kubernetes/component/status/snapshot").setClusterId(cluster.clusterId).callback(drawComponentStatus).get();
	})
	drawTopChart(hostList);
	drawPod(podList)
}
function drawTableInit (data) {
	var columns = [
		{ field : "clusterName", title : "Name", width : 100,
			template : function(row) {
				if (row.cpu == "-")
					return "<a href='/manager/agent/" + row.clusterId + "@" + row.clusterName + "'><span class='font-weight-bold m--font-danger'>" + row.clusterName + "</span></a>";
				else
					return "<span class='font-weight-bold m--font-brand'>" + row.clusterName + "</span>";
			}
		},
		{ field : "description", title : "Description", width : 100},
		{ field : "nodes", title : "Nodes", width : 100},
		{ field : "pods", title : "Pods", width : 100},
		{ field : "cpu", title : "CPU(Used/Total)", width : 100},
		{ field : "memory", title : "Memory(Used/Total)", width : 100},
		{ field : "cloud", title : "Cloud", width : 100}
	];
	data.forEach(function(d){
		d.description = "-";
		d.nodes = "-";
		d.pods = "-";
		d.cpu = "-";
		d.memory = "-";
		d.cloud = "-";
	})
	return new MDT().area("clusterListTableArea").columns(columns).data(data).draw();
}
function drawComponentStatus(data){
	data.items.forEach(function(item){
		if (item.metadata.name == "controller-manager") {
			item.conditions.forEach(function(condition){
				if (condition.type == "Healthy") {
					if (condition.status == "True") {
						var count = Number($("#controllerStatus").text());
						$("#controllerStatus").text(++count);
					}
				}
			})
		}
		if (item.metadata.name == "etcd-0") {
			item.conditions.forEach(function(condition){
				if (condition.type == "Healthy") {
					if (condition.status == "True") {
						var count = Number($("#etcdStatus").text());
						$("#etcdStatus").text(++count);
					}
				}
			})
		}
		if (item.metadata.name == "scheduler") {
			item.conditions.forEach(function(condition){
				if (condition.type == "Healthy") {
					if (condition.status == "True") {
						var count = Number($("#schedulerStatus").text());
						$("#schedulerStatus").text(++count);
					}
				}
			})
		}
	})
}
function drawClusterStatus (data, bindData) {
	var count = Number($("#namespaceCount").text());
	$("#namespaceCount").text(count+data.namespaces.current);
	count = Number($("#namespaceCountTotal").text());
	$("#namespaceCountTotal").text(count+data.namespaces.desired);
	
	count = Number($("#nodeCount").text());
	$("#nodeCount").text(count+data.nodes.current);
	count = Number($("#nodeCountTotal").text());
	$("#nodeCountTotal").text(count+data.nodes.desired);
	appendClusterTable(data,bindData);
}
function appendClusterTable(data,bindData) {
	tableData = bindData.table.getData();
	tableData.forEach(function (d) {
		if (d.clusterId == bindData.cluster.clusterId) {
			d.nodes = data.nodes.current + "/" + data.nodes.desired;
			d.pods = data.pods.current + "/" + data.pods.desired;
			d.cpu = data.used_cpu + "/" + data.cpu;
			d.memory = data.used_mem + "/" + data.mem;
		}
	})
	bindData.table.setData(tableData);
	bindData.table.refresh();
}


$("#controllerStatus").text(0);
$("#etcdStatus").text(0);
$("#schedulerStatus").text(0);
$("#nodeCount").text(0);
$("#namespaceCount").text(0);
$("#nodeCountTotal").text(0);
$("#namespaceCountTotal").text(0);
new Client().url("/api/v1/cluster").callback(drawCluster).getNotCluster();
</script>