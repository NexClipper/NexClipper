<div class="m-subheader">
	<div class="d-flex align-items-center">
		<div class="mr-auto">
			<h3 class="m-subheader__title">
				Pod
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
		<div class="col-lg-6">
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
							<div class="m-portlet__head-text">Pod List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="podListTableArea"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script src="/resources/js/module/common/colors.js" type="text/javascript"></script>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script type="text/javascript">
function drawChart (podList) {
	drawInitChart("cpu", podList);
	drawInitChart("memory", podList);
}
function drawInitChart(resource, podList) {
	var podName = podList[0];
	new Client().url("/api/v1/kubernetes/pod/" + podName + "/" + resource + "/used/percent?startTime=1h&time=5s&limit=1000")
	.callback(
		function(data, podName){
			var chart = new BasicLine().area(resource + "ChartArea").data(data, resource + "UsedPercent", podName).draw();
			drawAppendChart(resource, podList, chart);
		}
	).bindData(podName).get();
}
function drawAppendChart(resource, podList, chart) {
	podList.forEach(function(pod, idx){
		if (idx != 0) {
			new Client().url("/api/v1/kubernetes/pod/" + pod + "/" + resource + "/used/percent?startTime=1h&time=5s&limit=1000")
			.callback(
				function(data, podName){
					chart.appendData(data, resource + "UsedPercent", podName);
				}
			).bindData(pod).get();
		}
	});
}
function searchCheckPod() {
	var podList = [];
	$('input:checkbox[type=checkbox]:checked').each(function () {
		if( this.value != "on" ) {
    		podList.push(this.value);
		}
	});
	return podList;
}
function mdtEvent() {
	$('#podListTableArea').on('click', 'input:checkbox[type=checkbox]', function() {
		drawChart(searchCheckPod());
	});
}
function drawPodList (data) {
	var tableData = [];
	data.items.forEach(function (item){
		var m = {
			key : item.metadata.name,
			name : item.metadata.name,
			hostIp : item.status.hostIP,
			namespace : item.metadata.namespace,
			kind : item.metadata.ownerReferences[0].kind,
			cpu : Number(item.resource.used_percent.cpu),
			memory : Number(item.resource.used_percent.memory),
			status : item.status.phase
		}
		tableData.push(m);
	})
	var columns = [
		{ 
			field : "key", 
			title : "#", 
			width: 50,
			sortable: false,
			selector: {class: 'm-checkbox--solid m-checkbox--brand'}
		},
		{ 
			field : "name", 
			title : "Name", 
			width : 100,
			template : function(row){
				return "<a href='/kubernetes/pod/"+row.name+"/detail'><span style='cursor:pointer;' class='font-weight-bold m--font-brand'>" + row.name + "</span></a>";
			}
		},
		{ field : "hostIp", title : "Host Ip", width : 100},
		{ field : "namespace", title : "Namespace", width : 100},
		{ field : "kind", title : "Kind", width : 100},
		{ field : "cpu", title : "Cpu(%)", width : 100},
		{ field : "memory", title : "Memory(%)", width : 100},
		{ field : "status", title : "Status", width : 100}
	];
	new MDT().area("podListTableArea").columns(columns).data(tableData).event(mdtEvent).draw();
	$('input:checkbox[type=checkbox]:eq(1)').prop("checked", true);
	drawChart(searchCheckPod());
}
new Client().url("/api/v1/kubernetes/pod/snapshot").callback(drawPodList).get();
</script>