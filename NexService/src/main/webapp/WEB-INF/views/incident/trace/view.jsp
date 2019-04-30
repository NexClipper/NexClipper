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
				Event Trace
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
			<!--begin:: Status-->
            <div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
                <div class="m-portlet__body">
                    <div id="m_flotcharts1" class="flotchart-dummy" style="height: 200px;"></div>
                    <div id="m_flotcharts2" class="flotchart-dummy" style="height: 200px;display:none"></div>
                    <div class="btn-group m--margin-top-20" id="agentMapButtons">
                      <a href="#" onClick="getChartData('time')" class="btn btn-primary" id="count">By time</a>
                      <a href="#" onClick="getChartData('ip')" class="btn btn-default" id="agent">By node</a>
                      <a href="#" onClick="getChartData('target')" class="btn btn-default" id="task">By metric</a>
                 </div>
                </div>
            </div>
            <!--end:: Status-->
		</div>
	</div>
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
                                                    
                                                    
                                                    <div class="col">
                                                        <select class="form-control m-input form-filters" id="status">
                                                            <option value="">Select Status</option>
                                                            <option value="S">Start</option>
                                                            <option value="F">Finish</option>
                                                            <option value="P">Prediction</option>
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
					<div id="containerListTableArea"></div>
			</div>
		</div>
	</div>
</div>

<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script type="text/javascript">
var START_TIME 		= "1h";
var TIME 			= "5s";
var type			= 'time';
var m_flotcharts1;
var m_flotcharts2;

function timeRefresh(startTime, time) {
	START_TIME = startTime;
	TIME = time;
	getChartData( type );
	doPagingClick( );
}

function getChartData( flag ) {
	type			= flag;
	new Client().url("/api/v1/incident/event/"+type+"?start_time="+START_TIME).callback( drawChart ).get();
}
function drawChart(data){
	var seriesLength 	= m_flotcharts1.series.length;
	var seriesLength2 	= m_flotcharts2.series.length;
	
	var data1	 	= new Array();
	var chart_type	= '';
	data.forEach(function(item){
		if( type == "time" )
		{
			var time = item.time+":00";
			var dt = new Date(time);

			var offset = new Date().getTimezoneOffset();
			dt.setHours(dt.getHours() - (offset / 60 ));
			var dateA = dt.getTime();
			
			data1.push( [dateA, parseInt(item.count)] );
			chart_type = "line";
		}
		else if( type == "ip" )
		{
			var x_name 		= item.target_ip;
			data1.push( {'name':x_name, 'y':parseInt(item.count)} );
		}
		else if( type == "target" )
		{
			var x_name 		= item.target;
			data1.push( {'name':x_name, 'y':parseInt(item.count)} );
		}
	});
	
	
	if( type == "time" )
	{
		var chart1Data = {
            type: chart_type,
            pointInterval : 60*1000,
            name: "incident count",
            data: data1
      	};
		m_flotcharts1.addSeries(chart1Data);
		document.getElementById("m_flotcharts1").style.display="";
		document.getElementById("m_flotcharts2").style.display="none";
	}
	else
	{
		var chart1Data = {
			colorByPoint: true,
            data: data1
      	};
		m_flotcharts2.addSeries(chart1Data);
		document.getElementById("m_flotcharts1").style.display="none";
		document.getElementById("m_flotcharts2").style.display="";
	}
	
	if( seriesLength > 0 )
	{
    	for( var i=(seriesLength-1);i>= 0;i--)
    	{
    		try{
    			m_flotcharts1.series[i].remove();
    		}catch(e){}
    	}
	}

	if( seriesLength2 > 0 )
	{
    	for( var i=(seriesLength2-1);i>= 0;i--)
    	{
    		try{
    			m_flotcharts2.series[i].remove();
    		}catch(e){}
    	}
	}
	
	if( type == "time" )
	{
		document.getElementById("m_flotcharts1").style.display="";
		document.getElementById("m_flotcharts2").style.display="none";
	}
	else
	{
		document.getElementById("m_flotcharts1").style.display="none";
		document.getElementById("m_flotcharts2").style.display="";
	}
}

function getTimeStamp(d) {
  var s =
    leadingZeros(d.getFullYear(), 4) + '-' +
    leadingZeros(d.getMonth() + 1, 2) + '-' +
    leadingZeros(d.getDate(), 2) + ' ' +

    leadingZeros(d.getHours(), 2) + ':' +
    leadingZeros(d.getMinutes(), 2) + ':' +
    leadingZeros(d.getSeconds(), 2);

  return s;
}

