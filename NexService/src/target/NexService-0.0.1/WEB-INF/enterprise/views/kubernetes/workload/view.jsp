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
				Workload
			</h3>
		</div>
		<div class="dropdown" id = "namespaceArea"></div>
	</div>
</div>
<div class="m-content">
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text"> Daemonset</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="daemonsetArea" style="height: 250px;"></div>
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
							<div class="m-portlet__head-text"> Deployment</div>
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
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text"> Pod</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="podArea" style="height: 250px;"></div>
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
							<div class="m-portlet__head-text"> Replicaset</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="replicasetArea" style="height: 250px;"></div>
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
							<div class="m-portlet__head-text"> Statefulset</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="statefulsetArea" style="height: 250px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script src="/resources/js/module/common/workloadUtils.namespace.js" type="text/javascript"></script>
<script type="text/javascript">
var namespace = "${namespace}";
function drawDaemonset (data) {
	var arr = getArrayByNamespace(data,namespace);
	var tableData = [];
	arr.forEach(function (item){
		var m = {
			name : item.metadata.name,
			namespace : item.metadata.namespace,
			label : item.metadata.labels,
			pod : item.status.numberReady + "/" + item.status.numberAvailable,
			creationTimestamp : timeFormat(item.metadata.creationTimestamp),
			image : item.spec.template.spec.containers
		}
		if (item.status.numberUnavailable == 0)
			m.status = "Running";
		else
			m.status = "Not Running";
		tableData.push(m);
	})
	var columns = getColumns("daemonset");
	new MDT().area("daemonsetArea").columns(columns).data(tableData).makeSearch().draw();
}
function drawDeployment (data) {
	var arr = getArrayByNamespace(data,namespace);
	var tableData = [];
	arr.forEach(function (item){
		var m = {
			name : item.metadata.name,
			label : item.metadata.labels,
			namespace : item.metadata.namespace,
			pod : item.status.replicas + "/" + item.status.readyReplicas,
			creationTimestamp : timeFormat(item.metadata.creationTimestamp),
			image : item.spec.template.spec.containers
		}
		if (item.status.replicas == item.status.availableReplicas)
			m.status = "Running";
		else
			m.status = "Not Running";
		tableData.push(m);
	})
	var columns = getColumns ("deployment");
	new MDT().area("deploymentArea").columns(columns).data(tableData).makeSearch().draw();
}
function drawPod (data) {
	var arr = getArrayByNamespace(data,namespace);
	var tableData = [];
	arr.forEach(function (item){
		var m = {
			name : item.metadata.name,
			namespace : item.metadata.namespace,
			status : item.status.phase,
			//restart : item.status.containerStatuses[0].restartCount,
			creationTimestamp : timeFormat(item.metadata.creationTimestamp)
		}
		if (typeof item.status.containerStatuses[0] != "undefined")
			m.restart = item.status.containerStatuses[0].restartCount;
		else
			m.restart = 0;
		tableData.push(m);
	})
	var columns = [
		{ field : "status", title : "", width : 30,
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
			width : 300,
			template : function(row){
				return "<a href='/kubernetes/pod/"+row.name+"/detail?namespace=" + row.namespace + "'><span style='cursor:pointer;' class='font-weight-bold m--font-brand'>" + row.name + "</span></a>";
			}
		},
		{ field : "restart", title : "Restart", width : 50},
		{ field : "creationTimestamp", title : "Creation Timestamp", width : 150}
	];
	new MDT().area("podArea").columns(columns).data(tableData).makeSearch().draw();
	
}
function drawReplicaset (data) {
	var arr = getArrayByNamespace(data,namespace);
	var tableData = [];
	arr.forEach(function (item){
		var m = {
			name : item.metadata.name,
			label : item.metadata.labels,
			namespace : item.metadata.namespace,
			pod : item.status.replicas + "/" + item.status.readyReplicas,
			creationTimestamp : timeFormat(item.metadata.creationTimestamp),
			image : item.spec.template.spec.containers
		}
		if (item.status.replicas == 0 || item.status.replicas == item.status.availableReplicas)
			m.status = "Running";
		else
			m.status = "Not Running";
		tableData.push(m);
	})
	var columns = getColumns ("replicaset");
	new MDT().area("replicasetArea").columns(columns).data(tableData).makeSearch().draw();
}
function drawStatefulset (data) {
	var arr = getArrayByNamespace(data,namespace);
	var tableData = [];
	arr.forEach(function (item){
		var m = {
			name : item.metadata.name,
			label : item.metadata.labels,
			namespace : item.metadata.namespace,
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
	var columns = getColumns ("statefulset");
	new MDT().area("statefulsetArea").columns(columns).data(tableData).makeSearch().draw();
}

new Client().url("/api/v1/kubernetes/daemonset/snapshot").callback(drawDaemonset).get();
new Client().url("/api/v1/kubernetes/deployment/snapshot").callback(drawDeployment).get();
new Client().url("/api/v1/kubernetes/pod/snapshot").callback(drawPod).get();
new Client().url("/api/v1/kubernetes/replicaset/snapshot").callback(drawReplicaset).get();
new Client().url("/api/v1/kubernetes/statefulset/snapshot").callback(drawStatefulset).get();
new Namespace().area("namespaceArea").thisNs("${namespace}").thisWorkload("workload").draw();
</script>