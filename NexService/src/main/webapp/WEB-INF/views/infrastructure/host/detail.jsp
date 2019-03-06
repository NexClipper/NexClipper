<div class="m-subheader">
	<div class="d-flex align-items-center">
		<div class="mr-auto">
			<h3 class="m-subheader__title">
				Host Detail
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
		<div class="col-lg-6">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Cpu Usage</div>
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
							<div class="m-portlet__head-text">Memory Usage</div>
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
function drawHost (data) {
	console.log(data);
	
	drawSystem(data);
	drawTopProcess(data.process);
	
	drawCpuUsage();
	drawMemoryUsage();
	
	drawDiskUsage(data.disks);
	drawNetworkUsage(data.net.ifaces);
}
function drawSystem (data) {
	$("#hostNameArea").empty().text(data.host_name);
	$("#hostIpArea").empty().text(data.host_ip);
	$("#osArea").empty().text(data.vendorName + "[" + data.vendor + " " + data.vendorVersion + " " + data.version + "]");
	$("#uptimeArea").empty().text(data.uptime);
	$("#cpuArea").empty().text(data.cpu.cpu_total);
	$("#memoryArea").empty().text((data.mem.total / 1024 / 1024 / 1024).toFixed(2) + " GB");
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
function drawCpuUsage () {
	new Client().url("/api/v1/host/" + hostIp + "/cpu/used/percent?startTime=1h&time=10s&limit=1000").callback(function(data){
		new BasicLine().area("cpuChartArea").colors(colors.cpuUsed).data(data, "used").draw();
	}).get();
}
function drawMemoryUsage () {
	new Client().url("/api/v1/host/" + hostIp + "/memory/used/percent?startTime=1h&time=10s&limit=1000").callback(function(data){
		new BasicLine().area("memoryChartArea").colors(colors.cpuTotal).data(data, "mem_used_percent").draw();
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
			width : 100/* , 
			template : function(row){
				console.log(row.devName);
				return "<a href='/infrastructure/host/"+row.devName+"/detail'><span style='cursor:pointer;' class='font-weight-bold m--font-brand'>" + row.devName + "</span></a>";
			} */
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
		{ field : "name", title : "Interface", width : 100},
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