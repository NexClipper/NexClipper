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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NexClipper</title>
<link href="/resources/img/icon/cropped-logo4_정사각형-32x32.png" sizes="32x32" rel="icon" />
<link href="/resources/img/icon/cropped-logo4_정사각형-192x192.png" sizes="192x192" rel="icon" />
<link href="/resources/img/icon/cropped-logo4_정사각형-180x180.png" rel="apple-touch-icon-precomposed" />
<meta name="msapplication-TileImage" content="/resources/img/icon/cropped-logo4_정사각형-270x270.png" />

<link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/resources/css/notosanskr.css" rel="stylesheet" type="text/css">
<link href="/resources/css/fullcalendar/fullcalendar.bundle.css" rel="stylesheet" type="text/css">

<link href="/resources/css/vendors.bundle.css" rel="stylesheet" type="text/css">
<link href="/resources/css/style.bundle.css" rel="stylesheet" type="text/css">
<link href="/resources/css/nexcloud.css" rel="stylesheet" type="text/css">

<script src="/resources/js/lib/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="/resources/js/lib/vendors.bundle.js" type="text/javascript"></script>
<script src="/resources/js/lib/scripts.bundle.js" type="text/javascript"></script>
<script src="/resources/js/lib/jquery.preloaders.js" type="text/javascript"></script>

<script type="text/javascript" src="/resources/js/lib/highchart/highcharts.js"></script>
<script type="text/javascript" src="/resources/js/lib/highchart/highcharts-more.js"></script>
<script type="text/javascript" src="/resources/js/lib/highchart/exporting.js"></script>
<script type="text/javascript" src="/resources/js/lib/highchart/tilemap.js"></script>
<script type="text/javascript" src="/resources/js/lib/highchart/heatmap.js"></script>
<script type="text/javascript" src="/resources/js/lib/highchart/drilldown.js"></script>
<script type="text/javascript" src="/resources/js/lib/highchart/solid-gauge.js"></script>
<script type="text/javascript" src="/resources/js/module/common/utils.js"></script>
<script type="text/javascript" src="/resources/js/module/picker.js"></script>
</head>
<body class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--fixed m-aside-left--skin-light m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default">
<div class="m-grid m-grid--hor m-grid--root m-page">
	<header class="m-grid__item m-header"  data-minimize-offset="200" data-minimize-mobile-offset="200" >
		<tiles:insertAttribute name="header" />
	</header>
	<div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
		<tiles:insertAttribute name="leftSide" />
		<div class="m-grid__item m-grid__item--fluid m-wrapper" id="body">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
</div>
</body>
</html>