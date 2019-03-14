<div class="m-subheader">
	<div class="d-flex align-items-center">
		<div class="mr-auto">
			<h3 class="m-subheader__title">
				Resource
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
							<div class="m-portlet__head-text">Container</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="hostContainerArea"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Host</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="hostChartArea"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script src="/resources/js/module/common/colors.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/scatter.js" type="text/javascript"></script>
<script type="text/javascript">
function drawHost(data){
	var chartData = [];
	data.forEach(function (item){
		var m = {};
		m.name = item.host_name + "[" + item.host_ip + "]";
		m.data = [{ y:item.cpu.cpu_user_per, x:item.mem.used_per}];
		if (item.mem.used_per >= 80 || item.cpu.cpu_user_per >= 80) m.color = '#f4516c';
		else m.color = '#00c5dc';
		chartData.push(m);
	})
	new Scatter().area("hostChartArea").data(chartData).draw();
}
function drawContainer(data){
	var chartData = [];
	Object.keys(data).forEach(function (key){
		data[key].containers.forEach(function (container){
			if (container.Labels["io.kubernetes.docker.type"] != "podsandbox") {
				var m = {};
				m.name = container.Labels["io.kubernetes.container.name"] + " [" + key + "]";
				m.data = [{ y:container.cpuPercent, x:container.memPercent}];
				if (container.cpuPercent >= 80 || container.memPercent >= 80) m.color = '#f4516c';
				else m.color = '#00c5dc';
				chartData.push(m);
			}
		})
	})
	new Scatter().area("hostContainerArea").data(chartData).draw();
}
function refresh() {
	new Client().url("/api/v1/host/snapshot").callback(drawHost).get(); 
	new Client().url("/api/v1/host/docker/snapshot").callback(drawContainer).get();
}
refresh();
refreshInterval = setInterval("refresh()", 10000); 
</script>