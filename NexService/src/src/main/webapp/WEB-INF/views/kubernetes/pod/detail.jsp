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
				Pod Detail
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
							<div class="m-portlet__head-text">Title</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget12" style="height: 270px;">
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Pod Name</span> <div id="podNameArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Namespace</span> <div id="namespaceArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Creation Time</span> <div id="creationTimestampArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Status</span> <div id="statusArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>QoS Class</span> <div id="qosClassArea"></div>
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
							<div class="m-portlet__head-text">Containers</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget12" style="height: 270px;">
						<div  id = "containerArea">
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Commands</span> <div id=""> - </div>
							</div>
							<div class="m-widget12__text2">
								<span>Args</span> <div id="">-</div>
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
							<div class="m-portlet__head-text">Cpu</div>
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
</div>
<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script src="/resources/js/module/common/colors.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script type="text/javascript">
var podName = "${podName}";
function drawPod (data) {
	var thisPod;
	data.items.forEach(function(pod){
		if (podName == pod.metadata.name)
			thisPod = pod;
	})
	drawPodTitle(thisPod);
	drawContainers(thisPod);
}
function drawPodTitle (pod) {
	$("#podNameArea").empty().text(pod.metadata.name);
	$("#namespaceArea").empty().text(pod.metadata.namespace);
	$("#creationTimestampArea").empty().text(pod.metadata.creationTimestamp);
	$("#statusArea").empty().text(pod.status.phase);
	$("#qosClassArea").empty().text(pod.status.qosClass);
	drawLabels (pod.metadata.labels);
	drawAnnotations (pod.status.annotations);
}
function drawContainers (pod) {
	var html = '';
	pod.spec.containers.forEach(function(container){
		html += '<div class="m-widget12__item"><div class="m-widget12__text1">';
			html += '<span>Image</span> <div>' + container.image + '</div>';
		html += '</div>';
		html += '<div class="m-widget12__text2">';
			html += '<span>Name</span> <div>' + container.name + '</div>';
		html += '</div></div>';
	})
	$("#containerArea").empty().append(html);
}
function drawPodCpu (data) {
	new BasicLine().area("cpuChartArea").colors(colors.cpuUsed).data(data, "cpuUsedPercent", podName).draw();
}
function drawPodMemory (data) {
	new BasicLine().area("memoryChartArea").setYAxisMax(100).colors(colors.memoryUsed).data(data, "memoryUsedPercent", podName).draw();
}
new Client().url("/api/v1/kubernetes/pod/snapshot").callback(drawPod).get();

new Picker().refreshFunction(timeRefresh).draw();
function timeRefresh (startTime, time) {
	new Client().url("/api/v1/kubernetes/pod/" + podName + "/cpu/used/percent?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(drawPodCpu).get();
	new Client().url("/api/v1/kubernetes/pod/" + podName + "/memory/used/percent?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(drawPodMemory).get();
}
function drawLabels (labels) {
	var html = '';
	Object.keys(labels).forEach(function(key){
		html += '<span class="badge badge-secondary"> ' + textCut(key + ':' + labels[key]) + '</span><br>';
	})
	$("#labelsArea").empty().append(html);
}
function drawAnnotations (annotations) {
	if (typeof annotations == "undefined") return;
	var html = '';
	annotations.forEach(function(item){
		html += '<span class="badge badge-secondary">' + textCut(item) + '</span><br>';
	})
	$("#annotationsArea").empty().append(html);
}
</script>