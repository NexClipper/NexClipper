

/**
 * Highcharts Option setting
 *  UTC time to local time
 */
Highcharts.setOptions({
    global: {
        useUTC: false
    }
});

/**
 * Cluster Summary
 * 	:Cluster Name
 * 	:Cluster Leader
 * 	:Cluster uptime
 */
var dashboardInfo = function(cluster_name, cluster_leader, cluster_uptime){
	var start_time = new Date(parseInt(cluster_uptime) * 1000);
	start_time.setHours(start_time.getHours() + 9);
	
	$("#cluster_name").text(cluster_name);
	$("#cluster_leader").text(cluster_leader);
	$("#cluster_uptime").text(start_time.format('yyyy-MM-dd HH:dd'));
}


/**
 * Node별 task 목록 return
 */
var fnTasks = function(node_ip) {
	var taskJson = "";
	$.ajax({
		url : g_prefix+"/v1/agent/" + node_ip + "/tasks",
		type : "get",
		dataType : "json",
		async: false,
		success : function(json) {
			
			taskJson = json;
		},
		error : function(request, status, error) { // 오류가 발생했을 때 호출된다. 
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		},
		complete : function() { // 정상이든 비정상인든 실행이 완료될 경우 실행될 함수
			
		}
	});
	
	return taskJson;
};


/**
 * Task Donut 차트 제어
 * Chart 옆 summary 제어
 */ 
var dashboardTask = function(task_running, task_staging, task_warning) {
	if ($('#m_chart_dashboard_task').length == 0) {
        return;
    } 

	if( $('#m_chart_dashboard_task').children().length > 1 ) {
		$('#m_chart_dashboard_task').find('svg:first').remove();
	}
	
	var chart = new Morris.Donut({
        element: 'm_chart_dashboard_task',
        data: [{
                label: "task_running",
                value: task_running
            },
            {
                label: "task_staging",
                value: task_staging
            }
        ],
        colors: [
            mUtil.getColor('accent'),
            mUtil.getColor('warning')
        ],
        formatter: function(y,data) {
            return '$' + y;
        },
        resize:true
    });
    
    var task_total = task_running + task_staging; 
    
    $("#task_total").text(task_total);
	$("#task_running").text(task_running);
	$("#task_staging").text(task_staging);
	$("#task_warning").text(task_warning);
}


/**
 * Agent Donut chart 제어
 * Agent summary 제어
 */
var dashboardAgent = function(agent_total, agent_normal, agent_warning, agent_critical, agent_unknown, agent_disconnect, agent_unhealthy) {
    if ($('#m_chart_dashboard_agent').length == 0) {
        return;
    }
    
    if( $('#m_chart_dashboard_agent').children().length > 1 ) {
		$('#m_chart_dashboard_agent').find('svg:first').remove();
	}
    
    var chart = new Morris.Donut({
        element: 'm_chart_dashboard_agent',
        data: [{
                label: "agent_normal",
                value: agent_normal
            },
            {
                label: "agent_warning",
                value: agent_warning
            },
            {
                label: "agent_critical",
                value: agent_critical
            },
            {
                label: "agent_disconnect",
                value: agent_disconnect
            }
        ],
        colors: [
            mUtil.getColor('accent'),
            mUtil.getColor('warning'),
            mUtil.getColor('metal'),
            '#000000'
        ],
        formatter: function(y,data) {
            return '$' + y;
        },
        resize:true
    });
    
    $("#agent_total").text(agent_total);
    $("#agent_normal").text(agent_normal);
    $("#agent_warning").text(agent_warning);
    $("#agent_critical").text(agent_critical);
    $("#agent_unknown").text(agent_disconnect);
    $("#agent_unhealthy").text(agent_unhealthy);
}


/**
 * Host Donut chart 제어
 * Host summary 제어
 */
var dashboardHost = function(/*host_total, host_normal, host_shutdown, host_warning*/) {
    if ($('#m_chart_dashboard_host').length == 0) {
        return;
    }
    
    if( $('#m_chart_dashboard_host').children().length > 1 ) {
		$('#m_chart_dashboard_host').find('svg:first').remove();
	}
    
    var chart = new Morris.Donut({
        element: 'm_chart_dashboard_host',
        data: [{
                label: "host_total",
                value: 10
            },
            {
                label: "host_shutdown",
                value: 0
            }
        ],
        colors: [
            mUtil.getColor('accent'),
            '#000000'
        ],
        formatter: function(y,data) {
            return '$' + y;
        },
        resize:true
    });

    
    $("#host_total").text(10);
    $("#host_normal").text(10);
    $("#host_shutdown").text(0);
    $("#host_warning").text(0);
}

/**
 * Dashboard main view ajax call
 *  Task 및 Agent
 */
// Master 정보 ajax 호출
var g_task_running 		= 0;	// fnMaster()에서 초기화
var g_task_staging 		= 0;	// fnMaster()에서 초기화

var idx_master = 0;
var idxPoolSuccess_master = 'N';
function fnAjaxMaster() {
	idx_master++;
	if (idx_master == 50 && idxPoolSuccess_master == 'N') {
		fnMaster();
	} else if ((idx_master % 500) == 0) {
		fnMaster();
	}
}

function fnMaster(){
	idxPoolSuccess_master = 'Y';
	$.ajax({
		url			: g_prefix+"/v1/dashboard/master",
		type		: "get",
		dataType	: "json",
		success		: function(data){
			var master_obj			= data;
			
			var cluster_name		= master_obj.cluster;
			var cluster_leader		= master_obj.hostname;
			var cluster_uptime		= master_obj.start_time;
			
			g_task_running			= master_obj.task_running;
			g_task_staging			= parseInt(master_obj.task_staging) + parseInt(master_obj.task_starting);
			
			$("#cluster_total_cpus").text(master_obj.totalResource.cpus.toFixed(2));
		    $("#cluster_total_mem").text(master_obj.totalResource.mem.toFixed(2));
		    $("#cluster_total_disk").text(master_obj.totalResource.disk.toFixed(2));
		    $("#cluster_total_gpus").text(master_obj.totalResource.gpus.toFixed(2));
		    
		    $("#cluster_alloc_cpus").text(master_obj.allocateResource.cpus.toFixed(2));
		    $("#cluster_alloc_mem").text(master_obj.allocateResource.mem.toFixed(2));
		    $("#cluster_alloc_disk").text(master_obj.allocateResource.disk.toFixed(2));
		    $("#cluster_alloc_gpus").text(master_obj.allocateResource.gpus.toFixed(2));
		    
		    $("#cluster_alloc_per_cpus").text(master_obj.allocateResource.per_cpus.toFixed(2));
		    $("#cluster_alloc_per_mem").text(master_obj.allocateResource.per_mem.toFixed(2));
		    $("#cluster_alloc_per_disk").text(master_obj.allocateResource.per_disk.toFixed(2));
		    $("#cluster_alloc_per_gpus").text(master_obj.allocateResource.per_gpus.toFixed(2));
		    
		    
		    $("#cluster_usage_alloc_cpus").text(master_obj.allocateResource.cpus.toFixed(2));
		    $("#cluster_usage_alloc_mem").text(master_obj.allocateResource.mem.toFixed(2));
		    $("#cluster_usage_alloc_disk").text(master_obj.allocateResource.disk.toFixed(2));
		    $("#cluster_usage_alloc_gpus").text(master_obj.allocateResource.gpus.toFixed(2));
		    
		    $("#cluster_usage_used_cpus").text(master_obj.usedResource.cpus.toFixed(2));
		    $("#cluster_usage_used_mem").text(master_obj.usedResource.mem.toFixed(2));
		    $("#cluster_usage_used_disk").text(master_obj.usedResource.disk.toFixed(2));
		    $("#cluster_usage_used_gpus").text(master_obj.usedResource.gpus.toFixed(2));
		    
		    $("#cluster_usage_cpus").text(master_obj.usedResource.per_cpus.toFixed(2));
		    $("#cluster_usage_mem").text(master_obj.usedResource.per_mem.toFixed(2));
		    //$("#cluster_usage_disk").text(master_obj.usedResource.per_disk.toFixed(2));
		    $("#cluster_usage_disk").text(master_obj.usedResource.disk.toFixed(2));
		    $("#cluster_usage_gpus").text(master_obj.usedResource.per_gpus.toFixed(2));
			
			dashboardInfo(cluster_name, cluster_leader, cluster_uptime);
		},
		error		: function(reqeust, status, error) {
			console.log("code:" + request.status + "\n message:" 
					+ request.responseText + "\n error:" + error);
		},
		complete	: function() {
			idxPoolSuccess_master = 'N';
			idx_master = 0;
		}
	});
}

