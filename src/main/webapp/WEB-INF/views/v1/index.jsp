<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- <script src="/resources/v1/assets/js/vendors.bundle.js" type="text/javascript"></script> -->

            <div class="m-content">
                <h2 class="m--font-success m--margin-bottom-10"><span>Docker Container (Host: <span id="host_name"></span> ) </span></h2>
                <!--Begin:: Widget-->
                <div class="row">
                    <div class="col-md-6">
                        <!--begin:: Container Status-->
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text">Container Status </h3>
                                    </div>
                                </div>
                            </div>
                            <div class="m-portlet__body">
                                <div class="m-widget14">
                                    <div class="row  align-items-center">
                                        <div class="col">
                                            <div id="m_chart_dashboard_container" class="m-widget14__chart m--margin-bottom-5" style="height: 200px">
                                                <div class="m-widget14__stat">
                                                    <a href="${prefix}/v1/agent" class="btn-link m--font-metal"><strong id="container_total"></strong></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="m-widget14__legends-container">
                                                <div class="m-widget14__legends">
                                                	<div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-accent"></span>
                                                        <span class="m-widget14__legend-text m--font-accent">Total : <strong id="total"></strong></span>
                                                    </div>
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-warning"></span>
                                                        <span class="m-widget14__legend-text m--font-warning">Running : <strong id="container_running"></strong></span>
                                                    </div>
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-metal"></span>
                                                        <span class="m-widget14__legend-text m--font-metal">Paused : <strong id="container_paused"></strong></span>
                                                    </div>
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-black"></span>
                                                        <span class="m-widget14__legend-text m--font-black">Stopped : <strong id="container_stopped"></strong></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!--end:: Container Status-->
                    </div>
                    
                    
                    <div class="col-md-6">
                        <!--begin:: Container Info-->
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text">Container Info</h3>
                                    </div>
                                </div>
                            </div>
                            <div class="m-portlet__body m-portlet__body--no-padding">
                                <!-- <table class="table m-table"> -->
                                <table class="table m-table">
                                    <colgroup>
                                        <col/>
                                        <col/>
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th class="text-left">Attribute Name</th>
                                        <th class="text-left">Values</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tbody">
                            
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!--end:: Container Info-->
                    </div>
                </div>
                <!--End:: Widget-->

                <!--Begin:: Widget-->
                <div class="row">
                    <div class="col-md-12">
                        <!--begin:: Container List-->
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text">Container List</h3>
                                    </div>
                                </div>
                            </div>
                            <div class="m-portlet__body">

								<!--begin: Search Form -->
                                <div class="m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30">
                                    <div class="row align-items-center">
                                        <div class="col-xl-12 col-lg-12">
                                            <div class="form-group m-form__group row align-items-center">
                                                <div class="col-lg-6 col-xl-6">

                                                    <div class="m-form__group m-form__group--inline">
                                                        <div class="m-form__label">
                                                            <label>
                                                                Filter
                                                            </label>
                                                        </div>
                                                        <div class="m-form__control">
                                                            <div class="row">
                                                                <div class="col">
                                                                    <select class="form-control m-input form-metrics" id="docker_name">
		                                                            	<option value="">Select Docker name</option>
		                                                            </select>
                                                                </div>
                                                                <div class="col">
		                                                            <select class="form-control m-input form-metrics" id="docker_status">
		                                                                <option value="">Select Docker status</option>
		                                                            </select>
		                                                        </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="d-xl-none m--margin-bottom-10"></div>
                                                </div>

                                                <div class="col-lg-10 col-xl-4">
                                                    <div class="form-group m-form__group">
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" placeholder="Search for..." aria-label="Search for..." aria-describedby="basic-addon2" id="searchTxt" OnKeyDown="f_fnSearch();">
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

                                <div id="list">
                                	<jsp:include page="/WEB-INF/views/v1/docker_list.jsp"/>
                                </div>
                                

							</div>
                        </div>
                        <!--end:: Container List-->
                    </div>
                </div>
                <!--End:: Widget-->
            </div>
            
