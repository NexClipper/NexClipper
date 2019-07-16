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
				Deployment Detail
			</h3>
		</div>
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
								<span>Selector</span> <div id="selectorArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Creation Time</span> <div id="creationTimestampArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Strategy</span> <div id="strategyArea"></div>
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
							<div class="m-portlet__head-text">New Replica</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="replicasetListTableArea" style="height: 250px;">
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
							<div class="m-portlet__head-text">Prev Replica</div>
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
							<div class="m-portlet__head-text">Horizontal Pod Autoscalers</div>
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
var deploymentName = "${deploymentName}";
function drawDeployment (data) {
	var thisDeployment;
	data.items.forEach(function(item){
		if (deploymentName == item.metadata.name)
			thisDeployment = item;
	})
	if (typeof thisDeployment != "undefined")
		drawDeploymentInfo(thisDeployment);
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
function drawDeploymentInfo (data) {
	$("#nameArea").empty().text(data.metadata.name);
	$("#namespaceArea").empty().text(data.metadata.namespace);
	drawLabels (data.metadata.labels);
	drawAnnotations (data.metadata.annotations);
	drawSelector (data.spec.selector.matchLabels);
	$("#creationTimestampArea").empty().text(timeFormat(data.metadata.creationTimestamp));
	$("#strategyArea").empty().text(data.spec.strategy.type);
	getReplicaset(data.metadata.name, data.metadata.namespace);
}
function getReplicaset(appName, thisNs) {
	new Client().url("/api/v1/kubernetes/replicaset/snapshot").callback(function(data){
		var replicasetList = [];
		var thisReplicaset;
		data.items.forEach(function(replicaset){
			if (replicaset.metadata.namespace == thisNs &&
					replicaset.spec.replicas > 0 && replicaset.metadata.ownerReferences[0].name == appName)
				thisReplicaset = replicaset;
		})
		drawReplicaset(thisReplicaset);
	}).get();
}
function drawReplicaset(replicaset){
	var tableData = [{
		name : replicaset.metadata.name,
		namespace : replicaset.metadata.namespace,
		label : replicaset.metadata.labels,
		pod : replicaset.status.replicas + "/" + replicaset.status.fullyLabeledReplicas,
		creationTimestamp : timeFormat(replicaset.metadata.creationTimestamp),
		image : replicaset.spec.template.spec.containers
	}];
	if (replicaset.status.replicas == 0 || replicaset.status.replicas == replicaset.status.availableReplicas)
		tableData[0].status = "Running";
	else
		tableData[0].status = "Not Running";
	var columns = getColumns("replicaset");
	new MDT().area("replicasetListTableArea").columns(columns).data(tableData).makeSearch().draw();
}
new Client().url("/api/v1/kubernetes/deployment/snapshot").callback(drawDeployment).get();
</script>