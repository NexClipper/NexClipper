<%@page import="com.nexcloud.util.Const"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="org.springframework.web.util.UrlPathHelper" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>NexClipper</title>
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- <link rel="shortcut icon" href="${prefix}/resources/v1/assets/favicon.ico"> -->
    
    
    <link rel="icon" href="${prefix}/resources/v1/assets/img/icon/cropped-logo4_정사각형-32x32.png" sizes="32x32" />
	<link rel="icon" href="${prefix}/resources/v1/assets/img/icon/cropped-logo4_정사각형-192x192.png" sizes="192x192" />
	<link rel="apple-touch-icon-precomposed" href="${prefix}/resources/v1/assets/img/icon/cropped-logo4_정사각형-180x180.png" />
	<meta name="msapplication-TileImage" content="${prefix}/resources/v1/assets/img/icon/cropped-logo4_정사각형-270x270.png" />

    <!--begin::Web font -->
    <script src="${prefix}/resources/js/webfont.js"></script>
    <script>
    	var g_prefix = "${prefix}";
        WebFont.load({
            google: {"families":["Poppins:300,400,500,600,700","Roboto:300,400,500,600,700","Lato:300,400,500,600,700"]},
            active: function() {
                sessionStorage.fonts = true;
            }
        });
    </script>
    <link rel="stylesheet" href="${prefix}/resources/v1/assets/css/notosanskr.css">

    <!--vendors-->
    <link href="${prefix}/resources/v1/assets/vendors/fullcalendar/fullcalendar.bundle.css" rel="stylesheet" type="text/css">

    <!--base styles-->
    <link href="${prefix}/resources/v1/assets/css/vendors.bundle.css" rel="stylesheet" type="text/css">
    <link href="${prefix}/resources/v1/assets/css/style.bundle.css" rel="stylesheet" type="text/css">
    <link href="${prefix}/resources/v1/assets/css/nexcloud.css" rel="stylesheet" type="text/css">
    
    <!-- begin::jQuery -->
	<script type="text/javascript" src="${prefix}/resources/js/jquery-1.12.4.min.js"></script>
	<!-- end::jQuery -->

	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-122075277-2"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag(){dataLayer.push(arguments);}
		gtag('js', new Date());
		gtag('config', 'UA-122075277-2');
		
		var diff_hour = -999;
		
		var diff_id;
		var load_script_id;
		
		$(document).ready(function () {
			//diff_id 		= window.setTimeout(fnSetTimezone, 10);
			$('#agentButtons a').on('click', function(e){
		        e.preventDefault();
		        $(this).attr('class', 'btn btn-primary').siblings().attr('class', 'btn btn-default');
		        
		        var select_val = $(this).attr('id');
		        
		        if(select_val == "k8s_btn") {
		        	$("#docker_area").hide();
		        	$("#dcos_area").hide();
		        	$("#k8s_area").show();
		        } else if(select_val == "docker_btn") {
		        	$("#k8s_area").hide();
		        	$("#dcos_area").hide();    	
		        	$("#docker_area").show();
				} else if(select_val == "dcos_btn") {
					$("#docker_area").hide();
			    	$("#k8s_area").hide();
			    	$("#dcos_area").show();
				}
		    });
		});
		
		function setTimeZone( diff )
		{
			diff_hour = diff;
		}
		
		function fnSetTimezone()
		{
			window.clearTimeout(diff_id);
			
			$.ajax({
				url			: "https://api.ipify.org",
				type		: "get",
				data		: {},
				dataType	: "html",
				timeout		: 1000,
				success		: function (data){
					$.getJSON('https://ipapi.co/'+data+'/json', function(data) {
						var timezone	= data.utc_offset.substring(0, data.utc_offset.length-2);
						diff_hour 		= parseInt(timezone);
					});
				},
				error		: function(request, status, error) {
					console.log(error);
					//fnSetTimezone();
				},
				complete	: function() {
				}
			});
			/*
			$.getJSON('https://api.ipify.org', function(data) {
				console.log("data::"+data);
				var data 		= JSON.stringify(data, null, 2);
				data 			= JSON.parse(data);
				console.log("data::"+data);
				
				$.getJSON('https://ipapi.co/'+data+'/json', function(data) {
					var data 		= JSON.stringify(data, null, 2);
					data 			= JSON.parse(data);
					data			= data.utc_offset;
				});
			});
			*/
		}
		
		
		function fnSetTimezone1()
		{
			window.clearTimeout(diff_id);
			$.getJSON('http://gd.geobytes.com/GetCityDetails?callback=?', function(data) {
				var data 		= JSON.stringify(data, null, 2);
				data 			= JSON.parse(data);
				var strArray 	= (data.geobytestimezone).split(":");
				var timezone	= strArray[0];
				diff_hour 		= parseInt(timezone);
			});
		}
	</script>

