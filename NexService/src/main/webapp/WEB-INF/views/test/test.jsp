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
				Node
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
							<div class="m-portlet__head-text">Pod List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="barArea" style = "height:800px"></div>
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
							<div class="m-portlet__head-text">Pod List</div>
						</div>
					</div>
					<div class="m-portlet__head-tools"></div>
				</div>
				<div class="m-portlet__body">
					<div class="m-widget14">
						<div id="container" style = "height:800px"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<link href="/resources/css/billboard/billboard.min.css" rel="stylesheet" >
<style type="text/css">
.histogramLine60 line { stroke: #ffb822; stroke-width: 2; stroke-dasharray: 2,2; }
.histogramLine80 line { stroke: #f4516c; stroke-width: 2; stroke-dasharray: 2,2; }
strong { color: black; font-weight: bold; }
.titleA {color: black; }
.titleA:hover {color: black; }
</style>

<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>

<script src="/resources/js/module/common/client.js" type="text/javascript"></script>
<script src="/resources/js/module/common/colors.js" type="text/javascript"></script>
<script src="/resources/js/module/table/mDataTable.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/basicLine.js" type="text/javascript"></script>
<script src="/resources/js/module/list/statusList.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/donut.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/tilemap.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/solidgauge.js" type="text/javascript"></script>
<script src="/resources/js/module/chart/bar.js" type="text/javascript"></script>
<script src="/resources/js/lib/billboard/d3.min.js" type="text/javascript"></script>
<script src="/resources/js/lib/billboard/billboard.min.js" type="text/javascript"></script>
<script src="https://code.highcharts.com/modules/networkgraph.js"></script>
<script type="text/javascript">
var START_TIME = "1h";
var TIME = "5s";
function timeRefresh(startTime, time) {
	START_TIME = startTime;
	TIME = time;
}
function cf (data) {
	//console.log(data);
}
function drawBar(nodes) {
	console.log(nodes);
	var barChartData = [];
	barChartData.series = {name : '', colorByPoint : true, data : []}
	nodes.items.forEach(function(node){
		barChartData.series.data.push({name : node.metadata.node_ip, y : node.status.allocatable.pods, drilldown : node.metadata.node_ip });
	})
	new Client().url("/api/v1/kubernetes/pod/snapshot").callback(function (pods){
		var podSet = {};
		pods.items.forEach(function(pod){
			if (typeof podSet[pod.status.hostIP] == "undefined") podSet[pod.status.hostIP] = [];
			podSet[pod.status.hostIP].push({podName : pod.metadata.name, podCpu : pod.resource.cpu});
		})
		barChartData.drilldown = {series : []};
		Object.keys(podSet).forEach(function (key) {
			var podData = [];
			podSet[key].forEach(function(p){
				podData.push([p.podName,p.podCpu])
			})
			barChartData.drilldown.series.push({name : key, id : key, data : podData})
		})
		
		console.log(barChartData);
		new Bar().area("barArea").data(barChartData).draw();
	}).get();
}
new Client().url("/api/v1/host/snapshot").callback(cf).get();
new Client().url("/api/v1/host/agent/status").callback(cf).get();
new Client().url("/api/v1/host/docker/snapshot").callback(cf).get();

new Client().url("/api/v1/kubernetes/cluster/snapshot").callback(cf).get();
new Client().url("/api/v1/kubernetes/pod/snapshot").callback(cf).get();
new Client().url("/api/v1/kubernetes/node/snapshot").callback(drawNode).get();

new Client().url("/api/v1/kubernetes/component/status/snapshot").callback(cf).get();

new Client().url("/api/v1/kubernetes/namespace/snapshot").callback(cf).get();
new Client().url("/api/v1/kubernetes/daemonset/snapshot").callback(cf).get();
new Client().url("/api/v1/kubernetes/deployment/snapshot").callback(cf).get();
new Client().url("/api/v1/kubernetes/replicaset/snapshot").callback(cf).get();
new Client().url("/api/v1/kubernetes/statefulset/snapshot").callback(cf).get();

new Client().url("/api/v1/kubernetes/node/devkubemaster/cpu/used?startTime=" + START_TIME + "&time=" + TIME + "&limit=1000").callback(cf).get();
new Client().url("/api/v1/kubernetes/node/devkubemaster/memory/used?startTime=" + START_TIME + "&time=" + TIME + "&limit=1000").callback(cf).get();
new Picker().refreshFunction(timeRefresh).draw();
function drawNode(nodes) {
	drawNetworkGraph(nodes);
	//drawBar(nodes);
}
function drawNetworkGraph(nodes) {
	var ngData = [];
	nodes.items.forEach(function(node){
		ngData.push(['cluster', node.metadata.node_ip]);
	})
	new Client().url("/api/v1/kubernetes/pod/snapshot").callback(function (pods){
		pods.items.forEach(function(pod){
			ngData.push([pod.status.hostIP, pod.metadata.name]);
		})
		networkGraph(ngData);
	}).get();
}
function networkGraph(data) {
	// Add the nodes option through an event call. We want to start with the parent
	// item and apply separate colors to each child element, then the same color to
	// grandchildren.
	Highcharts.addEvent(
	  Highcharts.seriesTypes.networkgraph,
	  'afterSetOptions',
	  function (e) {
	    var colors = Highcharts.getOptions().colors,
	      i = 0,
	      nodes = {};
	    e.options.data.forEach(function (link) {

	      if (link[0] === 'cluster') {
	        nodes['cluster'] = {
	          id: 'cluster',
	          marker: {
	            radius: 20
	          }
	        };
	        nodes[link[1]] = {
	          id: link[1],
	          marker: {
	            radius: 10
	          },
	          color: colors[i++]
	        };
	      } else if (nodes[link[0]] && nodes[link[0]].color) {
	        nodes[link[1]] = {
	          id: link[1],
	          color: nodes[link[0]].color
	        };
	      }
	    });

	    e.options.nodes = Object.keys(nodes).map(function (id) {
	      return nodes[id];
	    });
	  }
	);

	Highcharts.chart('container', {
	  chart: {
	    type: 'networkgraph',
	    height: 600
	  },
	  title: {
	    text: ''
	  },
	  subtitle: {
	    text: ''
	  },
	  plotOptions: {
	    networkgraph: {
	      keys: ['from', 'to'],
	      layoutAlgorithm: {
	        enableSimulation: true
	      }
	    }
	  },
	  series: [{
	    dataLabels: {
	      enabled: true
	    },
	    data: data
	  }]
	});
}
</script>