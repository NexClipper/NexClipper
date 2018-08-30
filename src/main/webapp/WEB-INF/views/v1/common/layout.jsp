<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="org.springframework.web.util.UrlPathHelper" %>

<%
	UrlPathHelper urlPathHelper = new UrlPathHelper(); 
	String uri = urlPathHelper.getOriginatingRequestUri(request);
%>

<!DOCTYPE html>
<html lang="ko">
<head>

    <meta charset="utf-8">
    <title>NexClipper</title>
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" href="${prefix}/resources/v1/assets/favicon.ico">

    <!--begin::Web font -->
    <script src="${prefix}/resources/js/webfont.js"></script>
    <script>
    	var g_prefix			= "${prefix}";
    	
    	// Javascript Map사용
    	Map = function(){
    	  this.map = new Object();
    	};   
    	Map.prototype = {   
    	    put : function(key, value){   
    	    this.map[key] = value;
    	  },   
    	  get : function(key){   
    	    return this.map[key];
    	  },
    	  getAll : function(){   
    	      return this.map; 
    	  },  
    	  containsKey : function(key){    
    	    return key in this.map;
    	  },
    	  containsValue : function(value){    
    	    for(var prop in this.map){
    	      if(this.map[prop] == value) return true;
    	    }
    	    return false;
    	  },
    	  isEmpty : function(key){    
    	    return (this.size() == 0);
    	  },
    	  clear : function(){   
    	    for(var prop in this.map){
    	      delete this.map[prop];
    	    }
    	  },
    	  remove : function(key){    
    	    delete this.map[key];
    	  },
    	  keys : function(){   
    	    var keys = new Array();   
    	    for(var prop in this.map){   
    	      keys.push(prop);
    	    }   
    	    return keys;
    	  },
    	  values : function(){   
    	    var values = new Array();   
    	      for(var prop in this.map){   
    	        values.push(this.map[prop]);
    	      }   
    	      return values;
    	    },
    	  size : function(){
    	    var count = 0;
    	    for (var prop in this.map) {
    	      count++;
    	    }
    	    return count;
    	  },
    	  getValueKey : function(value){    
    	    for(var prop in this.map){
    	      if(this.map[prop] == value) return prop;
    	    }
    	    return "";
    	  },
    	};
    	
    	/**
    	 * Node별 task 목록 return
    	 */
    	var fnTaskResource = function(node_ip) {
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
    
        WebFont.load({
            google: {"families":["Poppins:300,400,500,600,700","Roboto:300,400,500,600,700","Lato:300,400,500,600,700"]},
            active: function() {
                sessionStorage.fonts = true;
            }
        });
    </script>
    <link rel="stylesheet" href="${prefix}/resources/v1/assets/css/notosanskr.css">

    <!--begin::Page Vendors -->
    <link href="${prefix}/resources/v1/assets/vendors/fullcalendar/fullcalendar.bundle.css" rel="stylesheet" type="text/css">
    <link href="${prefix}/resources/v1/assets/vendors/amchart/export.css" rel="stylesheet" type="text/css" />
    <!--end::Page Vendors -->

    <!--base styles-->
    <link href="${prefix}/resources/v1/assets/css/vendors.bundle.css" rel="stylesheet" type="text/css">
    <link href="${prefix}/resources/v1/assets/css/style.bundle.css" rel="stylesheet" type="text/css">
    <link href="${prefix}/resources/v1/assets/css/nexcloud.css" rel="stylesheet" type="text/css">
	
	<!-- begin::jQuery -->
	<script type="text/javascript" src="${prefix}/resources/js/jquery-1.12.4.min.js"></script>
	<!-- end::jQuery -->
	
	<!-- begin::google chart -->
	<script type="text/javascript" src="${prefix}/resources/js/lib/google/loader.js"></script>
	<!-- ed::google chart -->
	
	<!-- begin::highcharts.js -->
	<script type="text/javascript" src="${prefix}/resources/js/lib/highchart/highcharts.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/lib/highchart/exporting.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/lib/highchart/tilemap.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/lib/highchart/heatmap.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/lib/highchart/drilldown.js"></script>
	<!-- end::highcharts.js -->
	
	<!-- begin::chart.js Plugin -->
	<script src="${prefix}/resources/js/lib/chartjs/Chart.bundle.min.js"></script>
	<!-- end::chart.js Plugin -->
	
	<!--begin::Base Scripts -->
	<script src="${prefix}/resources/v1/assets/js/vendors.bundle.js" type="text/javascript"></script>
	<script src="${prefix}/resources/v1/assets/js/scripts.bundle.js" type="text/javascript"></script>
	<!--end::Base Scripts -->
	
	<!--begin::Page Vendors -->
	<script src="${prefix}/resources/v1/assets/vendors/amchart/amcharts.js" type="text/javascript"></script>
	<script src="${prefix}/resources/v1/assets/vendors/amchart/serial.js" type="text/javascript"></script>
	<script src="${prefix}/resources/v1/assets/vendors/amchart/radar.js" type="text/javascript"></script>
	<script src="${prefix}/resources/v1/assets/vendors/amchart/pie.js" type="text/javascript"></script>
	<script src="${prefix}/resources/v1/assets/vendors/amchart/polarScatter.min.js" type="text/javascript"></script>
	<script src="${prefix}/resources/v1/assets/vendors/amchart/animate.min.js" type="text/javascript"></script>
	<script src="${prefix}/resources/v1/assets/vendors/amchart/export.min.js" type="text/javascript"></script>
	<script src="${prefix}/resources/v1/assets/vendors/amchart/light.js" type="text/javascript"></script>
	<!--end::Page Vendors -->
	<!--begin::Page Vendors -->
	<script src="${prefix}/resources/v1/assets/vendors/flot/flot.bundle.js" type="text/javascript"></script>
	<!--end::Page Vendors -->

</head>

<body class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--fixed m-aside-left--skin-light m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default">
<input type="hidden" id="time" name="time" value="1h:5s"/>

<!-- begin:: Page -->
<div class="m-grid m-grid--hor m-grid--root m-page" style="padding: 35px 35px 0 35px;">
    <!-- begin::Body -->
    <div>
        <!-- END: Left Aside -->
        <div class="m-grid__item m-grid__item--fluid m-wrapper">
			<jsp:include page="/WEB-INF/views/${body}.jsp"/>
        </div>
    </div>
    <!-- end:: Body -->

</div>
<!-- end:: Page -->

</body>
</html>