</head>

<!-- end::Body -->
<body class="m--skin- m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default"  >
<!-- begin:: Page -->
<div class="m-grid m-grid--hor m-grid--root m-page">
    <div class="m-login m-login--signin  m-login--5" id="m_login" style="background-image: url(${prefix}/resources/v1/assets/img/bg/bg-3.jpg);">
        <div class="m-login__wrapper-1 m-portlet-full-height">
            <div class="m-login__wrapper-1-1">
                <div class="m-login__contanier">
                    <div class="m-login__content">
                        <div class="m-login__logo">
                            <a href="#">
                                <%-- <img src="${prefix}/resources/v1/assets/img/logo-nexcloud.png"> --%>
                                <img src="${prefix}/resources/v1/assets/img/logo10.png" widht="250px" height="200px">
                            </a>
                        </div>
                        <div class="m-login__title">
                            <h3>
                                Get a free account and try beta service for Container monitoring and Performance Management
                            </h3>
                        </div>
                        <!-- <div class="m-login__desc">
                            Amazing container monitoring and performance management solution specialized in Docker, Apache Mesos, Marathon, DC/OS, Mesosphere.
                        </div> -->
                        <div class="m-login__form-action">
                            <button type="button" id="m_login_signup" class="btn btn-outline-focus m-btn--pill">
                                Get Free Account
                            </button>
                        </div>
                        <div class="m-login__form-action">
                            <button type="button" id="m_login_demo" class="btn btn-outline-focus m-btn--pill">
                                Demo Account
                            </button>
                        </div>
                    </div>
                </div>
                <div class="m-login__border">
                    <div></div>
                </div>
            </div>
        </div>
        <div class="m-login__wrapper-2 m-portlet-full-height">
        	<div class="m-login__contanier">
                <div class="m-login__signin" style="margin-bottom: 130px;">
                    <div class="m-login__head">
                        <h3 class="m-login__title">
                            Sign Up
                        </h3>
                        <div class="m-login__desc">
                            Enter your details to create your account:
                        </div>
                    </div>
                    <form class="m-login__form m-form" action="">
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="Email" name="user_id" id="user_id" autocomplete="off">
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="Company Name" name="company_name" id="company_name" autocomplete="off">
                            <!-- <button id="m_account_check" style="width:100%" class="btn btn-outline-focus m-btn m-btn--square m-btn--custom">Check Company</button> -->
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="password" placeholder="Password" name="user_passwd" id="user_passwd">
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input m-login__form-input--last" type="password" placeholder="Confirm Password" name="rpassword" id="rpassword">
                        </div>
                        <div class="m-login__form-sub">
                            <label class="m-checkbox m-checkbox--focus">
                                <input type="checkbox" name="agree" id="agree">
                                I Agree the
                                <a href="#" class="m-link m-link--focus" onClick="displayTermsAndCondition()">
                                    term of use
                                </a>
                                .
                                <span></span>
                            </label>
                            <span class="m-form__help"></span>
                        </div>
                        <div class="m-login__form-action">
                            <button id="m_login_signup_submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air">
                                Sign Up
                            </button>
                            <button id="m_login_signup_cancel" class="btn btn-outline-focus m-btn m-btn--pill m-btn--custom">
                                Sign In
                            </button>
                        </div>
                    </form>
                </div>
                <div class="m-login__signup" style="margin-bottom: 130px;">
                	<div class="m-login__head">
                        <h3 class="m-login__title">
                            Login To Your Account
                        </h3>
                    </div>
                    <form class="m-login__form m-form" action="">
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="Username" name="username" id="username" autocomplete="off">
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input m-login__form-input--last" type="Password" placeholder="Password" name="password" id="password">
                        </div>
                        <div class="row m-login__form-sub">
                            <div class="col m--align-left">
                                <label class="m-checkbox m-checkbox--focus">
                                    <input type="checkbox" name="remember">
                                    Remember me
                                    <span></span>
                                </label>
                            </div>
                            <div class="col m--align-right">
                                <a href="javascript:;" id="m_login_forget_password" class="m-link">
                                    Forget Password ?
                                </a>
                            </div>
                        </div>
                        <div class="m-login__form-action">
                            <button id="m_login_signin_submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air">
                                Sign In
                            </button>
                        </div>
                    </form>
                    
                </div>
                <div class="m-login__forget-password">
                    <div class="m-login__head">
                        <h3 class="m-login__title">
                            Forgotten Password ?
                        </h3>
                        <div class="m-login__desc">
                            Enter your email to reset your password:
                        </div>
                    </div>
                    <form class="m-login__form m-form" action="">
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="Email" name="email" id="m_email" autocomplete="off">
                        </div>
                        <div class="m-login__form-action">
                            <button id="m_login_forget_password_submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air">
                                Request
                            </button>
                            <button id="m_login_forget_password_cancel" class="btn btn-outline-focus m-btn m-btn--pill m-btn--custom ">
                                Cancel
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- <div class="m-login__contanier">
                <div class="m-login__signin" style="margin-bottom: 130px;">
                    <div class="m-login__head">
                        <h3 class="m-login__title">
                            Login To Your Account
                        </h3>
                    </div>
                    <form class="m-login__form m-form" action="">
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="Username" name="username" id="username" autocomplete="off">
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input m-login__form-input--last" type="Password" placeholder="Password" name="password" id="password">
                        </div>
                        <div class="row m-login__form-sub">
                            <div class="col m--align-left">
                                <label class="m-checkbox m-checkbox--focus">
                                    <input type="checkbox" name="remember">
                                    Remember me
                                    <span></span>
                                </label>
                            </div>
                            <div class="col m--align-right">
                                <a href="javascript:;" id="m_login_forget_password" class="m-link">
                                    Forget Password ?
                                </a>
                            </div>
                        </div>
                        <div class="m-login__form-action">
                            <button id="m_login_signin_submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air">
                                Sign In
                            </button>
                        </div>
                    </form>
                </div>
                <div class="m-login__signup" style="margin-bottom: 130px;">
                    <div class="m-login__head">
                        <h3 class="m-login__title">
                            Sign Up
                        </h3>
                        <div class="m-login__desc">
                            Enter your details to create your account:
                        </div>
                    </div>
                    <form class="m-login__form m-form" action="">
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="Email" name="user_id" id="user_id" autocomplete="off">
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="Company Name" name="company_name" id="company_name" autocomplete="off">
                            <button id="m_account_check" style="width:100%" class="btn btn-outline-focus m-btn m-btn--square m-btn--custom">Check Company</button>
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="password" placeholder="Password" name="user_passwd" id="user_passwd">
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input m-login__form-input--last" type="password" placeholder="Confirm Password" name="rpassword">
                        </div>
                        <div class="m-login__form-sub">
                            <label class="m-checkbox m-checkbox--focus">
                                <input type="checkbox" name="agree" id="agree">
                                I Agree the
                                <a href="#" class="m-link m-link--focus" onClick="displayTermsAndCondition()">
                                    term of use
                                </a>
                                .
                                <span></span>
                            </label>
                            <span class="m-form__help"></span>
                        </div>
                        <div class="m-login__form-action">
                            <button id="m_login_signup_submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air">
                                Sign Up
                            </button>
                            <button id="m_login_signup_cancel" class="btn btn-outline-focus m-btn m-btn--pill m-btn--custom">
                                Sign In
                            </button>
                        </div>
                    </form>
                </div>
                <div class="m-login__forget-password">
                    <div class="m-login__head">
                        <h3 class="m-login__title">
                            Forgotten Password ?
                        </h3>
                        <div class="m-login__desc">
                            Enter your email to reset your password:
                        </div>
                    </div>
                    <form class="m-login__form m-form" action="">
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="Email" name="email" id="m_email" autocomplete="off">
                        </div>
                        <div class="m-login__form-action">
                            <button id="m_login_forget_password_submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air">
                                Request
                            </button>
                            <button id="m_login_forget_password_cancel" class="btn btn-outline-focus m-btn m-btn--pill m-btn--custom ">
                                Cancel
                            </button>
                        </div>
                    </form>
                </div>
            </div> -->
        </div>
    </div>
	<!-- begin::Footer -->
	<footer class="m-grid__item		m-footer " style="bottom: 0; position: fixed; margin-left: 0; width: 100%;">
		<div class="m-container m-container--fluid m-container--full-height m-page__container">
			<div class="m-stack m-stack--flex-tablet-and-mobile m-stack--ver m-stack--desktop">
				<div class="m-stack__item m-stack__item--left m-stack__item--middle m-stack__item--last">
					<span class="m-footer__copyright">
						© 2018 <a href="http://www.nexclipper.com/" class="m-link" target="_BLANK">NexClipper</a>
					</span>
				</div>
				<div class="m-stack__item m-stack__item--right m-stack__item--middle m-stack__item--first">
					<ul class="m-footer__nav m-nav m-nav--inline m--pull-right">
						<!-- <li class="m-nav__item">
							<a href="#" class="m-nav__link">
								<span class="m-nav__link-text">About</span>
							</a>
						</li> -->
						<li class="m-nav__item">
							<a href="#" class="m-nav__link" onClick="displayTermsAndCondition()">
								<span class="m-nav__link-text">Term Of Use</span>
							</a>
						</li>
						<li class="m-nav__item">
							<a href="#" class="m-nav__link" onClick="displayPrivacy();">
								<span class="m-nav__link-text">Privacy</span>
							</a>
						</li>
						<!-- <li class="m-nav__item">
							<a href="#" class="m-nav__link">
								<span class="m-nav__link-text">Purchase</span>
							</a>
						</li> -->
						<li class="m-nav__item m-nav__item">
							<!-- <a href="mailto:support@nexcloud.co.kr" class="m-nav__link" data-toggle="m-tooltip" title="" data-placement="left" data-original-title="Support Center"> -->
							<a href="mailto:support@nexclipper.com" class="m-nav__link">
								<!-- <i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i> -->
								<span class="m-nav__link-text">Help</span>
							</a>
						</li>
					</ul>
				</div>	
			</div>
		</div>
	</footer>
	<!-- end::Footer -->
