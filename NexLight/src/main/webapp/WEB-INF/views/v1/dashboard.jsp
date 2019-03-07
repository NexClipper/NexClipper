<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form name="frm" id="frm" method="post">
	<input type="hidden" id="service_id" name="service_id"/>
	<input type="hidden" id="executor_id" name="executor_id"/>
</form>
			<!-- BEGIN: Subheader -->
            <div class="m-subheader">
                <div class="d-flex align-items-center">
                    <div class="mr-auto">
                        <h3 class="m-subheader__title">Cluster : <span id="cluster_name"></span></h3>
                    </div>
                    <div>
                        Leader : <strong id="cluster_leader"></strong>, Uptime : <strong id="cluster_uptime"></strong>
                        &nbsp;&nbsp;&nbsp;
                    </div>
                    <div>
	                    <span class="m-subheader__daterange" id="m_dashboard_daterangepicker">
							<span class="m-subheader__daterange-label">
								<span class="m-subheader__daterange-title"></span>
								<span class="m-subheader__daterange-date m--font-brand"></span>
							</span>
							<a href="#" class="btn btn-sm btn-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill">
								<i class="la la-angle-down"></i>
							</a>
						</span>
					</div>
                </div>
            </div>

            <!-- END: Subheader -->

            <div class="m-content">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height nc-dashboard-main-portlet">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text" style="cursor:pointer;" onclick="location.href='${prefix}/v1/service';">Container</h3>
                                    </div>

                                </div>

                            </div>
                            <div class="m-portlet__body">
                                <!--그래프는 dashboard.js에서 dashboardTask 함수참조-->
                                <div class="m-widget14">


                                    <div class="row  align-items-center">
                                        <div class="col">
                                            <div id="m_chart_dashboard_task" class="m-widget14__chart m--margin-bottom-5" style="height: 140px">
                                                <div class="m-widget14__stat">
                                                    <a href="${prefix}/v1/service" class="btn-link m--font-metal"><strong id="task_total"></strong></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="m-widget14__legends-container">
                                                <div class="m-widget14__legends">
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-accent"></span>
                                                        <span class="m-widget14__legend-text"><a href="${prefix}/v1/service" class="btn-link m--font-accent">Normal : <strong id="task_running"></strong></a></span>
                                                    </div>
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-warning"></span>
                                                        <span class="m-widget14__legend-text  m--font-warning">Staging : <strong id="task_staging"></strong></span>
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <br>
                                                    <br>
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-danger"></span>
                                                        <span class="m-widget14__legend-text"><a href="#" onClick="fnHeavyTask()" class="btn-link m--font-danger">Warning : <strong id="task_warning"></strong></a></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height nc-dashboard-main-portlet">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text" style="cursor:pointer;" onclick="location.href='${prefix}/v1/agent';">Agent</h3>
                                    </div>
                                </div>
                            </div>
                            <div class="m-portlet__body">
                                <!--그래프는 dashboard.js에서 dashboardAgent 함수참조-->
                                <div class="m-widget14">
                                    <div class="row  align-items-center">
                                        <div class="col">
                                            <div id="m_chart_dashboard_agent" class="m-widget14__chart m--margin-bottom-5" style="height: 140px">
                                                <div class="m-widget14__stat">
                                                    <a href="${prefix}/v1/agent" class="btn-link m--font-metal"><strong id="agent_total"></strong></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="m-widget14__legends-container">
                                                <div class="m-widget14__legends">
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-accent"></span>
                                                        <span class="m-widget14__legend-text"><a href="${prefix}/v1/agent" class="btn-link m--font-accent">Normal : <strong id="agent_normal"></strong></a></span>
                                                    </div>
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-warning"></span>
                                                        <span class="m-widget14__legend-text m--font-warning">Warning : <strong id="agent_warning"></strong></span>
                                                    </div>
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-metal"></span>
                                                        <span class="m-widget14__legend-text m--font-metal">Critical : <strong id="agent_critical"></strong></span>
                                                    </div>
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-black"></span>
                                                        <span class="m-widget14__legend-text m--font-black">Disconnect : <strong id="agent_unknown"></strong></span>
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-danger"></span>
                                                        <span class="m-widget14__legend-text m--font-danger"><a href="#" onClick="fnHeavyAgent()" class="btn-link m--font-danger">Warning : <strong id="agent_unhealthy"></strong></a></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height nc-dashboard-main-portlet">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text" style="cursor:pointer;" onclick="location.href='${prefix}/v1/host';">Host</h3>
                                    </div>
                                </div>
                            </div>
                            <div class="m-portlet__body">
                                <!--그래프는 dashboard.js에서 dashboardHost 함수참조-->
                                <div class="m-widget14">

                                    <div class="row  align-items-center">
                                        <div class="col">
                                            <div id="m_chart_dashboard_host" class="m-widget14__chart m--margin-bottom-5" style="height: 140px">
                                                <div class="m-widget14__stat">
                                                    <a href="${prefix}/v1/host" class="btn-link m--font-metal"><strong id="host_total"></strong></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="m-widget14__legends-container">
                                                <div class="m-widget14__legends">
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-accent"></span>
                                                        <span class="m-widget14__legend-text m--font-accent"><a href="${prefix}/v1/host" class="btn-link m--font-accent">Up : <strong id="host_normal"></strong></a></span>
                                                    </div>
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-black"></span>
                                                        <span class="m-widget14__legend-text m--font-black">Shutdown : <strong id="host_shutdown"></strong></span>
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <br>
                                                    <br>
                                                    <div class="m-widget14__legend">
                                                        <span class="m-widget14__legend-bullet m--bg-danger"></span>
                                                        <span class="m-widget14__legend-text m--font-danger"><a href="#" onClick="fnHeavyHost()" class="btn-link m--font-danger">Warning: <strong id="host_warning"></strong></a></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">

                    <div class="col-lg-4">
                        <!--begin:: Widgets/Stats Resource Allocated-->
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height ">

                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text" style="cursor:pointer;" onclick="location.href='${prefix}/v1/cluster';">
                                            Cluster Resource Allocated

                                        </h3>
                                    </div>

                                </div>
                                <div class="m-portlet__head-tools">

                                </div>
                            </div>


                            <div class="m-portlet__body  m-portlet__body--no-padding">
                                <div class="row m-row--no-padding">
                                    <div class="col-sm-3 col-md-3 col-lg-6 col-xl-6">
                                        <!--begin::Total Profit-->
                                        <div class="m-widget24">
                                            <div class="m-widget24__item">
                                                <h4 class="m-widget24__title">CPU</h4><br>
                                                <span class="m-widget24__desc"><strong id="cluster_alloc_cpus"></strong> / <strong id="cluster_total_cpus"></strong></span>
                                                <span class="m-widget24__stats m--font-brand"><span id="cluster_alloc_per_cpus"></span>%</span>
                                                <div class="m--space-10"></div>
                                                <div class="m-widget24__desc">
                                                    <canvas id="m_chart_cpu_alloc" style="display: block; width: 100px; height: 20px;" width="100" height="20"></canvas>
                                                </div>

                                            </div>
                                        </div>
                                        <!--end::Total Profit-->
                                    </div>
                                    <div class="col-sm-3 col-md-3 col-lg-6 col-xl-6">
                                        <!--begin::New Feedbacks-->
                                        <div class="m-widget24">
                                            <div class="m-widget24__item">
                                                <h4 class="m-widget24__title">MEMORY</h4><br>
                                                <span class="m-widget24__desc"><strong id="cluster_alloc_mem"></strong> / <strong id="cluster_total_mem"></strong></span>
                                                <span class="m-widget24__stats m--font-danger"><span id="cluster_alloc_per_mem"></span>%</span>
                                                <div class="m--space-10"></div>
                                                <div class="m-widget24__desc">
                                                    <canvas id="m_chart_mem_alloc" style="display: block; width: 100px; height: 20px;" width="100" height="20"></canvas>
                                                </div>

                                            </div>
                                        </div>
                                        <!--end::New Feedbacks-->
                                    </div>
                                    <div class="col-sm-3 col-md-3 col-lg-6 col-xl-6">
                                        <!--begin::New Orders-->
                                        <div class="m-widget24">
                                            <div class="m-widget24__item">
                                                <h4 class="m-widget24__title">DISK</h4><br>
                                                <span class="m-widget24__desc"><strong id="cluster_alloc_disk"></strong> / <strong id="cluster_total_disk"></strong></span>
                                                <span class="m-widget24__stats m--font-success"><span id="cluster_alloc_per_disk"></span>%</span>
                                                <div class="m--space-10"></div>
                                                <div class="m-widget24__desc">
                                                    <canvas id="m_chart_disk_alloc" style="display: block; width: 100px; height: 20px;" width="100" height="20"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                        <!--end::New Orders-->
                                    </div>
                                    <div class="col-sm-3 col-md-3 col-lg-6 col-xl-6">
                                        <!--begin::New Users-->
                                        <div class="m-widget24">
                                            <div class="m-widget24__item">
                                                <h4 class="m-widget24__title">GPU</h4><br>
                                                <span class="m-widget24__desc"><strong id="cluster_alloc_gpus"></strong> / <strong id="cluster_total_gpus"></strong></span>
                                                <span class="m-widget24__stats m--font-warning"><span id="cluster_alloc_per_gpus"></span>%</span>
                                                <div class="m--space-10"></div>
                                                <div class="m-widget24__desc">
                                                    <canvas id="m_chart_gpu_alloc" style="display: block; width: 100px; height: 20px;" width="100" height="20"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                        <!--end::New Users-->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--end:: Widgets/Stats Resource Allocated-->
                    </div>
                    <div class="col-lg-4">
                        <!--begin:: Widgets/Cluster Resource Used-->
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height ">

                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text" style="cursor:pointer;" onclick="location.href='${prefix}/v1/cluster';">
                                            Cluster Resource Used

                                        </h3>
                                    </div>

                                </div>
                                <div class="m-portlet__head-tools">

                                </div>
                            </div>


                            <div class="m-portlet__body  m-portlet__body--no-padding">
                                <div class="row m-row--no-padding">
                                    <div class="col-sm-3 col-md-3 col-lg-6 col-xl-6">
                                        <!--begin::Total Profit-->
                                        <div class="m-widget24">
                                            <div class="m-widget24__item">
                                                <h4 class="m-widget24__title">CPU</h4><br>
                                                <span class="m-widget24__desc"><strong id="cluster_usage_used_cpus"></strong> / <strong id="cluster_usage_alloc_cpus"></strong></span>
                                                <span class="m-widget24__stats m--font-brand"><span id="cluster_usage_cpus"></span>%</span>
                                                <div class="m--space-10"></div>
                                                <div class="m-widget24__desc">
                                                    <canvas id="m_chart_cpu_usage" style="display: block; width: 100px; height: 20px;" width="100" height="20"></canvas>
                                                </div>

                                            </div>
                                        </div>
                                        <!--end::Total Profit-->
                                    </div>
                                    <div class="col-sm-3 col-md-3 col-lg-6 col-xl-6">
                                        <!--begin::New Feedbacks-->
                                        <div class="m-widget24">
                                            <div class="m-widget24__item">
                                                <h4 class="m-widget24__title">MEMORY</h4><br>
                                                <span class="m-widget24__desc"><strong id="cluster_usage_used_mem"></strong> / <strong id="cluster_usage_alloc_mem"></strong></span>
                                                <span class="m-widget24__stats m--font-danger"><span id="cluster_usage_mem"></span>%</span>
                                                <div class="m--space-10"></div>
                                                <div class="m-widget24__desc">
                                                    <canvas id="m_chart_mem_usage" style="display: block; width: 100px; height: 20px;" width="100" height="20"></canvas>
                                                </div>

                                            </div>
                                        </div>
                                        <!--end::New Feedbacks-->
                                    </div>
                                    <div class="col-sm-3 col-md-3 col-lg-6 col-xl-6">
                                        <!--begin::New Orders-->
                                        <div class="m-widget24">
                                            <div class="m-widget24__item">
                                                <h4 class="m-widget24__title">DISK</h4><br>
                                                <span class="m-widget24__desc"><!-- <strong id="cluster_usage_used_disk"></strong> / <strong id="cluster_usage_alloc_disk"></strong> --></span>
                                                <span class="m-widget24__stats m--font-success"><span id="cluster_usage_disk"></span>GB</span>
                                                <div class="m--space-10"></div>
                                                <div class="m-widget24__desc">
                                                    <canvas id="m_chart_disk_usage" style="display: block; width: 100px; height: 20px;" width="100" height="20"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                        <!--end::New Orders-->
                                    </div>
                                    <div class="col-sm-3 col-md-3 col-lg-6 col-xl-6">
                                        <!--begin::New Users-->
                                        <div class="m-widget24">
                                            <div class="m-widget24__item">
                                                <h4 class="m-widget24__title">GPU</h4><br>
                                                <span class="m-widget24__desc"><strong id="cluster_usage_used_gpus"></strong> / <strong id="cluster_usage_alloc_gpus"></strong></span>
                                                <span class="m-widget24__stats m--font-warning"><span id="cluster_usage_gpus"></span>%</span>
                                                <div class="m--space-10"></div>
                                                <div class="m-widget24__desc">
                                                    <canvas id="m_chart_gpu_usage" style="display: block; width: 100px; height: 20px;" width="100" height="20"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                        <!--end::New Users-->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- <div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text">Cluster Resource Used</h3>
                                    </div>
                                </div>
                                <div class="m-portlet__head-tools">

                                </div>
                            </div>
                            <div class="m-portlet__body">
                                <div id="m_amcharts_1" class="amchart-line-dummy" style="height: 200px;"></div>
                            </div>
                        </div> -->
                        <!--end:: Widgets/Cluster Resource Used-->
                    </div>
                    <div class="col-lg-4">
                        <!--begin:: Widgets/Task (Container) Performance-->
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text" style="cursor:pointer;" onclick="location.href='${prefix}/v1/performance';">Service Performance</h3>
                                    </div>
                                </div>
                                <div class="m-portlet__head-tools">

                                </div>
                            </div>
                            <div class="m-portlet__body">
                            	<div id="m_chart_task_perform" style="height: 200px;"></div>
                                <!-- <div id="m_amcharts_2" class="amchart-bar-dummy" style="height: 200px;"></div> -->
                            </div>
                        </div>
                        <!--end:: Task (Container) Performance-->
                    </div>
                </div>

                <div class="row">

                    <div class="col-lg-4">
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height">

                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text" style="cursor:pointer;" onclick="location.href='${prefix}/v1/service';">Selected Framework / Service</h3>
                                    </div>

                                </div>
                                
                                <div class="m-portlet__head-tools">
                                    <div class="btn-group" role="group">
                                        <button id="btnFrameworkSelector" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Select</button>
                                        <div class="dropdown-menu" aria-labelledby="btnFrameworkSelector">
                                            <a class="dropdown-item" href="#">NexRouter</a>
                                            <a class="dropdown-item" href="#">NexGate</a>
                                            <a class="dropdown-item" href="#">NexDNS</a>
                                            <!-- <a class="dropdown-item" href="#">Select 4</a> -->
                                        </div>
                                    </div>
                                </div>
                               
                            </div>

                            <div class="m-portlet__body m--padding-right-5">

                                <div class="m-scrollable mCustomScrollbar _mCS_5 mCS-autoHide" data-scrollbar-shown="true" data-scrollable="true" data-max-height="220" style="overflow: visible; height: 220px; max-height: 220px; position: relative;">
                                    <div class="m-widget4 m-dashboard-widget4" id="m_widget_framework">
                                        
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <!--begin:: Widgets/Task Failure-->
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text" style="cursor:pointer;" onclick="location.href='${prefix}/v1/service';">Task Failure</h3>
                                    </div>
                                </div>
                                <div class="m-portlet__head-tools">

                                </div>
                            </div>
                            <div class="m-portlet__body">
                            	<div id="m_chart_failure" style="height: 200px;"></div>
                                <!-- <div id="m_amcharts_3" class="amchart-bar-dummy" style="height: 200px;"></div> -->
                                
                                <!-- begin::Failure Rate -->
                                <div class="row m-row--no-padding">
									<!-- begin::blank -->
								  	<div class="col-sm-1 col-md-1 col-lg-1 col-xl-3"></div>
								  	<!-- end::blank -->
								  
								  	<div class="col-sm-1 col-md-1 col-lg-1 col-xl-2">
								      	<!--begin::failure rate (Task Failed)-->
								      	<div class="m-widget24">
								          	<div class="m-widget24__item">
								              	<h2 class="m-widget24__title m--font-brand">Failed</h2><br>
								              	<span class="m-widget24__desc m--font-brand"><strong id="failed_rate"></strong></span>
								          	</div>
								      	</div>
								      	<!--end::failure rate (Task Failed)-->
								  	</div>
								  	
								  	<div class="col-sm-1 col-md-1 col-lg-1 col-xl-2">
									    <!--begin::failure rate (Task Killed)-->
									    <div class="m-widget24">
									        <div class="m-widget24__item">
									            <h4 class="m-widget24__title m--font-warning">Lost</h4><br>
									            <span class="m-widget24__desc m--font-warning" ><strong id="failed_lost"></strong></span>
									        </div>
									    </div>
									    <!--end::failure rate (Task Killed)-->
									</div>
								  
									<div class="col-sm-1 col-md-1 col-lg-1 col-xl-2">
									    <!--begin::failure rate (Task Killed)-->
									    <div class="m-widget24">
									        <div class="m-widget24__item">
									            <h4 class="m-widget24__title m--font-danger">Killed</h4><br>
									            <span class="m-widget24__desc m--font-danger"><strong id="failed_killed"></strong></span>
									        </div>
									    </div>
									    <!--end::failure rate (Task Killed)-->
									</div>
									
									<!-- begin::blank -->
									<div class="col-sm-1 col-md-1 col-lg-1 col-xl-3"></div>
									<!-- end::blank -->
								</div>
								<!-- end::Failure Rate -->
                            </div>
                        </div>
                        <!--end:: Widgets/Task Failure-->
                    </div>
                    
                    
                    <div class="col-lg-4">
                        <!--begin:: Widgets/Log Watch-->
                        <div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <h3 class="m-portlet__head-text" style="cursor:pointer;" onclick="location.href='${prefix}/v1/log';">Log Watch</h3>
                                    </div>
                                </div>
                                <div class="m-portlet__head-tools">
                                </div>
                            </div>
                            <div class="m-portlet__body">
                            	<div id="m_chart_log" class="amchart-bar-dummy" style="height: 200px;"></div>
                                <!-- <div id="m_amcharts_4" class="amchart-line-dummy" style="height: 200px;"></div> -->
                            </div>
                        </div>
                        <!--end:: Log Watch-->
                    </div>
                </div>
			</div>