<script>

	var isProcess = false;
	
	function f_fnSearch() 
	{
		if(event.keyCode != 13)
			return false;
		else
			fnSearch();
	}
	
	function fnSearch()
	{
		if( isProcess )
		{			
			alert('Loading data.\nPlease wait a minute.');
			return;
		}
		
		var name 		= $.trim($("#docker_name").val());
		var status		= $.trim($("#docker_status").val());
		var searchTxt	= $.trim($("#searchTxt").val());
		
	 	isProcess 	= true;

	 	$.ajax({
			url: "/containers/container_list",
			type: "POST",
			data: {
					  "ajax"		: "ajax"
					 ,"name"		: name
		     		 ,"status"		: status
					 ,"searchTxt"	: searchTxt
				  },
			dataType: "html",
			success: function(con_list){
				isProcess = false;
				$('#list').html(con_list);
			},
			error: function(request, status, error){
				isProcess = false;
 		        alert("code:"+request.status+"\n\n"+"message:"+request.responseText+"\n\n"+"error:"+error);
 		    }
		});
	}
	
	function fnDetailView( container_id )
	{
		location.href="/containers/"+container_id+"/detail";
	}
	
	function formatBytes(bytes, decimals) {
		if (bytes == 0)
			return '0 Bytes';
		var k = 1024, dm = decimals || 2, sizes = [ 'Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB' ], i = Math.floor(Math.log(bytes)/ Math.log(k));
		return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
	}
	
	function fnIndexView() {
		var container_name;
		var tmp_docker_status = [];
		var docker_status = [];
		var dockerData = JSON.parse(JSON.stringify(${con_list}));
		for(var i=0; i<dockerData.length; i++) {
			if (dockerData[i].Labels.MESOS_TASK_ID ){
    			container_name = dockerData[i].Labels.MESOS_TASK_ID;
    		}
    		else {
    			container_name = dockerData[i].Names[0].substr(1);
    		}
						
			$("#docker_name").append("<option value='"+container_name+"'>"+container_name+"</option>");
			
			tmp_docker_status.push(dockerData[i].State);
		}
		
		$.each(tmp_docker_status, function(key, value ){ 
			if($.inArray(value, docker_status) === -1)
				docker_status.push(value); 
		});
		
		for (var i=0; i<docker_status.length; i++) {
			$("#docker_status").append("<option value='"+docker_status[i]+"'>"+docker_status[i]+"</option>");	
		}
	
		var system_info = ${system_info};
		
		$("#host_name").html(system_info.Name);
		$("#total").html(system_info.Containers);
		$("#container_running").html(system_info.ContainersRunning);
		$("#container_paused").html(system_info.ContainersPaused);
		$("#container_stopped").html(system_info.ContainersStopped);
		
		if ($('#m_chart_dashboard_container').length == 0) {
	        return;
	    } 

		if( $('#m_chart_dashboard_container').children().length > 1 ) {
			$('#m_chart_dashboard_container').find('svg:first').remove();
		}
		
		var chart = new Morris.Donut({
	        element: 'm_chart_dashboard_container',
	        data: [{
	                label: "Total",
	                value: system_info.Containers
	            },
	            {
	                label: "Running",
	                value: system_info.ContainersRunning
	            },
	            {
	                label: "Paused",
	                value: system_info.ContainersPaused
	            },
	            {
	                label: "Stopped",
	                value: system_info.ContainersStopped
	            }
	        ],
	        colors: [
	            mUtil.getColor('accent'),
	            mUtil.getColor('warning'),
	            mUtil.getColor('metal'),
	            '#000000'
	        ],
	        
	        resize:true
	     
	    });
		
		var html = "";
		html += "<tr>";
		html += "  <td class='text-left'>Host Name</td>";
		html += "  <td class='text-left'>" + (system_info.Name) + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "  <td class='text-left'>Number of CPU</td>";
		html += "  <td class='text-left'>" + (system_info.NCPU) + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "  <td class='text-left'>Total Memory</td>";
		html += "  <td class='text-left'>" +formatBytes(system_info.MemTotal, 2) + "</td>";
		html += "</tr>";
		html += "  <td class='text-left'>OS Version</td>";
		html += "  <td class='text-left'>" + (system_info.OperatingSystem) + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "  <td class='text-left'>Kernel Version</td>";
		html += "  <td class='text-left'>" + (system_info.KernelVersion) + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "  <td class='text-left'>Docker Version</td>";
		html += "  <td class='text-left'>" + (system_info.ServerVersion) + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<tr>";
		html += "  <td class='text-left'>Docker Root Directory</td>";
		html += "  <td class='text-left'>" + (system_info.DockerRootDir) + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "  <td class='text-left'>Number of Images</td>";
		html += "  <td class='text-left'>" + (system_info.Images) + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "  <td class='text-left'>Number of Containers</td>";
		html += "  <td class='text-left'>" + (system_info.Containers) + "</td>";
		html += "</tr>";
		$("#tbody").html(html);
	}
	
	$(function() {
		fnIndexView();
		index_timer = setInterval("fnSearch()", 1000 * 30);
	});
</script>