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
				Account
			</h3>
		</div>
		<button id = "addModalBtn" type="button" class="btn btn-primary btn-pill" data-toggle="modal" data-target="#addModal" style = "display: none;">Add Account</button>
	</div>
</div>
<div class="m-content">
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Account List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="accountListTableArea"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">New Account</h5>
			</div>
			<div class="modal-body">
				<div id = "msgDiv"></div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Email :</label>
					<input type="text" class="form-control" id="userId">
				</div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Company Name :</label>
					<input type="text" class="form-control" id="companyName">
				</div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Password:</label>
					<input type="password" class="form-control" id="password">
				</div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Confirm Password :</label>
					<input type="password" class="form-control" id="rpassword">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id = "addAccountBtn">Add Account</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="changeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Change Password</h5>
			</div>
			<div class="modal-body">
				<div id = "cpMsgDiv"></div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Email :</label>
					<input type="text" class="form-control" id="cpUserId" disabled>
				</div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Password:</label>
					<input type="password" class="form-control" id="cpUserPassword">
				</div>
				<div class="form-group">
					<label for="recipient-name" class="form-control-label">Confirm Password :</label>
					<input type="password" class="form-control" id="cpUserRpassword">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id = "changePasswordBtn">Change Password</button>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script type="text/javascript">
var USER = [];
function drawUser (data) {
	if (data.length > 1 || (data.length == 1 && data[0].userId == "root")) 
		$("#addModalBtn").show();
	USER = data;
	var tableData = [];
	data.forEach(function (item){
		var m = {
			userId : item.userId,
			userName : item.userName,
			companyName : item.companyName,
			regdate : item.regdate
		}
		tableData.push(m);
	})
	var columns = [
		{ field : "userId", title : "User Id",
			template : function(row) {
				return "<span class='font-weight-bold m--font-brand'>" + row.userId + "</span>";
			}
		},
		{ field : "userName", title : "User Name",
			template : function(row) {
				return "<span class='font-weight-bold m--font-brand'>" + row.userName + "</span>";
			}
		},
		{ field : "companyName", title : "Company Name",
			template : function(row) {
				return "<span class='font-weight-bold m--font-brand'>" + row.companyName + "</span>";
			}
		},
		{ field : "regdate", title : "Regist Date",
			template : function(row) {
				return "<span class='font-weight-bold m--font-brand'>" + row.regdate + "</span>";
			}
		},
		{ field : "#1",
			template : function(row) {
				return '<button type="button" class="btn btn-primary btn-pill changeBtn" userId = "' + row.userId + '">Change Password</button>';
			}
		},
		{ field : "#2",
			template : function(row) {
				if (row.userId == "root")
					return '';
				else 
					return '<button type="button" class="btn btn-primary btn-pill deleteBtn" userId = "' + row.userId + '">Delete</button>';
			}
		}
	];
	new MDT().area("accountListTableArea").columns(columns).data(tableData).makeSearch().draw();
	$(".changeBtn").click(function(){
		var userId = $(this).attr("userId");
		$("#cpUserId").val(userId);
		$("#changeModal").modal();
	})
	$(".deleteBtn").click(function(){
		var userId = $(this).attr("userId");
		new Client().url("/api/v1/account/delete").data({ userId : userId }).callback(function(data){
			if (userId == "root")
				location.href = "/account/default";
			else
				location.href = "/user/logout";
		}).post();
	})
}
$("#changePasswordBtn").click(function(){
	var cpUserId = $("#cpUserId").val();
	var pw1 = $("#cpUserPassword").val();
	var pw2 = $("#cpUserRpassword").val();
	var flag = true;
	if (pw1 == "" || pw2 == ""){
		msgBox("cpMsgDiv", "Password is empry.");
		flag = false;
	}
	if (pw1 != pw2){
		msgBox("cpMsgDiv", "The passwords are not the same.");
		flag = false;			
	}
	if (flag) {
		new Client().url("/api/v1/account/changePassword").data({userId : cpUserId,password : pw1}).callback(function(data){
			location.href = "/account/default";
		}).post();
	}
		
})
$("#addAccountBtn").click(function(){
	var m = {
		userId : $("#userId").val(),
		companyName : $("#companyName").val(),
		password : $("#password").val(),
		rpassword : $("#rpassword").val(),
		"${_csrf.parameterName}" : "${_csrf.token}"
	};
	new Client().url("/api/v1/account").data(m).callback(function(data){
		if (data == "success")
			location.href = "/account/default";
		else
			msgBox("msgDiv", data)
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
new Client().url("/api/v1/account").callback(drawUser).get();
</script>