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
<div class="m-content">
	<div class="row">
		<div class="col-lg-6">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">CPU</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id = "cpuChartArea" style="height: 250px;">
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<div class="m-portlet__head-text">Memory</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div id = "memChartArea" style="height: 250px;">
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
							<div class="m-portlet__head-text">List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30">
						<div class="row align-items-center">
							<div class="col-xl-12 col-lg-12">
								<div class="form-group m-form__group row align-items-center">
									<div class="col-lg-6 col-xl-6">
										<div class="m-form__group m-form__group--inline">
											<div class="m-form__label">
												<label>
													Filter
												</label>
											</div>
											<div class="m-form__control">
												<div class="row">
													<div class="col">
														<select class="form-control m-input form-metrics" id="hostIp">
														</select>
													</div>
													<div class="col">
														<select class="form-control m-input form-metrics" id="namespace">
														</select>
													</div>
													<div class="col">
														<select class="form-control m-input form-metrics" id="kind">
														</select>
													</div>
													<div class="col">
														<select class="form-control m-input form-metrics" id="type">
															<option value = "cpu">cpu</option>
															<option value = "mem">mem</option>
														</select>
													</div>
													<div class="col">
														<select class="form-control m-input form-metrics" id="equality">
															<option value="">Select Equality</option>
															<option value=">">&gt;</option>
															<option value="=">=</option>
															<option value="<">&lt;</option>
														</select>
													</div>
													<div class="col">
														<select class="form-control m-input form-metrics" id="usage">
															<option value="">Select Usage(%)</option>
															<option value="10">10 (%)</option>
															<option value="20">20 (%)</option>
															<option value="30">30 (%)</option>
															<option value="40">40 (%)</option>
															<option value="50">50 (%)</option>
															<option value="60">60 (%)</option>
															<option value="70">70 (%)</option>
															<option value="80">80 (%)</option>
															<option value="90">90 (%)</option>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="d-xl-none m--margin-bottom-10"></div>
									</div>
									<div class="col-lg-10 col-xl-4">
										<div class="form-group m-form__group">
											<div class="input-group">
												<input type="text" class="form-control" placeholder="Search for..." aria-label="Search for..." aria-describedby="basic-addon2" id="searchTxt">
												<!-- <div class="input-group-append">
													<button class="btn btn-success" type="button" id = "searchBtn">Search</button>
												</div> -->
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id = "podListArea">
						<div id = "podTableArea">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	refresh();
	refreshInterval = setInterval("refresh()", 1000);
})
function refresh() {
	console.log("aa");
}
	/* new Client()
		.url("/api/v1/host/2/cpu/idle/percent?startTime=20h&time=5h&limit=1000")
		.callback(
			function(data){
				new Pie().data(data).area("area").draw();
			}
		)
		.get();	
	new Client()
		.url("/api/v1/host/2/cpu/idle/percent?startTime=2000h&time=5h&limit=1000")
		.callback(function(data){console.log("r2 = " + data)})
		.get();
	new Client()
		.url("/api/v1/host/2/cpu/idle/percent?startTime=1h&time=5h&limit=1000")
		.callback(function(data){console.log("r3 = " + data)})
		.get();
	 */
	// test
/* 	var callbackPodSnapshot = function(data) {
		var items = JSON.parse(data.responseBody).items;
		items.forEach(function (item) {
			new Client()
			.url("/api/v1/kubernetes/pod/" + item.metadata.name + "/cpu/request?startTime=50d&time=10m&limit=1000")
			.callback(
				function(data){
					var rows = JSON.parse(data.responseBody);
					rows.forEach(function(row) {
						 if (typeof row.cpuRequest != "undefined") {
							console.log(item.metadata.name + " : " + row.cpuRequest + "[" + row.time + "]")
						} else {
							console.log(item.metadata.name + " : - ")
						}
					});
				}
			)
			.get();
		})
	}va
	var callbackCpuRequest = function(data) {
		
	} 
  	  	new Client()
		.url("/api/v1/kubernetes/pod/snapshot")
	.callback(callbackPodSnapshot)
	.get(); */
	

</script>