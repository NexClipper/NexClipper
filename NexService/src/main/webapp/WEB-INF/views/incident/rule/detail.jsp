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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                	<form name="frm" id="frm" method="POST">
                    <!--begin: Search Form -->
                    
                    <div class="m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30">
                        <div class="row align-items-center">
                            <div class="col-xl-12 col-lg-12">
                                <div class="form-group m-form__group row align-items-center">
                                	
                                    <div class="col-lg-6 col-xl-4">

                                        <div class="m-form__group m-form__group--inline">
                                            <div class="m-form__label">
                                                <label>
                                                    Type
                                                </label>
                                            </div>
                                            <div class="m-form__control">
                                                <div class="row">
                                                    <div class="col">
                                                        <select class="form-control m-input form-filters" id="severity" name="severity">
                                                            <option value="">Select Severity</option>
                                                            <option value="Warning">Warning</option>
                                                            <option value="Critical">Critical</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-xl-none m--margin-bottom-10"></div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    
                    <div class="m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30">
                        <div class="row align-items-center">
                            <div class="col-xl-12 col-lg-12">
                                <div class="form-group m-form__group row align-items-center">
                                	
                                    <div class="col-lg-6 col-xl-4">

                                        <div class="m-form__group m-form__group--inline">
                                            <div class="m-form__label">
                                                <label>
                                                    Where
                                                </label>
                                            </div>
                                            <div class="m-form__control">
                                                <div class="row">
                                                    <div class="col">
                                                        <select class="form-control m-input form-filters" id="target_system" name="target_system">
                                                            <option value="">Select Target System</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-xl-none m--margin-bottom-10"></div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    
                    <div class="m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30">
                        <div class="row align-items-center">
                            <div class="col-xl-12 col-lg-12">
                                <div class="form-group m-form__group row align-items-center">
                                	
                                    <div class="col-lg-6 col-xl-12">

                                        <div class="m-form__group m-form__group--inline">
                                            <div class="m-form__label">
                                                <label>
                                                    Metric
                                                </label>
                                            </div>
                                            <div class="m-form__control">
                                                <div class="row">
                                                    <div class="col-2">
                                                        <select class="form-control m-input form-filters" id="target" name="target">
                                                            <option value="">Select Target</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-3">
                                                        <select class="form-control m-input form-filters" id="metric" name="metric">
                                                            <option value="">Select Metric</option>
                                                        </select>
                                                    </div>
                                                    
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-xl-none m--margin-bottom-10"></div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    
                    
                    
                    <!-- Trigger -->
                    <div class="m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30">
                        <div class="row align-items-center">
                            <div class="col-xl-12 col-lg-12">
                                <div class="form-group m-form__group row align-items-center">
                                	
                                    <div class="col-lg-6 col-xl-12">

                                        <div class="m-form__group m-form__group--inline">
                                            <div class="m-form__label">
                                                <label>
                                                    Trigger
                                                </label>
                                            </div>
                                            <div class="m-form__control">
                                                <div class="row">
                                                    <div class="col-2">
                                                        <select class="form-control m-input form-filters" id="equility" name="equility">
                                                            <option value="">Select Equility</option>
                                                            <option value=">">></option>
                                                            <option value="=">=</option>
                                                            <option value="<"><</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-1">
                                                        <input type="text" class="form-control" placeholder=""  aria-label="" aria-describedby="basic-addon2" onkeyup="javascript:numOnly(this,document.frm,true);" name="calc_value" id="calc_value">
                                                        <select class="form-control m-input form-filters" id="calc_value_sel" name="calc_value_sel" style="display:none">
                                                            <option value="1">1</option>
                                                            <option value="2" selected>2</option>
                                                            <option value="3">3</option>
                                                            <option value="4">4</option>
                                                        </select>
                                                    </div>
                                                    <label id="duration_label">
                                                    	And for the last
                                                    </label>
                                                    <div class="col-1" id="duration_div">
                                                        <input type="text" class="form-control" placeholder="minute" aria-label="" aria-describedby="basic-addon2" onkeyup="javascript:numOnly(this,document.frm,true);" name="duration" id="duration">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-xl-none m--margin-bottom-10"></div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    
                    <!-- Notify -->
                    <div class="m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30">
                        <div class="row align-items-center">
                            <div class="col-xl-12 col-lg-12">
                                <div class="form-group m-form__group row align-items-center">
                                	
                                    <div class="col-lg-6 col-xl-12">

                                        <div class="m-form__group m-form__group--inline">
                                            <div class="m-form__label">
                                                <label>
                                                    Notify
                                                </label>
                                            </div>
                                            <div class="m-form__control">
                                                <div class="row">
                                                    <div class="col-2">
                                                        <select class="form-control m-input form-filters" id="notify" name="notify">
                                                            <option value="">Select notify channel</option>
                                                            <option value="all">Email+Slack</option>
                                                            <option value="email">Email</option>
                                                            <option value="slack">Slack</option>
                                                            <option value="no">not use notify channel</option>
                                                        </select>
                                                    </div>
                                                    <label id="slack_label" style="display:none">
                                                    	Slack Information
                                                    </label>
                                                    <div class="col-5" id="slack_div"  style="display:none">
                                                        <input type="text" class="form-control" placeholder="slack token" aria-label="" aria-describedby="basic-addon2" name="slack_token" id="slack_token" value="">
                                                        &nbsp;&nbsp;
                                                        <input type="text" class="form-control" placeholder="slack channel name" aria-label="" aria-describedby="basic-addon2" name="slack_channel" id="slack_channel" value="">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-xl-none m--margin-bottom-10"></div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    
                    <!-- Notifi and Action -->
                    <div class="m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30">
                        <div class="row align-items-center">
                            <div class="col-xl-12 col-lg-12">
                                <div class="form-group m-form__group row align-items-center">
                                	
                                    <div class="col-lg-6 col-xl-12">

                                        <div class="m-form__group m-form__group--inline">
                                            <div class="m-form__label">
                                                <label>
                                                    Action
                                                </label>
                                            </div>
                                            <div class="m-form__control">
                                                <div class="row">
                                                    <div class="col-2">
                                                        <select class="form-control m-input form-filters" id="status" name="status">
                                                            <option value="">Select Action</option>
                                                            <option value="Y">Active</option>
                                                            <option value="N">InActive</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-xl-none m--margin-bottom-10"></div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    
                    <!-- Notify Message -->
                    <div class="m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30">
                        <div class="row align-items-center">
                            <div class="col-xl-12 col-lg-12">
                                <div class="form-group m-form__group row align-items-center">
                                	
                                    <div class="col-lg-6 col-xl-12">

                                        <div class="m-form__group m-form__group--inline">
                                            <div class="m-form__label">
                                                <label>
                                                    Message
                                                </label>
                                            </div>
                                            <div class="m-form__control">
                                                <div class="row">
                                                    <div class="col">
                                                        <input type="text" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon2" id="message" name="message" readOnly/><br>
                                                        <input type="text" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon2" id="message1" name="message1" readOnly/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-xl-none m--margin-bottom-10"></div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-10 col-xl-4">
                        <div class="form-group m-form__group">
                            <div class="input-group">
                                <div class="input-group-append">
                                    <button class="btn btn-success" type="button" onClick="location.href='/incident/rule'">Cancel</button>&nbsp;&nbsp;
                                    <button class="btn btn-brand" type="button" onClick="fnSave()" style="float:right">Save</button>
                                </div>
                                
                            </div>

                        </div>
                    </div>
                    </form>
                </div>
			</div>
		</div>
	</div>
