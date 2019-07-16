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
				Event Map
			</h3>
		</div>
		<span class="m-subheader__daterange" id="m_dashboard_daterangepicker">
			<span class="m-subheader__daterange-label">
				<span class="m-subheader__daterange-title"></span>
				<span class="m-subheader__daterange-date m--font-brand"></span>
			</span>
			<a href="#" class="btn btn-sm btn-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill">
				<i class="la la-angle-down"></i>
			</a>
		</span>
	</div>
</div>
<div class="m-content">
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Event</div>
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
                                	
                                    <div class="col-lg-8 col-xl-8">

                                        <div class="m-form__group m-form__group--inline">
                                            <div class="m-form__label">
                                                <label>
                                                    Filter_By
                                                </label>
                                            </div>
                                            <div class="m-form__control">
                                                <div class="row">
                                                    <div class="col">
                                                        <select class="form-control m-input form-filters" id="target_ip">
                                                            <option value="">Select Node</option>
                                                        </select>
                                                    </div>
                                              
                                                    <div class="col">
                                                        <select class="form-control m-input form-filters" id="target_system">
                                                            <option value="">Select Type</option>
                                                        </select>
                                                    </div>
                                                    
                                                    <div class="col">
                                                        <select class="form-control m-input form-filters" id="target">
                                                            <option value="">Select Metric</option>
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
                                </div>
                            </div>

                        </div>
                    </div>
                    <!--end: Search Form -->
					<div id="m_flotcharts" style="height:60%"></div>
			</div>
		</div>
	</div>
</div>

<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script src="/resources/js/lib/google/loader.js" type="text/javascript"></script>
<script type="text/javascript">
var START_TIME 		= "1h";
var TIME 			= "5s";

google.charts.load("current", {packages:["timeline"]});

var container;
var chart;
var dataTable;
var isFirst = true;

function timeRefresh(startTime, time) {
	START_TIME = startTime;
	TIME = time;

	if( !isFirst )
		getChartData( );
	
	isFirst	 = false;
}

function ValidateIPaddress(ipaddress) 
{
	if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(ipaddress))
  	{
    	return (true)
  	}
	return (false)
}

function getChartData(  ) {
	var target_ip				= $.trim($("#target_ip").val());
	var target_system			= $.trim($("#target_system").val());
	var target					= $.trim($("#target").val());
	var searchTxt				= $.trim($("#searchTxt").val());
	
	if( START_TIME.indexOf("d") != -1 )
	{
		var start_time			= START_TIME.substring(0, START_TIME.indexOf("d"));
		start_time				= parseInt(start_time) * 24;
	}
	else if( START_TIME.indexOf("h") != -1 )
	{
		var start_time			= START_TIME.substring(0, START_TIME.indexOf("h"));
	}
	
	var param			= "start_time="+start_time+"&target_ip="+target_ip+"&target_system="+target_system+"&target="+target+"&searchTxt="+searchTxt;
	
	new Client().url("/api/v1/incident/event/map?"+param).callback( drawChart ).getNotCluster();
}
function drawChart(data){
	container = document.getElementById('m_flotcharts');
	chart = new google.visualization.Timeline(container);
    dataTable = new google.visualization.DataTable();
    
    var searchTxt		= $.trim($("#searchTxt").val());
    
    dataTable.addColumn({ type: 'string', id: 'Position' });
    dataTable.addColumn({ type: 'string', id: 'Name' });
    dataTable.addColumn({ type: 'date', id: 'Start' });
    dataTable.addColumn({ type: 'date', id: 'End' });
	
    var arrData = [];

    data.forEach(function(item){
    	var start_time = item.start_time;
							
		var dateA = new Date(start_time.substring(0,4),start_time.substring(5,7)-1, start_time.substring(8,10), start_time.substring(11,13), start_time.substring(14,16), start_time.substring(17,19) );
		
		var finish_time = item.finish_time;
		
		var dateB = new Date(finish_time.substring(0,4),finish_time.substring(5,7)-1, finish_time.substring(8,10), finish_time.substring(11,13), finish_time.substring(14,16), finish_time.substring(17,19) );
		
		
		var offset = new Date().getTimezoneOffset();
		dateA.setHours(dateA.getHours() - (offset / 60 ));
		var startA = dateA.getTime();
		
		
		var offset = new Date().getTimezoneOffset();
		dateB.setHours(dateB.getHours() - (offset / 60 ));
		var startB = dateB.getTime();
		
		if( searchTxt != "" )
		{
			if( (item.id).indexOf( searchTxt ) == -1 && !ValidateIPaddress(item.id) )
			{
				
			}
			else
			{
				arrData.push( [item.target_system, item.id+" [ "+item.target_ip+" / "+item.metric+" / "+item.target+" / "+item.status+" ] ", new Date(startA), new Date(startB)] );
			}
		}
		else
			arrData.push( [item.target_system, item.id+" [ "+item.target_ip+" / "+item.metric+" / "+item.target+" / "+item.status+" ] ", new Date(startA), new Date(startB)] );
	});

	if( arrData.length > 0 )
	{
		dataTable.addRows(arrData);
		chart.draw(dataTable);
		google.visualization.events.addListener(chart, 'select', clickHandler);
	}
	else
	{
		$("#m_flotcharts").html("");
	}
}

function clickHandler()
{
	//var selection = chart.getSelection();
	//console.log(selection);
}

// Node IP List
function appendNode (data) {
	data.sort()
	data.forEach(function(item){
		$('#target_ip').append("<option value='"+item+"'>"+item+"</option>");
	});
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
	getChartData();
}

$(function(){
	google.charts.setOnLoadCallback(getChartData);
	new Picker().refreshFunction(timeRefresh).draw();
	new Client().url("/api/v1/host/ips").callback(appendNode).get();
	new Client().url("/api/v1/incident/rule/targetsystem").callback(appendTargetSystem).getNotCluster();
	new Client().url("/api/v1/incident/rule/target").callback(appendTarget).getNotCluster();
});
</script>
