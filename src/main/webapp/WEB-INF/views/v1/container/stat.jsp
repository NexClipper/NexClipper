<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="m-portlet">
	<div class="m-portlet__body m-portlet__body--no-padding">
	    <div class="row m-row--no-padding m-row--col-separator-xl">
	        <div class="col-md-12 col-lg-12 col-xl-12">
	            <div class="m-widget14">
	                <div class="m-widget14__header">
	                    <div class="m-widget14__title">
	                        <select class="form-control m-input m-input--square d-inline" id="sel1" style="width:auto;">
	                            <option value="CPU" selected>CPU (%)</option>
	                            <!-- 
	                            <option value="CPU_USED">CPU (Core)</option>
	                            <option value="MEM">MEM (%)</option>
	                            <option value="MEM_USED">MEM (GB)</option>
	                             -->
	                        </select>
	                    </div>
	                </div>
	                <div id="m_flotcharts1" class="flotchart-dummy" style="height: 200px;"></div>
	            </div>
	
	        </div>
	    </div>
	</div>
</div>

<div class="m-portlet">
    <div class="m-portlet__body m-portlet__body--no-padding">
        <div class="row m-row--no-padding m-row--col-separator-xl">
            <div class="col-md-12 col-lg-12 col-xl-12">
                <div class="m-widget14">
                    <div class="m-widget14__header">
                        <div class="m-widget14__title">
                            <select class="form-control m-input m-input--square d-inline" id="sel2" style="width:auto;">
                                <option value="Memory">Memory </option>
                                <!-- 
                                <option value="CPU_USED">CPU (Core)</option>
                                <option value="MEM" selected>MEM (%)</option>
                                <option value="MEM_USED">MEM (GB)</option>
                                 -->
                            </select>
                        </div>
                    </div>
                    <div id="m_flotcharts2" class="flotchart-dummy" style="height: 200px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
				
<div class="m-portlet" id="chart_network">
	<div class="m-portlet__body m-portlet__body--no-padding">
	    <div class="row m-row--no-padding m-row--col-separator-xl">
	        <div class="col-md-12 col-lg-12 col-xl-12">
	            <div class="m-widget14">
	                <div class="m-widget14__header">
	                    <div class="m-widget14__title">
	                        <select class="form-control m-input m-input--square d-inline" id="sel2" style="width:auto;">
	                            <option value="Networks">Networks</option>
	                            <!-- 
	                            <option value="CPU_USED">CPU (Core)</option>
	                            <option value="MEM" selected>MEM (%)</option>
	                            <option value="MEM_USED">MEM (GB)</option>
	                             -->
	                        </select>
	                    </div>
	                </div>
	                <div id="m_flotcharts3" class="flotchart-dummy" style="height: 200px;"></div>
	            </div>
	        </div>
	    </div>
	</div>
</div>
                
<div class="m-portlet" id="chart_error">
    <div class="m-portlet__body m-portlet__body--no-padding">
        <div class="row m-row--no-padding m-row--col-separator-xl">
            <div class="col-md-12 col-lg-12 col-xl-12">
                <div class="m-widget14">
                    <div class="m-widget14__header">
                        <div class="m-widget14__title">
                            <select class="form-control m-input m-input--square d-inline" id="sel2" style="width:auto;">
                                <option value="Networks">Networks</option>
                                <!-- 
                                <option value="CPU_USED">CPU (Core)</option>
                                <option value="MEM" selected>MEM (%)</option>
                                <option value="MEM_USED">MEM (GB)</option>
                                 -->
                            </select>
                        </div>
                    </div>
                    <div id="m_flotcharts4" class="flotchart-dummy" style="height: 200px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="m-portlet" id="chart_error">
    <div class="m-portlet__body m-portlet__body--no-padding">
        <div class="row m-row--no-padding m-row--col-separator-xl">
            <div class="col-md-12 col-lg-12 col-xl-12">
                <div class="m-widget14">
                    <div class="m-widget14__header">
                        <div class="m-widget14__title">
                            <select class="form-control m-input m-input--square d-inline" id="sel2" style="width:auto;">
                                <option value="Block I/O">Block I/O</option>
                                <!-- 
                                <option value="CPU_USED">CPU (Core)</option>
                                <option value="MEM" selected>MEM (%)</option>
                                <option value="MEM_USED">MEM (GB)</option>
                                 -->
                            </select>
                        </div>
                    </div>
                    <div id="m_flotcharts5" class="flotchart-dummy" style="height: 200px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>	
											
