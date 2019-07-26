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
				Services
			</h3>
		</div>
		<div class="dropdown" id = "namespaceArea"></div>
	</div>
</div>
<div class="m-content">
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
					<div class="m-widget14">
						<div id="servicesTableArea" style="height: 250px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script src="/resources/js/module/common/workloadUtils.namespace.js" type="text/javascript"></script>
<script src="/resources/js/module/common/workloadUtils.services.js" type="text/javascript"></script>
<script type="text/javascript">
var namespace = "${namespace}";
function drawServices (data) {
	var arr = getArrayByNamespace(data,namespace);
	var tableData = [];
	console.log(arr);
	arr.forEach(function (item){
		var m = {
			key : item.metadata.name + ":" + item.metadata.namespace,
			name : item.metadata.name,
			namespace : item.metadata.namespace,
			label : item.metadata.labels,
			ports : item.spec.ports,
			clusterIP : item.spec.clusterIP,
			creationTimestamp : timeFormat(item.metadata.creationTimestamp)
		}
		if (typeof item.metadata.annotations != "undefined" &&
				typeof item.metadata.annotations["field.cattle.io/publicEndpoints"] != "undefined")
			m.publicEndpoints = item.metadata.annotations["field.cattle.io/publicEndpoints"];
		if (item.status.replicas == item.status.readyReplicas)
			m.status = "Running";
		else
			m.status = "Not Running";
		tableData.push(m);
	})
	var columns = getServicesColumns("services");
	new MDT().area("servicesTableArea").columns(columns).data(tableData).makeSearch().draw();
}
new Client().url("/api/v1/kubernetes/service/snapshot").callback(drawServices).get();
new Namespace().area("namespaceArea").thisNs(namespace).thisWorkload("services").draw();
</script>