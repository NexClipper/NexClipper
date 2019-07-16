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
<div class="m-subheader">
	<div class="d-flex align-items-center">
		<div class="mr-auto">
			<h3 class="m-subheader__title">
				Host Disk
			</h3>
		</div>
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
<div class="m-content">
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Disk Usage</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="diskUsageChartArea" style="height: 250px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Disk Read</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="diskReadChartArea" style="height: 250px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Disk Write</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="diskWriteChartArea" style="height: 250px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script type="text/javascript">
var hostIp = "${hostIp}";
var mountName = "${mountName}";

new Picker().refreshFunction(timeRefresh).draw();
function timeRefresh (startTime, time) {
	new Client().url("/api/v1/host/" + hostIp + "/disk/usage?mountName=" + mountName + "&startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(function(data){
		new BasicLine().area("diskUsageChartArea").data(data, "used_percent", mountName).draw();
	}).get();
	new Client().url("/api/v1/host/" + hostIp + "/disk/reads?mountName=" + mountName + "&startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(function(data){
		new BasicLine().area("diskReadChartArea").data(data, "reads", mountName).draw();
	}).get();
	new Client().url("/api/v1/host/" + hostIp + "/disk/writes?mountName=" + mountName + "&startTime=" + startTime + "&time=" + time + "&limit=1000")
	.callback(function(data){
		new BasicLine().area("diskWriteChartArea").data(data, "writes", mountName).draw();
	}).get();
}
</script>