<script>

/*
 *############################################################################
 */
var CHART_COUNT = 360;

var m_chart1_last_time;
var m_chart1_series;
var m_chart2_used_series;
var m_chart2_limit_series;
var m_chart3_rx_series;
var m_chart3_tx_series;
var m_chart4_rx_series;
var m_chart4_tx_series;
var m_chart5_read_series;
var m_chart5_write_series;
 
var obj = ${stat};
var cpu_percent = [];
var mem_percent = [];
var mem_used = [];
var mem_limit = [];
var tx_bytes = [];
var tx_packets = [];
var tx_errors = [];
var tx_dropped = [];
var rx_bytes = [];
var rx_packets = [];
var rx_errors = [];
var rx_dropped = [];
var block_io_read = [];
var block_io_write = [];
var timestamp = [];

var diff_hour = -1;

if( obj.useds.length > 0)
{
	var m_chart1_last_time = obj.useds[obj.useds.length-1].timestamp;
	var docker_date = new Date(m_chart1_last_time);
	
	for(var i=0; i<CHART_COUNT-obj.useds.length; i++ )
	{
		var dt = new Date();
	    dt.setSeconds(dt.getSeconds() - (CHART_COUNT-i)*5);
	    
	    if( diff_hour == -1 )
	    {
		    var milisec_diff; 
		    
		    if( docker_date.getTime() > dt.getTime() )
		    {
		    	milisec_diff  = docker_date.getTime() - dt.getTime();
		    }
		    else
		    {
		    	milisec_diff  = dt.getTime() - docker_date.getTime();
		    }
		    
		    var date_diff = new Date( milisec_diff );
		    diff_hour = date_diff.getHours();
	    }
	    
	    dt.setHours(dt.getHours() + parseInt(diff_hour));  
		var dateA = dt.getTime();
		
		cpu_percent.push([dateA, null]);
		mem_used.push([dateA, null]);
		mem_limit.push([dateA, null]);

	 	tx_bytes.push([dateA, null]);
		tx_packets.push([dateA, null]);
		tx_errors.push([dateA, null]);
		tx_dropped.push([dateA, null]);
		rx_bytes.push([dateA, null]);
		rx_packets.push([dateA, null]);
		rx_errors.push([dateA, null]);
		rx_dropped.push([dateA, null]);
		block_io_read.push([dateA, null]);
		block_io_write.push([dateA, null]);
	}
	 
	for( var i=0; i<obj.useds.length; i++ )
	{
		m_chart1_last_time = obj.useds[i].timestamp;	
		var dt = new Date(m_chart1_last_time);
		dt.setHours(dt.getHours() + parseInt(diff_hour));
		var dateA = dt.getTime();

		cpu_percent.push([dateA, obj.useds[i].per_cpu]);
		mem_used.push([dateA, obj.useds[i].used_mem]);
		mem_limit.push([dateA, obj.useds[i].limit_mem]);
		
		tx_bytes.push([dateA, obj.useds[i].tx_bytes]);
		tx_packets.push([dateA, obj.useds[i].tx_packets]);
		tx_errors.push([dateA, obj.useds[i].tx_errors]);
		tx_dropped.push([dateA, obj.useds[i].tx_dropped]);
		rx_bytes.push([dateA, obj.useds[i].rx_bytes]);
		rx_packets.push([dateA, obj.useds[i].rx_packets]);
		rx_errors.push([dateA, obj.useds[i].rx_errors]);
		rx_dropped.push([dateA, obj.useds[i].rx_dropped]);
		block_io_read.push([dateA, obj.useds[i].block_io_read]);
		block_io_write.push([dateA, obj.useds[i].block_io_write]);
	}
}

