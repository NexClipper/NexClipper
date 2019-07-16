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
<%@ page contentType="text/html; charset=utf-8"%>

<div class="m-content">
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Rule Manager</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				
				<div class="m-portlet__body">
                	<!--begin: Search Form -->
                    <div class="m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30">
                        <div class="row align-items-center">
                            <div class="col-xl-12 col-lg-12">
                                <div class="form-group m-form__group row align-items-center">
                                	
                                    <div class="col-lg-6 col-xl-4">

                                        <div class="m-form__group m-form__group--inline">
                                            <div class="m-form__label">
                                                <label>
                                                    Filter_By
                                                </label>
                                            </div>
                                            <div class="m-form__control">
                                                <div class="row">
                                                    <div class="col">
                                                        <select class="form-control m-input form-filters" name="target_system" id="target_system">
                                                            <option value="">Select System</option>
                                                        </select>
                                                    </div>
                                                    
                                                    <div class="col">
                                                        <select class="form-control m-input form-filters" name="target" id="target">
                                                            <option value="">Select Target</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-xl-none m--margin-bottom-10"></div>
                                    </div>
                                    
                                    
                                    <div class="col-lg-4 col-xl-4">
                                        <div class="form-group m-form__group">
                                            <div class="input-group">
                                                <input type="text" class="form-control" placeholder="Search for..." aria-label="Search for..." aria-describedby="basic-addon2" value="" id="searchTxt" OnKeyDown="f_fnSearch();">
                                                <div class="input-group-append">
                                                    <button class="btn btn-success" type="button" onClick="fnSearch()">Search</button>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div>
										<button class="btn btn-brand" type="button" onClick="location.href='/incident/rule/create';" style="float:right">Create</button>
									</div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!--end: Search Form -->
					<div id="containerListTableArea"></div>
			</div>
			
			</div>
		</div>
	</div>
</div>

<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script type="text/javascript">
var isProcess = false;
// rule List 
function drawContainerList (data) {
	var tableData = [];
	data.forEach(function(item){
		if( item.status == "Y" )
			item.status	= "Active";
		else
			item.status	= "InActive";
		
		var m = {
				'id' 			: item.idx,
                'target_system' : item.target_system,
                'severity' 		: item.severity,
                'target' 		: item.target,
                'type' 			: item.type,
                'data_source' 	: item.data_source,
                'table' 		: item.table,
                'metric' 		: item.metric,
                'group_by' 		: item.group_by,
                'condition' 	: item.condition,
                'message' 		: item.message,
                'status' 		: item.status,
                'regdt' 		: item.regdt
		}
		tableData.push(m);
	});
	
	var columns = [
		{
             field: "id",
             title: "#",
             width: 20,
             sortable: false,
             textAlign: 'center',
             selector: {class: 'm-checkbox--solid m-checkbox--brand'}
         }, {
             field: "target_system",
             title: "Target System",
             textAlign: 'center',
             width: 50,
             template: function(row) {
              return "<a href='#' onClick=\"fnRuleDetail('"+row.id+"')\" class='btn-show-event-detail m-btn--link font-weight-bold' >" + row.target_system + "</a>";
          }
         }, {
             field: "severity",
             title: "Severity",
             textAlign: 'center',
             width: 70
         },{
             field: "target",
             title: "Target Source",
             textAlign: 'center',
             width: 70
         }, {
             field: "data_source",
             title: "Data Source",
             textAlign: 'center',
             width: 100
         }, {
             field: "table",
             title: "Table",
             textAlign: 'center',
             width: 60
         }, {
             field: "metric",
             title: "Metric",
             textAlign: 'center',
             width:200
         }, {
             field: "condition",
             title: "Condition",
             textAlign: 'center',
             width:100
         }, {
             field: "message",
             title: "Alert Message",
             textAlign: 'center'
         }, {
             field: "status",
             title: "Status",
             textAlign: 'center',
             width:70
         }, {
             field: "regdt",
             title: "Register",
             textAlign: 'center',
             width:100
         }
	];
	new MDT().area("containerListTableArea").columns(columns).data(tableData).draw();
}


// Rule Target System List
function appendTargetSystem (data) {
	data.sort();
	data.forEach(function(item){
		$('#target_system').append("<option value='"+item.target_system+"'>"+item.target_system+"</option>");
	});
}


//Rule Target List
function appendTarget (data) {
	data.sort();
	data.forEach(function(item){
		$('#target').append("<option value='"+item.target+"'>"+item.target+"</option>");
	});
}

// Search button
function f_fnSearch() 
{
	if(event.keyCode != 13)
		return false;
	else
		fnSearch();
}

function fnSearch()
{
	doPagingClick();
}

function doPagingClick( )
{

	var target_system	= $.trim($("#target_system").val());
	var target			= $.trim($("#target").val());
	var metric			= $.trim($("#searchTxt").val());
	
	var param			= "target_system="+target_system+"&target="+target+"&metric="+metric;
	new Client().url("/api/v1/rule/list?"+param).callback(drawContainerList).getNotCluster();
}

function fnRuleDetail( rule_idx )
{
	location.href		= "/incident/rule/"+rule_idx+"/detail";
}

$(function(){
	doPagingClick( );
	new Client().url("/api/v1/rule/targetsystem").callback(appendTargetSystem).getNotCluster();
	
	$("#target_system").change(function() {
		  var target_system 	= $(this).val();
		 	$.ajax({
				url: "/api/v1/rule/"+target_system+"/target",
				type: "get",
				data: {},
				dataType: "json",
				success: function(data){
					//console.log(data);
					$("select[name='target'] option").remove();
					
					var obj = JSON.parse(data.responseBody);
					
					$('#target').append("<option value=''>Select Target</option>");
					
					for( var i=0;i<obj.length;i++)
					{
						$('#target').append("<option value='"+obj[i].target+"'>"+obj[i].target+"</option>");
					}
					
				},
				error		:
					function (jqXHR, textStatus, errorThrown) {
						alert('오류가 발생했습니다\n[' + textStatus + ']\n' + errorThrown);
				}
			});
	});
});
</script>