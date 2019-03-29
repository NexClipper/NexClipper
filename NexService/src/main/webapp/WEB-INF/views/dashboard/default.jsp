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
		<div class="col-lg-8">
			<div class="m-subheader">
				<div class="d-flex align-items-center">
					<div class="mr-auto">
						<h3 class="m-subheader__title">
							Kubernetes Cluster
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/cluster">Controller</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/kubernetes/cluster" class="btn-link m--font-metal" id="controllerStatus" >
											<strong style = "color : #f4516c;">False</strong>
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/cluster">ETCD</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/kubernetes/cluster" class="btn-link m--font-metal" id="etcdStatus">
											<strong style = "color : #f4516c;">False</strong>
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/cluster">Scheduler</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/kubernetes/cluster" class="btn-link m--font-metal" id="schedulerStatus">
											<strong style = "color : #f4516c;">False</strong>
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/node">Nodes</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/kubernetes/node" class="btn-link m--font-metal">
											<strong id="nodeTotal">0</strong>
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/cluster">Namespace</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/kubernetes/cluster" class="btn-link m--font-metal">
											<strong id="namespaceTotal">0</strong>
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
									<div class="m-portlet__head-text"><a class = "titleA" href="javascript:;">Event Error</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="javascript:;" class="btn-link m--font-metal">
											<strong id="errorTotal">0</strong>
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
							Kubernetes Workloads
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/pod">Daemon Sets</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/kubernetes/pod" class="btn-link m--font-metal">
											<strong id="daemonSetsTotal">0</strong>
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/pod">Deployments</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/kubernetes/pod" class="btn-link m--font-metal">
											<strong id="deploymentsTotal">0</strong>
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/pod">Replica Sets</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/kubernetes/pod" class="btn-link m--font-metal">
											<strong id="replicaSetsTotal">0</strong>
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/pod">Stateful Sets</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/kubernetes/pod" class="btn-link m--font-metal">
											<strong id="statefulSetsTotal">0</strong>
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/pod">Pods</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/kubernetes/pod" class="btn-link m--font-metal">
											<strong id="podTotal">0</strong>
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/admin/agent">NexClipper Agent</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/admin/agent" class="btn-link m--font-metal">
											<strong id="agentTotal">0</strong>
										</a>
									</div>	
								</div>	
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/cluster">Cluster Cpu Utilization</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 180px;" id = "clusterCpuUtilizationChartArea">
							</div>
							<div class="m-widget12" style="height: 40px;">
								<div class="m-widget12__item">
									<div class="m-widget12__text1" style="text-align: center;">
										<span id="clusterCpuUtilizationUsed"></span><div>Used</div>
									</div>
									<div class="m-widget12__text2" style="text-align: center;">
										<span id="clusterCpuUtilizationTotal"></span><div>Total</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/cluster">Cluster Memory Utilization</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 180px;" id = "clusterMemoryUtilizationChartArea">
							</div>
							<div class="m-widget12" style="height: 40px;">
								<div class="m-widget12__item">
									<div class="m-widget12__text1" style="text-align: center;">
										<span id="clusterMemoryUtilizationUsed"></span><div>Used</div>
									</div>
									<div class="m-widget12__text2" style="text-align: center;">
										<span id="clusterMemoryUtilizationTotal"></span><div>Total</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/cluster">Cluster Pod Utilization</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 180px;" id = "clusterPodUtilizationChartArea">
							</div>
							<div class="m-widget12" style="height: 40px;">
								<div class="m-widget12__item">
									<div class="m-widget12__text1" style="text-align: center;">
										<span id="clusterPodUtilizationUsed"></span><div>Used</div>
									</div>
									<div class="m-widget12__text2" style="text-align: center;">
										<span id="clusterPodUtilizationTotal"></span><div>Total</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="m-subheader">
				<div class="d-flex align-items-center">
					<div class="mr-auto">
						<h3 class="m-subheader__title">
							Infrastructures
						</h3>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text"><a class = "titleA" href="/infrastructure/host">Host</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/infrastructure/host" class="btn-link m--font-metal">
											<strong id="hostTotal">0</strong>
										</a>
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/infrastructure/container">Docker Container</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="height: 40px; text-align: center;">
								<div class="m-widget14__chart">
									<div class="m-widget14__stat">
										<a href="/infrastructure/container" class="btn-link m--font-metal">
											<strong id="containerTotal">0</strong>
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
						&nbsp
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
									<div class="m-portlet__head-text"><a class = "titleA" href="/infrastructure/host">Infrastructure Map</a></div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body" style="max-width:800px; margin: 0 auto">
							<div class="m-widget14" style="height: 345px;" id = "infrastructureMapChartArea">
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
							<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/pod">Pods Top Cpu (%)</a></div>
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
							<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/pod">Pods Top Memory (%)</a></div>
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
							<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/node">Node Top Cpu (%)</a></div>
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
							<div class="m-portlet__head-text"><a class = "titleA" href="/kubernetes/node">Node Top Memory (%)</a></div>
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
<script src="/resources/js/module/common/client.js?22" type="text/javascript"></script>
<script src="/resources/js/module/common/colors.js" type="text/javascript"></script>
<script src="/resources/js/module/list/statusList.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/donut.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/tilemap.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/solidgauge.js?2" type="text/javascript"></script>
<script src="/resources/js/lib/billboard/d3.min.js" type="text/javascript"></script>
<script src="/resources/js/lib/billboard/billboard.min.js" type="text/javascript"></script>
<script type="text/javascript">
// 1 row - 1 col - 1 row
function drawStatus (data){
	data.items.forEach(function(item){
		if (item.metadata.name == "controller-manager") {
			item.conditions.forEach(function(condition){
				if (condition.type == "Healthy") {
					if (condition.status == "True") $("#controllerStatus").empty().append('<strong style = "color : #36a3f7;">True</strong>');
					else $("#controllerStatus").empty().append('<strong style = "color : #000000;">False</strong>');
				}
			})
		}
		if (item.metadata.name == "etcd-0") {
			item.conditions.forEach(function(condition){
				if (condition.type == "Healthy") {
					if (condition.status == "True") $("#etcdStatus").empty().append('<strong style = "color : #36a3f7;">True</strong>');
					else $("#etcdStatus").empty().append('<strong style = "color : #000000;">False</strong>');
				}
			})
		}
		if (item.metadata.name == "scheduler") {
			item.conditions.forEach(function(condition){
				if (condition.type == "Healthy") {
					if (condition.status == "True") $("#schedulerStatus").empty().append('<strong style = "color : #36a3f7;">True</strong>');
					else $("#schedulerStatus").empty().append('<strong style = "color : #000000;">False</strong>');
				}
			})
		}
	})
}
function drawNamespace(data) {
	var activeCount = 0;
	var notActiveCount = 0;
	data.items.forEach(function(item){
		if (item.status.phase == "Active")
			activeCount++;
		else
			notActiveCount++;
	})	
	$("#namespaceTotal").text(activeCount + "/" + (activeCount+notActiveCount))
	if (activeCount != (activeCount+notActiveCount)) $("#namespaceTotal").css("color","#f4516c");
	else $("#namespaceTotal").css("color","#000000");
}
// event error code 