</div>

<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script>
var g_dataload = false;

function numOnly(obj)
{
  if (event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39) return;
  var returnValue = "";
  for (var i = 0; i < obj.value.length; i++){
    if (obj.value.charAt(i) >= "0" && obj.value.charAt(i) <= "9"){
      returnValue += obj.value.charAt(i);
    }else{
      returnValue += "";
    }
  }

  obj.focus();
  obj.value = returnValue;
}

function startSpinner()
{
	$.preloader.start({
            modal: true,
            src : 'sprites2.png'
        });
}

function stopSpinner()
{
	$.preloader.stop();
}

function fnSave()
{
	var target			= $("#target").val();
	var metric			= $("#metric").val();
	var target_system	= $("#target_system").val();
	var equility		= $("#equility").val();
	var calc_value		= $("#calc_value").val();
	var calc_value_sel	= $("#calc_value_sel").val();
	var duration		= $("#duration").val();
	var notify			= $("#notify").val();
	var status			= $("#status").val();
	var message			= $.trim($("#message").val());
	var message1		= $.trim($("#message1").val());
	

	if( target == "" )
	{
		alert("This is a required.");
		$("#target").focus();
		return;
	}
	else if( metric == "" )
	{
		alert("This is a required.");
		$("#metric").focus();
		return;
	}
	else if( target_system == "" )
	{
		alert("This is a required.");
		$("#target_system").focus();
		return;
	}
	else if( equility == "" )
	{
		alert("This is a required.");
		$("#equility").focus();
		return;
	}
	else if( metric.indexOf("load") != -1 && calc_value_sel == "")
	{
		alert("This is a required.");
		$("#calc_value_sel").focus();
		return;
	}
	else if( metric.indexOf("load") == -1 && calc_value == "" )
	{
		alert("This is a required.");
		$("#calc_value").focus();
		return;
	}
	else if( duration == "" && target != "Disk" )
	{
		alert("This is a required.");
		$("#duration").focus();
		return;
	}
	
	else if( notify == "" )
	{
		alert("This is a required.");
		$("#notify").focus();
		return;
	}
	else if( notify == "all" || notify == "slack")
	{
		if( $.trim($("#slack_token").val()) == "" )
		{
			alert("This is a required.");
			$("#slack_token").focus();
			return;
		}
		else if( $.trim($("#slack_channel").val()) == "" )
		{
			alert("This is a required.");
			$("#slack_channel").focus();
			return;
		}
	}
	
	else if( status == "" )
	{
		alert("This is a required.");
		$("#status").focus();
		return;
	}
	/*
	else if( message == "" )
	{
		alert("This is a required.");
		$("#message").focus();
		return;
	}
	*/
	
	$("#message").val( message+" "+message1);
	
	if(confirm("Would you like to create it?"))
	{
		startSpinner();
		var param	= jQuery("#frm").serialize();
		$.ajax({
			url: "/api/v1/rule/create",
			type: "post",
			data: param,
			dataType: "json",
			success: function(data){
				if( data.responseCode == "200" )
				{
					location.href='/incident/rule'
				}
				else
				{
					alert('작업중 오류가 발생 하였습니다. 잠시후 다시 시도 하세요.')
					return;
				}
			},
			error		:
				function (jqXHR, textStatus, errorThrown) {
					stopSpinner();
					alert('오류가 발생했습니다\n[' + textStatus + ']\n' + errorThrown);
			},
			complete   : function() {
		         stopSpinner();
		    }
		});
	}
}

