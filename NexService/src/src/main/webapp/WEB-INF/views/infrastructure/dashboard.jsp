<div class="m-subheader">
	<div class="d-flex align-items-center">
		<div class="mr-auto">
			<h3 class="m-subheader__title">
				Infrastructure
			</h3>
		</div>
	</div>
</div>
<div class="m-content">
	<div class="row">
		<div class="col-lg-4">
			<div class="row">
				<div class="col-lg-12">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text">Summary</div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget12" style="height: 130px;">
								<div class="m-widget12__item">
									<div class="m-widget12__text1">
										<span>Host</span> <div id="hostCountArea"></div>
									</div>
									<div class="m-widget12__text2">
										<span>Cpu Core</span> <div id="cpuCoreArea"></div>
									</div>
								</div>
								<div class="m-widget12__item">
									<div class="m-widget12__text1">
										<span>Memory Total</span> <div id="memoryTotalArea"></div>
									</div>
									<div class="m-widget12__text2">
										<span>Disk Total</span> <div id="diskTotalArea"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
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
								<div id = "hostChartArea" class="m-widget14__chart m--margin-bottom-5" style="height: 120px;">
									<div class="m-widget14__stat">
										<a href="#" class="btn-link m--font-metal"><strong id="totalHost"></strong></a>
									</div>	
								</div>
							</div>
						</div>
					</div>
 				</div>
			</div>
		</div>
		<div class="col-lg-8">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Infrastructure Map</div>
						</div>
					</div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="text-align: center;">
						<div id = "infrastructureMapChartArea" style="height: 346px;">
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
							<div class="m-portlet__head-text">Top Cpu</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id="topCpuChart" style="height: 200px;"></div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Top Memory</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id="topMemChart" style="height: 200px;"></div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Top Disk</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id="topDiskChart" style="height: 200px;"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<link href="/resources/css/billboard/billboard.min.css" rel="stylesheet" >
<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script src="/resources/js/module/common/colors.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/donut.js" type="text/javascript"></script>
<script src="/resources/js/module/list/simpleList.js" type="text/javascript"></script>
<script src="/resources/js/lib/billboard/d3.min.js" type="text/javascript"></script>
<script src="/resources/js/lib/billboard/billboard.min.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/tilemap.js" type="text/javascript"></script>
<style type="text/css">
.histogramLine60 line {
    stroke: #ffb822;
    stroke-width: 2;
    stroke-dasharray: 2,2;
}
.histogramLine80 line {
    stroke: #f4516c;
    stroke-width: 2;
    stroke-dasharray: 2,2;
}
</style>
<script type="text/javascript">
function drawHost (data) {
	drawSummary(data);
	drawHostCountChart(data.length);
	drawTopChart(data);
	drawInfrastructureMap(data);
}
function drawInfrastructureMap (data) {
	var tilemapData = [];
	data.forEach(function (item){
		tilemapData.push({'hc-a2' : item.host_name, name : item.host_ip, value: item.cpu.cpu_per });
	})
	new Tilemap().area("infrastructureMapChartArea").data(tilemapData).draw();
}
function drawSummary(data){
	$("#hostCountArea").text(data.length);
	var cpuTotal = 0;
	var memoryTotal = 0;
	var diskTotal = 0;
	data.forEach(function(item){
		cpuTotal += item.cpu.cpu_total;
		memoryTotal += item.mem.total;
		item.disks.forEach(function(disk){
			diskTotal += disk.total;
		})
	})
	$("#cpuCoreArea").text(cpuTotal);
	
	$("#memoryTotalArea").text((memoryTotal/1024/1024/1024).toFixed(2) + " GB");
	$("#diskTotalArea").text((diskTotal/1024/1024).toFixed(2) + " GB");
}
function drawHostCountChart(count) {
	new Donut().area("hostChartArea").colors(colors.Running).data([{ label: "", value: count }]).draw();
	$("#totalHost").text(count);
}
function drawTopChart(data) {
	var formatHistogramData = formatHistogramChartData(data);
	drawHistogramChart("topCpuChart", formatHistogramData, "cpu", "#716aca");
	drawHistogramChart("topMemChart", formatHistogramData, "mem", "#f4516c");
	drawHistogramChart("topDiskChart", formatHistogramData, "disk", "#ffb822");
}
function drawHistogramChart (chartArea, chartData, key, color){
	var categories = [];
	var key_normal = key+"_normal";
	var key_warning = key+"_warning";
	var key_danger = key+"_danger";
	var columnNormal = [key_normal];
	var columnWarning = [key_warning];
	var columnDanger = [key_danger];
	var ipList = [];
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
			ipList.push(item.ip);
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
				fnHostDetail(ipList[d.index]);
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
new Client().url("/api/v1/host/snapshot").callback(drawHost).get();
</script>