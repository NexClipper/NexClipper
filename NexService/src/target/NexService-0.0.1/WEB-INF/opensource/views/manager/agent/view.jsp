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
				Agent
			</h3>
		</div>
		<div class="dropdown" id = "clusterArea"></div>
	</div>
</div>
<div class="m-content">
	<div class="row">
		<div class="col-lg-12">
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
						<div id="agentListTableArea"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script type="text/javascript">
var clusterInfo = "${clusterInfo}";
var clusterId = clusterInfo.split("@")[0];
var clusterName = clusterInfo.split("@")[1];
function drawAgents (data) {
	var tableData = [];
	data.forEach(function (item){
		var m = {
			hostIp : item.host_ip,
			hostName : item.host_name,
			startTime : item.start_time,
			lastUpdate : item.last_update,
			timestamp : item.timestamp,
			version : item.version,
			status : item.status
		}
		tableData.push(m);
	})
	var columns = [
		{ field : "hostIp", title : "Host Ip", width : 100,
			template : function(row) {
				return "<span class='font-weight-bold m--font-brand'>" + row.hostIp + "</span>";
			}
		},
		{ field : "hostName", title : "Host Name", width : 100,
			template : function(row) {
				return "<span class='m--font-bold m--font-brand'>" + row.hostName + "</span>";
			}
		},
		{ field : "version", title : "version", width : 100,
			template : function(row) {
				return "<span class='m--font-bold m--font-brand'>" + row.version + "</span>";
			}
		},
		{ field : "status", title : "status", width : 100,
			template : function(row) {
				var color = { 'INACTIVE'	: 'danger', 'ACTIVE'	: 'success' }
				return '<span class="m-badge m-badge--' + color[row.status] + ' m-badge--dot"></span>&nbsp;<span class="m--font-bold m--font-' + color[row.status] + '">' + row.status + '</span>';
			}
		},
		{ field : "startTime", title : "startTime", width : 100,
			template : function(row) {
				return "<span class='m--font-bold m--font-brand'>" + row.startTime + "</span>";
			}
		}
	];
	new MDT().area("agentListTableArea").columns(columns).data(tableData).makeSearch().draw();
}
new Client().url("/api/v1/host/agent/status").callback(drawAgents).setClusterId(clusterId).get();
new Client().url("/api/v1/cluster").callback(function(data){
	var r = '<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">' + clusterName + '</button>';
	r += '<div class="dropdown-menu">';
	data.forEach(function(item){
		r += '<a class="dropdown-item" href="/manager/agent/' + item.clusterId + '@' + item.clusterName + '" style = "color: #ffffff;">' + item.clusterName + '</a>';
	})
	r += '</div>';
	$("#clusterArea").empty().append(r);
}).getNotCluster();
</script>