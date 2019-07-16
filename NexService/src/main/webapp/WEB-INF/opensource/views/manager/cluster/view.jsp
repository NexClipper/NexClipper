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
				Cluster
			</h3>
		</div>
		<button type="button" class="btn btn-primary btn-pill" data-toggle="modal" data-target="#addClusterModal">Add Cluster</button>
	</div>
</div>
<div class="m-content">
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Cluster List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="clusterListTableArea"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="addClusterModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">New Cluster</h5>
			</div>
			<div class="modal-body">
				<div id = "msgDiv"></div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Cluster Name :</label>
					<input type="text" class="form-control" id="clusterName">
				</div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Description :</label>
					<textarea class="form-control" id="description" rows="6"></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id = "addClusterBtn">Add Cluster</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="updateClusterModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Cluster</h5>
			</div>
			<div class="modal-body">
				<div id = "updateMsgDiv"></div>
				<input type="hidden" id="updateClusterId">
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Cluster Name :</label>
					<input type="text" class="form-control" id="updateClusterName">
				</div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Description :</label>
					<textarea class="form-control" id="updateDescription" rows="6"></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id = "updateClusterBtn">Update Cluster</button>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script type="text/javascript">
$("#addClusterBtn").click(function(){
	var m = {
		clusterName : $("#clusterName").val(),
		description : $("#description").val()
	};
	new Client().url("/api/v1/cluster").data(m).callback(function(data){
		if (data == "success")
			location.href = "/manager/cluster";
		else
			msgBox("msgDiv", data)
	}).post();
})
function drawCluster(data){
	var columns = [
		{ field : "clusterName", title : "Cluster Name",
			template : function(row) {
				return "<span class='font-weight-bold m--font-brand'><a href='/manager/agent/" + row.clusterId + "@" + row.clusterName + "'>" + row.clusterName + "</a></span>";
			}
		},
		{ field : "description", title : "Description",
			template : function(row) {
				return "<span class='font-weight-bold m--font-brand'>" + row.description + "</span>";
			}
		},
		{ field : "clusterStatus", title : "Cluster Status",
			template : function(row) {
				return "<span class='font-weight-bold m--font-brand'>" + row.clusterStatus + "</span>";
			}
		},
		{ field : "#1",
			template : function(row) {
				return '<button type="button" class="btn btn-primary btn-pill updateBtn" clusterInfo= "' + row.clusterId + "=" + row.clusterName + '">Update</button>';
			}
		},
		{ field : "#2",
			template : function(row) {
				return '<button type="button" class="btn btn-primary btn-pill deleteBtn" clusterId = "' + row.clusterId + '">Delete</button>';
			}
		}
	];
	new MDT().area("clusterListTableArea").columns(columns).data(data).makeSearch().draw();
	$(".deleteBtn").click(function(){
		var clusterId = $(this).attr("clusterId");
		new Client().url("/api/v1/cluster/delete").data({ clusterId : clusterId }).callback(function(data){
			location.href = "/manager/cluster";
		}).post();
	})
	$(".updateBtn").click(function(){
		var clusterInfo = $(this).attr("clusterInfo");
		$("#updateClusterId").val(clusterInfo.split("=")[0]);
		$("#updateClusterName").val(clusterInfo.split("=")[1]);
		$("#updateClusterModal").modal();
	})
}
$("#updateClusterBtn").click(function(){
	var m = {
			clusterId : $("#updateClusterId").val(),
			clusterName : $("#updateClusterName").val(),
			description : $("#updateDescription").val()
		};
		new Client().url("/api/v1/cluster/update").data(m).callback(function(data){
			if (data == "success")
				location.href = "/manager/cluster";
			else
				msgBox("updateMsgDiv", data)
		}).post();
})
new Client().url("/api/v1/cluster").callback(drawCluster).getNotCluster();

function msgBox (msgDiv, msg) {
	var color = "danger";
	if (msg == "success") {
		color = "success";
	}
	var html = '<div class="m-alert m-alert--outline alert alert-' + color + ' alert-dismissible" role="alert">';
	html += '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>';
	html += '<span>' + msg + '</span>';
	html += '</div>';
	document.getElementById(msgDiv).innerHTML = html;
}
</script>