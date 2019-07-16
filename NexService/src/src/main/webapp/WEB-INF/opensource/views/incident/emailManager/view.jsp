<%@ page contentType="text/html; charset=utf-8"%>
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
				Event Alert Email Manager
			</h3>
		</div>
		<button id = "addSendModalBtn" type="button" class="btn btn-primary btn-pill" data-toggle="modal" data-target="#addSendModal">Add Send Email</button>
		<button id = "addReceiveModalBtn" type="button" class="btn btn-primary btn-pill" data-toggle="modal" data-target="#addReceiveModal" style="display: none;">Add Receive Email</button>
	</div>
</div>
<div class="m-content">
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Send Email</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="sendEmailTableArea" style="height: 250px;"></div>
					</div>
				</div>
			</div>
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
							<div class="m-portlet__head-text">Receive Email List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="receiveEmailTableArea" style="height: 250px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="addSendModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">New Send Email</h5>
			</div>
			<div class="modal-body">
				<div id = "sendMsgDiv"></div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Email :</label>
					<input type="text" class="form-control" id="sendEmail">
				</div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Password:</label>
					<input type="password" class="form-control" id="password">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id = "addSendEmailBtn">Add Send Email</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="addReceiveModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">New Send Email</h5>
			</div>
			<div class="modal-body">
				<div id = "receiveMsgDiv"></div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Email :</label>
					<input type="text" class="form-control" id="receiveEmail">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id = "addReceiveEmailBtn">Add Send Email</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="updateEmailModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Email</h5>
			</div>
			<div class="modal-body">
				<div id = "updateMsgDiv"></div>
				<input type="hidden" id="updateEmailNo">
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Email :</label>
					<input type="text" class="form-control" id="updateEmail">
				</div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Password:</label>
					<input type="password" class="form-control" id="updateEmailPassword">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id = "updateEmailBtn">Update Email</button>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script type="text/javascript">
var USER = [];
function drawEmail (data) {
	var sendEmail = [];
	var receiveEmail = [];
	data.forEach(function (email){
		if (email.emailType == "S") {
			sendEmail.push(email);
			$("#addSendModalBtn").hide();
			$("#addReceiveModalBtn").show();
		} else
			receiveEmail.push(email);
	})
	var sendColumns = [
		{ field : "email", title : "Email",
			template : function(row) {
				return "<span class='font-weight-bold m--font-brand'>" + row.email + "</span>";
			}
		},
		{ field : "#1",
			template : function(row) {
				return '<button type="button" class="btn btn-primary btn-pill updateBtn" emailInfo= "' + row.emailNo + "=" + row.email + '">Update</button>';
			}
		}
	];
	new MDT().area("sendEmailTableArea").columns(sendColumns).data(sendEmail).draw();
	
	var receiveColumns = [
		{ field : "email", title : "Email",
			template : function(row) {
				return "<span class='font-weight-bold m--font-brand'>" + row.email + "</span>";
			}
		},
		{ field : "#1",
			template : function(row) {
				return '<button type="button" class="btn btn-primary btn-pill deleteBtn" emailNo = "' + row.emailNo + '">Delete</button>';
			}
		}
	];
	new MDT().area("receiveEmailTableArea").columns(receiveColumns).data(receiveEmail).makeSearch().draw();
	$(".deleteBtn").click(function(){
		var emailNo = $(this).attr("emailNo");
		new Client().url("/api/v1/email/delete").data({ emailNo : emailNo }).callback(function(data){
			location.href = "/incident/emailManager";
		}).post();
	})
	$(".updateBtn").click(function(){
		var emailInfo = $(this).attr("emailInfo");
		$("#updateEmailNo").val(emailInfo.split("=")[0]);
		$("#updateEmail").val(emailInfo.split("=")[1]);
		$("#updateEmailModal").modal();
	})
}
$("#addSendEmailBtn").click(function(){
	var m = {
		email : $("#sendEmail").val(),
		emailType : "S",
		emailPassword : $("#password").val()
	};
	new Client().url("/api/v1/email").data(m).callback(function(data){
		if (data == "success")
			location.href = "/incident/emailManager";
		else
			msgBox("sendMsgDiv", data)
	}).post();
})
$("#updateEmailBtn").click(function(){
	var m = {
			emailNo : $("#updateEmailNo").val(),
			email : $("#updateEmail").val(),
			emailPassword : $("#updateEmailPassword").val()
		};
		new Client().url("/api/v1/email/update").data(m).callback(function(data){
			if (data == "success")
				location.href = "/incident/emailManager";
			else
				msgBox("updateMsgDiv", data)
		}).post();
})
$("#addReceiveEmailBtn").click(function(){
	var m = {
		email : $("#receiveEmail").val(),
		emailType : "R",
		emailPassword : ""
	};
	new Client().url("/api/v1/email").data(m).callback(function(data){
		if (data == "success")
			location.href = "/incident/emailManager";
		else
			msgBox("receiveMsgDiv", data)
	}).post();
})
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
new Client().url("/api/v1/email").callback(drawEmail).getNotCluster();
</script>