function leadingZeros(n, digits) {
  var zero = '';
  n = n.toString();

  if (n.length < digits) {
    for (i = 0; i < digits - n.length; i++)
      zero += '0';
  }
  return zero + n;
}

// event List 
function drawContainerList (data) {
	var tableData = [];
	data.forEach(function(item){
		if(item.status == "S"){
			item.status = "Start";
		}
		else if(item.status == "F"){
			item.status = "Closed";
		}
		else if(item.status == "P"){
			item.severity = "Prediction "+severity;
			item.status = "Prediction";
		}
		
		var start_time = item.start_time;
		var dateA = new Date(start_time.substring(0,4),start_time.substring(5,7)-1, start_time.substring(8,10), start_time.substring(11,13), start_time.substring(14,16), start_time.substring(17,19) );
		var offset = new Date().getTimezoneOffset();
		dateA.setHours(dateA.getHours() - (offset / 60 ));
		var startA = dateA.getTime();
		item.start_time = getTimeStamp(new Date(startA));
		
		var finish_time = item.finish_time;
		if( item.finish_time === undefined  )
		{
			item.finish_time	= "";
		}
		else
		{
			var dateB = new Date(finish_time.substring(0,4),finish_time.substring(5,7)-1, finish_time.substring(8,10), finish_time.substring(11,13), finish_time.substring(14,16), finish_time.substring(17,19) );
			var offset = new Date().getTimezoneOffset();
			dateB.setHours(dateB.getHours() - (offset / 60 ));
			var startB = dateB.getTime();
			item.finish_time = getTimeStamp(new Date(startB));
		}
		
		
		
		var m = {
			id 			: item.idx,
			no 			: item.idx,
			severity	: item.severity,
	        target_ip	: item.target_ip,
	        target		: item.target,
	        status		: item.status,
	        severity	: item.severity,
	        type		: item.target_system,
	        on			: item.id,
	        detail		: item.contents,
	        start		: item.start_time,
	        end			: item.finish_time,
	        metric		: item.metric
		}
		tableData.push(m);
	});
	
	var columns = [
		{
	        field: "no",
	        title: "No",
	        sortable: false,
	        width: 30
	
	    },{
	        field: "detail",
	        title: "Event",
	        template: function(row) {
	            return '<a href="#" class="btn-show-event-detail-xx m-btn--link font-weight-bold" data-id="' + row.target_ip + ';'+row.target+';'+row.type+';'+row.on +';'+row.no+';'+row.metric+'">' + row.detail + '</a>';
	        	//return '<a href=\"javascript:fnEventDetail('+row.no+');\" class="btn-show-event-detail-xx m-btn--link font-weight-bold" data-id-'+row.no+'="' + row.target_ip + ';'+row.target+';'+row.type+';'+row.on +';'+row.no+';'+row.metric+'">' + row.detail + '</a>';
	        }
	
	    },{
	        field: "severity",
	        title: "Severity",
	        sortable : false,
	        width:100,
	        template: function(row) {
	            var color = {
	                'Critical' : 'danger',
	                'Warning' : 'warning',
	                'Prediction Critical' : 'danger',
	                'Prediction Warning' : 'warning'
	            }
	            return '<span class="m-badge m-badge--' + color[row.severity] + ' m-badge--dot"></span>&nbsp;<span class="m--font-bold m--font-' + color[row.severity] + '">' + row.severity + '</span>';
	        }
	    },{
	        field: "on",
	        title: "Target",
	
	    },{
	        field: "type",
	        title: "Type",
	        textAlign: 'center',
	        width:80,
	
	    },{
	        field: "status",
	        title: "Status",
	        /* sortable: false,
	        template: function(row) {
	            return '<a href="#" class="btn-show-event-detail m-btn--link font-weight-bold" data-id="' + row.id + '">' + row.status + '</a>';
	        } */
	
	    },  {
	        field: "start",
	        title: "Start",
	        width:130
	    }, {
	        field: "end",
	        title: "End",
	        width:130
	    },{
	        field: "target_ip",
	        title: "Agent IP",
	        sortable: false
	
	    },{
	        field: "target",
	        title: "Metric",
	        sortable: false
	
		}
	];
	new MDT().area("containerListTableArea").columns(columns).data(tableData).draw();
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
	doPagingClick();
}

function doPagingClick( )
{

	var target_ip		= $.trim($("#target_ip").val());
	var target_system	= $.trim($("#target_system").val());
	var target			= $.trim($("#target").val());
	var status			= $.trim($("#status").val());
	var searchTxt		= $.trim($("#searchTxt").val());
	
	var param			= "start_time="+START_TIME+"&time="+TIME+"&target_ip="+target_ip+"&target_system="+target_system+"&target="+target+"&status="+status+"&searchTxt="+searchTxt;
	new Client().url("/api/v1/incident/event?"+param).callback(drawContainerList).get();
}