// Agent 상태 AJAX 호출
var idx_agent = 0;
var idxPoolSuccess_agent = 'N';
function fnAjaxAgent() {
	idx_agent++;
	if (idx_agent == 50 && idxPoolSuccess_agent == 'N') {
		fnAgent();
	} else if ((idx_framework % 500) == 0) {
		fnAgent();
	}
}

function fnAgent(){
	idxPoolSuccess_agent = 'Y';
	$.ajax({
		url			: g_prefix+"/v1/dashboard/agentStatus",
		type		: "get",
		dataType	: "json",
		success		: function(data){
			var agent_total			= 0;
			var agent_normal		= 0;
			var agent_warning		= 0;
			var agent_critical		= 0;
			var agent_unknown		= 0;
			var agent_disconnect 	= 0;
			
			var agent_list		= data;
			agent_total	= agent_list.length;
			
			for(var idx in agent_list){
				if( agent_list[idx].status == "-1" ){
					agent_disconnect++;
				}
				else if( agent_list[idx].status == "0"){
					agent_normal++;
				}
				else if( agent_list[idx].status == "1" ){
					agent_warning++;
				}
				else if( agent_list[idx].status == "2" ){
					agent_critical++;
				}
				else if( agent_list[idx].status == "3" ){
					agent_unknown++;
				}
			}
			
			dashboardAgent(agent_total, agent_normal, agent_warning, agent_critical, agent_unknown, agent_disconnect, g_agent_unhealthy);
			
			
		},
		error		: function(reqeust, status, error) {
			console.log("code:" + request.status + "\n message:" 
					+ request.responseText + "\n error:" + error);
		},
		complete	: function() {
			idxPoolSuccess_agent = 'N';
			idx_agent = 0;
		}
	});
}

// Service 및 Framework 정보 Ajax 호출
var g_task_warning 		= 0;	// fnFramework()에서 초기화
var g_agent_unhealthy 	= 0;	// fnFramework()에서 초기화

var idx_framework = 0;
var idxPoolSuccess_framework = 'N';
function fnAjaxFramework() {
	idx_framework++;
	if (idx_framework == 50 && idxPoolSuccess_framework == 'N') {
		fnFramework();
	} else if ((idx_framework % 500) == 0) {
		fnFramework();
	}
}

function fnFramework(){
	idxPoolSuccess_framework = 'Y';
	$.ajax({
		url			: g_prefix+"/v1/dashboard/agents",
		type		: "get",
		dataType	: "json",
		success		: function(data){
			g_task_warning = 0;
			g_agent_unhealthy = 0;
			var selected_service_arr = new Array();
			
			// 각 Node를 반복하여 task_warning의 갯수 count
			for( var idx in data.nodes ){
				var taskJson = fnTasks(data.nodes[idx].hostname);
				
				for( var i= 0; i < taskJson.tasks.length; i++){
					var executor_id = taskJson.tasks[i].executor_id;
					var task_name = executor_id;
					task_name = task_name.split(".")[0];
					task_name = task_name.split("_")[0];
					
					var node_ip = taskJson.tasks[i].node_ip;
					
					if( taskJson.tasks[i].usedResource.max_value >= 80 ){
						g_task_warning += 1;
					}
					
					// Selected framework / service - influxdb, nexcollecter, mysql
					var framework_obj = {};
					framework_obj.executor_id	= executor_id;
		        	framework_obj.name 			= task_name;
		        	framework_obj.cpu_usage		= taskJson.tasks[i].usedResource.per_cpus.toFixed(1) ;
		        	framework_obj.mem_usage		= taskJson.tasks[i].usedResource.per_mem.toFixed(1) ;
		        	framework_obj.disk_usage	= taskJson.tasks[i].usedResource.per_disk.toFixed(1) ;
		        	
		        	if( taskJson.tasks[i].usedResource.max_value >= 60 && taskJson.tasks[i].usedResource.max_value < 80 )	
		        		framework_obj.color = "warning";
		            else if( taskJson.tasks[i].usedResource.max_value >= 80 )	
		            	framework_obj.color = "danger";
		            else
		            	framework_obj.color = "success";
		        	
			        if( taskJson.tasks[i].executor_id.substr(0, "influx".length) == "influx" 
			        		|| taskJson.tasks[i].executor_id.substr(0, "nexcollecter".length) == "nexcollecter"
			        		|| task_name == "mysql" ) {
			            
			            selected_service_arr.push( framework_obj );
			        }
				}
				
				// 노드의 리소스 최대 사용률이 80이상일 때 agent unhealthy count 증가 
				if( data.nodes[idx].usedResource.max_value >= 80 ){
					g_agent_unhealthy += 1;
				}
			}
			
			dashboardFramework(selected_service_arr);
			dashboardTask(g_task_running, g_task_staging, g_task_warning);
		},
		error		: function(reqeust, status, error) {
			console.log("code:" + request.status + "\n message:" 
					+ request.responseText + "\n error:" + error);
		},
		complete	: function() {
			idxPoolSuccess_framework = 'N';
			idx_framework = 0;
		}
	});
}