<script>
	////////////////////////////////////////
	// begin::클러스터 관련 변수
	var g_cluster_name		= "";
	var g_cluster_leader	= "";
	var g_cluster_uptime	= 0;
	// end::클러스터 관련 변수
	////////////////////////////////////////
	
	
	////////////////////////////////////////
	// begin::task 관련 변수
	var g_task_running		= 0;
	var g_task_staging		= 0;
	var g_task_warning		= 0;	// 80이상
	// end::task 관련 변수
	////////////////////////////////////////
	
	
	////////////////////////////////////////
	// begin::agent 관련 변수
	var g_agent_total		= 0;
	var g_agent_normal		= 0;
	var g_agent_warning		= 0;
	var g_agent_critical	= 0;
	var g_agent_unknown		= 0;
	var g_agent_unhealthy	= 0;	//80 이상
	
	var g_agent_disconnect	= 0;
	// end::agent 관련 변수
	////////////////////////////////////////
	
	
	////////////////////////////////////////
	// begin::host 관련 변수
	var g_host_total		= 0;
	var g_host_normal		= 0;
	var g_host_shutdown		= 0;
	var g_host_warning		= 0;	// 80 이상
	// end::host 관련 변수
	////////////////////////////////////////
	
	
	////////////////////////////////////////
	// begin::cluster resource allocate 관련 변수
	var g_resource_obj		= {};
	var g_cluster_alloc_data	= new Array();
	var g_cluster_alloc_date	= new Array();
	// end::cluster resource allocate 관련 변수
	////////////////////////////////////////
	
	
	////////////////////////////////////////
	// begin::cluster resource used 관련 변수
	var g_resource_used_obj		= {};
	var g_cluster_usage_data	= new Array();
	var g_cluster_usage_date	= new Array();
	// end::cluster resource used 관련 변수
	////////////////////////////////////////
	
	
	////////////////////////////////////////
	// begin::task failure 관련 변수
	var g_task_failure_data		= new Array();
	// end::task failure관련 변수
	////////////////////////////////////////
	
	
	////////////////////////////////////////
	// begin::log watch 관련 변수
	var g_log_count		= new Array();
	var g_err_log_count	= new Array();
	// end::log watch 관련 변수
	////////////////////////////////////////
	
	
	//////////////////////////////////////////////
	// begin::Selected framework / service 관련 변수 초기화
	var g_selected_service_arr = new Array();
	// begin::Selected framework / service 관련 변수 초기화
	//////////////////////////////////////////////
	

	//////////////////////////////////////////////
	// begin::Event Toggle btn 관련 변수
	//var toggle_evt = false;
	// begin::Event Toggle btn 관련 변수
	//////////////////////////////////////////////
	
	
	//////////////////////////////////////////////
	// begin::failure rate 관련 변수
	var g_failed_rate		= 0;
	var g_failed_lost		= 0;
	var g_failed_killed		= 0;
	// begin::failure rate 관련 변수
	//////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	//#######################################0309###############################################
	
	var master_obj;
	var node_obj;
	var agent_list;
	var g_agent_total;
	var g_alloc_last_time;
	var g_usage_last_time;
	var failure_chart_colors;
	var failure_time;
	var log_time;
	
	var g_get_task_warnig_finish 	= false;
	var g_load_finish				= false;
	var g_loadPool;
	var g_loadTask;
	var g_loadData;
	
	function fnGetTaskWarning() 
	{
		clearInterval(g_loadTask);
		
		// 각 Node를 반복하여 task_warning의 갯수 count
		for( var idx in node_obj.nodes ){
			var taskJson = fnTasks(node_obj.nodes[idx].hostname);
			
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
		            
		            g_selected_service_arr.push( framework_obj );
		        }
			}
			
			// 노드의 리소스 최대 사용률이 80이상일 때 agent unhealthy count 증가 
			if( node_obj.nodes[idx].usedResource.max_value >= 80 ){
				g_agent_unhealthy += 1;
			}
		}
		// end::Selected framework / service 관련 변수 초기화
		// end::task summary 관련변수 초기화
		////////////////////////////////////////
		
		g_get_task_warnig_finish = true;
	}
	
	function fnLoadFinish()
	{
		clearInterval(g_loadPool);
		if( g_load_finish == true && g_get_task_warnig_finish == true)
			fnRendering();
		else
			g_loadPool = setInterval("fnLoadFinish()", 10);
		
		console.log("fnLoadFinish===")
	}
	
	function fnRendering()
	{
		// begin::chart를 그리는 함수 호출
		dashboardTask(g_task_running, g_task_staging, g_task_warning);
		dashboardInfo(g_cluster_name, g_cluster_leader, g_cluster_uptime);
		dashboardAgent(g_agent_total, g_agent_normal, g_agent_warning, g_agent_critical, g_agent_unknown, g_agent_disconnect, g_agent_unhealthy);
		dashboardHost(g_host_total, g_host_normal, g_host_shutdown, g_host_warning);
		dashboardAllocResource(g_resource_obj, g_cluster_alloc_data, g_cluster_alloc_date, g_alloc_last_time);
		dashboardUsedResource(g_resource_used_obj, g_cluster_usage_data, g_cluster_usage_date, g_usage_last_time);
		dashboardPerfom();
		dashboardFailure(g_task_failure_data, failure_chart_colors, failure_time);
		dashboardFailureRate(g_failed_rate, g_failed_lost, g_failed_killed);
		dashboardLog(g_log_count,g_err_log_count, log_time);
		dashboardFramework(g_selected_service_arr);
		
		/* var t_resource_alloc 	= setInterval("fnAjaxAllocResource()", 100);
		var t_resource_usage 	= setInterval("fnAjaxUsedResource()", 100);
		var t_master			= setInterval("fnAjaxMaster()", 100);
		var t_framewrok			= setInterval("fnAjaxFramework()", 100);
		var t_agent				= setInterval("fnAjaxAgent()", 100);
		var t_host				= setInterval("fnAjaxHost()", 100);
		var t_failure_rate		= setInterval("fnAjaxFailureRate()", 100); */
	}
	
	function fnDataLoad()
	{
		clearInterval(g_loadData);
		
		// begin::agent 관련 변수 초기화
		agent_list		= JSON.parse('${nodeList}');
		g_agent_total	= agent_list.length;
		
		for(var idx in agent_list){
			if( agent_list[idx].status == '-1' ){
				g_agent_disconnect++;
			}
			else if( agent_list[idx].status == "0"){
				g_agent_normal++;
			}
			else if( agent_list[idx].status == "1" ){
				g_agent_warning++;
			}
			else if( agent_list[idx].status == "2" ){
				g_agent_critical++;
			}
			else if( agent_list[idx].status == "3" ){
				g_agent_unknown++;
			}
		}
		// end::agent 관련 변수 초기화
		////////////////////////////////////////
		
		
		////////////////////////////////////////
		// begin::host 관련 변수 초기화
		/* var host_cpu_obj	= JSON.parse('${hostCPU}');
		var host_mem_obj	= JSON.parse('${hostMEM}');
		var host_disk_obj	= JSON.parse('${hostDISK}');
		
		//var cpu_system_series	= host_cpu_obj.results[0].series;
		//var cpu_user_series		= host_cpu_obj.results[1].series;
		//var cpu_iowait_series	= host_cpu_obj.results[2].series;
		var mem_series			= host_mem_obj.results[0].series;
		var disk_series			= host_disk_obj.results[0].series;
		
		//g_host_total = cpu_system_series.length;
		g_host_total = host_cpu_obj.results[0].series.length;
		for( var i = 0; i < g_host_total; i++){
			//var used_percent = cpu_system_series[i].values[0][1] + cpu_user_series[i].values[0][1] + cpu_iowait_series[i].values[0][1];
			var used_percent = host_cpu_obj.results[0].series[i].values[0][1] + host_cpu_obj.results[0].series[i].values[0][2] + host_cpu_obj.results[0].series[i].values[0][3];
			
			if( used_percent >= 80 || mem_series[i].values[0][1] >= 80 || disk_series[i].values[0][1] >= 80 ) {
				g_host_warning++;
			}
			else {
				g_host_normal++;
			}
		} */
		// end::host 관련 변수 초기화
		////////////////////////////////////////
		
		
		//////////////////////////////////////////////
		// begin::cluster resource allocate 관련 변수 초기화
		//var alloc_obj = JSON.parse('${alloc}');
		var alloc_obj = JSON.parse('${cluster}');
		var usage_obj = alloc_obj;
		
		
		g_resource_obj.total_cpus = master_obj.totalResource.cpus.toFixed(2);
		g_resource_obj.total_mem = master_obj.totalResource.mem.toFixed(2);
		g_resource_obj.total_disk = master_obj.totalResource.disk.toFixed(2);
		g_resource_obj.total_gpus = master_obj.totalResource.gpus.toFixed(2);
		
		g_resource_obj.allocate_cpus = master_obj.allocateResource.cpus.toFixed(2);
		g_resource_obj.allocate_mem = master_obj.allocateResource.mem.toFixed(2);
		g_resource_obj.allocate_disk = master_obj.allocateResource.disk.toFixed(2);
		g_resource_obj.allocate_gpus = master_obj.allocateResource.gpus.toFixed(2);
		
		g_resource_obj.allocate_per_cpus = master_obj.allocateResource.per_cpus.toFixed(2);
		g_resource_obj.allocate_per_mem = master_obj.allocateResource.per_mem.toFixed(2);
		g_resource_obj.allocate_per_disk = master_obj.allocateResource.per_disk.toFixed(2);
		g_resource_obj.allocate_per_gpus = master_obj.allocateResource.per_gpus.toFixed(2);
		
		var cluster_alloc_cpu_arr = new Array();
		var cluster_alloc_mem_arr = new Array();
		var cluster_alloc_disk_arr = new Array();
		var cluster_alloc_gpu_arr = new Array();
		
		for( var i = 0; i < alloc_obj.results[0].series[0].values.length; i++){
			if( i >= 1000 ) break;
			g_alloc_last_time		= alloc_obj.results[0].series[0].values[i][0];
			var arrTime			= g_alloc_last_time.split("Z");
			var dt				= new Date(arrTime[0]);
			dt.setHours(dt.getHours() + 9);
			
			g_cluster_alloc_date.push(dt);
			
			cluster_alloc_cpu_arr.push( alloc_obj.results[0].series[0].values[i][2] );
			cluster_alloc_mem_arr.push( alloc_obj.results[0].series[0].values[i][6] );
			cluster_alloc_disk_arr.push( alloc_obj.results[0].series[0].values[i][10] );
			cluster_alloc_gpu_arr.push( alloc_obj.results[0].series[0].values[i][14] );
		}
		
		g_cluster_alloc_data.push( cluster_alloc_cpu_arr );
		g_cluster_alloc_data.push( cluster_alloc_mem_arr );
		g_cluster_alloc_data.push( cluster_alloc_disk_arr );
		g_cluster_alloc_data.push( cluster_alloc_gpu_arr );
		// begin::cluster resource allocate 관련 변수 초기화
		//////////////////////////////////////////////
		
		
		//////////////////////////////////////////////
		// begin::cluster resource used 관련 변수 초기화
		//var usage_obj = JSON.parse('${cluster}');
		
		
		g_resource_used_obj.allocate_cpus = master_obj.allocateResource.cpus.toFixed(2);
		g_resource_used_obj.allocate_mem = master_obj.allocateResource.mem.toFixed(2);
		g_resource_used_obj.allocate_disk = master_obj.allocateResource.disk.toFixed(2);
		g_resource_used_obj.allocate_gpus = master_obj.allocateResource.gpus.toFixed(2);
		
		g_resource_used_obj.use_cpus = master_obj.usedResource.cpus.toFixed(2);
		g_resource_used_obj.use_mem = master_obj.usedResource.mem.toFixed(2);
		g_resource_used_obj.use_disk = master_obj.usedResource.disk.toFixed(2);
		g_resource_used_obj.use_gpus = master_obj.usedResource.gpus.toFixed(2);
		
		g_resource_used_obj.use_per_cpus = master_obj.usedResource.per_cpus.toFixed(2);
		g_resource_used_obj.use_per_mem = master_obj.usedResource.per_mem.toFixed(2);
		g_resource_used_obj.use_per_disk = master_obj.usedResource.per_disk.toFixed(2);
		g_resource_used_obj.use_per_gpus = master_obj.usedResource.per_gpus.toFixed(2);
		
		var cluster_used_cpu_arr = new Array();
		var cluster_used_mem_arr = new Array();
		var cluster_used_disk_arr = new Array();
		var cluster_used_gpu_arr = new Array();
		
		for( var i = 0; i < usage_obj.results[0].series[0].values.length; i++){
			if( i >= 1000 ) break;
			g_usage_last_time		= usage_obj.results[0].series[0].values[i][0];
			var arrTime				= g_usage_last_time.split("Z");
			var dt					= new Date(arrTime[0]);
			dt.setHours(dt.getHours() + 9);
			
			g_cluster_usage_date.push(dt);
			
			cluster_used_cpu_arr.push( usage_obj.results[0].series[0].values[i][4] );
			cluster_used_mem_arr.push( usage_obj.results[0].series[0].values[i][8] );
			cluster_used_disk_arr.push( usage_obj.results[0].series[0].values[i][11] );
			cluster_used_gpu_arr.push( usage_obj.results[0].series[0].values[i][16] );
		}
		
		g_cluster_usage_data.push( cluster_used_cpu_arr );
		g_cluster_usage_data.push( cluster_used_mem_arr );
		g_cluster_usage_data.push( cluster_used_disk_arr );
		g_cluster_usage_data.push( cluster_used_gpu_arr );
		
		console.log(g_cluster_alloc_data);
		console.log(g_cluster_usage_data);
		// end::cluster resource used 관련 변수 초기화
		//////////////////////////////////////////////
		
		
		//////////////////////////////////////////////
		// begin::task failure 관련 변수 초기화
		//var failure_obj = JSON.parse('${failure}');
		var failure_obj = JSON.parse('${failureRate}');
		failureRate_obj = failure_obj;
		
		var failure_FAILED	= new Array();
		var failure_LOST	= new Array();
		var failure_KILLED	= new Array();
		
		
		for( var i = 0; i < failure_obj.results[0].series[0].values.length; i++){
			if( i >= 1000 ) break;
			failure_time	= failure_obj.results[0].series[0].values[i][0];
			var arrTime		= failure_time.split("Z");
			var dt			= new Date(arrTime[0]);
			dt.setHours(dt.getHours() + 9);
			var dateA 		= dt.getTime();
			
			failure_FAILED.push( {x:dateA, y:failure_obj.results[0].series[0].values[i][1]} );
			failure_LOST.push( {x:dateA, y:failure_obj.results[0].series[0].values[i][2]} );
			failure_KILLED.push( {x:dateA, y:failure_obj.results[0].series[0].values[i][3]} );
		}
		
		g_task_failure_data.push( failure_FAILED );
		g_task_failure_data.push( failure_LOST );
		g_task_failure_data.push( failure_KILLED );
		
		failure_chart_colors = [ mUtil.getColor('brand') , mUtil.getColor('warning'), mUtil.getColor('danger')];
		// begin::task failure 관련 변수 초기화
		//////////////////////////////////////////////
		
		
		//////////////////////////////////////////////
		// begin::log watch 관련 변수 초기화
		/* var elastic = JSON.parse('${elastic}');
		
		for( var i = 0; i< elastic.results[0].series[0].values.length; i++ )
		{
			if( i >= 1000 ) break;
			log_time	= elastic.results[0].series[0].values[i][0];
			var arrTime = log_time.split("Z");
			var dt = new Date(arrTime[0]);
		    dt.setHours(dt.getHours() + 9);  
			var dateA = dt.getTime();
			
			g_log_count.	push( {x:dateA, y:elastic.results[0].series[0].values[i][1]} );
			g_err_log_count.push( {x:dateA, y:elastic.results[0].series[0].values[i][2]} );
		} */
		// begin::log watch 관련 변수 초기화
		//////////////////////////////////////////////
		
		
		/////////////////////////////////////////////////////////////////////////////////////////
		// begin::failure rate 관련변수 초기화
		//var failureRate_obj = ${failureRate};	

		var cur_index = failureRate_obj.results[0].series[0].values.length - 1;
		
		var cur_failed	= failureRate_obj.results[0].series[0].values[ failureRate_obj.results[0].series[0].values.length - 1 ][ 1 ];
		var cur_lost	= failureRate_obj.results[0].series[0].values[ failureRate_obj.results[0].series[0].values.length - 1 ][ 2 ];
		var cur_killed	= failureRate_obj.results[0].series[0].values[ failureRate_obj.results[0].series[0].values.length - 1 ][ 3 ];
		
		var min_failed	= 2147483647;
		var min_lost	= 2147483647;
		var min_killed	= 2147483647;
		
		for( var i = 0; i< failureRate_obj.results[0].series[0].values.length; i++ )
		{
			min_failed	= Math.min( failureRate_obj.results[0].series[0].values[i][1], min_failed );
			min_lost	= Math.min( failureRate_obj.results[0].series[0].values[i][2], min_lost );
			min_killed	= Math.min( failureRate_obj.results[0].series[0].values[i][3], min_killed );
		}
		g_failed_rate	= cur_failed - min_failed;
		g_failed_lost	= cur_lost - min_lost;
		g_failed_killed	= cur_killed - min_killed;
		// end::failure rate 관련변수 초기화
		/////////////////////////////////////////////////////////////////////////////////////////
		
		
		//////////////////////////////////////////////
		// begin::event list 관련 변수
		/* var g_event_list = ${ eventList };
		dashboardEvent( g_event_list ); */
		// begin::event list rate 관련 변수
		//////////////////////////////////////////////
		
		g_load_finish = true;
	}
	//#######################################0309###############################################
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	///////////////////////////////////////////
	// begin:: ready()
	 $(document).ready(function () {
		master_obj 		= ${master};
		node_obj		= ${nodes};
		
		////////////////////////////////////////
		// begin::cluster summary 관련변수 초기화
		g_cluster_name		= master_obj.cluster;
		g_cluster_leader	= master_obj.hostname;
		g_cluster_uptime	= master_obj.start_time;
		// end::cluster summary 관련변수 초기화
		////////////////////////////////////////
		
		
		////////////////////////////////////////
		// begin::task summary 관련변수 초기화
		// begin::Selected framework / service 관련 변수 초기화
		g_task_running		= master_obj.task_running;
		g_task_staging		= parseInt(master_obj.task_staging) + parseInt(master_obj.task_starting);
		
		
		////////////////////////////////////////////////////////////////////////////////////////////
		//#######################################0309###############################################
		// 1. settimeout function
		g_loadTask 	= setInterval("fnGetTaskWarning()", 10);
		g_loadData	= setInterval("fnDataLoad()", 10);
		g_loadPool 	= setInterval("fnLoadFinish()", 10);
		
		//#######################################0309###############################################
		////////////////////////////////////////////////////////////////////////////////////////////	
		
		
		
		////////////////////////////////////////////////////////////////
		// begin:: Real Time Monitoring (AJAX)
		/*
		var t_resource_alloc 	= setInterval("fnAjaxAllocResource()", 100);
		var t_resource_usage 	= setInterval("fnAjaxUsedResource()", 100);
		var t_master			= setInterval("fnAjaxMaster()", 100);
		var t_framewrok			= setInterval("fnAjaxFramework()", 100);
		var t_agent				= setInterval("fnAjaxAgent()", 100);
		var t_host				= setInterval("fnAjaxHost()", 100);
		var t_failure_rate		= setInterval("fnAjaxFailureRate()", 100);
		*/
		//var t_event				= setInterval("fnAjaxEvent()", 100);
		// end:: Real Time Monitoring (AJAX)
		////////////////////////////////////////////////////////////////

	});
	// end:: ready()
	///////////////////////////////////////////
	
	function fnHeavyTask()
	{
		document.heavyFrm.submit();
	}
	
	function fnHeavyAgent()
	{
		document.heavyAgent.submit();
	}
	
	function fnHeavyHost()
	{
		document.heavyHost.submit();
	}
	
	function fnChangeTime()
	{
		//fnCompare();
	}
</script>

<!--begin::Page Snippets -->
<script src="${prefix}/resources/v1/assets/js/dashboard.js" type="text/javascript"></script>
<!--end::Page Snippets -->

<form name="heavyFrm" id="heavyFrm" method="POST" action="${prefix}/v1/service">
	<input type="hidden" name="state" id="state" value="TASK_RUNNING"/>
	<input type="hidden" name="metric" id="metric" value="all"/>
	<input type="hidden" name="usage" id="usage" value="80"/>
	<input type="hidden" name="equality" id="equality" value=">"/>
</form>

<form name="heavyAgent" id="heavyAgent" method="POST" action="${prefix}/v1/agent">
	<input type="hidden" name="metric" id="metric" value="all" />
	<input type="hidden" name="usage" id="usage" value="80" />
	<input type="hidden" name="equality" id="equality" value=">" />
</form>

<form name="heavyHost" id="heavyHost" method="POST" action="${prefix}/v1/host">
	<input type="hidden" name="metric" id="metric" value="all" />
	<input type="hidden" name="usage" id="usage" value="80" />
	<input type="hidden" name="equality" id="equality" value=">" />
</form>