$(function(){
	$('#agentMapButtons a').on('click', function(e){
        e.preventDefault();
        $(this).attr('class', 'btn btn-primary').siblings().attr('class', 'btn btn-default');
    });
	  
    m_flotcharts1 = new Highcharts.Chart({
	    chart: {
	    	zoomType: 'x',
	    	backgroundColor: '#FFFFFF',
	        animation: Highcharts.svg, // don't animate in old IE
	        renderTo: 'm_flotcharts1',
	        type: 'column',
	    },
	    time : {
	    	useUTC:false
	    },
	    title: {
	        text: '',
	        align : 'center'
	    },
	    xAxis: {
	        type: 'datetime',
	        dateTimeLabelFormats: {
	            second: '%Y-%m-%d %H:%M:%S'
	        }
	    },
	    yAxis: { title :{text:'Count'}},
	    tooltip: {
            headerFormat: '<b>{series.name}</b><br/>',
            pointFormat: '{point.x:%Y-%m-%d %H:%M:%S}<br/>{point.y:.0f} Count'
        },
	    plotOptions: {
	    	area: {
	            fillColor: {
	                linearGradient: {
	                    x1: 0,
	                    y1: 0,
	                    x2: 0,
	                    y2: 1
	                },
	                stops: [
	                    [0, Highcharts.getOptions().colors[0]],
	                    [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
	                ]
	            },
	            marker: {
	                radius: 2
	            },
	            lineWidth: 1,
	            states: {
	                hover: {
	                    lineWidth: 1
	                }
	            },
	            threshold: null
	        },
	        series: {
	            pointInterval: 60 * 1000 // one day
	        }
	    },
	    exporting: {
	        enabled: false
	    },
	    series: [{}]
	});
    
    m_flotcharts2 = new Highcharts.Chart({
        chart: {
        	zoomType: 'x',
        	backgroundColor: '#FFFFFF',
            animation: Highcharts.svg, // don't animate in old IE
            renderTo: 'm_flotcharts2',
            type: 'column'
        },
        title: {
            text: '',
            align : 'center'
        },
        xAxis: {
        	type: 'category'
        },
        yAxis: { title :{text:'Count'}},
        tooltip: {
            headerFormat: '<span style="font-size:11px">event count</span><br>',
            pointFormat: '<span style="color:black">{point.name}</span>: <b>{point.y:.0f} Count</b><br/>'
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true
                },
                cursor: 'pointer',
                point: {
                	events:{
                		click: function (e) {
                			if( type == "ip")
                			{
                				$("#target_ip").val(e.point.name);
                				$("#target").val("");
                			}
                			else if( type == "target")
                			{
                				$("#target_ip").val("");
                				$("#target").val(e.point.name);
                			}
                			doPagingClick( );
                    	}
                    }
                }
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{}]
    });

	new Picker().refreshFunction(timeRefresh).draw();
	new Client().url("/api/v1/incident/event?start_time="+START_TIME).callback(drawContainerList).get();
	new Client().url("/api/v1/host/ips").callback(appendNode).get();
	new Client().url("/api/v1/incident/rule/targetsystem").callback(appendTargetSystem).get();
	new Client().url("/api/v1/incident/rule/target").callback(appendTarget).get();
});
</script>