// Host 정보 AJAX 호출
var idx_hostResource = 0;
var idxPoolSuccess_hostResource = 'N';
function fnAjaxHost() {
	idx_hostResource++;
	if (idx_hostResource == 50 && idxPoolSuccess_hostResource == 'N') {
		fnHostCPU();
	} else if ((idx_hostResource % 500) == 0) {
		fnHostCPU();
	}
}

function fnHostCPU() {
	idxPoolSuccess_hostResource = 'Y';
	$.ajax({
		url : g_prefix+"/v1/host/cpu",
		type : "get",
		dataType : "json",
		success : function(data) {
			var host_cpu_obj	= JSON.parse(data.json);
			var host_mem_obj	= JSON.parse(fnHostMEM());
			var host_disk_obj	= JSON.parse(fnHostDISK());
			
			/*var cpu_system_series	= host_cpu_obj.results[0].series;
			var cpu_user_series		= host_cpu_obj.results[1].series;
			var cpu_iowait_series	= host_cpu_obj.results[2].series;*/
			var mem_series			= host_mem_obj.results[0].series;
			var disk_series			= host_disk_obj.results[0].series;
			
			var host_total		= 0;
			var host_normal		= 0;
			var host_shutdown	= 0;
			var host_warning	= 0;	// 80 이상
			
			host_total = host_cpu_obj.results[0].series.length;
			for( var i = 0; i < g_host_total; i++){
				//var used_percent = cpu_system_series[i].values[0][1] + cpu_user_series[i].values[0][1] + cpu_iowait_series[i].values[0][1];
				var used_percent = host_cpu_obj.results[0].series[i].values[0][1] + host_cpu_obj.results[0].series[i].values[0][2] + host_cpu_obj.results[0].series[i].values[0][3];
				
				if( used_percent >= 80 || mem_series[i].values[0][1] >= 80 || disk_series[i].values[0][1] >= 80 ) {
					host_warning++;
				}
				else {
					host_normal++;
				}
			}
			
			dashboardHost(host_total, host_normal, host_shutdown, host_warning);
		},
		error : function(request, status, error) { // 오류가 발생했을 때 호출된다. 
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		},
		complete : function() { // 정상이든 비정상인든 실행이 완료될 경우 실행될 함수
			idxPoolSuccess_hostResource= 'N';
			idx_hostResource = 0;
		}
	});
}


function fnHostMEM() {
	var host_mem_obj = "";
	$.ajax({
		url : g_prefix+"/v1/host/mem",
		type : "get",
		dataType : "json",
		async: false,
		success : function(data) {
			
			host_mem_obj = data.json;
		},
		error : function(request, status, error) { // 오류가 발생했을 때 호출된다. 
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		},
		complete : function() { // 정상이든 비정상인든 실행이 완료될 경우 실행될 함수
			
		}
	});
	
	return host_mem_obj;
};


function fnHostDISK() {
	var host_disk_obj = "";
	$.ajax({
		url : g_prefix+"/v1/host/disk",
		type : "get",
		dataType : "json",
		async: false,
		success : function(data) {
			
			host_disk_obj = data.json;
		},
		error : function(request, status, error) { // 오류가 발생했을 때 호출된다. 
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		},
		complete : function() { // 정상이든 비정상인든 실행이 완료될 경우 실행될 함수
			
		}
	});
	
	return host_disk_obj;
};


