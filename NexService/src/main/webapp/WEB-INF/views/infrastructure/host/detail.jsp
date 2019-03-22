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
				Host Detail
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
							<div class="m-portlet__head-text">System</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget12" style="height: 250px;">
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Host</span> <div id="hostNameArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Ip</span> <div id="hostIpArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>OS</span> <div id="osArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Up Time</span> <div id="uptimeArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Cpu</span> <div id="cpuArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Memory</span> <div id="memoryArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span>Disk</span> <div id="diskArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Docker Version</span> <div id="dockerVersionArea"></div>
							</div>
						</div>
						<div class="m-widget12__item">
							<div class="m-widget12__text1">
								<span># Of Images</span> <div id="imagesArea"></div>
							</div>
							<div class="m-widget12__text2">
								<span>Running</span> <div id="runningArea"></div>
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
							<div class="m-portlet__head-text">Top Process</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id="topProcessArea" class="m-widget4 m-dashboard-widget4" style="overflow-x: hidden; height: 250px;"></div>
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
							<h3 class="m-portlet__head-text">CPU Usage</h3>
						</div>
					</div>
					<div class="m-portlet__head-tools">
						<ul class="nav nav-pills nav-pills--brand m-nav-pills--align-right m-nav-pills-btn-pill m-nav-pills--btn-sm">
							<li class="nav-item m-tabs__item">
								<button class="nav-link m-tabs__link active show" id = "hostCpuBtn" style = "cursor: pointer;">Detail</button>
							</li>
						</ul>
					</div>
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
							<div class="m-portlet__head-text">Memory Usage</div>
						</div>
					</div>
					<div class="m-portlet__head-tools">
						<ul class="nav nav-pills nav-pills--brand m-nav-pills--align-right m-nav-pills-btn-pill m-nav-pills--btn-sm">
							<li class="nav-item m-tabs__item"> 
								<button class="nav-link m-tabs__link active show" id = "memoryCpuBtn" style = "cursor: pointer;">Detail</button>
							</li>
						</ul>
					</div>
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
							<div class="m-portlet__head-text">Disk Usage</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="diskUsedListArea"></div>
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
							<div class="m-portlet__head-text">Network Usage</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="networkUsedListArea"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script src="/resources/js/module/common/colors.js" type="text/javascript"></script>