</div>
<!-- end:: Page -->

<!--begin::Base Scripts -->
<script src="${prefix}/resources/v1/assets/js/vendors.bundle.js" type="text/javascript"></script>
<script src="${prefix}/resources/v1/assets/js/scripts.bundle.js" type="text/javascript"></script>
<!--end::Base Scripts -->

<script src="${prefix}/resources/v1/assets/js/jquery.preloaders.js"></script>

<!--begin::Page Snippets -->
<script src="${prefix}/resources/v1/assets/js/login.js" type="text/javascript"></script>
<!--end::Page Snippets -->



<!--begin::[Modal]Agent Script -->
<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document" style="max-width:800px">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Welcome to NexClipper</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            
            <div class="modal-body">
            	<div class="m-widget27__item">
					<h5 class="modal-title" id="">Congratulations on joining our beta service.</h5>
				</div>	
				
				<div class="m-widget27__item">
					<dl>
						<dt>Don't forget install agent first</dt>
						<dd style="padding-bottom: 1rem;">NexClipper agents are delivered as a Docker container and can be deployed on any hosts with Docker or Orchestrator such as DC/OS, Mesosphere or Kubernetes. Run the docker on any hosts to be monitored, providing the agent key as below</dd>
					</dl>
				</div>			
				
				<div class="btn-group m--margin-top-20" id="agentButtons">
					<a href="#" class="btn btn-primary" id="docker_btn">Docker</a>
					<a href="#" class="btn btn-default" id="k8s_btn">Kubernetes</a>
                    <a href="#" class="btn btn-default" id="dcos_btn">DC/OS</a>
                </div>
                
                <div class="m-widget27__item" id="k8s_area">
                	<dl>
						<h5 class="modal-title">This script set to SSL. If you are not using SSL, you must change following environment variables.</h5>
						<dd>k8s_apiserver_protocol: http</dd>
						<dd>k8s_apiserver_port: '8080'</dd>
					</dl>
					<dl>
						<h5 class="modal-title">Agent Kuabernetes Installation</h5>
						<dd><label id="k8s_script"></label></dd>
					</dl>
				</div>
                
                <div class="m-widget27__item" id="docker_area">
                	<dl>
						<h5 class="modal-title">This script set to SSL. If you are not using SSL, you must change following environment variables.</h5>
						<dd>k8s_apiserver_protocol=http</dd>
						<dd>k8s_apiserver_port=8080</dd>
					</dl>
					<dl>
						<h5 class="modal-title">Agent Docker Installation</h5>
						<dd><label id="docker_cmd"></label></dd>
					</dl>
				</div>
				
				<div class="m-widget27__item" id="dcos_area">
					<dl>
						<h5 class="modal-title">Agent DC/OS Installation</h5>
						<dd><label id="dcos_json" style="max-width:730px"></label></dd>
					</dl>
				</div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--end::[Modal]Agent Script -->


