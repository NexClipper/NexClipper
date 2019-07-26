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
				Node
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
							<div class="m-portlet__head-text">Node Information</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget12" style="overflow-x: hidden; height: 300px;">
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Node Name</span> <div id="nodeNameArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Node Ip</span> <div id="nodeIpArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Creation Time</span> <div id="creationTimestampArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>UID</span> <div id="uidArea"></div>
							</div>
						</div>
						
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>SelfLink</span><br>
								<div id = "selfLinkArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Resource Version</span><br>
								<div id = "resourceVersionArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Labels</span><br>
								<div id = "labelsArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Annotations</span><br>
								<div id = "annotationsArea"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">System Information</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget12" style="height: 300px;">
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Architecture</span> <div id="architectureArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Boot ID</span> <div id="bootIdArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Container Runtime Version</span> <div id="crvArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>KernelVersion</span> <div id="kvArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Kube Proxy Version</span> <div id="kpvArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Kubelet Version</span> <div id="kuvArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Operating System</span> <div id="osArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>OS Image</span> <div id="osiArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Machine ID</span> <div id="machineIdArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>System UUID</span> <div id="systemUUIDArea"></div>
							</div>
						</div>
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
	</div>
	<div class="row">
		<div class="col-lg-12">
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
							<div class="m-portlet__head-text">Pod List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="podListArea"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script type="text/javascript">
var nodeIp = "${nodeIp}";
var NODE_NAME;
var START_TIME = "1h";
var TIME = "5s";
function timeRefresh(startTime, time) {
	START_TIME = startTime;
	TIME = time;
	getNodeCpu();
	getNodeMemory();
}
function drawNode (data) {
	var thisNode;
	data.items.forEach(function(node){
		if(node.metadata.node_ip == nodeIp)
			thisNode = node;
	})
	drawNodeInfo(thisNode);
	drawSystemInfo(thisNode);
	NODE_NAME = thisNode.metadata.name;
	getPodList(thisNode.metadata.name);
	new Picker().refreshFunction(timeRefresh).draw();
}
function drawNodeInfo(node) {
	$("#nodeNameArea").empty().text(node.metadata.name);
	$("#nodeIpArea").empty().text(node.metadata.node_ip);
	$("#creationTimestampArea").empty().text(node.metadata.creationTimestamp);
	$("#uidArea").empty().text(node.metadata.uid);
	$("#selfLinkArea").empty().text(node.metadata.selfLink);
	$("#resourceVersionArea").empty().text(node.metadata.resourceVersion);
	drawLabels (node.metadata.labels);
	drawAnnotations (node.metadata.annotations);
}
function drawLabels (labels) {
	var html = '';
	Object.keys(labels).forEach(function(key){
		html += '<span class="badge badge-secondary"> ' + textCut(key + ':' + labels[key]) + '</span><br>';
	})
	$("#labelsArea").empty().append(html);
}
function drawAnnotations (annotations) {
	var html = '';
	Object.keys(annotations).forEach(function(key){
		html += '<span class="badge badge-secondary">' + textCut(key + ':' + annotations[key])  + '</span><br>';
	})
	$("#annotationsArea").empty().append(html);
}
function drawSystemInfo(node) {
	console.log(node);
	$("#architectureArea").empty().text(node.status.nodeInfo.architecture);
	$("#bootIdArea").empty().text(node.status.nodeInfo.bootID);
	$("#crvArea").empty().text(node.status.nodeInfo.containerRuntimeVersion);
	$("#kvArea").empty().text(node.status.nodeInfo.kernelVersion);
	$("#kpvArea").empty().text(node.status.nodeInfo.kubeProxyVersion);
	$("#kuvArea").empty().text(node.status.nodeInfo.kubeletVersion);
	$("#osArea").empty().text(node.status.nodeInfo.operatingSystem);
	$("#osiArea").empty().text(node.status.nodeInfo.osImage);
	$("#machineIdArea").empty().text(node.status.nodeInfo.machineID);
	$("#systemUUIDArea").empty().text(node.status.nodeInfo.systemUUID);
}
function getNodeCpu() {
	new Client().url("/api/v1/kubernetes/node/" + NODE_NAME + "/cpu/used?startTime=" + START_TIME + "&time=" + TIME + "&limit=1000")
	.callback(drawNodeCpu).get();
}
function drawNodeCpu(data) {
	new BasicLine().area("cpuChartArea").setYAxisMax(100).colors(colors.cpuUsed).data(data, "cpuUsed", "CPU Used").draw();
}
function getNodeMemory() {
	new Client().url("/api/v1/kubernetes/node/" + NODE_NAME + "/memory/used?startTime=" + START_TIME + "&time=" + TIME + "&limit=1000")
	.callback(drawNodeMemory).get();
}
function drawNodeMemory(data) {
	new BasicLine().area("memoryChartArea").setYAxisMax(100).colors(colors.memoryUsed).data(data, "memoryUsed", "Memory Used").draw();
}
function getPodList(nodeName) {
	new Client().url("/api/v1/kubernetes/pod/snapshot").callback(drawPodList).bindData(nodeName).get();
}
function drawPodList(data, nodeName) {
	var tableData = [];
	data.items.forEach(function(item) {
		if (nodeName == item.spec.nodeName) {
			var m = {
				name : item.metadata.name,
				statuds : item.status.phase,
				hostIp : item.status.hostIP,
				namespace : item.metadata.namespace,
				kind : item.metadata.ownerReferences[0].kind,
				cpu : Number(item.resource.used_percent.cpu),
				memory : Number(item.resource.used_percent.memory),
				memory : Number(item.resource.used_percent.memory),
				status : item.status.phase
			}
			tableData.push(m);
		}
	})
	var columns = [
		{ 
			field : "name", 
			title : "Name", 
			width : 400, 
			template : function(row){
				return "<a href='/kubernetes/pod/"+row.name+"/detail'><span style='cursor:pointer;' class='font-weight-bold m--font-brand'>" + row.name + "</span></a>";
			}
		},
		{ field : "status", title : "Status", width : 100,
			template: function (row) {
				var color = "danger";
				if ("Running" == row.status)
					color = "success";
				return "<span class='m-badge m-badge--"+color+" m-badge--wide'></span>&nbsp&nbsp<strong>"+row.status+"</strong>";
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
	new MDT().area("podListArea").columns(columns).data(tableData).makeSearch().draw();
	
}
new Client().url("/api/v1/kubernetes/node/snapshot").callback(drawNode).get();
</script>