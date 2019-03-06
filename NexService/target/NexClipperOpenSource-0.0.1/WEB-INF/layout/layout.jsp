<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NexClipper</title>
<link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/resources/css/notosanskr.css" rel="stylesheet" type="text/css">
<link href="/resources/css/fullcalendar/fullcalendar.bundle.css" rel="stylesheet" type="text/css">
<link href="/resources/css/amchart/export.css" rel="stylesheet" type="text/css" />

<link href="/resources/css/vendors.bundle.css" rel="stylesheet" type="text/css">
<link href="/resources/css/style.bundle.css" rel="stylesheet" type="text/css">
<link href="/resources/css/nexcloud.css" rel="stylesheet" type="text/css">

<script src="/resources/js/lib/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="/resources/js/lib/vendors.bundle.js" type="text/javascript"></script>
<script src="/resources/js/lib/scripts.bundle.js" type="text/javascript"></script>
<script src="/resources/js/lib/jquery.preloaders.js" type="text/javascript"></script>

<script type="text/javascript" src="/resources/js/lib/highchart/highcharts.js"></script>
<script type="text/javascript" src="/resources/js/lib/highchart/exporting.js"></script>
<script type="text/javascript" src="/resources/js/lib/highchart/tilemap.js"></script>
<script type="text/javascript" src="/resources/js/lib/highchart/heatmap.js"></script>
<script type="text/javascript" src="/resources/js/lib/highchart/drilldown.js"></script>
</head>
<body class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--fixed m-aside-left--skin-light m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default">
<div class="m-grid m-grid--hor m-grid--root m-page">
	<header class="m-grid__item m-header"  data-minimize-offset="200" data-minimize-mobile-offset="200" >
		<tiles:insertAttribute name="header" />
	</header>
	<div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
		<button class="m-aside-left-close  m-aside-left-close--skin-light " id="m_aside_left_close_btn">
			<i class="la la-close"></i>
		</button>
		<tiles:insertAttribute name="leftSide" />
		<div class="m-grid__item m-grid__item--fluid m-wrapper" id="body">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
</div>
</body>
</html>