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
				Container
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
							<div class="m-portlet__head-text">Container Info</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget12" style="height: 300px; overflow-x: hidden;">
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Container Name</span> <div id="containerNameArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Docker Type</span> <div id="dockerTypeArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Pod Name</span> <div id="podNameArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Namespace</span> <div id="podNamespaceArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Status</span> <div id="statusArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>State</span> <div id="stateArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Image</span> <div id="imageArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text2">
								<span>Command</span> <div id="commandArea"></div>
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
							<div class="m-portlet__head-text">Docker Server Info</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget12" style="height: 300px;">
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Host Ip</span> <div id="hostIpArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Containers</span> <div id="containersArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Kernel Version</span> <div id="kernelVersionArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Host Name</span> <div id="hostNameArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Operating System</span> <div id="operatingSystemArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Server Version</span> <div id="serverVersionArea"></div>
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
							<div class="m-portlet__head-text">Disk Write</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="diskWriteChartArea" style="height: 250px;"></div>
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
							<div class="m-portlet__head-text">Disk Read</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="diskReadChartArea" style="height: 250px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/list/simpleList.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script type="text/javascript">
var containerId = "${container}";
var hostIp = "${hostIp}";
console.log(containerId);
console.log(hostIp);
function drawContainer (data) {
	var containerInfo;
	var dockerServerInfo;
	Object.keys(data).forEach(function(key) {
		data[key].containers.forEach(function(container){
			if (containerId == container.Id) {
				containerInfo = container;
				dockerServerInfo = data[key].docker_info;
				dockerServerInfo.ip = key;
			}
		})
	})
	drawContainerInfo(containerInfo);
	drawDockerServerInfo(dockerServerInfo);
}
function drawContainerInfo (ci) {
	$("#containerNameArea").text(ci.Labels["io.kubernetes.container.name"]);
	$("#dockerTypeArea").text(ci.Labels["io.kubernetes.docker.type"]);
	$("#podNameArea").text(ci.Labels["io.kubernetes.pod.name"]);
	$("#podNamespaceArea").text(ci.Labels["io.kubernetes.pod.namespace"]);
	$("#statusArea").text(ci.Status);
	$("#stateArea").text(ci.State);
	$("#imageArea").text(ci.Image);
	$("#commandArea").text(ci.Command);
}
function drawDockerServerInfo(dsi) {
	$("#hostIpArea").text(dsi.ip);
	$("#containersArea").text(dsi.Containers);
	$("#kernelVersionArea").text(dsi.KernelVersion);
	$("#hostNameArea").text(dsi.Name);
	$("#operatingSystemArea").text(dsi.OperatingSystem);
	$("#serverVersionArea").text(dsi.ServerVersion);
}
new Client().url("/api/v1/host/docker/snapshot").callback(drawContainer).get();

new Picker().refreshFunction(timeRefresh).draw();
function timeRefresh (startTime, time) {
	new Client().url("/api/v1/host/" + hostIp + "/docker/container/" + containerId + "/cpu/usage?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(function(data){
		new BasicLine().area("cpuChartArea").data(data, "cpu_used_percent", containerId).draw();
	}).get();
	new Client().url("/api/v1/host/" + hostIp + "/docker/container/" + containerId + "/memory/usage?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(function(data){
		new BasicLine().area("memoryChartArea").data(data, "mem_used_percent", containerId).draw();
	}).get();
	new Client().url("/api/v1/host/" + hostIp + "/docker/container/" + containerId + "/diskio/write?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(function(data){
		new BasicLine().area("diskWriteChartArea").data(data, "disk_io_write", containerId).draw();
	}).get();
	new Client().url("/api/v1/host/" + hostIp + "/docker/container/" + containerId + "/diskio/read?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(function(data){
		new BasicLine().area("diskReadChartArea").data(data, "disk_io_read", containerId).draw();
	}).get();
}
</script>