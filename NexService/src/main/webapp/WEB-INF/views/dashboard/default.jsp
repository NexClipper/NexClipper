<div class="m-subheader">
	<div class="d-flex align-items-center">
		<div class="mr-auto">
			<h3 class="m-subheader__title">
				DashBoard
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
				<div class="col-lg-12">
					<div class="m-portlet m-portlet--head-sm m-portlet--full-height">
						<div class="m-portlet__head">
							<div class="m-portlet__head-caption">
								<div class="m-portlet__head-title">
									<div class="m-portlet__head-text">Container</div>
								</div>
							</div>
							<div class="m-portlet__head-tools"></div>
						</div>
						<div class="m-portlet__body">
							<div class="m-widget14" style="text-align: center;">
								<div id = "podChartArea" class="m-widget14__chart m--margin-bottom-5" style="height: 150px;">
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
						<div id = "infrastructureMapChartArea" style="height: 445px;">
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
							<div class="m-portlet__head-text">Agent List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						
					<div class="m-widget4" style="overflow-x: hidden; height: 200px;" id="agentListArea" >
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
							<div class="m-portlet__head-text">DC/OS Cluster</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="text-align: center;">
						<div id = "podChartArea" style="height: 200px;">
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
							<div class="m-portlet__head-text">Kubernetes Cluster</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="text-align: center;">
			        	<div class = "row" style="height: 200px;">
			        		<div class = "col-lg-6">
			        			<div style="margin: 20px 0px 0px 0px; text-align: center;">
				        			<h3><b>Nodes</b></h3>
				        			<a href="#kube_node" onClick="/kubernetes/node/detail')" class="m-menu__link" style="color:black">
				        				<h1 style="font-size: 6rem; margin-top: 50px;" id = "nodeCount">0</h1>
				        			</a>
			        			</div>
			        		</div>
			        		<div class = "col-lg-6" style="border-left: 2px solid #00c5dc;">
			        			<div style="margin: 20px 0px 0px 0px; text-align: center;">
				        			<h3><b>Pods</b></h3>
				        			<a href="#kube_pod" onClick="/kubernetes/pod/detail')" class="m-menu__link" style="color:black">
				        				<h1 style="font-size: 6rem; margin-top: 50px;" id = "podCount">0</h1>
				        			</a>
			        			</div>
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
<script src="/resources/js/module/list/statusList.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/donut.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/tilemap.js" type="text/javascript"></script>
<script type="text/javascript">
function drawAgents(data) {
	var fields = ['host_name','host_ip', 'status'];
	new StatusList().area("agentListArea").fields(fields).data(data).draw();
}
function drawNodeCount (data) {
	$("#nodeCount").text(data.items.length);
}
function drawPodCount (data) {
	$("#podCount").text(data.items.length);
}
function drawHost (data) {
	new Donut().area("hostChartArea").colors(colors.Running).data([{ label: "", value: data.length }]).draw();
	$("#totalHost").text(data.length);
	drawInfrastructureMap(data);
}
function drawInfrastructureMap (data) {
	var tilemapData = [];
	data.forEach(function (item){
		tilemapData.push({'hc-a2' : item.host_name, name : item.host_ip, value: item.cpu.cpu_per });
	})
	new Tilemap().area("infrastructureMapChartArea").data(tilemapData).draw();
}
new Client().url("/api/v1/kubernetes/node/snapshot").callback(drawNodeCount).get();
new Client().url("/api/v1/kubernetes/pod/snapshot").callback(drawPodCount).get();
new Client().url("/api/v1/host/snapshot").callback(drawHost).get();
new Client().url("/api/v1/host/agent/status").callback(drawAgents).get();
</script>