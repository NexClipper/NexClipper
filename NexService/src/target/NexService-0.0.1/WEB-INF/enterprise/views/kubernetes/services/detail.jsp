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
				Services Detail
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
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Info</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget12" style="height: 300px; overflow-x: hidden;">
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Name</span> <div id="nameArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Namespace</span> <div id="namespaceArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Labels</span><br>
								<div id = "labelsArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Annotations</span> <div id="annotationsArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Creation Time</span> <div id="creationTimestampArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Selector</span> <div id="selectorArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Cluster IP</span> <div id="clusterIpArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Internal Endpoint</span> <div id="endPointArea"></div>
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
							<div class="m-portlet__head-text">End Point</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="endPointListTableArea"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
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
							<div class="m-portlet__head-text">Pods</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="podListTableArea"></div>
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
							<div class="m-portlet__head-text">Events</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script src="/resources/js/module/common/workloadUtils.namespace.js" type="text/javascript"></script>
<script src="/resources/js/module/common/workloadUtils.pod.js" type="text/javascript"></script>
<script type="text/javascript">
var namespace = "${namespace}";
var serviceName = "${serviceName}";
var START_TIME = "1h";
var TIME = "5s";
function timeRefresh(startTime, time) {
	START_TIME = startTime;
	TIME = time;
	drawChart(searchCheckPod());
}
function drawAppendChart(resource, podList, chart) {
	podList.forEach(function(pod, idx){
		if (idx != 0) {
			new Client().url("/api/v1/kubernetes/pod/" + pod + "/" + resource + "/used/percent?startTime="+START_TIME+"&time=" + TIME + "&limit=1000")
			.callback(
				function(data, podName){
					chart.appendData(data, resource + "UsedPercent", podName);
				}
			).bindData(pod).get();
		}
	});
}
function drawServices (data) {
	var thisService;
	data.items.forEach(function(item){
		if (namespace == item.metadata.namespace && serviceName == item.metadata.name)
			thisService = item;
	})
	console.log(thisService)
	getPods(thisService.metadata.name, thisService.metadata.namespace);
	drawServiceDetail (thisService);
}
function drawServiceDetail (service) {

	$("#nameArea").empty().text(service.metadata.name);
	$("#namespaceArea").empty().text(service.metadata.namespace);
	drawLabels (service.metadata.labels);
	drawAnnotations (service.metadata.annotations);
	$("#creationTimestampArea").empty().text(timeFormat(service.metadata.creationTimestamp));
	drawSelector (service.spec.selector);
	$("#clusterIpArea").empty().text(service.spec.clusterIP);
	if (typeof service.metadata.annotations != "undefined")
		drawEndPoint("endPointArea", service.metadata.annotations);
	
}
function drawEndPoint(area, annotations) {
	var publicEndpoints = JSON.parse(annotations["field.cattle.io/publicEndpoints"]);
	var configuration = JSON.parse(annotations["kubectl.kubernetes.io/last-applied-configuration"]);
	var portHtml = '';
	configuration.spec.ports.forEach(function(port){
		portHtml += '<span>' + configuration.metadata.name + '.';
		portHtml += configuration.metadata.namespace + ':';
		portHtml += port.port + " ";
		if (typeof port.protocol != "undefined")
			portHtml += port.protocol;
		else
			portHtml += "TCP";
		portHtml += '</span><br>';
	})
	publicEndpoints.forEach(function(endpoint) {
		portHtml += '<span>' + configuration.metadata.name + '.';
		portHtml += configuration.metadata.namespace + ':';
		portHtml += endpoint.port + " ";
		if (typeof endpoint.protocol != "undefined")
			portHtml += endpoint.protocol;
		else
			portHtml += "TCP";
		portHtml += '</span><br>';
	})
	$("#" + area).empty().append(portHtml);
}
function drawAnnotations (annotations) {
	if (typeof annotations == "undefined") return;
	var html = '';
	Object.keys(annotations).forEach(function(key){
		html += '<span class="badge badge-secondary"> ' + textCut(key + ':' + annotations[key]) + '</span><br>';
	})
	$("#annotationsArea").empty().append(html);
}
function getImages(data){
	var r = '';
	data.forEach(function(image){
		r += image.image + ' ';
	})
	return r;
}
function drawEndPointList(podList) {
	var tableData = [];
	podList.forEach(function (item){
		var m = {
			host : item.status.podIP,
			containers : item.spec.containers,
			nodeName : item.spec.nodeName,
			conditions : item.status.conditions
		}
		tableData.push(m);
	})
	var columns = [
		{ field : "host", title : "Host", width : 100},
		{ field : "ports", title : "Ports[Name, Port, Protocol]",
			template: function (row) {
				var html = '';
				row.containers.forEach(function(container){
					container.ports.forEach(function(port){
						console.log(port)
						var name = '';
						if (typeof port.name != "undefined")
							name = port.name;
						else
							name = port.containerPort;
						html += '<span>' + name + ', ' + port.containerPort + ', ' + port.protocol + '</span><br>';
					})
				})
				return html;
			}
		},
		{ field : "nodeName", title : "Node", width : 100},
		{ 
			field : "ready", 
			title : "Ready", 
			width : 400,
			template : function(row){
				var ready = '-';
				row.conditions.forEach(function(condition){
					if (condition.type == "Ready")
						ready = condition.status;
				})
				return ready;
			}
		}
	];
	new MDT().area("endPointListTableArea").columns(columns).data(tableData).makeSearch().draw();
}
function getPods(appName, thisNs) {
	new Client().url("/api/v1/kubernetes/pod/snapshot").callback(function(data){
		var podList = [];
		data.items.forEach(function(pod){
			if (pod.metadata.namespace == thisNs && typeof pod.metadata.labels != "undefined"  
					&& typeof pod.metadata.labels.app != "undefined" && pod.metadata.labels.app == appName)
				podList.push(pod);
			else if (pod.metadata.labels == "undefined")
				console.log(pod);
		})
		drawEndPointList(podList);
		drawPodList(podList);
	}).get();
}
new Client().url("/api/v1/kubernetes/service/snapshot").callback(drawServices).get();
</script>