/*
 *############################################################################
 */ 
 var idx_chart1 = 0;
 var idxPoolSuccess1 = 'N';
 
function fnAjaxChart1(){
	idx_chart1++;
		
	if( idx_chart1 == 50 && idxPoolSuccess1 == 'N' ){
		fnChart1();
	} else if( (idx_chart1 % 500) == 0 ){
		fnChart1();
	}
}

function fnChart1 (){
	idxPoolSuccess1 = 'Y';
	$.ajax({
		url			: "${prefix}/containers/${container_id}/realtime_stat",
		type		: "POST",
		data		: {"time": m_chart1_last_time},
		dataType	: "json",
		success		: function (data){
			//console.log( data.list );
			
			if( data.result == "SUCCESS" )
			{
				if( data.list.length > 0 )
				{
					for( var i = 0; i < data.list.length; i++ )
					{
						m_chart1_last_time = data.list[i].timestamp;		
						
						var dt = new Date(m_chart1_last_time);
						dt.setHours(dt.getHours() + parseInt(diff_hour));
						var dateA = dt.getTime();
						
						m_chart1_series.addPoint( [dateA, data.list[i].per_cpu], true, true );
						m_chart2_used_series.addPoint( [dateA, data.list[i].used_mem], true, true );
						m_chart2_limit_series.addPoint( [dateA, data.list[i].limit_mem], true, true );
						m_chart3_tx_series.addPoint( [dateA, data.list[i].tx_bytes], true, true );
						m_chart3_rx_series.addPoint( [dateA, data.list[i].rx_bytes], true, true );
						m_chart4_tx_series.addPoint( [dateA, data.list[i].tx_errors], true, true );
						m_chart4_rx_series.addPoint( [dateA, data.list[i].rx_errors], true, true );		
						m_chart5_read_series.addPoint( [dateA, data.list[i].block_io_read], true, true );
						m_chart5_write_series.addPoint( [dateA, data.list[i].block_io_write], true, true );
					}
				}
			}
		},
		error		: function(request, status, error) {
			console.log("code:" + request.status + "\n message:" 
					+ request.responseText + "\n error:" + error);
		},
		complete	: function() {
			idxPoolSuccess1 = 'N';
			idx_chart1 = 0;
		}
	})
}
	
/*
 *############################################################################
 */ 
var t_chart1;
Highcharts.chart('m_flotcharts1', {
	chart: {
        zoomType: 'x',
    	events: {
        	load: function(){
        		m_chart1_series	= this.series[0];
        		t_chart1		= setInterval("fnAjaxChart1()", 100);
        	}
        }
    },
    title: {
        text: 'CPU Usage'
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

	yAxis : {
		title : {
			text : 'CPU Usage(%)'
		}
	},

	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle'
	},
	
	colors: ['#00ccff'],
	
	exporting: {
        enabled: false
    },

	series : [ {
		name : 'cpu_percent',
        pointInterval : 60*1000,
		data : cpu_percent
	} ],
	
	responsive : {
		rules : [ {
			condition : {
				maxWidth : 500
			},
			chartOptions : {
				legend : {
					layout : 'horizontal',
					align : 'center',
					verticalAlign : 'bottom'
				}
			}
		} ]
	}
});

Highcharts.chart('m_flotcharts2', {
	chart: {
        zoomType: 'x',
    	events: {
        	load: function(){
        		m_chart2_used_series	= this.series[0];
        		m_chart2_limit_series	= this.series[1];
        	}
        }
    },
    
    title: {
        text: 'Memory Usage'
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

	yAxis : {
		title : {
			text : 'Memory Usage'
		}
	},

	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle'
	},
	
	colors: ['#00ccff', '#ff9900'],
	
	exporting: {
        enabled: false
    },

	series : [ {
		name : 'mem_used',
		type: 'line',
        pointInterval : 60*1000,
		data : mem_used
	}, {
		name : 'mem_limit',
		type: 'line',
        pointInterval : 60*1000,
		data : mem_limit
	}],

	responsive : {
		rules : [ {
			condition : {
				maxWidth : 500
			},
			chartOptions : {
				legend : {
					layout : 'horizontal',
					align : 'center',
					verticalAlign : 'bottom'
				}
			}
		} ]
	}
});