<!--begin::Modal-->
<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-righted" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Event Viewer</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="m-widget27">
                	<div class="m-widget27__item">
                        <dl>
                            <dt>Event</dt>
                            <dd><label id="contents"></label></dd>
                        </dl>
                    </div>
                    
                    <div class="m-widget27__item">
                        <dl>
                            <dt>Target</dt>
                            <dd>
                                <label id="event_target"></label>
                            </dd>
                        </dl>
                    </div>
                    
                    <div class="m-widget27__item">
                        <dl>
                            <dt>Status</dt>
                            <dd>
                                <label id="host_status"></label>
                            </dd>
                        </dl>
                    </div>
                    
                    <div class="m-widget27__item">
                        <dl>
                            <dt>Event Time</dt>
                            <dd><label id="event_time"></label></dd>
                        </dl>
                    </div>
                    
                    <div class="m-widget27__item">
                        <dl>
                            <dt>Hostname</dt>
                            <dd><label id="host_name"></label></dd>
                        </dl>
                    </div>
                    <div class="m-widget27__item">
                        <dl>
                            <dt>IP</dt>
                            <dd><label id="agent_ip"></label></dd>
                        </dl>
                    </div>
                    
                    <div class="m-widget27__item">
                        <dl>
                            <dt>Metric</dt>
                            <dd><label id="metric"></label></dd>
                        </dl>
                    </div>
                    <div class="m-widget27__item">
                        <dl>
                            <dt>Condition</dt>
                            <dd><label id="condition"></label></dd>
                        </dl>
                    </div>
                    <div class="m-widget27__item">
                        <dl>
                            <dt>Duration</dt>
                            <dd><label id="duration"></label></dd>
                        </dl>
                    </div>

                    
                    <div class="m-widget27__item">
                        <dl>
                            <dt>Resource<br>Agent</dt>
                            <dd><label id="resource_agent" class="flotchart-dummy" style="height: 150px;width:100%"></label></dd>
                        </dl>
                    </div>
                    
                    <div class="m-widget27__item" id="view_task_chart" style="display:none">
                        <dl>
                            <dt>Resource<br>Task</dt>
                            <dd><label id="resource_task" class="flotchart-dummy" style="height: 150px;width:100%"></label></dd>
                        </dl>
                    </div>
                    
                    <div class="m-widget27__item" id="view_metric_chart" style="display:none">
                        <dl>
                            <dt>Resource<br><label id="metric_txt"></label></dt>
                            <dd><label id="resource_metric" class="flotchart-dummy" style="height: 150px;width:100%"></label></dd>
                        </dl>
                    </div>
                    
                    <!--  
                    <div class="m-widget27__item">
                        <dl>
                            <dt>Assign</dt>
                            <dd><label id="assign"></label></dd>
                        </dl>
                    </div>
                    -->
                    
                    <div class="m-widget27__item">
                        <dl>
                            <dt>History</dt>
                            <dd>
                                <a href="#" class="btn btn-primary m-btn m-btn--icon m-btn--sm m-btn--icon-only  m-btn--pill" id="btnToggleHistoryList"><i class="la la-plus"></i></a>
                                <div id="historyList">
                                    <table class="table table-sm table-xs m--margin-top-10">
                                        <tbody id="tbody">
                                        <!--  
                                        <tr>
                                            <td>2018-01-01 15:120:12</td>
                                            <td>CPU Usage</td>
                                        </tr>
                                        -->
                                        </tbody>
                                    </table>
                                </div>
                            </dd>
                        </dl>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--end::Modal-->

