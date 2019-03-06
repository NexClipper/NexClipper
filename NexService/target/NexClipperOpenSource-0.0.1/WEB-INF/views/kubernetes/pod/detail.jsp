<div class="m-subheader">
	<div class="d-flex align-items-center">
		<div class="mr-auto">
			<h3 class="m-subheader__title">
				Pod Detail
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
							<div class="m-portlet__head-text">Title</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget12" style="height: 300px;">
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
					<div class="m-widget12" style="height: 300px;" id = "containerArea">
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
	getPodCpu(thisPod.metadata.name);
	getPodMemory(thisPod.metadata.name);
}
function drawPodTitle (pod) {
	$("#podNameArea").empty().text(pod.metadata.name);
	$("#namespaceArea").empty().text(pod.metadata.namespace);
	$("#creationTimestampArea").empty().text(pod.metadata.creationTimestamp);
	$("#statusArea").empty().text(pod.status.phase);
	$("#qosClassArea").empty().text(pod.status.qosClass);
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
function getPodCpu (podName) {
	new Client().url("/api/v1/kubernetes/pod/" + podName + "/cpu/used/percent?startTime=1h&time=5s&limit=1000")
	.callback(drawPodCpu).get();
}
function drawPodCpu (data) {
	new BasicLine().area("cpuChartArea").colors(colors.cpuUsed).data(data, "cpuUsedPercent", "cpuUsedPercent").draw();
}
function getPodMemory (podName) {
	new Client().url("/api/v1/kubernetes/pod/" + podName + "/memory/used/percent?startTime=1h&time=5s&limit=1000")
	.callback(drawPodMemory).get();
}
function drawPodMemory (data) {
	new BasicLine().area("memoryChartArea").colors(colors.memoryUsed).data(data, "memoryUsedPercent", "memoryUsedPercent").draw();
}
new Client().url("/api/v1/kubernetes/pod/snapshot").callback(drawPod).get();
</script>