<!--begin::[Modal]Terms and conditions -->
<div class="modal fade" id="detailModal_terms" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document" style="max-width:50%;">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Term Of Use.</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            
            <!-- 1. General Rule -->
            <div class="modal-body">
            	<h5 class="modal-title">General Rule</h5>
	            <div class="m-widget27__item">
					<dl>
						
						<dt><label>1. Purpose</label></dt>
						<dd>
							<label>
								This site and service is owned and provided by NexCloud (“NexCloud”).
								The purpose of these terms is to classify the general details regarding procedures and conditions that govern your access to and use of the service.
								Please read these Terms of Use (“Terms”) carefully before using the Site. By accessing or using the Site or any of the content on the Site you agree to be legally bound by these Terms.
								If you do not accept these Terms, do not use the Site or any of its Content.<br/><br/>
								Please note that these terms written in English are just for reference ONLY, for any specifics, refer the ones in Korean available at <a href="https://www.nexclipper.com" target="_blank">https://www.nexclipper.com</a><br/>
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>2.Effectiveness and Revision of Terms</label></dt>
						<dd>
							<label>
								2.1. These terms are enacted by Telecommunication Business Act (Ministry of Government Legislation) and become effective by making a disclosure.
								The disclosure of these terms are available on NexCloud’s official website. (<a href="https://www.nexclipper.com" target="_blank">https://www.nexclipper.com</a>)
							</label>
						</dd>
						<dd>
							<label>
								2.2. This agreement shall be effective from the time you apply for NexCloud’s service to the time when the payment is fully paid after the service is completed.
							</label>
						</dd>
						<dd>
							<label>
								2.3. NexCloud may modify these terms if there’s justifiable reasons, and if any changes are made by NexCloud, NexCloud is responsible for making a disclosure.
								Except any changes in important terms such as your rights or responsibilities regarding subscription fees, it shall be effective after seven (7) days once a disclosure is made.
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>3. Applications of Terms</label></dt>
						<dd>
							<label>
								Any matters not specified in these terms shall be considered and applied in accordance with applicable laws, the intent of these terms, and practices of the same or similar industry.
							</label>
						</dd>
					</dl>
				</div>
            </div>
            
            
            <!-- 2. The Conclusion of Contracts -->
            <div class="modal-body">
            	<h5 class="modal-title">The Conclusion of Contracts</h5>
	            <div class="m-widget27__item">
					<dl>
						<dt><label>1. Establishment of Contracts</label></dt>
						<dd>
							<label>
								The service contract shall be established by NexCloud’s approvals to your application for service including your consent to NexCloud’s terms.
							</label>
						</dd>
						<dd>
							<label>
								1) The period of a contract may vary based on the selected period you want to use from the commencement date of service selected among NexCloud’s service displayed on the website.
							</label>
						</dd>
						<dd>
							<label>
								2) The service commences the day you actually start using the service. Subscription based service may be effective on the first day you switch free service to subscription-based service.
							</label>
						</dd>
						<dd>
							<label>
								3) You may apply for service on NexCloud’s official website.
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>2. Approvals for Application for Service</label></dt>
						<dd>
							<label>
								2.1. NexCloud may approve your application of use unless you don’t have business related or technical issues.
							</label>
						</dd>
						<dd>
							<label>
								2.2. If NexCloud approves your application of use, NexCloud shall inform you the approvals with the terms listed below through email, written letter, or phone calls. <br/><br/>
								1) The effective date of service<br/><br/>
								2) terms related to subscription fees.<br/><br/>
								3) your rights and responsibilities.<br/><br/>
								4) other matters that NexCloud thinks it’s necessary for you to acknowledge before or while using the service.<br/><br/>
							</label>
						</dd>
						<dd>
							<label>
								2.3. The service contract may not be established if NexCloud disapproves new contract or renew contract submitted by you who are currently using NexCloud’s service.
							</label>
						</dd>
						<dd>
							<label>
								2.4. NexCloud may disapprove new contract or re-contract if you are applicable to any of case listed below. In case NexCloud disapproves your contract, NexCloud shall inform you the reasons of disapproval.<br/><br>
								1) Applying for service using their party’s name.<br/><br>
								2) Fill out or attach false information when applying for service.<br/><br>
								3) The contract may not be approved due to your imputation or your interruptions on NexCloud’s service or your new service contract which causes possible interruptions on NexCloud’s service.<br/><br>
							</label>
						</dd>
					</dl>
				</div>
            </div>
            
            <!-- 3. Contract Modification and Termination -->
            <div class="modal-body">
            	<h5 class="modal-title">Contract Modification and Termination</h5>
	            <div class="m-widget27__item">
					<dl>
						<dt><label>1. Contract Modification</label></dt>
						<dd>
							<label>
								1.1.  If you are applicable any changes in terms listed in below, you should update your information on NexCloud’s official website. (<a href="http://www.nexcloud.co.kr" target="_blank">http://www.nexcloud.co.kr</a>)<br/><br/>
								1) Your business name, name, address, contact number<br/><br/>
								2) Changes in service<br/><br/>
								3) Payment methods or payment information such as bank, credit or debit card number.
							</label>
						</dd>
						<dd>
							<label>
								1.2. In accordance with Article1, subparagraph 2, in case of service modification, service fees will be charged based on a new service contract.
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>2. Contract Renew</label></dt>
						<dd>
							<label>
								The service period shall be every month from the first date of your service to the end of the month. If there is no separate agreement between you and NexCloud within the service period, the service automatically extends for one month on the same terms as the previous contract.
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>3. Assignment</label></dt>
						<dd>
							<label>
								3.1. Any rights and licenses may not be transferred or assigned by you unless you obtained written consent from NexCloud.
							</label>
						</dd>
						<dd>
							<label>
								3.2. If you want to transfer or assign any rights and licenses to third-party,the existing unpaid service fees should be paid first. The transfer of your status refers to changes in key factors that identify your identity such as the change of the mission, change of the representative, change of the person in charge (if the service user is different than the payment officer).
							</label>
						</dd>
						<dd>
							<label>
								3.3. In case the transfer of your status has occurred by mergers, divisions, or transfer of business, it is your responsibility to inform NexCloud the reasons for the transfer of your status with documents that proves the transfer of your status, a copy of business license (corporate only) within thirty (30) days.
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>4. Termination</label></dt>
						<dd>
							<label>
								4.1. If terms of service are terminated, fees will be charged based on from the effective date of service to the end of use. It will be prorated based on the first day of service and last day of service (your actual signup date)
							</label>
						</dd>
						<dd>
							<label>
								4.2. If either NexCloud or you are subject to bankruptcy application, seizure, pressurization, bankruptcy, auction, or company cleanup, the contract may be terminated immediately.
							</label>
						</dd>
						<dd>
							<label>
								4.3. If you want to terminate this service, you should submit a cancellation form to NexCloud, and NexCloud shall terminate the service by the day you requested for cancellation after payment is calculated. If fees or other charges are not fully paid, you may not terminate the terms.
							</label>
						</dd>
						<dd>
							<label>
								4.4. In case there’s restriction on your use of service in accordance with terms, NexCloud may terminate the terms using its authority if the cause is not cured within five (5) day period or do not comply with NexCloud’s request for comment without justifiable reason.
							</label>
						</dd>
						<dd>
							<label>
								4.5. NexCloud may cancel the service contract after submitting a written letter for the reason in case you cause any loss or damage and considered not being suitable for the service.
							</label>
						</dd>
						<dd>
							<label>
								4.6. Once contract cancellation is completed, NexCloud immediately deletes your server monitoring data.
							</label>
						</dd>
						<dd>
							<label>
								4.7. NexCloud may delete your server monitoring data if service fee was paid within thirty (30) days based on the billing date (cycle).
							</label>
						</dd>
						<dd>
							<label>
								4.8. Under article 17 of the E-commerce Act, any remorse return or a mistaken purchase cannot be withdrawn after thirty (30) days of application for a service or products submitted. If there’s a critical issues or problems on your use, it will be handled by the Electronic Commerce Act and the consumer dispute resolution standards (notify the Fair Trade Commission)
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>5. Deprivation of Rights of License</label></dt>
						<dd>
							<label>
								If you are applicable for any of the case listed below, NexCloud may terminate your service.<br/><br/>
								1) In case controversial information is provided.<br/><br/>
								2) In case you conduct any activities that might violate software protection laws.<br/><br/>
								3) In case you conduct any activities that might violate any laws or acts.<br/><br/>
								4) In case you apply for the service with false or invalid information.<br/><br/>
								5) In case You cause other users any damage while you are using NexCloud’s service.<br/><br/>
								6) In case you cause NexCloud any loss or damage or have possible loss or damage.<br/><br/>
								7) In case you use other users’ information or password that is stolen/In case you steal other users’ information or password from the service.<br/><br/>
								8) Application for service was submitted by users who previous removed or banned from NexCloud.
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>6. Service Fee</label></dt>
						<dd>
							<label>
								NexCloud shall clearly state service fee and any specifics on NexCloud’s official website (<a href="http://www.nexcloud.co.kr" target="_blank">http://www.nexcloud.co.kr</a>)
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>7. Service Fee Modification</label></dt>
						<dd>
							<label>
								7.1. Service fee change means fees increase or decrease, it applies to you the following month after NexCloud notice you the changes. If the period of contract is already fixed or service is already prepaid to NexCloud, you may not change the fee unless service fees decrease.
							</label>
						</dd>
						<dd>
							<label>
								NexCloud has a responsibility to the reasons of service fee change referred to 1, on NexCloud’s official website.
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>8. Compensation for Damages</label></dt>
						<dd>
							<label>
								Service is not provided to you more than three (3) days due to NexCloud’s fault, the service fee will be discounted. The discounts shall be the amount that average of the most recent monthly fee multiply by the number of days when service is unavailable.
							</label>
						</dd>
						<dd>
							<label>
								8.1. A written letter including reasons, amount or the basis of calculation for reimbursement must be submitted to have reimbursement for damage or loss.
							</label>
						</dd>
						<dd>
							<label>
								8.2. The claim for damages you submitted will be expired in thirty (30) days after it has occurred.
							</label>
						</dd>
						<dd>
							<label>
								8.3. To the maximum extent permitted by applicable laws, NexCloud is not liable for use of service or service unavailability any indirect, special, incidental damage or any related damage including but not limited to profit loss or data loss.
							</label>
						</dd>
						<dd>
							<label>
								8.4. In no event shall NexCloud’s cumulative and aggregate liability under these terms exceed service fees that NexCloud offers.
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>9. Indemnity</label></dt>
						<dd>
							<label>
								9.1.  NexCloud is not liable for any of your activities or results that cause damage, loss, illegal activities, and any civil and criminal liability and compensation. Your use of the NexCloud is at your own risk.
							</label>
						</dd>
						<dd>
							<label>
								9.2. As long as NexCloud conduct any required legal activities, NexCloud is not liable for any damage or loss.
							</label>
						</dd>
						<dd>
							<label>
								9.3. NexCloud is not liable for any information leakage and damage of information caused by external interruptions.
							</label>
						</dd>
						<dd>
							<label>
								9.4. NexCloud is not liable for loss or damage caused by free trials that provided to you and its use.
							</label>
						</dd>
						<dd>
							<label>
								9.5. Changes are periodically made to the information and NexCloud may make additions, updates, improvements and discard at any time without your prior consent to the changes.
							</label>
						</dd>
						<dd>
							<label>
								9.6. NexCloud assumes no liability or responsibility for any service and software provided by NexCloud.
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>10. The Effectiveness of Terms</label></dt>
						<dd>
							<label>
								When you apply for service, NexCloud grants a license to you, this agreement shall be effective.
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>11. A Competent Court</label></dt>
						<dd>
							<label>
								11.1. Except specified in the terms, general practices and contingency act shall be followed and interpretation of terms or conflict over proper fulfillment of the terms shall be resolved by agreement between you and NexCloud.
							</label>
						</dd>
						<dd>
							<label>
								11.2. If the agreement is not established, it will be dealt with a court that in charge of where NexCloud is located.
							</label>
						</dd>
					</dl>
					
					<dl>
						<dt><label>These terms are effective as of August 1. 2018</label></dt>
					</dl>
				</div>
            </div>
            
            
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--end::[Modal]Terms and conditions -->



<!--begin::[Modal]Terms and conditions -->
<div class="modal fade" id="detailModal_privacy" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document" style="max-width:98%;">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Privacy</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            
            <div class="modal-body">
	            <div class="m-widget27__item">
					<dl>
						<dd><label><pre>개인정보 보호정책

정보통신망법 규정에 따라 넥스클라우드에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간을 안내 드리오니 자세히 읽은 후 동 의하여 주시기 바랍니다.

* 수집하는 개인정보
* 수집한 개인정보의 이용
* 개인정보의 파기
1. 수집하는 개인정보

이용자가 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 넥스클라우드는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.

회원가입 시점에 넥스클라우드가 이용자로부터 수집하는 개인정보는 아래와 같습니다.

– 회원 가입 시에 ‘이메일, 비밀번호’를 필수항목으로 수집합니다. 그리고 선택항목으로 이 름, 연락처를 수집합니다.

서비스 이용 과정에서 이용자로부터 수집하는 개인정보는 아래와 같습니다.

넥스클라우드 내의 개별 서비스 이용 과정에서 해당 서비스의 이용자에 한해 추가 개인정보 수집이 발생할 수 있습니다.

추가로 개인정보를 수집할 경우에는 해당 개인정보 수집 시점에서 이용자에게 ‘수집하는 개인정보 항목, 개인정보의 수집 및 이용목적, 개인정보의 보관기간’에 대해 안내 드 리고 동의를 받습니다.

서비스 이용 과정에서 IP 주소, 쿠키, 서비스 이용 기록, 기기정보, 위치정보가 생성되어 수집될 수 있습니다.

구체적으로,

1)서비스 이용 과정에서 이용자에 관한 정보를 정보통신서비스 제공자가 자동화된 방법으로 생성하여 이를 저장(수집)하거나,