<script>
	var isProcess = false;
		//end::Event List 관련 변수 및 함수
	/////////////////////////////////////////////////
    $(document).on('click', '.btn-show-event-detail-xx', function(e){
        e.preventDefault();
        
        return;
        
		//alert($(this).attr("data-id"));
		
		// row.target_ip + ';'+row.target+';'+row.type+';'+row.on
		// dataArray[0] :  IP
		// dataArray[1] : Target
		// dataArray[2] : Target_system
		// dataArray[3] : id
		// dataArray[4] : notification table idx key
		var dataArray = $(this).attr("data-id").split(";");

		g_target			= dataArray[1];
		g_target_system		= dataArray[2];
		g_target_ip			= dataArray[0];
		g_metric			= dataArray[5];
		
		
		if( g_target_system.toLowerCase() == "cluster")
		{
			alert('상세정보를 제공하지 않는 이벤트입니다.');
			return;
		}
		
		if( isProcess )
			return;
		isProcess  = true;
		startSpinner();
		
		$.ajax({
			url: "/v1/events/history",
			type: "post",
			data: {
					  "ajax"			: "ajax"
					 ,"target_ip"		: dataArray[0]
					 ,"target"			: dataArray[1]
					 ,"target_system"	: dataArray[2]
					 ,"id"				: dataArray[3]
					 ,"idx"				: dataArray[4]
					 ,"metric"			: dataArray[5]
				  },
			dataType: "json",
			success: function(data){
				isProcess = false;
				
				var jsonNode = data.nodes;
				var jsonTask = data.tasks;
				var jsonMetric = data.metric;
				
				
				var seriesLength = resource_agent.series.length;
				if( seriesLength > 0 )
		    	{
			    	for( var i=(seriesLength-1);i>=0;i--)
			    	{
			    		resource_agent.series[i].remove();
			    	}
		    	}
				
				
				var arrayNodeCpu	= [];
				var arrayNodeMem	= [];
				for( var i=0;i<jsonNode.results[0].series[0].values.length;i++)
				{
					time	= jsonNode.results[0].series[0].values[i][0];
					var arrTime = time.split("Z");
					var dt = new Date(arrTime[0]);
				    //dt.setHours(dt.getHours() + 9);
				    dt.setHours(dt.getHours() + (diff_hour));
					var dateA = dt.getTime();
					
					arrayNodeCpu.push( [dateA, jsonNode.results[0].series[0].values[i][2]] );
					arrayNodeMem.push( [dateA, jsonNode.results[0].series[0].values[i][4]] );
				}
				
				var chartData = {
			         type: 'area',
			         pointInterval : 60*1000,
			         name: "CPU",
			         color:"#679867",
			         data: arrayNodeCpu
			   	};
				resource_agent.addSeries(chartData);
				
				var chartData = '';
				var chartData = {
			         type: 'area',
			         pointInterval : 60*1000,
			         name: "MEM",
			         color:"#9DD9DF",
			         data: arrayNodeMem
			   	};
				resource_agent.addSeries(chartData);
				
				
				if( dataArray[0] != dataArray[3] && dataArray[1] != "Disk" )
				{
					document.getElementById("view_task_chart").style.display="";
					
					var seriesLength = resource_task.series.length;
					if( seriesLength > 0 )
			    	{
				    	for( var i=(seriesLength-1);i>=0;i--)
				    	{
				    		resource_task.series[i].remove();
				    	}
			    	}
					
					
					var arrayTaskCpu	= [];
					var arrayTaskMem	= [];
					for( var i=0;i<jsonTask.results[0].series[0].values.length;i++)
					{
						time	= jsonTask.results[0].series[0].values[i][0];
						var arrTime = time.split("Z");
						var dt = new Date(arrTime[0]);
					    //dt.setHours(dt.getHours() + 9);
					    dt.setHours(dt.getHours() + (diff_hour));
						var dateA = dt.getTime();
						
						arrayTaskCpu.push( [dateA, jsonTask.results[0].series[0].values[i][1]] );
						arrayTaskMem.push( [dateA, jsonTask.results[0].series[0].values[i][2]] );
					}
					
					if( dataArray[2] == "Docker")
					{
						resource_task.yAxis[0].update({
				            title:{
				                text:"%"
				            },
				            min: 0,
				            max: null
				        });
					}
					else if( dataArray[2] == "Task")
					{
						resource_task.yAxis[0].update({
				            title:{
				                text:"%"
				            },
				            min: 0,
				            max: 100
				        });
					}
					
					var chartData = '';
					var chartData = {
				         type: 'area',
				         pointInterval : 60*1000,
				         name: "CPU",
				         color:"#679867",
				         data: arrayTaskCpu
				   	};
					resource_task.addSeries(chartData);
					
					var chartData = '';
					var chartData = {
				         type: 'area',
				         pointInterval : 60*1000,
				         name: "MEM",
				         color:"#9DD9DF",
				         data: arrayTaskMem
				   	};
					resource_task.addSeries(chartData);
				}
				else
					document.getElementById("view_task_chart").style.display="none";
				
				
				// Load Cpu Metirc일경우
				if( dataArray[5].indexOf("load_") != -1 )
				{
					resource_metric.yAxis[0].update({
			            title:{
			                text:"Core"
			            },
			            min: 0,
			            max: null
			        });
					
					document.getElementById("view_metric_chart").style.display="";
					$("#metric_txt").html( dataArray[5] );
                	
					var seriesLength = resource_metric.series.length;
					if( seriesLength > 0 )
			    	{
				    	for( var i=(seriesLength-1);i>=0;i--)
				    	{
				    		resource_metric.series[i].remove();
				    	}
			    	}
					
					
					var arrayMetric	= [];
					for( var i=0;i<jsonMetric.results[0].series[0].values.length;i++)
					{
						time	= jsonMetric.results[0].series[0].values[i][0];
						var arrTime = time.split("Z");
						var dt = new Date(arrTime[0]);
					    //dt.setHours(dt.getHours() + 9);
					    dt.setHours(dt.getHours() + (diff_hour));
						var dateA = dt.getTime();
						
						arrayMetric.push( [dateA, jsonMetric.results[0].series[0].values[i][1]] );
					}
					
					var chartData = '';
					var chartData = {
				         type: 'area',
				         pointInterval : 60*1000,
				         name: dataArray[5],
				         color:"#679867",
				         data: arrayMetric
				   	};
					resource_metric.addSeries(chartData);
				}
				// Disk
				else if( dataArray[1].indexOf("Disk") != -1 )
				{
					resource_metric.yAxis[0].update({
			            title:{
			                text:"%"
			            },
			            min: 0,
			            max: 100
			        });
					
					document.getElementById("view_metric_chart").style.display="";
					$("#metric_txt").html( dataArray[5] );
                	
					var seriesLength = resource_metric.series.length;
					if( seriesLength > 0 )
			    	{
				    	for( var i=(seriesLength-1);i>=0;i--)
				    	{
				    		resource_metric.series[i].remove();
				    	}
			    	}
					
					
					var arrayMetric	= [];
					for( var i=0;i<jsonMetric.results[0].series[0].values.length;i++)
					{
						time	= jsonMetric.results[0].series[0].values[i][0];
						var arrTime = time.split("Z");
						var dt = new Date(arrTime[0]);
					    //dt.setHours(dt.getHours() + 9);
					    dt.setHours(dt.getHours() + (diff_hour));
						var dateA = dt.getTime();
						
						arrayMetric.push( [dateA, jsonMetric.results[0].series[0].values[i][1]] );
					}
					
					var chartData = '';
					var chartData = {
				         type: 'area',
				         pointInterval : 60*1000,
				         name: "Disk used percent",
				         color:"#679867",
				         data: arrayMetric
				   	};
					resource_metric.addSeries(chartData);
				}
				else
				{
					document.getElementById("view_metric_chart").style.display="none";
				}
				
				$("#event_target").html(data.data.id);
				$("#host_name").html(data.host_name);
				$("#agent_ip").html(data.data.target_ip);
				
				
				
				if( data.data.status == "Start" )
					$("#host_status").html("<span class='m--font-danger'>* "+data.data.status+"</span>");
				else
					$("#host_status").html(data.data.status);
				
				
				$("#metric").html(data.data.metric);
				$("#condition").html(data.data.condition);
				$("#duration").html(data.data.duration);
				$("#contents").html(data.data.contents);
				
				/*
				var dt		   = new Date(data.data.start_time);
				dt.setHours(dt.getHours() + (diff_hour));
				var start_time = dt.format("yyyy-MM-dd HH:mm:ss");
				*/
				var start_time = data.data.start_time;
				if( data.data.finish_time != "")
				{
					//$("#event_time").html(data.data.start_time+" ~ "+data.data.finish_time)
					/*
					var dt		   = new Date(data.data.finish_time);
					dt.setHours(dt.getHours() + (diff_hour));
					var finish_time = dt.format("yyyy-MM-dd HH:mm:ss");
					*/
					var finish_time = data.data.finish_time;
					$("#event_time").html(start_time+" ~ "+finish_time)
				}
				else
				{
					//$("#event_time").html(data.data.start_time+" ~ 현재")
					$("#event_time").html(start_time+" ~ 현재")
				}
				
				var html = "";
				for( var i=0;i<data.list.length;i++ )
				{
					var bgcolor = "";
					if( dataArray[4] == data.list[i].idx)
						bgcolor = "#808000";
					
					if( data.list[i].status == "Start" )
						html += "<tr bgcolor='"+bgcolor+"' onClick=\"fnHistory('"+data.list[i].idx+"', '"+data.list[i].id+"', '"+data.list[i].target+"', '"+data.list[i].target_ip+"', '"+data.list[i].target_system+"','"+data.list[i].metric+"')\" style='cursor:pointer'><td>"+data.list[i].start_time+"</td><td><span class='m--font-danger'>*"+data.list[i].status+"</span></td><td>"+data.list[i].target+"</td></tr>";
					else
						html += "<tr bgcolor='"+bgcolor+"' onClick=\"fnHistory('"+data.list[i].idx+"', '"+data.list[i].id+"', '"+data.list[i].target+"', '"+data.list[i].target_ip+"', '"+data.list[i].target_system+"','"+data.list[i].metric+"')\" style='cursor:pointer'><td>"+data.list[i].start_time+"</td><td>"+data.list[i].status+"</td><td>"+data.list[i].target+"</td></tr>";
				}
				
				
				$('#tbody').html(html);
				
				stopSpinner();
				
			},
			error		:
				function (jqXHR, textStatus, errorThrown) {
					stopSpinner();
					isProcess = false;
					alert('오류가 발생했습니다\n[' + textStatus + ']\n' + errorThrown);
			},
			complete: function() {
				$('#detailModal').modal('show');
		    }
		});
		
		
        //$('#detailModal').modal('show');

    });
	
	
	
    $('#historyList').slideUp(0);
    $('#btnToggleHistoryList').on('click', function(e){
        e.preventDefault();
        $(this).toggleClass('active');
        if ($(this).hasClass('active')) {
            $(this).html('<i class="la la-minus"></i>');
            $('#historyList').slideDown();
        } else {
            $(this).html('<i class="la la-plus"></i>');
            $('#historyList').slideUp();
        }
    });
    
    
    function fnHistory( idx, id,target, target_ip,target_system,metric )
	{
		// row.target_ip + ';'+row.target+';'+row.type+';'+row.on
		// dataArray[0] :  IP
		// dataArray[1] : Target
		// dataArray[2] : Target_system
		// dataArray[3] : id
		// dataArray[4] : notification table idx key
		
		
		isProcess  = true;
		startSpinner();
		$.ajax({
			url: "${prefix}/v1/events/history",
			type: "post",
			data: {
					  "ajax"			: "ajax"
					 ,"target_ip"		: target_ip
					 ,"target"			: target
					 ,"target_system"	: target_system
					 ,"id"				: id
					 ,"idx"				: idx
					 ,"metric"			: metric
				  },
			dataType: "json",
			success: function(data){
				isProcess = false;
				
				var jsonNode = data.nodes;
				var jsonTask = data.tasks;
				var jsonMetric = data.metric;
				
				
				var seriesLength = resource_agent.series.length;
				if( seriesLength > 0 )
		    	{
			    	for( var i=(seriesLength-1);i>=0;i--)
			    	{
			    		resource_agent.series[i].remove();
			    	}
		    	}
				
				
				var arrayNodeCpu	= [];
				var arrayNodeMem	= [];
				for( var i=0;i<jsonNode.results[0].series[0].values.length;i++)
				{
					time	= jsonNode.results[0].series[0].values[i][0];
					var arrTime = time.split("Z");
					var dt = new Date(arrTime[0]);
				    //dt.setHours(dt.getHours() + 9);
				    dt.setHours(dt.getHours() + (diff_hour));
					var dateA = dt.getTime();
					
					arrayNodeCpu.push( [dateA, jsonNode.results[0].series[0].values[i][2]] );
					arrayNodeMem.push( [dateA, jsonNode.results[0].series[0].values[i][4]] );
				}
				
				var chartData = {
			         type: 'area',
			         pointInterval : 60*1000,
			         name: "CPU",
			         color:"#679867",
			         data: arrayNodeCpu
			   	};
				resource_agent.addSeries(chartData);
				
				var chartData = '';
				var chartData = {
			         type: 'area',
			         pointInterval : 60*1000,
			         name: "MEM",
			         color:"#9DD9DF",
			         data: arrayNodeMem
			   	};
				resource_agent.addSeries(chartData);
				
				
				if( target_ip != id && target != "Disk" )
				{
					document.getElementById("view_task_chart").style.display="";
					
					var seriesLength = resource_task.series.length;
					if( seriesLength > 0 )
			    	{
				    	for( var i=(seriesLength-1);i>=0;i--)
				    	{
				    		resource_task.series[i].remove();
				    	}
			    	}
					
					
					var arrayTaskCpu	= [];
					var arrayTaskMem	= [];
					for( var i=0;i<jsonTask.results[0].series[0].values.length;i++)
					{
						time	= jsonTask.results[0].series[0].values[i][0];
						var arrTime = time.split("Z");
						var dt = new Date(arrTime[0]);
					    //dt.setHours(dt.getHours() + 9);
					    dt.setHours(dt.getHours() + (diff_hour));
						var dateA = dt.getTime();
						
						arrayTaskCpu.push( [dateA, jsonTask.results[0].series[0].values[i][1]] );
						arrayTaskMem.push( [dateA, jsonTask.results[0].series[0].values[i][2]] );
					}
					
					if( target_system == "Docker")
					{
						resource_task.yAxis[0].update({
				            title:{
				                text:"%"
				            },
				            min: 0,
				            max: null
				        });
					}
					else if( target_system == "Task")
					{
						resource_task.yAxis[0].update({
				            title:{
				                text:"%"
				            },
				            min: 0,
				            max: 100
				        });
					}
					
					var chartData = '';
					var chartData = {
				         type: 'area',
				         pointInterval : 60*1000,
				         name: "CPU",
				         color:"#679867",
				         data: arrayTaskCpu
				   	};
					resource_task.addSeries(chartData);
					
					var chartData = '';
					var chartData = {
				         type: 'area',
				         pointInterval : 60*1000,
				         name: "MEM",
				         color:"#9DD9DF",
				         data: arrayTaskMem
				   	};
					resource_task.addSeries(chartData);
				}
				else
					document.getElementById("view_task_chart").style.display="none";
				
				
				// Load Cpu Metirc일경우
				if( metric.indexOf("load_") != -1 )
				{
					document.getElementById("view_metric_chart").style.display="";
					$("#metric_txt").html( metric );
                	
					var seriesLength = resource_metric.series.length;
					if( seriesLength > 0 )
			    	{
				    	for( var i=(seriesLength-1);i>=0;i--)
				    	{
				    		resource_metric.series[i].remove();
				    	}
			    	}
					
					
					var arrayMetric	= [];
					for( var i=0;i<jsonMetric.results[0].series[0].values.length;i++)
					{
						time	= jsonMetric.results[0].series[0].values[i][0];
						var arrTime = time.split("Z");
						var dt = new Date(arrTime[0]);
					    //dt.setHours(dt.getHours() + 9);
					    dt.setHours(dt.getHours() + (diff_hour));
						var dateA = dt.getTime();
						
						arrayMetric.push( [dateA, jsonMetric.results[0].series[0].values[i][1]] );
					}
					
					var chartData = '';
					var chartData = {
				         type: 'area',
				         pointInterval : 60*1000,
				         name: metric,
				         color:"#679867",
				         data: arrayMetric
				   	};
					resource_metric.addSeries(chartData);
				}
				else if( target.indexOf("Disk") != -1 )
				{
					document.getElementById("view_metric_chart").style.display="";
					$("#metric_txt").html( metric );
                	
					var seriesLength = resource_metric.series.length;
					if( seriesLength > 0 )
			    	{
				    	for( var i=(seriesLength-1);i>=0;i--)
				    	{
				    		resource_metric.series[i].remove();
				    	}
			    	}
					
					
					var arrayMetric	= [];
					for( var i=0;i<jsonMetric.results[0].series[0].values.length;i++)
					{
						time	= jsonMetric.results[0].series[0].values[i][0];
						var arrTime = time.split("Z");
						var dt = new Date(arrTime[0]);
					    //dt.setHours(dt.getHours() + 9);
					    dt.setHours(dt.getHours() + (diff_hour));
						var dateA = dt.getTime();
						
						arrayMetric.push( [dateA, jsonMetric.results[0].series[0].values[i][1]] );
					}
					
					var chartData = '';
					var chartData = {
				         type: 'area',
				         pointInterval : 60*1000,
				         name: "Disk used percent",
				         color:"#679867",
				         data: arrayMetric
				   	};
					resource_metric.addSeries(chartData);
				}
				else
				{
					document.getElementById("view_metric_chart").style.display="none";
				}
				
				$("#event_target").html(data.data.id);
				$("#host_name").html(data.host_name);
				$("#agent_ip").html(data.data.target_ip);
				
				
				
				if( data.data.status == "Start" )
					$("#host_status").html("<span class='m--font-danger'>* "+data.data.status+"</span>");
				else
					$("#host_status").html(data.data.status);
				
				
				$("#metric").html(data.data.metric);
				$("#condition").html(data.data.condition);
				$("#duration").html(data.data.duration);
				$("#contents").html(data.data.contents);
				
				if( data.data.finish_time != "")
					$("#event_time").html(data.data.start_time+" ~ "+data.data.finish_time)
				else
					$("#event_time").html(data.data.start_time+" ~ 현재")
				
				var html = "";
				for( var i=0;i<data.list.length;i++ )
				{
					var bgcolor = "";
					if( idx == data.list[i].idx)
						bgcolor = "#808000";
					
					if( data.list[i].status == "Start" )
						html += "<tr bgcolor='"+bgcolor+"' onClick=\"fnHistory('"+data.list[i].idx+"', '"+data.list[i].id+"', '"+data.list[i].target+"', '"+data.list[i].target_ip+"', '"+data.list[i].target_system+"', '"+data.list[i].metric+"')\" style='cursor:pointer'><td>"+data.list[i].start_time+"</td><td><span class='m--font-danger'>*"+data.list[i].status+"</span></td><td>"+data.list[i].target+"</td></tr>";
					else
						html += "<tr bgcolor='"+bgcolor+"' onClick=\"fnHistory('"+data.list[i].idx+"', '"+data.list[i].id+"', '"+data.list[i].target+"', '"+data.list[i].target_ip+"', '"+data.list[i].target_system+"', '"+data.list[i].metric+"')\" style='cursor:pointer'><td>"+data.list[i].start_time+"</td><td>"+data.list[i].status+"</td><td>"+data.list[i].target+"</td></tr>";
				}
				
				
				$('#tbody').html(html);
				
				stopSpinner();
				
			},
			error		:
				function (jqXHR, textStatus, errorThrown) {
					stopSpinner();
					isProcess = false;
					alert('오류가 발생했습니다\n[' + textStatus + ']\n' + errorThrown);
			}
		});
		
		
        $('#detailModal').modal('show');
	}
    
</script>