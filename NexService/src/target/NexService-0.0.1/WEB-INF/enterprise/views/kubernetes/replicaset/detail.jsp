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
				Replicaset Detail
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
								<span>Pod</span> <div id="podCountArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Namespace</span> <div id="namespaceArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Selector</span> <div id="selectorArea"></div>
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
								<span>Images</span> <div id="imagesArea"></div>
							</div>
						</div>
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
							<div class="m-portlet__head-text">Services</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
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
var replicasetName = "${replicasetName}";
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
function drawReplicaset (data) {
	var thisDaemonset;
	data.items.forEach(function(item){
		if (replicasetName == item.metadata.name)
			thisDaemonset = item;
	})
	if (typeof thisDaemonset != "undefined")
		drawDaemonsetInfo(thisDaemonset);
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
function drawDaemonsetInfo (data) {
	$("#nameArea").empty().text(data.metadata.name);
	$("#namespaceArea").empty().text(data.metadata.namespace);
	$("#podCountArea").empty().text(data.status.replicas);
	drawSelector (data.spec.selector.matchLabels);
	drawLabels (data.metadata.labels);
	drawAnnotations (data.metadata.annotations);
	$("#creationTimestampArea").empty().text(timeFormat(data.metadata.creationTimestamp));
	$("#imagesArea").empty().text(getImages (data.spec.template.spec.containers));
	getPods(data.metadata.name, data.metadata.namespace);
}
function getPods(appName, thisNs) {
	new Client().url("/api/v1/kubernetes/pod/snapshot").callback(function(data){
		var podList = [];
		data.items.forEach(function(pod){
			if (pod.metadata.namespace == thisNs && pod.metadata.generateName == appName + "-")
				podList.push(pod);
		})
		drawPodList(podList);
	}).get();
}
new Client().url("/api/v1/kubernetes/replicaset/snapshot").callback(drawReplicaset).get();
</script>