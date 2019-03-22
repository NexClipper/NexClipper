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
				Cluster
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
							<div class="m-portlet__head-text">Cluster</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget12" style="height: 200px;">
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>gitVersion</span> <div id="gitVersionArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>goVersion</span> <div id="goVersionArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>gitCommit</span> <div id="gitCommitArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Platform</span> <div id="platformArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Built</span> <div id="builtArea"></div>
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
							<div class="m-portlet__head-text">Cluster Usage</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
						<div id="clusterUsageArea" style="height: 200px;"></div>
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
							<div class="m-portlet__head-text">Cpu (Core)</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="cpuChartArea" style="height: 200px;"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Memory (GB)</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="memoryChartArea" style="height: 200px;"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Pod (Count)</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="podChartArea" style="height: 200px;"></div>
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
							<div class="m-portlet__head-text">Namespaces <span id ="namespaceCountArea"></span></div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id="namespaceListArea" class="m-widget4 m-dashboard-widget4" style="overflow-x: hidden; height: 200px;"></div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Daemon Sets <span id ="daemonCountArea"></span></div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id="daemonsetsListArea" class="m-widget4 m-dashboard-widget4" style="overflow-x: hidden; height: 200px;"></div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Deployments <span id ="deploymentsCountArea"></span></div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id="deploymentListArea" class="m-widget4 m-dashboard-widget4" style="overflow-x: hidden; height: 200px;"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script src="/resources/js/module/common/colors.js" type="text/javascript"></script>
<script src="/resources/js/module/list/simpleList.js" type="text/javascript"></script>
<script src="/resources/js/module/list/statusList.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script type="text/javascript">
function drawVersion (data) {
	$("#gitVersionArea").empty().text(data.gitVersion);
	$("#goVersionArea").empty().text(data.goVersion);
	$("#gitCommitArea").empty().text(data.gitCommit);
	$("#platformArea").empty().text(data.platform);
	$("#builtArea").empty().text(data.buildDate.split("T")[0]);
}

function drawCluster (data) {
	var listData = [];
	listData.push({ resource : "Cpu", total : data.cpu, usage : data.used_cpu, usagePercent : data.used_percent_cpu + " %" });
	listData.push({ resource : "Memory", total : data.mem, usage : data.used_mem, usagePercent : data.used_percent_mem + " %" });
	listData.push({ resource : "Pod", total : data.pod, usage : data.used_pod, usagePercent : data.used_percent_pod + " %" });
	var fields = ['resource','total','usage','usagePercent'];
	new SimpleList().area("clusterUsageArea").fields(fields).data(listData).draw();
}

function drawCpuTotal (data, bindData) {
	var basicLine = new BasicLine().area("cpuChartArea").colors(colors.cpuTotal).data(data, "cpuTotal", "cpuTotal").draw();
	function drawCpuUsed(data) {
		basicLine.appendColors(colors.cpuUsed).appendData(data, "cpuPercent", "cpuPercent");
	}
	new Client().url("/api/v1/kubernetes/cluster/cpu/used/percent?startTime=" + bindData.startTime + "&time=" + bindData.time + "&limit=1000")
	.callback(drawCpuUsed).get();
}
function drawMemoryTotal (data, bindData) {
	var basicLine = new BasicLine().area("memoryChartArea").colors(colors.cpuTotal).data(data, "memoryTotal", "memoryTotal").draw();
	function drawMemoryUsed(data) {
		basicLine.appendColors(colors.memoryUsed).appendData(data, "memoryPercent", "memoryPercent");
	}
	new Client().url("/api/v1/kubernetes/cluster/memory/used/percent?startTime=" + bindData.startTime + "&time=" + bindData.time + "&limit=1000")
	.callback(drawMemoryUsed).get();
}
function drawPodTotal (data, bindData) {
	var basicLine = new BasicLine().area("podChartArea").colors(colors.podTotal).data(data, "podTotal", "podTotal").draw();
	function drawPodUsed(data) {
		basicLine.appendColors(colors.podUsed).appendData(data, "podPercent", "podPercent");
	}
	new Client().url("/api/v1/kubernetes/cluster/pod/used/percent?startTime=" + bindData.startTime + "&time=" + bindData.time + "&limit=1000")
	.callback(drawPodUsed).get();
}

function drawNamespace (data) {
	var activeCount = 0;
	var listData = [];
	data.items.forEach(function(item){
		if (item.status.phase == "Active") activeCount++;
		listData.push({
			name : item.metadata.name,
			status : item.status.phase,
			creationTimestamp : item.metadata.creationTimestamp
		});
	});
	$("#namespaceCountArea").text("[" + activeCount + "/" + data.items.length + "]")
	var fields = ['name','creationTimestamp'];
	new StatusList().area("namespaceListArea").fields(fields).data(listData).draw();
}
function drawDaemonsets (data) {
	var listData = [];
	var runningCount = 0;
	data.items.forEach(function(item){
		var status = '';
		if (item.status.numberUnavailable == 0) {
			runningCount++;
			status = "ACTIVE";
		}
		listData.push({
			name : item.metadata.name,
			status : status,
			namespace : item.metadata.namespace,
			count : "[" + item.status.numberReady + "/" + item.status.numberAvailable + "]",
			creationTimestamp : item.metadata.creationTimestamp
		});
	});
	$("#daemonCountArea").text("[" + runningCount + "/" + data.items.length + "]");
	var fields = ['name','namespace','count','creationTimestamp'];
	new StatusList().area("daemonsetsListArea").fields(fields).data(listData).draw();
}
function drawDeployment (data) {
	console.log(data);
	var listData = [];
	var trueCount = 0;
	data.items.forEach(function(item){
		var status = '';
		if (item.status.replicas == item.status.availableReplicas) {
			trueCount++;
			status = "ACTIVE";
		}
		listData.push({
			name : item.metadata.name,
			status : status,
			namespace : item.metadata.namespace,
			count : "[" + item.status.availableReplicas + "/" + item.status.replicas + "]",
			creationTimestamp : item.metadata.creationTimestamp
		});
	});
	$("#deploymentsCountArea").text("[" + trueCount + "/" + data.items.length + "]");
	var fields = ['name','namespace','count','creationTimestamp'];
	new StatusList().area("deploymentListArea").fields(fields).data(listData).draw();
}
new Client().url("/api/v1/kubernetes/version/snapshot").callback(drawVersion).get();
new Client().url("/api/v1/kubernetes/cluster/snapshot").callback(drawCluster).get();
new Client().url("/api/v1/kubernetes/namespace/snapshot").callback(drawNamespace).get();
new Client().url("/api/v1/kubernetes/daemonset/snapshot").callback(drawDaemonsets).get();
new Client().url("/api/v1/kubernetes/deployment/snapshot").callback(drawDeployment).get();

new Picker().refreshFunction(timeRefresh).draw();
function timeRefresh (startTime, time) {
	new Client().url("/api/v1/kubernetes/cluster/cpu/total?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(drawCpuTotal).bindData({startTime:startTime, time:time}).get();
	new Client().url("/api/v1/kubernetes/cluster/memory/total?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(drawMemoryTotal).bindData({startTime:startTime, time:time}).get();
	new Client().url("/api/v1/kubernetes/cluster/pod/total?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(drawPodTotal).bindData({startTime:startTime, time:time}).get();
}

</script>