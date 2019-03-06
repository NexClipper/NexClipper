<div class="m-subheader">
	<div class="d-flex align-items-center">
		<div class="mr-auto">
			<h3 class="m-subheader__title">
				Cluster
			</h3>
		</div>
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
							<div class="m-portlet__head-text">Namespaces</div>
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
							<div class="m-portlet__head-text">Daemon Sets</div>
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
							<div class="m-portlet__head-text">Deployments</div>
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

function drawCpuTotal (data) {
	var basicLine = new BasicLine().area("cpuChartArea").colors(colors.cpuTotal).data(data, "cpuTotal", "cpuTotal").draw();
	function drawCpuUsed(data) {
		basicLine.appendColors(colors.cpuUsed).appendData(data, "cpuPercent", "cpuPercent");
	}
	new Client().url("/api/v1/kubernetes/cluster/cpu/used/percent?startTime=1h&time=10s&limit=1000").callback(drawCpuUsed).get();
}
function drawMemoryTotal (data) {
	var basicLine = new BasicLine().area("memoryChartArea").colors(colors.cpuTotal).data(data, "memoryTotal", "memoryTotal").draw();
	function drawMemoryUsed(data) {
		basicLine.appendColors(colors.memoryUsed).appendData(data, "memoryPercent", "memoryPercent");
	}
	new Client().url("/api/v1/kubernetes/cluster/memory/used/percent?startTime=1h&time=10s&limit=1000").callback(drawMemoryUsed).get();
}
function drawPodTotal (data) {
	var basicLine = new BasicLine().area("podChartArea").colors(colors.podTotal).data(data, "podTotal", "podTotal").draw();
	function drawPodUsed(data) {
		basicLine.appendColors(colors.podUsed).appendData(data, "podPercent", "podPercent");
	}
	new Client().url("/api/v1/kubernetes/cluster/pod/used/percent?startTime=1h&time=10s&limit=1000").callback(drawPodUsed).get();
}

function drawNamespace (data) {
	var listData = [];
	data.items.forEach(function(item){
		listData.push({
			name : item.metadata.name,
			status : item.status.phase,
			creationTimestamp : item.metadata.creationTimestamp
		});
	});
	var fields = ['name','creationTimestamp'];
	new StatusList().area("namespaceListArea").fields(fields).data(listData).draw();
}
function drawDaemonsets (data) {
	var listData = [];
	data.items.forEach(function(item){
		listData.push({
			name : item.metadata.name,
			namespace : item.metadata.namespace,
			creationTimestamp : item.metadata.creationTimestamp
		});
	});
	var fields = ['name','namespace','creationTimestamp'];
	new SimpleList().area("daemonsetsListArea").fields(fields).data(listData).draw();
}
function drawDeployment (data) {
	var listData = [];
	data.items.forEach(function(item){
		listData.push({
			name : item.metadata.name,
			namespace : item.metadata.namespace,
			creationTimestamp : item.metadata.creationTimestamp
		});
	});
	var fields = ['name','namespace','creationTimestamp'];
	new SimpleList().area("deploymentListArea").fields(fields).data(listData).draw();
}
new Client().url("/api/v1/kubernetes/version/snapshot").callback(drawVersion).get();
new Client().url("/api/v1/kubernetes/cluster/snapshot").callback(drawCluster).get();

new Client().url("/api/v1/kubernetes/cluster/cpu/total?startTime=1h&time=10s&limit=1000").callback(drawCpuTotal).get();
new Client().url("/api/v1/kubernetes/cluster/memory/total?startTime=1h&time=10s&limit=1000").callback(drawMemoryTotal).get();
new Client().url("/api/v1/kubernetes/cluster/pod/total?startTime=1h&time=10s&limit=1000").callback(drawPodTotal).get();


new Client().url("/api/v1/kubernetes/namespace/snapshot").callback(drawNamespace).get();
new Client().url("/api/v1/kubernetes/daemonset/snapshot").callback(drawDaemonsets).get();
new Client().url("/api/v1/kubernetes/deployment/snapshot").callback(drawDeployment).get();
</script>