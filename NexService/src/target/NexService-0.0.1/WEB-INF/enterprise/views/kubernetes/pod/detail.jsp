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
					<div class="m-widget12" style="height: 270px; overflow-x: hidden;">
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
								<span>Node Name</span> <div id="nodeNameArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Host Ip</span> <div id="hostIpArea"></div>
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
	</div>
	<div class="row">
		<div class="col-lg-12">
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
					<div class="m-widget14">
						<div id="containerListTableArea" style="height: 270px; overflow-x: hidden;"></div>
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
							<div class="m-portlet__head-text">Status</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id="statusListArea" style="height: 200px;"></div>
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
							<div class="m-portlet__head-text">Statefulset</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="statefulsetListTableArea" style="height: 250px;"></div>
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
					<div id="eventListArea" style="height: 250px; overflow-x: hidden;"></div>
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
							<div class="m-portlet__head-text">volumes</div>
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
<script src="/resources/js/module/list/simpleList.js" type="text/javascript"></script>
<script src="/resources/js/module/common/workloadUtils.namespace.js" type="text/javascript"></script>
<script type="text/javascript">
var podName = "${podName}";
function drawPod (data) {
	var thisPod;
	data.items.forEach(function(pod){
		if (podName == pod.metadata.name)
			thisPod = pod;
	})
	if (typeof thisPod != "undefined") {
		drawPodInfo(thisPod);
		drawContainers(thisPod);
		drawStatus(thisPod.status.conditions);
		drawStatefulset(thisPod.metadata.generateName);
	}
}
function drawPodInfo (pod) {
	$("#podNameArea").empty().text(pod.metadata.name);
	$("#namespaceArea").empty().text(pod.metadata.namespace);
	$("#nodeNameArea").empty().append("<a href='/kubernetes/node/"+pod.status.hostIP+"/detail'><span style='cursor:pointer;' class='font-weight-bold m--font-brand'>" + pod.spec.nodeName + "</span></a>");
	
	$("#hostIpArea").empty().text(pod.status.hostIP);
	$("#creationTimestampArea").empty().text(timeFormat(pod.metadata.creationTimestamp));
	$("#statusArea").empty().text(pod.status.phase);
	$("#qosClassArea").empty().text(pod.status.qosClass);
	drawLabels (pod.metadata.labels);
	drawAnnotations (pod.status.annotations);
}
function getContainerEnv (env) {
	var envStr = "";
	env.forEach(function(envItem){
		try {
			envStr += envItem.name + " : "; 
			if (typeof envItem.valueFrom != "undefined") {
				if (typeof envItem.valueFrom.fieldRef != "undefined") 
					envStr += "{" + envItem.valueFrom.fieldRef.apiVersion + ":" + envItem.valueFrom.fieldRef.fieldPath + "}\n";
				else 
					envStr += JSON.stringify(envItem.valueFrom) + "\n";
			}
			else if (typeof envItem.value != "undefined")
				envStr += envItem.value + "\n";
		} catch (e) {
			envStr +="-";
			console.log(e);
		}
		
	})
	return envStr;
}
function drawContainers (pod) {
	var containers = [];
	pod.spec.containers.forEach(function(item, idx){
		var m = {
			image : item.image
		}
		if (pod.status.containerStatuses[idx].name == item.name && typeof pod.status.containerStatuses[idx].containerID != "undefined") {
			var containerID = pod.status.containerStatuses[idx].containerID.split("docker://")[1];
			m.name = "<a href = '/infrastructure/" + pod.status.hostIP + "/container/" + containerID + "/detail'>" + item.name + "</a>";
		} else {
			m.name = item.name;
		}
		if (typeof item.env != "undefined")
			m.env = getContainerEnv(item.env);
		else 
			m.env = "-";

		if (typeof item.command != "undefined") {
			var commandStr = "";
			item.command.forEach(function(commandItem){
				commandStr += commandItem + "\n";
			})
			m.command = commandStr;
		} else 
			m.command = "-";

		if (typeof item.args != "undefined") {
			var argsStr = "";
			item.args.forEach(function(argsItem){
				argsStr += argsItem + "\n";
			})
			m.args = argsStr;
		} else 
			m.args = "-";
		containers.push(m);
	})
	var fields = ['name','image', 'env', 'command', 'args'];
	new SimpleList().area("containerListTableArea").fields(fields).data(containers).draw();
}
function drawStatus (data) {
	var fields = ['type','status','reason','message','lastTransitionTime'];
	data.forEach(function (item){
		item.lastTransitionTime = timeFormat(item.lastTransitionTime); 
	})
	new SimpleList().area("statusListArea").fields(fields).data(data).draw();
}
function drawStatefulset(podName) {
	new Client().url("/api/v1/kubernetes/statefulset/snapshot").callback(function (data){
		var thisStatefulset = [];
		data.items.forEach(function(item){
			if (item.metadata.name + "-" == podName)
				thisStatefulset.push(item);
		})
		var tableData = [];
		thisStatefulset.forEach(function (item){
			var m = {
				name : item.metadata.name,
				namespace : item.metadata.namespace,
				label : item.metadata.labels,
				pod : item.status.replicas + "/" + item.status.readyReplicas,
				creationTimestamp : timeFormat(item.metadata.creationTimestamp),
				image : item.spec.template.spec.containers
			}
			if (item.status.replicas == item.status.readyReplicas)
				m.status = "Running";
			else
				m.status = "Not Running";
			tableData.push(m);
		})
		var columns = getColumns("statefulset");
		new MDT().area("statefulsetListTableArea").columns(columns).data(tableData).makeSearch().draw();
	}).get();
}
function drawPodCpu (data) {
	new BasicLine().area("cpuChartArea").colors(colors.cpuUsed).data(data, "cpuUsedPercent", podName).draw();
}
function drawPodMemory (data) {
	new BasicLine().area("memoryChartArea").setYAxisMax(100).colors(colors.memoryUsed).data(data, "memoryUsedPercent", podName).draw();
}
function drawEvent(data) {
	var eventList = [];
	data.items.forEach(function(item){
		if (item.involvedObject.name == podName)
			eventList.push(item)
	})
	console.log(data);

	var fields = ['message','source','subObject','firstTimestamp', 'lastTimestamp'];
	eventList.forEach(function (event){
		event.firstTimestamp = timeFormat(event.firstTimestamp); 
		event.lastTimestamp = timeFormat(event.lastTimestamp); 
		event.source = event.source.component + " " + event.source.host;
		event.subObject = event.involvedObject.fieldPath;
		if (event.reason == "Failed")
			event.message = "<span style='color: red;'>" + event.message + "</span>";

	})
	new SimpleList().area("eventListArea").fields(fields).data(eventList).draw();
}
new Client().url("/api/v1/kubernetes/pod/snapshot").callback(drawPod).get();
new Client().url("/api/v1/kubernetes/event/snapshot").callback(drawEvent).get();

new Picker().refreshFunction(timeRefresh).draw();
function timeRefresh (startTime, time) {
	new Client().url("/api/v1/kubernetes/pod/" + podName + "/cpu/used/percent?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(drawPodCpu).get();
	new Client().url("/api/v1/kubernetes/pod/" + podName + "/memory/used/percent?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(drawPodMemory).get();
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