//== Sparkline Chart helper function
// Chart.js 를 그리는 함수
var _initSparklineChart = function(src, data, color, border, date) {
    if (src.length == 0) {
        return;
    }

    var config = {
        type: 'line',
        data: {
            labels: date,
            datasets: [{
                label: "",
                borderColor: color,
                borderWidth: border,
                pointHoverRadius: 4,
                pointHoverBorderWidth: 12,
                pointBackgroundColor: Chart.helpers.color('#000000').alpha(0).rgbString(),
                pointBorderColor: Chart.helpers.color('#000000').alpha(0).rgbString(),
                pointHoverBackgroundColor: mUtil.getColor('danger'),
                pointHoverBorderColor: Chart.helpers.color('#000000').alpha(0.1).rgbString(),
                fill: false,
                data: data,
            }]
        },
        options: {
            title: {
                display: false,
            },
            tooltips: {
                enabled: false,
                intersect: false,
                mode: 'nearest',
                xPadding: 10,
                yPadding: 10,
                caretPadding: 10
            },
            legend: {
                display: false,
                labels: {
                    usePointStyle: false
                }
            },
            responsive: true,
            maintainAspectRatio: true,
            hover: {
                mode: 'index'
            },
            scales: {
                xAxes: [{
                    display: false,
                    gridLines: false,
                    scaleLabel: {
                        display: true,
                        labelString: 'Month'
                    }
                }],
                yAxes: [{
                    display: false,
                    gridLines: false,
                    scaleLabel: {
                        display: true,
                        labelString: 'Value'
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },

            elements: {
                point: {
                    radius: 4,
                    borderWidth: 12
                },
            },

            layout: {
                padding: {
                    left: 0,
                    right: 10,
                    top: 5,
                    bottom: 10
                }
            },
            animation: false
        }
    };

    return new Chart(src, config);
}

function setLabels(labels, append_labels) {
	for(var i in append_labels){
		labels.push( append_labels[i] );
		labels.shift();
	}
}

function setData(data_arr, append_data_arr) {
	for(var i in append_data_arr){
		data_arr.push( append_data_arr[i] );
		data_arr.shift();
	}
}


/**
 * Cluster Resource Allocated 제어
 * Allocated resource summary 제어
 */
//** Based on Chartjs plugin - http://www.chartjs.org/
// Cluster Resource Allocation 차트를 직접 그리는 부분
var g_chart_cpu_alloc;
var g_chart_mem_alloc;
var g_chart_disk_alloc;
var g_chart_gpu_alloc;
var g_alloc_last_time;

var idx_alloc_resource = 0;
var idxPoolSuccess_alloc_resource = 'N';

var dashboardAllocResource = function( resource_obj, data, date, last_time ) {
    // Init chart instances
	g_chart_cpu_alloc = _initSparklineChart($('#m_chart_cpu_alloc'), data[0], mUtil.getColor('brand'), 2, date);
	g_chart_mem_alloc = _initSparklineChart($('#m_chart_mem_alloc'), data[1], mUtil.getColor('danger'), 2, date);
	g_chart_disk_alloc = _initSparklineChart($('#m_chart_disk_alloc'), data[2], mUtil.getColor('success'), 2, date);
	g_chart_gpu_alloc = _initSparklineChart($('#m_chart_gpu_alloc'), data[3], mUtil.getColor('warning'), 2, date);
    
    g_alloc_last_time = last_time;
    
    $("#cluster_total_cpus").text(resource_obj.total_cpus);
    $("#cluster_total_mem").text(resource_obj.total_mem);
    $("#cluster_total_disk").text(resource_obj.total_disk);
    $("#cluster_total_gpus").text(resource_obj.total_gpus);
    
    $("#cluster_alloc_cpus").text(resource_obj.allocate_cpus);
    $("#cluster_alloc_mem").text(resource_obj.allocate_mem);
    $("#cluster_alloc_disk").text(resource_obj.allocate_disk);
    $("#cluster_alloc_gpus").text(resource_obj.allocate_gpus);
    
    $("#cluster_alloc_per_cpus").text(resource_obj.allocate_per_cpus);
    $("#cluster_alloc_per_mem").text(resource_obj.allocate_per_mem);
    $("#cluster_alloc_per_disk").text(resource_obj.allocate_per_disk);
    $("#cluster_alloc_per_gpus").text(resource_obj.allocate_per_gpus);
}

function fnAjaxAllocResource(){
	idx_alloc_resource++;
	
	if( idx_alloc_resource == 50 && idxPoolSuccess_alloc_resource == 'N' ){
		fnAllocResource();
	} else if( (idx_alloc_resource % 500) == 0 ){
		fnAllocResource();
	}
}

function fnAllocResource (){
	idxPoolSuccess_alloc_resource = 'Y';
	
	var append_data_cpu		= new Array();
	var append_data_mem		= new Array();
	var append_data_disk	= new Array();
	var append_data_gpu		= new Array();
	$.ajax({
		url			: g_prefix+"/v1/dashboard/allocCluster",
		type		: "get",
		data		: {"time": g_alloc_last_time},
		dataType	: "json",
		success		: function (data){
			if( data.result == "success" )
			{
				var data_arr = new Array();
				var date_arr = new Array();
				
				var obj = JSON.parse(data.json);

				if( obj.results.length > 0 && typeof obj.results[0].series != "undefined" )
				{
					for( var i = 0; i< obj.results[0].series[0].values.length; i++ )
					{
						var time	= obj.results[0].series[0].values[i][0];
						var arrTime = time.split("Z");
						var dt = new Date(arrTime[0]);
						dt.setHours(dt.getHours() + 9);
						
						date_arr.push(dt);
						
						append_data_cpu.push( obj.results[0].series[0].values[i][1] );
						append_data_mem.push( obj.results[0].series[0].values[i][2] );
						append_data_disk.push( obj.results[0].series[0].values[i][3] );
						append_data_gpu.push( obj.results[0].series[0].values[i][4] );
					}
				}
				
				var alloc_data_cpu		= g_chart_cpu_alloc.data.datasets[0].data;
				var alloc_data_mem		= g_chart_mem_alloc.data.datasets[0].data;
				var alloc_data_disk		= g_chart_disk_alloc.data.datasets[0].data;
				var alloc_data_gpu		= g_chart_gpu_alloc.data.datasets[0].data;
				
				var usage_labels		= g_chart_cpu_alloc.data.labels;
				
				setLabels(usage_labels, date_arr);
				setData(alloc_data_cpu, append_data_cpu);
				setData(alloc_data_mem, append_data_mem);
				setData(alloc_data_disk, append_data_disk);
				setData(alloc_data_gpu, append_data_gpu);
				
				g_chart_cpu_alloc = _initSparklineChart($('#m_chart_cpu_alloc'), alloc_data_cpu, mUtil.getColor('brand'), 2, usage_labels);
				g_chart_mem_alloc = _initSparklineChart($('#m_chart_mem_alloc'), alloc_data_mem, mUtil.getColor('danger'), 2, usage_labels);
				g_chart_disk_alloc = _initSparklineChart($('#m_chart_disk_alloc'), alloc_data_disk, mUtil.getColor('success'), 2, usage_labels);
				g_chart_gpu_alloc = _initSparklineChart($('#m_chart_gpu_alloc'), alloc_data_gpu, mUtil.getColor('warning'), 2, usage_labels);
			}
		},
		error		: function(reqeust, status, error) {
			console.log("code:" + request.status + "\n message:" 
					+ request.responseText + "\n error:" + error);
		},
		complete	: function() {
			idxPoolSuccess_alloc_resource = 'N';
			idx_alloc_resource = 0;
		}
	})
}


/**
 * Cluster Resource Used 제어
 * Used resource summary 제어
 */
//** Based on Chartjs plugin - http://www.chartjs.org/
// Cluster Resource Usage 차트를 직접 그리는 부분
var g_chart_cpu_usage;
var g_chart_mem_usage;
var g_chart_disk_usage;
var g_chart_gpu_usage;
var g_usage_last_time;

var idx_used_resource = 0;
var idxPoolSuccess_used_resource = 'N';

var dashboardUsedResource = function( resource_obj, data, date, last_time ) {	
    // Init chart instances
	g_chart_cpu_usage = _initSparklineChart($('#m_chart_cpu_usage'), data[0], mUtil.getColor('brand'), 2, date);
	g_chart_mem_usage = _initSparklineChart($('#m_chart_mem_usage'), data[1], mUtil.getColor('danger'), 2, date);
	g_chart_disk_usage = _initSparklineChart($('#m_chart_disk_usage'), data[2], mUtil.getColor('success'), 2, date);
	g_chart_gpu_usage = _initSparklineChart($('#m_chart_gpu_usage'), data[3], mUtil.getColor('warning'), 2, date);
    
	g_usage_last_time = last_time;
	
    $("#cluster_usage_cpus").text(resource_obj.use_per_cpus);
    $("#cluster_usage_mem").text(resource_obj.use_per_mem);
    //$("#cluster_usage_disk").text(resource_obj.use_per_disk);
    $("#cluster_usage_disk").text(resource_obj.use_disk);
    $("#cluster_usage_gpus").text(resource_obj.use_per_gpus);
    
    $("#cluster_usage_alloc_cpus").text(resource_obj.allocate_cpus);
    $("#cluster_usage_alloc_mem").text(resource_obj.allocate_mem);
    $("#cluster_usage_alloc_disk").text(resource_obj.allocate_disk);
    $("#cluster_usage_alloc_gpus").text(resource_obj.allocate_gpus);
    
    $("#cluster_usage_used_cpus").text(resource_obj.use_cpus);
    $("#cluster_usage_used_mem").text(resource_obj.use_mem);
    //$("#cluster_usage_used_disk").text(resource_obj.use_disk);
    $("#cluster_usage_used_gpus").text(resource_obj.use_gpus);
}

function fnAjaxUsedResource(){
	idx_used_resource++;
	
	if( idx_used_resource == 50 && idxPoolSuccess_used_resource == 'N' ){
		fnUsedResource();
	} else if( (idx_used_resource % 500) == 0 ){
		fnUsedResource();
	}
}

function fnUsedResource (){
	idxPoolSuccess_used_resource = 'Y';
	
	var append_data_cpu		= new Array();
	var append_data_mem		= new Array();
	var append_data_disk	= new Array();
	var append_data_gpu		= new Array();
	$.ajax({
		url			: g_prefix+"/v1/dashboard/usedCluster",
		type		: "get",
		data		: {"time": g_usage_last_time},
		dataType	: "json",
		success		: function (data){
			if( data.result == "success" )
			{
				var data_arr = new Array();
				var date_arr = new Array();
				
				var obj = JSON.parse(data.json);

				if( obj.results.length > 0 && typeof obj.results[0].series != "undefined" )
				{
					for( var i = 0; i< obj.results[0].series[0].values.length; i++ )
					{
						var time	= obj.results[0].series[0].values[i][0];
						var arrTime = time.split("Z");
						var dt = new Date(arrTime[0]);
						dt.setHours(dt.getHours() + 9);
						
						date_arr.push(dt);
						
						append_data_cpu.push( obj.results[0].series[0].values[i][1] );
						append_data_mem.push( obj.results[0].series[0].values[i][2] );
						append_data_disk.push( obj.results[0].series[0].values[i][3] );
						append_data_gpu.push( obj.results[0].series[0].values[i][4] );
					}
				}
				
				var usage_data_cpu		= g_chart_cpu_usage.data.datasets[0].data;
				var usage_data_mem		= g_chart_mem_usage.data.datasets[0].data;
				var usage_data_disk		= g_chart_disk_usage.data.datasets[0].data;
				var usage_data_gpu		= g_chart_gpu_usage.data.datasets[0].data;
				
				var usage_labels		= g_chart_cpu_usage.data.labels;
				
				setLabels(usage_labels, date_arr);
				setData(usage_data_cpu, append_data_cpu);
				setData(usage_data_mem, append_data_mem);
				setData(usage_data_disk, append_data_disk);
				setData(usage_data_gpu, append_data_gpu);
				
				g_chart_cpu_usage = _initSparklineChart($('#m_chart_cpu_usage'), usage_data_cpu, mUtil.getColor('brand'), 2, usage_labels);
				g_chart_mem_usage = _initSparklineChart($('#m_chart_mem_usage'), usage_data_mem, mUtil.getColor('danger'), 2, usage_labels);
				g_chart_disk_usage = _initSparklineChart($('#m_chart_disk_usage'), usage_data_disk, mUtil.getColor('success'), 2, usage_labels);
				g_chart_gpu_usage = _initSparklineChart($('#m_chart_gpu_usage'), usage_data_gpu, mUtil.getColor('warning'), 2, usage_labels);
			}
		},
		error		: function(reqeust, status, error) {
			console.log("code:" + request.status + "\n message:" 
					+ request.responseText + "\n error:" + error);
		},
		complete	: function() {
			idxPoolSuccess_used_resource = 'N';
			idx_used_resource = 0;
		}
	})
}


/**
 * Task(Container) Performance Chart
 * HighChart
 *  - Dummy Data
 */
var g_chart_perform;
var g_perform_time='';
var idx_perform = 0;
var idxPerformSuccess_failure = 'N';
var isPerformFirst			= true;
var t_performance;
var influxData = new Array();
var serviceMap = new Map();
var serviceUUIDMap = new Map();

var dashboardPerfom = function(){
	g_chart_perform = new Highcharts.Chart({
	    chart: {
	        zoomType: 'x',
	        animation: false,
	        renderTo: 'm_chart_task_perform',
	        events: {
	        	load: function(){
	        		t_performance		= setInterval("fnAjaxPerform()", 100);
	        	}
				/*
	        	,click: function () {
	        		alert(1);
                    //alert( serviceMap.get(this.x+"_"+this.y) );
                }
                */
	        }
	    },
	    title: {
	        text: ''
	    },
	    subtitle: {
	        text: ''
	    },
	    navigation: {
	        buttonOptions: {
	            enabled: false
	        }
	    },
	    xAxis: {
	        type: 'datetime',
        	dateTimelabelFormats: {
        		second: '%Y-%m-%d %H:%M:%S'
        	}
	    },
	    yAxis: { 
	    	title: {
                text: 'ms'
            }
	    },
	    
	    tooltip: {
            formatter: function() {
                return '<b>'+ serviceMap.get(this.x+"_"+this.y) +'</b><br/>'+
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                Highcharts.numberFormat(this.y, 2);
	        }
	    },
	    
	    legend: false,
	    colors: ['#008000'],
	    series: []
	});
	
}

function fnAjaxPerform(){
	idx_perform++;
	
	if( idx_perform == 1 && idxPerformSuccess_failure == 'N' ){
		fnPerform(time);
	} else if( (idx_perform % 100) == 0 ){
		fnPerform(time);
	}
}


var isAddSeries_normal	= false;
var isAddSeries_warning = false;

function fnPerform (){
	idxPerformSuccess_failure = 'Y';
	
	$.ajax({
		url			: g_prefix+"/v1/dashboard/perform",
		type		: "get",
		data		: {"time": g_perform_time},
		dataType	: "json",
		success		: function (data){
			if( data.result == "success" )
			{
				influxData_normal = new Array();
				influxData_warning = new Array();
				
				var obj = data.json;
				var isNullPush = false;

				if( obj.results.length > 0 && typeof obj.results[0].series != "undefined" )
				{
					influxData_normal = [];
					influxData_warning = [];
					for( var loop=0;loop<obj.results[0].series.length;loop++)
					{
						for( var i = 0; i < obj.results[0].series[loop].values.length; i++ )
						{
							if( obj.results[0].series[loop].values[i][0] > g_perform_time )
								g_perform_time	= obj.results[0].series[loop].values[i][0];
							
							var arrTime = (obj.results[0].series[loop].values[i][0]).split("Z");
							var dt = new Date(arrTime[0]);
							dt.setHours(dt.getHours() + 9);
							var dateA = dt.getTime();
							
							//console.log("size::"+serviceMap.size());
							
							// 3초이상 걸린 거래
							if( obj.results[0].series[loop].values[i][1] > 3000 )
							{
								if( !isAddSeries_warning )
								{
									influxData_warning.push( [dateA, obj.results[0].series[loop].values[i][1]] );
									influxData_normal.push( [dateA, null] );
									
									if( obj.results[0].series[loop].values[i][1] != null )
									{
										serviceMap.put( dateA+"_"+obj.results[0].series[loop].values[i][1],obj.results[0].series[loop].tags.service_id+"("+obj.results[0].series[loop].tags.uri+")");
										serviceUUIDMap.put( dateA+"_"+obj.results[0].series[loop].values[i][1],obj.results[0].series[loop].tags.uuid);
									}
								}
								else
								{
									if( isNullPush && obj.results[0].series[loop].values[i][1] == null ) continue;
									
									isNullPush = true;
									
									var index =  serviceMap.get(dateA+"_"+obj.results[0].series[loop].values[i][1]);
									
									if( typeof index === "undefined" )
									{
										g_chart_perform.series[1].addPoint( [dateA, obj.results[0].series[loop].values[i][1]], true, true );
										g_chart_perform.series[0].addPoint( [dateA, null], true, true );
										
										if( obj.results[0].series[loop].values[i][1] != null )
										{
											serviceMap.put( dateA+"_"+obj.results[0].series[loop].values[i][1],obj.results[0].series[loop].tags.service_id+"("+obj.results[0].series[loop].tags.uri+")");
											serviceUUIDMap.put( dateA+"_"+obj.results[0].series[loop].values[i][1],obj.results[0].series[loop].tags.uuid);
										}
									}
									
									if( serviceMap.size() >= 10000 )
									{
										var allTemp = serviceMap.getAll(); 
										
										for(mapData in allTemp){  
								        	var dummy = mapData.split(",");
								        	serviceMap.remove(dummy[0]);
								        	serviceUUIDMap.remove(dummy[0]);
								        	
								        	//g_chart_perform.series[0].data[0].remove(false,false);
								        	//g_chart_perform.series[1].data[0].remove(false,false);
								        	break;
								        }
									}
								}
							}
							// 3초이하
							else
							{
								if( !isAddSeries_normal )
								{
									influxData_warning.push( [dateA, null] );
									influxData_normal.push( [dateA, obj.results[0].series[loop].values[i][1]] );
									
									if( obj.results[0].series[loop].values[i][1] != null )
									{
										serviceMap.put( dateA+"_"+obj.results[0].series[loop].values[i][1],obj.results[0].series[loop].tags.service_id+"("+obj.results[0].series[loop].tags.uri+")");
										serviceUUIDMap.put( dateA+"_"+obj.results[0].series[loop].values[i][1],obj.results[0].series[loop].tags.uuid);
									}
								}
								else
								{
									if( isNullPush && obj.results[0].series[loop].values[i][1] == null ) continue;
									
									isNullPush = true;
									
									var index =  serviceMap.get(dateA+"_"+obj.results[0].series[loop].values[i][1]);
									
									if( typeof index === "undefined" )
									{
										g_chart_perform.series[1].addPoint( [dateA, null], true, true );
										g_chart_perform.series[0].addPoint( [dateA, obj.results[0].series[loop].values[i][1]], true, true );
										
										if( obj.results[0].series[loop].values[i][1] != null )
										{
											serviceMap.put( dateA+"_"+obj.results[0].series[loop].values[i][1],obj.results[0].series[loop].tags.service_id+"("+obj.results[0].series[loop].tags.uri+")");
											serviceUUIDMap.put( dateA+"_"+obj.results[0].series[loop].values[i][1],obj.results[0].series[loop].tags.uuid);
										}
									}
									
									if( serviceMap.size() >= 10000 )
									{
										var allTemp = serviceMap.getAll(); 
										
										for(mapData in allTemp){  
								        	var dummy = mapData.split(",");
								        	serviceMap.remove(dummy[0]);
								        	serviceUUIDMap.remove(dummy[0]);
								        	
								        	//g_chart_perform.series[0].data[0].remove(false,false);
								        	//g_chart_perform.series[1].data[0].remove(false,false);
								        	break;
								        }
									}
								}
							}
						}
						
						//g_chart_perform.redraw();
					}
					
					if( influxData_normal.length > 0 )
					{
						var chartData = {
		                    type: 'scatter',
		                    pointInterval : 10*1000,
		                    color: "#008000",
		                    data: influxData_normal,
		                    marker: { radius:2}
		              	};
						
						g_chart_perform.addSeries(chartData);
						
						isAddSeries_normal = true;
					}
					
					if( influxData_warning.length > 0 )
					{
						var chartData = {
		                    type: 'scatter',
		                    pointInterval : 10*1000,
		                    color: "#FF0000",
		                    data: influxData_warning,
		                    marker: { radius:2}
		              	};
						
						g_chart_perform.addSeries(chartData);
						
						isAddSeries_warning = true;
					}
				}
			}
		},
		error		: function(reqeust, status, error) {
			console.log("code:" + request.status + "\n message:" 
					+ request.responseText + "\n error:" + error);
		},
		complete	: function() {
			idxPerformSuccess_failure = 'N';
			idx_perform = 0;
			isPerformFirst = false;
		}
	})
}



/**
 * Task Failure Chart
 * HighChart
 */
var idxPoolSuccess_failure = 'N';
var idx_failure = 0;

var g_chart_failure;
var g_failure_failed;
var g_failure_lost;
var g_failure_killed;

var t_failure;
var g_failure_time;

var dashboardFailure = function(sereis_arr, colors, last_time){
	g_chart_failure = new Highcharts.Chart({
	    chart: {
	        zoomType: 'x',
	        renderTo: 'm_chart_failure',
	        events: {
	        	load: function(){
	        		
	        		g_failure_failed	= this.series[0];
	        		g_failure_lost		= this.series[1];
	        		g_failure_killed	= this.series[2];
	        		g_failure_time		= last_time;
	        		t_failure			= setInterval("fnAjaxFailure('"+g_failure_time+"')", 100);
	        	}
	        }
	    },
	    title: {
	        text: ''
	    },
	    subtitle: {
	        text: ''
	    },
	    navigation: {
	        buttonOptions: {
	            enabled: false
	        }
	    },
	    xAxis: {
	        type: 'datetime',
        	dateTimelabelFormats: {
        		second: '%Y-%m-%d %H:%M:%S'
        	}
	    },
	    yAxis: { 
	    	title: {
                text: ''
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
	    },
	    tooltip: {
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                Highcharts.numberFormat(this.y, 2);
	        }
	    },
	    legend: {
	        align: 'center'
	    },
	    colors: colors,
	    series: [{
            	type: 'line',
            	name: 'Task failed',
            	data: sereis_arr[0]
        	},
            {
        		type: 'line',
            	name: 'Task lost',
            	data: sereis_arr[1]
            },
            {
            	type: 'line',
            	name: 'Task killed',
            	data: sereis_arr[2]
            }]
	});
}

function fnAjaxFailure(time){
	idx_failure++;
	
	if( idx_failure == 100 && idxPoolSuccess_failure == 'N' ){
		fnFailure(time);
	} else if( (idx_failure % 1000) == 0 ){
		fnFailure(time);
	}
}

function fnFailure (){
	idxPoolSuccess_failure = 'Y';
	$.ajax({
		url			: g_prefix+"/v1/dashboard/failure",
		type		: "get",
		data		: {"time": g_failure_time},
		dataType	: "json",
		success		: function (data){
			if( data.result == "success" )
			{
				var obj = JSON.parse(data.json);

				if( obj.results.length > 0 && typeof obj.results[0].series != "undefined" )
				{
					for( var i = 0; i< obj.results[0].series[0].values.length; i++ )
					{
						g_failure_time	= obj.results[0].series[0].values[i][0];
						var arrTime = g_failure_time.split("Z");
						var dt = new Date(arrTime[0]);
						dt.setHours(dt.getHours() + 9);
						var dateA = dt.getTime();
						
						g_failure_failed.addPoint( [dateA, obj.results[0].series[0].values[i][1]], true, true );
						g_failure_lost.addPoint( [dateA, obj.results[0].series[0].values[i][2]], true, true );
						g_failure_killed .addPoint( [dateA, obj.results[0].series[0].values[i][3]], true, true );
					}
				}
			}
		},
		error		: function(reqeust, status, error) {
			console.log("code:" + request.status + "\n message:" 
					+ request.responseText + "\n error:" + error);
		},
		complete	: function() {
			idxPoolSuccess_failure = 'N';
			idx_failure = 0;
		}
	})
}


/**
 * Failure Rate
 */
var idxPoolSuccess_failureRate= 'N';
var idx_failureRate = 0;

var dashboardFailureRate = function(failed_rate, failed_lost, failed_killed){
	$("#failed_rate").text(failed_rate);
	$("#failed_lost").text(failed_lost);
	$("#failed_killed").text(failed_killed);
}

function fnAjaxFailureRate(){
	idx_failureRate++;
	if (idx_failureRate == 50 && idxPoolSuccess_failureRate == 'N') {
		fnFailureRate();
	} else if ((idx_failureRate % 500) == 0) {
		fnFailureRate();
	}
}

function fnFailureRate(){
	$.ajax({
		url : g_prefix+"/v1/dashboard/failureRate",
		type : "get",
		dataType : "json",
		success : function(data){
			if( data.result == "success" )
			{
				var obj = JSON.parse(data.json);
				
				if( obj.results.length > 0 && typeof obj.results[0].series != "undefined" )
				{
					var cur_index = obj.results[0].series[0].values.length - 1;
					
					var cur_failed	= obj.results[0].series[0].values[ obj.results[0].series[0].values.length - 1 ][ 1 ];
					var cur_lost	= obj.results[0].series[0].values[ obj.results[0].series[0].values.length - 1 ][ 2 ];
					var cur_killed	= obj.results[0].series[ 0].values[obj.results[0].series[0].values.length - 1 ][ 3 ];
					
					var min_failed	= 2147483647;
					var min_lost	= 2147483647;
					var min_killed	= 2147483647;
					
					for( var i = 0; i< obj.results[0].series[0].values.length; i++ )
					{
						min_failed	= Math.min( obj.results[0].series[0].values[i][1], min_failed );
						min_lost	= Math.min( obj.results[0].series[0].values[i][2], min_lost );
						min_killed	= Math.min( obj.results[0].series[0].values[i][3], min_killed );
					}
					var failed_rate		= cur_failed - min_failed;
					var failed_lost		= cur_lost - min_lost;
					var failed_killed	= cur_killed - min_killed;
					
					dashboardFailureRate( failed_rate, failed_lost, failed_killed );
				}
			}
		},
		error : function(request, status, error) { // 오류가 발생했을 때 호출된다. 
			console.log("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		},
		complete : function() { // 정상이든 비정상인든 실행이 완료될 경우 실행될 함수
			idxPoolSuccess_failureRate = 'N';
			idx_failureRate = 0;
		}
	});
};


/**
 * Log watch Chart
 * HighChart
 */
var idxPoolSuccess_log = 'N';
var idx_log = 0;

var g_chart_log;
var g_log_count;

var t_log;
var g_log_time;
var dashboardLog = function(log_count,err_log_count, last_time){
	g_chart_log = new Highcharts.Chart({
	    chart: {
	        zoomType: 'x',
	        renderTo: 'm_chart_log',
        	events: {
	        	load: function(){
	        		
	        		g_log_count		= this.series[0];
	        		g_err_log_count	= this.series[1];
	        		g_log_time		= last_time;
	        		t_log			= setInterval("fnAjaxLog('"+g_log_time+"')", 100);
	        	}
	        }
	    },
	    title: {
	        text: ''
	    },
	    subtitle: {
	        text: ''
	    },
	    navigation: {
	        buttonOptions: {
	            enabled: false
	        }
	    },
	    xAxis: {
	        type: 'datetime',
        	dateTimelabelFormats: {
        		second: '%Y-%m-%d %H:%M:%S'
        	}
	    },
	    yAxis: { 
	    	title: {
                text: ''
            },
            min: 0
	    },
	    tooltip: {
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                Highcharts.numberFormat(this.y, 2);
	        }
	    },
	    legend: {
	        enabled: true
	    },
	    series: [
      		{
      			type : 'line',
                name : 'Total Count',
                color: '#008000',
  				data: log_count
			},
			{
      			type : 'line',
                name : 'Error Count',
                color: '#FF0000',
  				data: err_log_count
			}
		]
	});
}

function fnAjaxLog(){
	idx_log++;
	
	if( idx_log == 50 && idxPoolSuccess_log == 'N' ){
		fnLog();
	} else if( (idx_log % 500) == 0 ){
		fnLog();
	}
}

function fnLog (){
	idxPoolSuccess_log = 'Y';
	$.ajax({
		url			: g_prefix+"/v1/dashboard/log",
		type		: "get",
		data		: {"time": g_log_time},
		dataType	: "json",
		success		: function (data){
			if( data.result == "success" )
			{
				var obj = JSON.parse(data.json);

				if( obj.results.length > 0 && typeof obj.results[0].series != "undefined" )
				{
					for( var i = 0; i< obj.results[0].series[0].values.length; i++ )
					{
						g_log_time	= obj.results[0].series[0].values[i][0];
						var arrTime = g_log_time.split("Z");
						var dt = new Date(arrTime[0]);
						dt.setHours(dt.getHours() + 9);
						var dateA = dt.getTime();
						
						g_log_count.addPoint( [dateA, obj.results[0].series[0].values[i][1]], true, true );
						g_err_log_count.addPoint( [dateA, obj.results[0].series[0].values[i][2]], true, true );
					}
				}
			}
		},
		error		: function(reqeust, status, error) {
			console.log("code:" + request.status + "\n message:" 
					+ request.responseText + "\n error:" + error);
		},
		complete	: function() {
			idxPoolSuccess_log = 'N';
			idx_log = 0;
		}
	})
}


function fnServiceDetail( service_id, executor_id )
{
	document.frm.service_id.value = service_id;
	document.frm.executor_id.value = executor_id;
	document.frm.action = g_prefix+"/v1/service/"+service_id+"/view";
	document.frm.submit();
}

/**
 * Selected Framework / Service 데이터
 */
var dashboardFramework = function(selected_service_arr) {
	var html = "";
	
	for(var i in selected_service_arr){
		html += "<div class='m-widget4__item'><div class='m-widget4__ext'>"
		html += "<span class='m-badge m-badge--" + selected_service_arr[i].color + " m-badge--wide'></span></div>"
		html += "<div class='m-widget4__info'>"
		html += "<a href=\"javascript:fnServiceDetail('"+selected_service_arr[i].name+"','"+selected_service_arr[i].executor_id+"')\" class='btn-link m--font-brand'><strong>" + selected_service_arr[i].name + "</strong></a></div>"
		html += "<div class='m-widget4__ext'><div class='m-table__progress m-table__progress-lg'>"
		html += "<strong class='label'>CPU</strong><div class='m-table__progress-sm progress m-progress--sm'>"
		html += "<div class='m-table__progress-bar progress-bar bg-brand' role='progressbar' aria-valuenow='25' aria-valuemin='0' aria-valuemax='100' style='width:" + selected_service_arr[i].cpu_usage + "%;'></div></div>"
		html += "<span class='m-table__stats'>" + selected_service_arr[i].cpu_usage + "%</span></div>"
		html += "<div class='m-table__progress m-table__progress-lg'><strong class='label'>MEM</strong>"
		html += "<div class='m-table__progress-sm progress m-progress--sm'>"
		html += "<div class='m-table__progress-bar progress-bar bg-warning' role='progressbar' aria-valuenow='25' aria-valuemin='0' aria-valuemax='100' style='width:" + selected_service_arr[i].mem_usage + "%;'></div></div>"
		html += "<span class='m-table__stats'>" + selected_service_arr[i].mem_usage + "%</span></div>"
		html += "<div class='m-table__progress m-table__progress-lg'><strong class='label'>DISK</strong>"
		html += "<div class='m-table__progress-sm progress m-progress--sm'>"
		html += "<div class='m-table__progress-bar progress-bar bg-info' role='progressbar' aria-valuenow='25' aria-valuemin='0' aria-valuemax='100' style='width:" + selected_service_arr[i].disk_usage + "%;'></div></div>"
		html += "<span class='m-table__stats'>" + selected_service_arr[i].disk_usage + "%</span></div></div></div>"
	}
	
	$("#m_widget_framework").html(html);
}


//== Class definition
var Dashboard = function() {

    var calendarInit = function() {
        if ($('#m_calendar').length === 0) {
            return;
        }
        
        var todayDate = moment().startOf('day');
        var YM = todayDate.format('YYYY-MM');
        var YESTERDAY = todayDate.clone().subtract(1, 'day').format('YYYY-MM-DD');
        var TODAY = todayDate.format('YYYY-MM-DD');
        var TOMORROW = todayDate.clone().add(1, 'day').format('YYYY-MM-DD');

        $('#m_calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay,listWeek'
            },
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            navLinks: true,
            defaultDate: moment('2017-09-15'),
            events: [
                {
                    title: 'Meeting',
                    start: moment('2017-08-28'),
                    description: 'Lorem ipsum dolor sit incid idunt ut',
                    className: "m-fc-event--light m-fc-event--solid-warning"
                },
                {
                    title: 'Conference',                    
                    description: 'Lorem ipsum dolor incid idunt ut labore',
                    start: moment('2017-08-29T13:30:00'),
                    end: moment('2017-08-29T17:30:00'),
                    className: "m-fc-event--accent"
                },
                {
                    title: 'Dinner',
                    start: moment('2017-08-30'),
                    description: 'Lorem ipsum dolor sit tempor incid',
                    className: "m-fc-event--light  m-fc-event--solid-danger"
                },
                {
                    title: 'All Day Event',
                    start: moment('2017-09-01'),
                    description: 'Lorem ipsum dolor sit incid idunt ut',
                    className: "m-fc-event--danger m-fc-event--solid-focus"
                },
                {
                    title: 'Reporting',                    
                    description: 'Lorem ipsum dolor incid idunt ut labore',
                    start: moment('2017-09-03T13:30:00'),
                    end: moment('2017-09-04T17:30:00'),
                    className: "m-fc-event--accent"
                },
                {
                    title: 'Company Trip',
                    start: moment('2017-09-05'),
                    end: moment('2017-09-07'),
                    description: 'Lorem ipsum dolor sit tempor incid',
                    className: "m-fc-event--primary"
                },
                {
                    title: 'ICT Expo 2017 - Product Release',
                    start: moment('2017-09-09'),
                    description: 'Lorem ipsum dolor sit tempor inci',
                    className: "m-fc-event--light m-fc-event--solid-primary"
                },
                {
                    title: 'Dinner',
                    start: moment('2017-09-12'),
                    description: 'Lorem ipsum dolor sit amet, conse ctetur'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: moment('2017-09-15T16:00:00'),
                    description: 'Lorem ipsum dolor sit ncididunt ut labore',
                    className: "m-fc-event--danger"
                },
                {
                    id: 1000,
                    title: 'Repeating Event',
                    description: 'Lorem ipsum dolor sit amet, labore',
                    start: moment('2017-09-18T19:00:00'),
                },
                {
                    title: 'Conference',
                    start: moment('2017-09-20T13:00:00'),
                    end: moment('2017-09-21T19:00:00'),
                    description: 'Lorem ipsum dolor eius mod tempor labore',
                    className: "m-fc-event--accent"
                },
                {
                    title: 'Meeting',
                    start: moment('2017-09-11'),
                    description: 'Lorem ipsum dolor eiu idunt ut labore'
                },
                {
                    title: 'Lunch',
                    start: moment('2017-09-18'),
                    className: "m-fc-event--info m-fc-event--solid-accent",
                    description: 'Lorem ipsum dolor sit amet, ut labore'
                },
                {
                    title: 'Meeting',
                    start: moment('2017-09-24'),
                    className: "m-fc-event--warning",
                    description: 'Lorem ipsum conse ctetur adipi scing'
                },
                {
                    title: 'Happy Hour',
                    start: moment('2017-09-24'),
                    className: "m-fc-event--light m-fc-event--solid-focus",
                    description: 'Lorem ipsum dolor sit amet, conse ctetur'
                },
                {
                    title: 'Dinner',
                    start: moment('2017-09-24'),
                    className: "m-fc-event--solid-focus m-fc-event--light",
                    description: 'Lorem ipsum dolor sit ctetur adipi scing'
                },
                {
                    title: 'Birthday Party',
                    start: moment('2017-09-24'),
                    className: "m-fc-event--primary",
                    description: 'Lorem ipsum dolor sit amet, scing'
                },
                {
                    title: 'Company Event',
                    start: moment('2017-09-24'),
                    className: "m-fc-event--danger",
                    description: 'Lorem ipsum dolor sit amet, scing'
                },
                {
                    title: 'Click for Google',
                    url: 'http://google.com/',
                    start: moment('2017-09-26'),
                    className: "m-fc-event--solid-info m-fc-event--light",
                    description: 'Lorem ipsum dolor sit amet, labore'
                }
            ],

            eventRender: function(event, element) {
                if (element.hasClass('fc-day-grid-event')) {
                    element.data('content', event.description);
                    element.data('placement', 'top');
                    mApp.initPopover(element);
                } else if (element.hasClass('fc-time-grid-event')) {
                    element.find('.fc-title').append('<div class="fc-description">' + event.description + '</div>');
                } else if (element.find('.fc-list-item-title').lenght !== 0) {
                    element.find('.fc-list-item-title').append('<div class="fc-description">' + event.description + '</div>');
                }
            }
        });
    }

    return {
        //== Init demos
        init: function() {
            // init charts
            

            // calendar
            calendarInit();
        }
    };
}();

//== Class initialization on page load
jQuery(document).ready(function() {
	
    Dashboard.init();
});