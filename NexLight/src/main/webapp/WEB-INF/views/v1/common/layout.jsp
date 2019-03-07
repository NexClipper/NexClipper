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
	<meta name="description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>NexClipper</title>
	<link rel="shortcut icon" href="${prefix}/resources/v1/assets/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${prefix}/resources/v1/assets/css/notosanskr.css">
	<link rel="stylesheet" type="text/css" href="${prefix}/resources/v1/assets/vendors/fullcalendar/fullcalendar.bundle.css">
	<link rel="stylesheet" type="text/css" href="${prefix}/resources/v1/assets/vendors/amchart/export.css"/>
	<link rel="stylesheet" type="text/css" href="${prefix}/resources/v1/assets/css/vendors.bundle.css">
	<link rel="stylesheet" type="text/css" href="${prefix}/resources/v1/assets/css/style.bundle.css">
	<link rel="stylesheet" type="text/css" href="${prefix}/resources/v1/assets/css/nexcloud.css">
	<script type="text/javascript" src="${prefix}/resources/js/webfont.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/lib/google/loader.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/lib/highchart/highcharts.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/lib/highchart/exporting.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/lib/highchart/tilemap.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/lib/highchart/heatmap.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/lib/highchart/drilldown.js"></script>
	<script type="text/javascript" src="${prefix}/resources/js/lib/chartjs/Chart.bundle.min.js"></script>
	<script type="text/javascript" src="${prefix}/resources/v1/assets/js/vendors.bundle.js"></script>
	<script type="text/javascript" src="${prefix}/resources/v1/assets/js/scripts.bundle.js"></script>
	<script type="text/javascript" src="${prefix}/resources/v1/assets/vendors/amchart/amcharts.js"></script>
	<script type="text/javascript" src="${prefix}/resources/v1/assets/vendors/amchart/serial.js"></script>
	<script type="text/javascript" src="${prefix}/resources/v1/assets/vendors/amchart/radar.js"></script>
	<script type="text/javascript" src="${prefix}/resources/v1/assets/vendors/amchart/pie.js"></script>
	<script type="text/javascript" src="${prefix}/resources/v1/assets/vendors/amchart/polarScatter.min.js"></script>
	<script type="text/javascript" src="${prefix}/resources/v1/assets/vendors/amchart/animate.min.js"></script>
	<script type="text/javascript" src="${prefix}/resources/v1/assets/vendors/amchart/export.min.js"></script>
	<script type="text/javascript" src="${prefix}/resources/v1/assets/vendors/amchart/light.js"></script>
	<script type="text/javascript" src="${prefix}/resources/v1/assets/vendors/flot/flot.bundle.js"></script>
	<script type="text/javascript">
		WebFont.load({
			google: {"families":["Poppins:300,400,500,600,700","Roboto:300,400,500,600,700","Lato:300,400,500,600,700"]},
			active: function() {
				sessionStorage.fonts = true;
			}
		});
		var fnTaskResource = function(node_ip) {
    		var taskJson = "";
    		$.ajax({
    			url : "${prefix}/v1/agent/" + node_ip + "/tasks",
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
	</script>
</head>
<body class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--fixed m-aside-left--skin-light m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default">
	<input type="hidden" id="time" name="time" value="1h:5s"/>
	<div class="m-grid m-grid--hor m-grid--root m-page">
		<div>
			<div class="m-grid__item m-grid__item--fluid m-wrapper">
				<jsp:include page="/WEB-INF/views/${body}.jsp"/>
			</div>
		</div>
	</div>
</body>
</html>