function fnMakeMessage()
{
	var message						= "";
	// Condition 설정
	var condition					= "";
	var metric						= $("#metric").val();
	var target_system				= $("#target_system").val();
	var target						= $("#target").val();
	var equility					= $("#equility").val();
	var calc_value					= $("#calc_value").val();
	var calc_value_sel				= $("#calc_value_sel").val();
	var duration					= $("#duration").val();
	
	if( metric.indexOf("load") != -1 )
		calc_value						= calc_value_sel;
	
	condition							= equility+""+calc_value+" and >"+duration+"m";
	
	// Message 설정
	// CPU & Memory 사용량 체크
	if( "CPU" == target || "Memory" == target )
	{
		if( metric.indexOf("percent") != -1 && metric.indexOf("cpu wait") == -1)
			message								= "[%s] The "+target+" usage has been being exceed "+calc_value+" over the last"+duration+" minutes. " + message;
			//message								= "[%s] "+duration+"분동안 "+target+" 사용율이 "+calc_value+"%% 이상 입니다."+message;
		else if( metric.indexOf("cpu wait") != -1)
			message								= "[%s] The average values of I/O Wait for " + duration+ " minutes has been being exceed threshold " +calc_value+"%%. "+ message;
			//message								= "[%s] iowait(%%) "+duration+"분 평균값이 임계치("+calc_value+"%%) 이상지속."+message;
		else if( metric.indexOf("cpu load 1 minutes") != -1)
			message								= "[%s] The average values of CPU Load1 have been being exceed threshold 2 for "+duration+" minutes. "+message;
			//message								= "[%s] "+duration+"분동안 CPU Load1  평균값이 임계치 이상 지속."+message;
		else if( metric.indexOf("cpu load 5 minutes") != -1)
			message								= "[%s] The average values of CPU Load5 have been being exceed threshold 2 for "+duration+" minutes. "+message;
			//message								= "[%s] "+duration+"분동안 CPU Load5  평균값이 임계치 이상 지속."+message;
		else if( metric.indexOf("cpu load 15 minutes") != -1)
			message								= "[%s] The average values of CPU Load15 have been being exceed threshold 2 for "+duration+" minutes. "+message;
			//message								= "[%s] "+duration+"분동안 CPU Load15  평균값이 임계치 이상 지속."+message;
		else if( metric.indexOf("swap") != -1)
			message								= "[%s] The Swap Memory usage has been being exceed "+calc_value+" for "+duration+" minutes. "+message;
			//message								= "[%s] "+duration+"분동안 Swap Memory 사용율이 "+calc_value+"%% 이상입니다."+message;
		else if( metric.indexOf("idle") != -1)
			message								= "[%s] The average values of CPU usage for "+duration+" minutes have been being exceed "+calc_value+ "%%. "+message;
			//message								= "[%s] CPU 사용량(%%)의 "+duration+"분 평균값이 "+calc_value+"%% 이상 지속."+message;
		else if( metric.indexOf("wait") != -1)
			message								= "[%s] The average values of iowait usage for "+duration+" minutes have been being exceed " + calc_value + "%%. "+message;
			//message								= "[%s] iowait(%%) "+duration+"분 평균값이 임계치("+calc_value+"%%) 이상."+message;
	}
	// Network
	else if ( "Network" == target )
	{
		// 수신 패킷 drop
		if( metric.indexOf("rx dropped") != -1) 
			message								= "[%s] The dropped IN packets have been increased over the last "+duration+" minutes. "+message;
			//message								= "[%s] "+duration+"동안 Dorp된 In 패킷이 증가 하고 있습니다."+message;
		
		// 송신 패킷 drop
		else if( metric.indexOf("tx dropped") != -1) 
			message								= "[%s] The dropped OUT packets have been increased over the last "+duration+" minutes. "+message;
			//message								= "[%s] "+duration+"동안 Dorp된 Out 패킷이 증가 하고 있습니다."+message;
		
		// 수신 패킷 error
		else if( metric.indexOf("rx errors") != -1) 
			message								= "[%s] The IN packets which Error happened have been increased over the last "+duration+" minutes. "+message;
			//message								= "[%s] "+duration+"분동안 Error가 발생된 In 패킷이 증가하고 있습니다."+message;
		
		// 송신 패킷 error
		else if( metric.indexOf("tx errors") != -1) 
			message								= "[%s] The OUT packets which Error happened have been increased over the last "+duration+" minutes. "+message;
			//message								= "[%s] "+duration+"분동안 Error가 발생된 Out 패킷이 증가하고 있습니다."+message;	
		
		// inbound bytes
		else if( metric.indexOf("inbound bytes") != -1) 
			message								= "[%s] The inbound network Traffic (byte) per second of NIC is over ("+calc_value+") during "+duration+" minutes. "+message;
			//message								= "[%s] "+duration+"분동안 NIC의 초당 inbound byte 값이 임계치("+calc_value+") 이상입니다."+message;	
		
		// outbound bytes
		else if( metric.indexOf("outbound bytes") != -1) 
			message								= "[%s] The outbound network Traffic (byte) per second of NIC is over ("+calc_value+") during "+duration+" minutes. "+message;
			//message								= "[%s] "+duration+"분동안 NIC의 초당 outbound byte 값이 임계치("+calc_value+") 이상입니다."+message;	
	}
	// Disk
	else if ( "Disk" == target )
	{
		message									= "[%s] Free disk space less than "+(100-parseInt(calc_value))+"%%";
	}
	
	// Process
	else if( "Process" == target )
	{
		// Stopped Process
		if( metric.indexOf("stopped") != -1 )
			message								= "[%s] The process is terminated abnormally in dead status.";
			//message								= "[%s] Process dead 상태로 비정상 종료";
		
		// Zombie process
		else if( metric.indexOf("zombie") != -1 )
			message								= "[%s] The Zombies process is detected on the computer resources";
			//message								= "[%s] Process Zombies 상태로 Compute Resource사용중";
	}
	
	$("#message").val(message);
}