Highcharts.chart('m_flotcharts3', {
	chart: {
        zoomType: 'x',
    	events: {
        	load: function(){
        		m_chart3_tx_series	= this.series[0];
        		m_chart3_rx_series	= this.series[1];
        	}
        }
    },
	title : {
		text : 'Network Throughput'
	},

	xAxis: {
        type: 'datetime',
    	dateTimelabelFormats: {
    		second: '%Y-%m-%d %H:%M:%S'
    	}
    },

	yAxis : {
		title : {
			text : 'Network (bytes)'
		}
	},

	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle'
	},
	
	colors: ['#00ccff', '#ff9900'],
	
	exporting: {
        enabled: false
    },

	series : [ {
		name : 'tx_bytes',
		type: 'line',
        pointInterval : 60*1000,
		data : tx_bytes
	}, {
		name : 'rx_bytes',
		type: 'line',
        pointInterval : 60*1000,
		data : rx_bytes
	} ],

	responsive : {
		rules : [ {
			condition : {
				maxWidth : 500
			},
			chartOptions : {
				legend : {
					layout : 'horizontal',
					align : 'center',
					verticalAlign : 'bottom'
				}
			}
		} ]
	}
});

Highcharts.chart('m_flotcharts4', {
	chart: {
        zoomType: 'x',
    	events: {
        	load: function(){
        		m_chart4_tx_series	= this.series[0];
        		m_chart4_rx_series	= this.series[1];
        	}
        }
    },
	title : {
		text : 'Network Error'
	},

	xAxis: {
        type: 'datetime',
    	dateTimelabelFormats: {
    		second: '%Y-%m-%d %H:%M:%S'
    	}
    },

	yAxis : {
		title : {
			text : 'Network Error'
		}
	},

	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle'
	},
	
	colors: ['#00ccff', '#ff9900'],
	
	exporting: {
        enabled: false
    },

	series : [ {
		name : 'tx_errors',
		type: 'line',
        pointInterval : 60*1000,
		data : tx_errors
	}, {
		name : 'rx_errors',
		type: 'line',
        pointInterval : 60*1000,
		data : rx_errors
	} ],

	responsive : {
		rules : [ {
			condition : {
				maxWidth : 500
			},
			chartOptions : {
				legend : {
					layout : 'horizontal',
					align : 'center',
					verticalAlign : 'bottom'
				}
			}
		} ]
	}
});

Highcharts.chart('m_flotcharts5', {
	chart: {
        zoomType: 'x',
    	events: {
        	load: function(){
        		m_chart5_read_series	= this.series[0];
        		m_chart5_write_series	= this.series[1];
        	}
        }
    },
	title : {
		text : 'Block I/O'
	},

	xAxis: {
        type: 'datetime',
    	dateTimelabelFormats: {
    		second: '%Y-%m-%d %H:%M:%S'
    	}
    },

	yAxis : {
		title : {
			text : 'Block I/O'
		}
	},

	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle'
	},
	
	colors: ['#00ccff', '#ff9900'],
	
	exporting: {
        enabled: false
    },

	series : [ {
		name : 'block_io_read_bytes',
		type: 'line',
        pointInterval : 60*1000,
		data : block_io_read
	}, {
		name : 'block_io_write_bytes',
		type: 'line',
        pointInterval : 60*1000,
		data : block_io_write
	} ],

	responsive : {
		rules : [ {
			condition : {
				maxWidth : 500
			},
			chartOptions : {
				legend : {
					layout : 'horizontal',
					align : 'center',
					verticalAlign : 'bottom'
				}
			}
		} ]
	}
});
</script>