//1 row - 1 col - 2 row
function drawDaemonSet(data) {
	var runningCount = 0;
	data.items.forEach(function(item){
		if (item.status.numberUnavailable == 0)
			runningCount++;
	})
	$("#daemonSetsTotal").text(runningCount + "/" + data.items.length);
	if (runningCount != data.items.length) $("#daemonSetsTotal").css("color","#f4516c");
	else $("#daemonSetsTotal").css("color","#000000");
}
function drawDeployments(data) {
	var trueCount = 0;
	var falseCount = 0;
	data.items.forEach(function(item){
		if (item.status.replicas == item.status.availableReplicas) trueCount++;
		else falseCount++;
	})
	$("#deploymentsTotal").text(trueCount + "/" + (trueCount+falseCount));
	if (trueCount != (trueCount+falseCount)) $("#deploymentsTotal").css("color","#f4516c");
	else $("#deploymentsTotal").css("color","#000000");
}
function drawReplicaSets(data) {
	var trueCount = 0;
	var falseCount = 0;
	data.items.forEach(function(item){
		if (item.status.replicas == 0 || item.status.replicas == item.status.availableReplicas) trueCount++;
		else falseCount++;
	})
	$("#replicaSetsTotal").text(trueCount + "/" + (trueCount+falseCount));
	if (trueCount != (trueCount+falseCount)) $("#replicaSetsTotal").css("color","#f4516c");
	else $("#replicaSetsTotal").css("color","#000000");
}
function drawStatefulSets(data) {
	var trueCount = 0;
	var falseCount = 0;
	data.items.forEach(function(item){
		if (item.status.replicas == item.status.readyReplicas) trueCount++;
		else falseCount++;
	})
	$("#statefulSetsTotal").text(trueCount + "/" + (trueCount+falseCount));
	if (trueCount != (trueCount+falseCount)) $("#statefulSetsTotal").css("color","#f4516c");
	else $("#statefulSetsTotal").css("color","#000000");
}
function drawPod(data) {
	var runningCount = 0;
	var notRunningCount = 0;
	var formatHistogramData = [];
	
	data.items.forEach(function(item){
		if (item.status.phase == "Running") runningCount++;
		else notRunningCount++;
		formatHistogramData.push({
			name : item.metadata.name,
			cpu : item.resource.used_percent.cpu,
			mem : item.resource.used_percent.memory
		});
	})
	$("#podTotal").text(runningCount + "/" + (runningCount+notRunningCount));
	if (runningCount != (runningCount+notRunningCount)) $("#podTotal").css("color","#f4516c");
	else $("#podTotal").css("color","#000000");
	drawHistogramChart("topPodCpuChart", formatHistogramData, "cpu");
	drawHistogramChart("topPodMemChart", formatHistogramData, "mem");
}
function drawAgents(data) {
	var activeCount = 0;
	var notActiveCount = 0;
	data.forEach(function(item){
		if (item.status == "ACTIVE") activeCount++;
		else notActiveCount++;
	})
	$("#agentTotal").text(activeCount + "/" + (activeCount+notActiveCount));
	$("#hostTotal").text(activeCount + "/" + (activeCount+notActiveCount));
	if (activeCount != (activeCount+notActiveCount)) {
		$("#agentTotal").css("color","#f4516c");
		$("#hostTotal").css("color","#f4516c");
	} else {
		$("#agentTotal").css("color","#000000");
		$("#hostTotal").css("color","#000000");
	}
	new Client().url("/api/v1/host/snapshot").refreshFlag(rf).callback(drawHost).bindData(data).get();
}
function drawNode(data) {
	var activeCount = 0;
	var notActiveCount = 0;
	data.items.forEach(function(item){
		item.status.conditions.forEach(function(condition){
			if (condition.type == "Ready") {
				if (condition.status == "True") activeCount++;
				else notActiveCousnt++;
			}
		})
	})
	
	$("#nodeTotal").text(activeCount + "/" + (activeCount+notActiveCount));
	if (activeCount != (activeCount+notActiveCount)) $("#nodeTotal").css("color","#f4516c");
	else  $("#nodeTotal").css("color","#000000");
}
//1 row - 1 col - 3 row
function drawClusterUtilization(data){
	new SolidGauge().area("clusterCpuUtilizationChartArea").data(data.used_percent_cpu, "Cpu(%)").draw();
	new SolidGauge().area("clusterMemoryUtilizationChartArea").data(data.used_percent_mem, "Memory(%)").draw();
	new SolidGauge().area("clusterPodUtilizationChartArea").data(data.used_percent_pod, "Pod(%)").draw();
	$("#clusterCpuUtilizationUsed").text(data.used_cpu);
	$("#clusterCpuUtilizationTotal").text(data.cpu);
	$("#clusterMemoryUtilizationUsed").text(data.used_mem);
	$("#clusterMemoryUtilizationTotal").text(data.mem);
	$("#clusterPodUtilizationUsed").text(data.used_pod);
	$("#clusterPodUtilizationTotal").text(data.pod);
}
//1 row - 2 col - 1 row
function drawHost (data, agents) {
	drawInfrastructureMap(data, agents);
	drawTopChart(data);
}
function drawDockerContainer(data) {
	var containersRunningCount = 0;
	Object.keys(data).forEach(function(key){
		data[key].containers.forEach(function(container){
			if (container.Labels["io.kubernetes.docker.type"] == "container" && 
					container.State == "running")
				containersRunningCount++;
		})
	});
	$("#containerTotal").text(containersRunningCount);
}
//1 row - 2 col - 2 row
function drawInfrastructureMap (data, agents) {
	var tilemapData = [];
	data.forEach(function (item){
		agents.forEach(function(agent){
			if (item.host_ip == agent.host_ip) {
				if (agent.status == "ACTIVE") tilemapData.push({'hc-a2' : item.host_ip, name : item.host_ip, value: item.cpu.cpu_per });
				else tilemapData.push({'hc-a2' : item.host_ip, name : item.host_ip, value: -1 });
			} 
		})
	})
	new Tilemap().area("infrastructureMapChartArea").data(tilemapData).draw();
}
//2 row
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
var rf = true;
function refresh() {
	rf = false;
	new Client().url("/api/v1/kubernetes/component/status/snapshot").refreshFlag(rf).callback(drawStatus).get();
	new Client().url("/api/v1/kubernetes/namespace/snapshot").refreshFlag(rf).callback(drawNamespace).get();
	
	// event error code
	new Client().url("/api/v1/kubernetes/daemonset/snapshot").refreshFlag(rf).callback(drawDaemonSet).get();
	new Client().url("/api/v1/kubernetes/node/snapshot").refreshFlag(rf).callback(drawNode).get();
	new Client().url("/api/v1/kubernetes/deployment/snapshot").refreshFlag(rf).callback(drawDeployments).get();
	new Client().url("/api/v1/kubernetes/replicaset/snapshot").refreshFlag(rf).callback(drawReplicaSets).get();
	new Client().url("/api/v1/kubernetes/statefulset/snapshot").refreshFlag(rf).callback(drawStatefulSets).get();
	new Client().url("/api/v1/kubernetes/pod/snapshot").refreshFlag(rf).callback(drawPod).get();
	new Client().url("/api/v1/host/agent/status").refreshFlag(rf).callback(drawAgents).get();
	new Client().url("/api/v1/kubernetes/cluster/snapshot").refreshFlag(rf).callback(drawClusterUtilization).get();
	new Client().url("/api/v1/host/docker/snapshot").refreshFlag(rf).callback(drawDockerContainer).get();
}
refresh();
refreshInterval = setInterval("refresh()", 10000); 
</script>