//Rule Target System List
function appendTargetSystem (data) {
	data.sort();
	data.forEach(function(item){
		$('#target_system').append("<option value='"+item.target_system+"'>"+item.target_system+"</option>");
	});
}

$(function(){
	new Client().url("/api/v1/rule/ruleset/targetsystem").callback(appendTargetSystem).get();
	
	$("#target_system").change(function() {
		  
		$("select[name='target'] option").remove();
		$("select[name='metric'] option").remove();
		$('#target').append("<option value=''>Select Target</option>");
		$('#metric').append("<option value=''>Select Metric</option>");
		
		
		$("#duration_label").show();
		$("#duration_div").show();
		$("#calc_value").show();
		$("#calc_value_sel").hide();
		
		var notify	= $("#notify").val();
		if( notify == "all" || notify == "slack")
		{
			$("#slack_label").show();
			$("#slack_div").show();
		}
		else
		{
			$("#slack_label").hide();
			$("#slack_div").hide();
		}
		
		var target_system 	= $(this).val();
		if( target_system != "" )
		{
		 	$.ajax({
				url: "/api/v1/rule/ruleset/target_system/"+target_system+"/target",
				type: "get",
				data: {},
				dataType: "json",
				success: function(data){
					if( !g_dataload )
						stopSpinner();
					
					//console.log(data);
					$("select[name='target'] option").remove();
					
					var obj = JSON.parse(data.responseBody);
					
					$('#target').append("<option value=''>Select Target</option>");
					
					for( var i=0;i<obj.length;i++)
					{
						$('#target').append("<option value='"+obj[i].target+"'>"+obj[i].target+"</option>");
					}
					
					if( g_dataload )
					{
						$("#target").val(g_target);
						$("#target").trigger("change");
					}
				},
				error		:
					function (jqXHR, textStatus, errorThrown) {
						alert('오류가 발생했습니다\n[' + textStatus + ']\n' + errorThrown);
				},
				complete   : function() {
			         stopSpinner();
			    }
			});
		}
	});
	
	$("#target").change(function() {
		var target_system 	= $("#target_system").val();
		var target 	= $(this).val();
		if( target == "Disk" )
		  {
			  $("#duration_label").hide();
			  $("#duration_div").hide();
			  $("#calc_value_sel").hide();
			  $("#calc_value").show();
		  }	
		  else
		  {
			  $("#duration_label").show();
			  $("#duration_div").show();
			  $("#calc_value_sel").hide();
			  $("#calc_value").show();
		  }
		  
		  var notify	= $("#notify").val();
		  if( notify == "all" || notify == "slack")
		  {
			  $("#slack_label").show();
			  $("#slack_div").show();
		  }
		  else
		  {
			  $("#slack_label").hide();
			  $("#slack_div").hide();
		  }
		  
		  if( !g_dataload )
		  	startSpinner();
		  
		if( target != "" )
		{
		 	$.ajax({
				url: "/api/v1/rule/ruleset/target_system/"+target_system+"/target/"+target+"/metric",
				type: "get",
				data: {},
				dataType: "json",
				success: function(data){
					//console.log(data);
					$("select[name='metric'] option").remove();
					
					var obj = JSON.parse(data.responseBody);
					
					$('#metric').append("<option value=''>Select Metric</option>");
					
					for( var i=0;i<obj.length;i++)
					{
						$('#metric').append("<option value='"+obj[i].alias+"'>"+obj[i].alias+"</option>");
					}
					if( g_dataload )
					{
						$("#metric").val(g_alias);
						$("#metric").trigger("change");
					}
				},
				error		:
					function (jqXHR, textStatus, errorThrown) {
						alert('오류가 발생했습니다\n[' + textStatus + ']\n' + errorThrown);
				},
				complete   : function() {
			         stopSpinner();
			    }
			});
		}
	});
	
	$("#metric").change(function() {
		  var target 	= $("#target").val();
		  var alias		= $(this).val();
		  
		  if( target == "CPU" && alias.indexOf("load") != -1 )
		  {
			  $("#duration_label").show();
			  $("#duration_div").show();
			  
			  $("#calc_value_sel").show();
			  $("#calc_value").hide();
		  }
		  else if( target == "CPU" && alias.indexOf("load") == -1 )
		  {
			  $("#duration_label").show();
			  $("#duration_div").show();
			  
			  $("#calc_value_sel").hide();
			  $("#calc_value").show();
		  }
		  else if( "Process" == target )
		  {
			  $("#duration_label").hide();
			  $("#duration_div").hide();
			  
			  $("#calc_value_sel").hide();
			  $("#calc_value").show();
		  }
		  
		  var notify	= $("#notify").val();
		  if( notify == "all" || notify == "slack")
		  {
			  $("#slack_label").show();
			  $("#slack_div").show();
		  }
		  else
		  {
			  $("#slack_label").hide();
			  $("#slack_div").hide();
		  }
		  
		  if( !g_dataload )
		  	fnMakeMessage();
		  
		  if( g_dataload )
		  {
			  	var condition	= g_condition;
			  
				// >50 and >20m
				if( condition.indexOf("and") != -1 )
					var arrStr		= $.trim(condition).split("and");
				else if( condition.indexOf("or") != -1 )
					var arrStr		= $.trim(condition).split("or");
				else
					var arrStr		= $.trim(condition).split(" ");
				var dummy			= $.trim(arrStr[0]).split("><")
				
				if( arrStr[0].indexOf(">") != -1)
					$("#equility").val(">");
				else if( arrStr[0].indexOf("<") != -1)
					$("#equility").val("<");
				else if( arrStr[0].indexOf("=") != -1)
					$("#equility").val("=");
				
				if( alias.indexOf("load") != -1 )
				{	
					$("#calc_value_sel").val($.trim(dummy[0]).substring(1));
				}	
				else
				{
					$("#calc_value").val($.trim(dummy[0]).substring(1));
				}
				
				if( arrStr.length >= 2 )
				{
					if( "Process" != target )
					{
						dummy			= $.trim(arrStr[1]);
						
						var arrDumy			= dummy.split("><m");
	
						$("#duration").val( $.trim(dummy.substring(1, dummy.length-1)));
					}
				}
				
				//if( arrStr )
					
			  	g_dataload = false;
		  }
	});
	
	$("#equility").change(function() {
		fnMakeMessage();
	});
	
	$("#calc_value").change(function() {
		fnMakeMessage();
	});
	
	$("#calc_value_sel").change(function() {
		fnMakeMessage();
	});
	
	$("#duration").change(function() {
		fnMakeMessage();
	});
	
	$("#notify").change(function() {
		var notify	= $(this).val();
		if( notify == "all" || notify == "slack")
		{
			$("#slack_label").show();
			$("#slack_div").show();
		}
		else
		{
			$("#slack_label").hide();
			$("#slack_div").hide();
		}
	});
	
	
	if( "${idx}" != "" )
	{
		g_dataload = true;
		
		new Client().url("/api/v1/rule/${idx}").callback(getRulesetData).get();
		
		/*
		$("#severity").val("${rule.severity}");
		
		// Target System
		$("#target_system").val("${rule.target_system}");
		$("#target_system").trigger("change");
		
		
		
		$("#status").val("${rule.status}");
		$("#notify").val("${rule.notify}");
		$("#message").val("${rule.message}");
		*/
		
	}
});

var g_target;
var g_alias;
var g_condition;

function getRulesetData (data) {
	$("#slack_token").val(data.slack_token);
	$("#slack_channel").val(data.slack_channel);
	g_target			= data.target;
	g_alias				= data.alias;
	g_condition			= data.condition;
	
	$("#severity").val(data.severity);
	
	// Target System
	$("#target_system").val(data.target_system);
	$("#target_system").trigger("change");
	
	
	
	$("#status").val(data.status);
	$("#notify").val(data.notify);
	$("#message").val(data.message);
}
</script>