2)이용자 기기의 고유한 정보를 원래의 값을 확인하지 못 하도록 안전하게 변환한 후에 수집하 는 경우를 의미합니다.

2. 수집한 개인정보의 이용

넥스클라우드는 회원관리, 서비스 개발・제공 및 향상, 안전한 인터넷 이용환경 구축 등 아래의 목적으로만 개인정보를 이용합니다

– 회원 가입 의사의 확인, 연령 확인 및 법정대리인 동의 진행, 이용자 및 법정대리인의 본인 확 인, 이용자 식별, 회원탈퇴 의사의 확인 등 회원관리를 위하여 개인정보를 이용합니다

– 콘텐츠 등 기존 서비스 제공(광고 포함)에 더하여, 인구통계학적 분석, 서비스 방문 및 이용기록 의 분석, 개인정보 및 관심에 기반한 이용자간 관계의 형성, 지인 및 관심사 등에 기반한 맞춤형 서비스 제공 등 신규 서비스 요소의 발굴 및 기존 서비스 개선 등을 위하여 개인정보를 이용합니 다.

– 법령 및 넥스클라우드 이용약관을 위반하는 회원에 대한 이용 제한 조치, 부정 이용 행위를 포함 하여 서비스의 원활한 운영에 지장을 주는 행위에 대한 방지 및 제재, 계정도용 및 부정거래 방 지, 약관 개정 등의 고지사항 전달, 분쟁조정을 위한 기록 보존, 민원처리 등 이용자 보호 및 서 비스 운영을 위하여 개인정보를 이용합니다.

