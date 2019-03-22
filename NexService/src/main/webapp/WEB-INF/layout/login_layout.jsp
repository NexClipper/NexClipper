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
<link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/resources/css/notosanskr.css" rel="stylesheet" type="text/css">
<link href="/resources/css/vendors.bundle.css" rel="stylesheet" type="text/css">
<link href="/resources/css/style.bundle.css" rel="stylesheet" type="text/css">
<link href="/resources/css/nexcloud.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="m-grid m-grid--hor m-grid--root m-page">
	<div class="m-login m-login--signin  m-login--5" id="m_login" style="background-image: url(/resources/img/bg/bg-3.jpg);">
		<div class="m-login__wrapper-1 m-portlet-full-height">
			<div class="m-login__wrapper-1-1">
				<div class="m-login__contanier">
					<div class="m-login__content">
						<div class="m-login__logo">
							<img src="/resources/img/logo10.png" widht="250px" height="200px">
						</div>
						<div class="m-login__title">
							<h3> Get a free account and try beta service for Container monitoring and Performance Management </h3>
						</div>
						<div class="m-login__form-action">
							<a href="/user/join" class="btn btn-outline-focus m-btn--pill" > Get Free Account </a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="m-login__wrapper-2 m-portlet-full-height">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<footer class="m-grid__item		m-footer " style="bottom: 0; position: fixed; margin-left: 0; width: 100%;">
		<div class="m-container m-container--fluid m-container--full-height m-page__container">
			<div class="m-stack m-stack--flex-tablet-and-mobile m-stack--ver m-stack--desktop">
				<div class="m-stack__item m-stack__item--left m-stack__item--middle m-stack__item--last">
					<span class="m-footer__copyright">© 2018 <a href="http://www.nexclipper.com/" class="m-link" target="_BLANK">NexClipper</a></span>
				</div>
				<div class="m-stack__item m-stack__item--right m-stack__item--middle m-stack__item--first">
					<ul class="m-footer__nav m-nav m-nav--inline m--pull-right">
						<li class="m-nav__item">
							<a href="#" class="m-nav__link"><span class="m-nav__link-text">Term Of Use</span></a>
						</li>
						<li class="m-nav__item">
							<a href="#" class="m-nav__link"><span class="m-nav__link-text">Privacy</span></a>
						</li>
						<li class="m-nav__item m-nav__item">
							<a href="mailto:support@nexclipper.com" class="m-nav__link"><span class="m-nav__link-text">Help</span></a>
						</li>
					</ul>
				</div>	
			</div>
		</div>
	</footer>
</div>
</body>
</html>