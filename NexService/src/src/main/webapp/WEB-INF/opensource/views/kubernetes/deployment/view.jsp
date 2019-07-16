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
				Deployment
			</h3>
		</div>
		<div class="dropdown" id = "namespaceArea"></div>
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
							<div class="m-portlet__head-text">List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="deploymentArea" style="height: 250px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script src="/resources/js/module/common/workloadUtils.namespace.js" type="text/javascript"></script>
<script type="text/javascript">
var PODS = [];
var REPLICASET = [];
var namespace = "${namespace}";
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
function searchCheckPod() {
	var podList = [];
	$('input:checkbox[type=checkbox]:checked').each(function () {
		if( this.value != "on" ) {
			var appNameAndNamespace = this.value;
			var appName = appNameAndNamespace.split(":")[0];
			var thisNs = appNameAndNamespace.split(":")[1];
			var thisReplicaset;
			REPLICASET.forEach(function(replicaset){
				if (replicaset.metadata.namespace == thisNs &&
						replicaset.spec.replicas > 0 && replicaset.metadata.ownerReferences[0].name == appName)
					thisReplicaset = replicaset.metadata.name;
			})
			PODS.forEach(function(pod){
				if (pod.metadata.namespace == thisNs && pod.metadata.generateName == thisReplicaset + "-")
					podList.push(pod.metadata.name);
			})
		}
	});
	return podList;
}
function mdtEvent() {
	$('#deploymentArea').on('click', 'input:checkbox[type=checkbox]', function() {
		$('input:checkbox[type=checkbox]').prop("checked", false);
		if( this.value != "on" )
			$(this).prop("checked", true);
		drawChart(searchCheckPod());
	});
}
function drawDeployment (data){
	var arr = getArrayByNamespace(data,namespace);
	var tableData = [];
	arr.forEach(function (item){
		var m = {
			key : item.metadata.name + ":" + item.metadata.namespace,
			name : item.metadata.name,
			namespace : item.metadata.namespace,
			label : item.metadata.labels,
			pod : item.status.readyReplicas + "/" + item.status.replicas,
			creationTimestamp : timeFormat(item.metadata.creationTimestamp),
			image : item.spec.template.spec.containers
		}
		if (item.status.replicas == item.status.availableReplicas)
			m.status = "Running";
		else
			m.status = "Not Running";
		tableData.push(m);
	})
	var columns = getColumns("deployment", "check");
	new MDT().area("deploymentArea").columns(columns).data(tableData).event(mdtEvent).makeSearch().draw();
	new Client().url("/api/v1/kubernetes/replicaset/snapshot").callback(function(replicasetData){
		REPLICASET = getPodListByNamespace(replicasetData.items, namespace);
		new Client().url("/api/v1/kubernetes/pod/snapshot").callback(function(data){
			PODS = getPodListByNamespace(data.items, namespace);
			$('input:checkbox[type=checkbox]:eq(1)').prop("checked", true);
			new Picker().refreshFunction(timeRefresh).draw();
		}).get();
	}).get();
}
new Client().url("/api/v1/kubernetes/deployment/snapshot").callback(drawDeployment).get();
new Namespace().area("namespaceArea").thisNs("${namespace}").thisWorkload("deployment").draw();
</script>