– 유료 서비스 제공에 따르는 본인인증, 구매 및 요금 결제, 상품 및 서비스의 배송을 위하여 개인정보를 이용합니다.

– 이벤트 정보 및 참여기회 제공, 광고성 정보 제공 등 마케팅 및 프로모션 목적으로 개인정보를 이용합니다.

– 서비스 이용기록과 접속 빈도 분석, 서비스 이용에 대한 통계, 서비스 분석 및 통계에 따른 맞 춤 서비스 제공 및 광고 게재 등에 개인정보를 이용합니다.

– 보안, 프라이버시, 안전 측면에서 이용자가 안심하고 이용할 수 있는 서비스 이용환경 구축을 위해 개인정보를 이용합니다.

3. 개인정보의 파기

회사는 원칙적으로 이용자의 개인정보를 회원 탈퇴 시 지체없이 파기하고 있습니다.

단, 이용자에게 개인정보 보관기간에 대해 별도의 동의를 얻은 경우, 또는 법령에서 일정 기간 정 보보관 의무를 부과하는 경우에는 해당 기간 동안 개인정보를 안전하게 보관합니다. 이용자에게 개인정보 보관기간에 대해 별도의 동의를 얻은 경우는 아래와 같습니다.

부정가입 및 징계기록 등의 부정이용기록은 부정 가입 및 이용 방지를 위하여 수집 시점으로부터 6개월간 보관하고 파기하고 있습니다.

