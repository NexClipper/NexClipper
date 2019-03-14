<div class="m-subheader">
	<div class="d-flex align-items-center">
		<div class="mr-auto">
			<h3 class="m-subheader__title">
				Node
			</h3>
		</div>
	</div>
</div>
<div class="m-content">
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Node Summary</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14" style="max-width:800px; margin: 0 auto">
						<div id="nodeSummaryChartArea">
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
							<div class="m-portlet__head-text">Node List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="nodeListTableArea"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script src="/resources/js/module/common/colors.js" type="text/javascript"></script>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/tilemap.js" type="text/javascript"></script>
<script type="text/javascript">
function drawNode(data) {
	drawNodeSummary(data.items);
	drawNodeList(data);
}
function drawNodeList (data) {
	var tableData = [];
	data.items.forEach(function (item){
		var status;
		item.status.conditions.forEach(function (condition){
			if (condition.type == "Ready")
				status = condition.status;
		})
		var m = {
			name : item.metadata.name,
			nodeIp : item.metadata.node_ip,
			aCpu : Number(item.status.allocatable.cpu),
			aMemory : Number(item.status.allocatable.memory),
			cCpu : Number(item.status.capacity.cpu),
			cMemory : Number(item.status.capacity.memory),
			status : status
		}
		tableData.push(m);
	})
	var columns = [
		{ 
			field : "name", 
			title : "Name", 
			width : 100, 
			template : function(row){
				return "<a href='/kubernetes/node/"+row.nodeIp+"/detail'><span style='cursor:pointer;' class='font-weight-bold m--font-brand'>" + row.name + "</span></a>";
			}
		},
		{ field : "status", title : "Status", width : 100,
			template: function (row) {
				var color = "danger";
				if ("True" == row.status) {
					color = "success";
					status = "Ready";
				} else 
					status = "Not Ready";
				return "<span class='m-badge m-badge--"+color+" m-badge--wide'></span>&nbsp&nbsp<strong>"+status+"</strong>";
			}
		},
		{ field : "nodeIp", title : "Node Ip", width : 100},
		{ field : "aCpu", title : "Allocatable Cpu", width : 100},
		{ field : "aMemory", title : "Allocatable Memory", width : 100},
		{ field : "cCpu", title : "Capacity Cpu", width : 100},
		{ field : "cMemory", title : "Capacity Memory", width : 100}
	];
	new MDT().area("nodeListTableArea").columns(columns).data(tableData).draw();
}

function drawNodeSummary (data) {
	var tilemapData = [];
	data.forEach(function (item){
		tilemapData.push({'hc-a2' : item.metadata.node_name, name : item.metadata.node_ip, value: 1 });
	})
	new Tilemap().area("nodeSummaryChartArea").data(tilemapData).clickEvent(function(){
		location.href = "/kubernetes/node/" + this.name + "/detail";
	}).draw();
}
new Client().url("/api/v1/kubernetes/node/snapshot").callback(drawNode).get();
</script>