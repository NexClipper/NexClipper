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
				Kubernetes
			</h3>
		</div>
	</div>
</div>
<div class="m-content">
	<div class="row">
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Pod</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="text-align: center;">
						<div id = "podChartArea" class="m-widget14__chart m--margin-bottom-5" style="height: 150px;">
							<div class="m-widget14__stat">
								<a href="#" class="btn-link m--font-metal"><strong id="totalPod"></strong></a>
							</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Node</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="text-align: center;">
						<div id = "nodeChartArea" class="m-widget14__chart m--margin-bottom-5" style="height: 150px;">
							<div class="m-widget14__stat">
								<a href="#" class="btn-link m--font-metal"><strong id="totalNode"></strong></a>
							</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Host</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="text-align: center;">
						<div id = "hostChartArea" class="m-widget14__chart m--margin-bottom-5" style="height: 150px;">
							<div class="m-widget14__stat">
								<a href="#" class="btn-link m--font-metal"><strong id="totalHost"></strong></a>
							</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Cluster Cpu Utilization</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id = "cpuUtilizationArea" style="height: 200px;">
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Cluster Memory Utilization</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id = "memoryUtilizationArea" style="height: 200px;">
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Cluster Pod Utilization</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id = "podUtilizationArea" style="height: 200px;">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Daemon Sets</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="text-align: center;">
						<div id = "daemonSetsChartArea" class="m-widget14__chart m--margin-bottom-5" style="height: 150px;">
							<div class="m-widget14__stat">
								<a href="#" class="btn-link m--font-metal"><strong id="daemonSetsTotal"></strong></a>
							</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Deployments</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="text-align: center;">
						<div id = "deploymentsChartArea" class="m-widget14__chart m--margin-bottom-5" style="height: 150px;">
							<div class="m-widget14__stat">
								<a href="#" class="btn-link m--font-metal"><strong id="deploymentsTotal"></strong></a>
							</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Replica Sets</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="text-align: center;">
						<div id = "replicaSetsChartArea" class="m-widget14__chart m--margin-bottom-5" style="height: 150px;">
							<div class="m-widget14__stat">
								<a href="#" class="btn-link m--font-metal"><strong id="replicaSetsTotal"></strong></a>
							</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Stateful Sets</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="text-align: center;">
						<div id = "statefulSetsChartArea" class="m-widget14__chart m--margin-bottom-5" style="height: 150px;">
							<div class="m-widget14__stat">
								<a href="#" class="btn-link m--font-metal"><strong id="statefulSetsTotal"></strong></a>
							</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script src="/resources/js/module/common/colors.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/donut.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/simpleLine.js" type="text/javascript"></script>
<script type="text/javascript">

// row 1
function drawPod(data) {
	new Donut().area("podChartArea").colors(colors.Running).data([{ label: "", value: data.items.length }]).draw();
	$("#totalPod").text(data.items.length);
}
function drawNode(data) {
	new Donut().area("nodeChartArea").colors([colors.Running]).data([{ label: "", value: data.items.length }]).draw();
	$("#totalNode").text(data.items.length);
}
function drawHost (data) {
	new Donut().area("hostChartArea").colors(colors.Running).data([{ label: "", value: data.length }]).draw();
	$("#totalHost").text(data.length);
}

//row 2
var lineTest;
function drawCpuUtilization(data) {
	lineTest = new SimpleLine().area("cpuUtilizationArea").colors(colors.cpuUtilization).data(data, "cpuPercent").draw();
	/* function drawCpuUtilization(data) {
		lineTest.appendColors(colors.memoryUtilization).appendData(data, "memoryPercent").redraw();
	}
	new Client().url("/api/v1/kubernetes/cluster/memory/used/percent?startTime=50d&time=1m&limit=300").callback(drawCpuUtilization).get(); */
}
function drawMemoryUtilization(data) {
	new SimpleLine().area("memoryUtilizationArea").colors(colors.memoryUtilization).data(data, "memoryPercent").draw();
}
function drawPodUtilization(data) {
	new SimpleLine().area("podUtilizationArea").colors(colors.podUtilization).data(data, "podPercent").draw();
}
//row 3
function drawDaemonSet(data) {
	new Donut().area("daemonSetsChartArea").colors([colors.Running]).data([{ label: "", value: data.items.length }]).draw();
	$("#daemonSetsTotal").text(data.items.length);
}
function drawDeployments(data) {
	new Donut().area("deploymentsChartArea").colors([colors.Running]).data([{ label: "", value: data.items.length }]).draw();
	$("#deploymentsTotal").text(data.items.length);
}
function drawReplicaSets(data) {
	new Donut().area("replicaSetsChartArea").colors([colors.Running]).data([{ label: "", value: data.items.length }]).draw();
	$("#replicaSetsTotal").text(data.items.length);
}
function drawStatefulSets(data) {
	new Donut().area("statefulSetsChartArea").colors([colors.Running]).data([{ label: "", value: data.items.length }]).draw();
	$("#statefulSetsTotal").text(data.items.length);
}

new Client().url("/api/v1/kubernetes/pod/snapshot").callback(drawPod).get();
new Client().url("/api/v1/kubernetes/node/snapshot").callback(drawNode).get();
new Client().url("/api/v1/host/snapshot").callback(drawHost).get();

new Client().url("/api/v1/kubernetes/cluster/cpu/used/percent?startTime=1h&time=1m&limit=300").callback(drawCpuUtilization).get();
new Client().url("/api/v1/kubernetes/cluster/memory/used/percent?startTime=1h&time=1m&limit=300").callback(drawMemoryUtilization).get();
new Client().url("/api/v1/kubernetes/cluster/pod/used/percent?startTime=1h&time=1m&limit=300").callback(drawPodUtilization).get();

new Client().url("/api/v1/kubernetes/daemonset/snapshot").callback(drawDaemonSet).get();
new Client().url("/api/v1/kubernetes/deployment/snapshot").callback(drawDeployments).get();
new Client().url("/api/v1/kubernetes/replicaset/snapshot").callback(drawReplicaSets).get();
new Client().url("/api/v1/kubernetes/statefulset/snapshot").callback(drawStatefulSets).get();
//http://192.168.0.171:443/api/v1/pods
</script>