전자상거래 등에서의 소비자 보호에 관한 법률, 전자금융거래법, 통신비밀보호법 등 법령에서 일 정기간 정보의 보관을 규정하는 경우는 아래와 같습니다.

넥스클라우드는 이 기간 동안 법령의 규 정에 따라 개인정보를 보관하며, 본 정보를 다른 목적으로는 절대 이용하지 않습니다.

– 전자상거래 등에서 소비자 보호에 관한 법률
계약 또는 청약철회 등에 관한 기록:5년 보관 대금결제 및 재화 등의 공급에 관한 기록: 5년 보관 소비자의 불만 또는 분쟁처리에 관한 기록: 3년 보관 – 전자금융거래법 전자금융에 관한 기록: 5년 보관

– 통신비밀보호법 로그인 기록: 3개월</pre></label></dd>
					</dl>
				</div>
				
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--end::[Modal]Terms and conditions -->


<!--begin::[Modal]Account notification -->
<div class="modal fade" id="detailModal_account" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document"">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Notification</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <!-- <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Company name already exists.</h5>
            </div> -->
            <div class="modal-body">
	            <div class="m-widget27__item">
					<dl>
						<h5 class="modal-title">Company name already exists.</h5>
						<br/>
						<dd style="padding-bottom: 1.5rem;">Account of ' <label class="input_company_name" style="font-style:italic"></label> ' already exists, you can create new account with ' <label class="input_company_name" style="font-style:italic"></label> ' but if you want use same agent already created, please contact</dd>
						<dd><label id="accounts" style="font-weight:bold"></label></dd>
					</dl>
				</div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--end::[Modal]Account notification -->


</body>
<!-- end::Body -->

</html>



<script>
var displayTermsAndCondition = function() {
	$('#detailModal_terms').modal('show');
}

var displayPrivacy = function() {
	$('#detailModal_privacy').modal('show');
}
$("#m_login_demo").click(function(){
	startSpinner();
	$.ajax({
		url: g_prefix+"/v1/login/check",
		type: "post",
		data: { "user_id":'demo@nexclipper.com', "user_passwd":'1234' },
		dataType: "json",
		success: function(data){
			if( data.result == "success") {
				location.href = g_prefix+"/v1/dashboard";
				stopSpinner();
			} else if( data.result == "fail") {
				alert(data.message);
				stopSpinner();
				return;
			}
		}, 
		error : function (jqXHR, textStatus, errorThrown) {
			stopSpinner();
				alert('오류가 발생했습니다\n[' + textStatus + ']\n' + errorThrown);
		}
	});
})
</script>

<iframe src="${prefix}/v1/resource_down" style="display:none"/>