<script src="/resources/js/module/list/simpleList.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script type="text/javascript">
var hostIp = "${hostIp}";
$("#hostCpuBtn").click(function(){
	window.location = '/infrastructure/host/' + hostIp + '/cpu/detail';
})
$("#memoryCpuBtn").click(function(){
	window.location = '/infrastructure/host/' + hostIp + '/memory/detail';
})
function drawHost (data) {
	drawSystem(data);
	drawTopProcess(data.process);
	drawDiskUsage(data.disks);
	drawNetworkUsage(data.net.ifaces);
}
function drawSystem (data) {
	console.log(data)
	$("#hostNameArea").empty().text(data.host_name);
	$("#hostIpArea").empty().text(data.host_ip);
	$("#osArea").empty().text(data.vendorName + "[" + data.vendor + " " + data.vendorVersion + " " + data.version + "]");
	$("#uptimeArea").empty().text(data.uptime);
	$("#cpuArea").empty().text(data.cpu.cpu_total);
	$("#memoryArea").empty().text((data.mem.total / 1024 / 1024 / 1024).toFixed(2) + " GB");
	$("#dockerVersionArea").empty().text(data.cpu.cpu_total);
	$("#imagesArea").empty().text(data.cpu.cpu_total);
	$("#runningArea").empty().text(data.cpu.cpu_total);
	
	runningArea
	var diskTotal = 0;
	data.disks.forEach(function(item) {
		diskTotal += item.total;
	})
	$("#diskArea").empty().text((diskTotal / 1024 / 1024).toFixed(2) + " GB");
}
function drawTopProcess (process) {
	var listData = [];
	process.forEach(function(p) {
		var m = {
			pid : p.pid,
			cpu : p.cpu_usage + " (%)",
			memory : (p.mem_share/1024/1024).toFixed(2) + " MB",
			majorFaults : p.majorFaults,
			minorFaults : p.minorFaults,
			name : p.name
		}
		listData.push(m);
	})
	var fields = ['pid','cpu','memory','majorFaults','minorFaults','name'];
	new SimpleList().area("topProcessArea").fields(fields).data(listData).draw();
}
new Picker().refreshFunction(timeRefresh).draw();
function timeRefresh (startTime, time) {
	drawCpuUsage (startTime, time);
	drawMemoryUsage (startTime, time);
}
function drawCpuUsage (startTime, time) {
	new Client().url("/api/v1/host/" + hostIp + "/cpu/used/percent?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(function(data){
		new BasicLine().area("cpuChartArea").colors(colors.cpuUsed).data(data, "used", "Cpu Usage").draw();
	}).get();
}
function drawMemoryUsage (startTime, time) {
	new Client().url("/api/v1/host/" + hostIp + "/memory/used/percent?startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(function(data){
		new BasicLine().area("memoryChartArea").setYAxisMax(100).colors(colors.cpuTotal).data(data, "mem_used_percent", "Memory Usage").draw();
	}).get();
}
function drawDiskUsage (disks) {
	var tableData = [];
	disks.forEach(function (disk){
		var m = {
			devName : disk.devName,
			mountName : disk.mountName,
			options : disk.options,
			sysType : disk.sysType,
			usedPer : disk.used_per,
			total : (disk.total / 1024 / 1024).toFixed(2) + " GB",
			free : (disk.free/ 1024 / 1024).toFixed(2) + " GB"
		}
		tableData.push(m);
	});
	var columns = [
		{ 
			field : "devName", 
			title : "Device", 
			width : 200 , 
			template : function(row){
				return "<a href='/infrastructure/host/"+hostIp+"/disk/detail?mountName=" + row.mountName + "'><span style='cursor:pointer;' class='font-weight-bold m--font-brand'>" + row.devName + "</span></a>";
			} 
		},
		{ field : "mountName", title : "Mount Name", width : 100},
		{ field : "options", title : "Options", width : 100},
		{ field : "sysType", title : "Type", width : 100},
		{ field : "usedPer", title : "Used Percent", width : 100},
		{ field : "total", title : "Total", width : 100},
		{ field : "free", title : "Free", width : 100}
	];
	new MDT().area("diskUsedListArea").columns(columns).data(tableData).draw();
}
function drawNetworkUsage (ifaces) {
	var tableData = [];
	ifaces.forEach(function (iface){
		var m = {
			name : iface.name,
			macAddr : iface.macAddr,
			address : iface.address,
			rxBytes : iface.rxBytes,
			rxErrors : iface.rxErrors,
			txBytes : iface.txBytes,
			txErrors : iface.txErrors
		}
		tableData.push(m);
	});
	var columns = [
		{ field : "name", title : "Interface", width : 100, 
			template : function(row){
				return "<a href='/infrastructure/host/"+hostIp+"/network/"+row.name+"/detail'><span style='cursor:pointer;' class='font-weight-bold m--font-brand'>" + row.name + "</span></a>";
			}
		},
		{ field : "macAddr", title : "Mac", width : 100},
		{ field : "address", title : "IPs", width : 100},
		{ field : "rxBytes", title : "Rx Bytes", width : 100},
		{ field : "rxErrors", title : "Rx Errors", width : 100},
		{ field : "txBytes", title : "Tx Bytes", width : 100},
		{ field : "txErrors", title : "Tx Errors", width : 100}
	];
	new MDT().area("networkUsedListArea").columns(columns).data(tableData).draw();
}
new Client().url("/api/v1/host/" + hostIp + "/snapshot").callback(drawHost).get();
</script>