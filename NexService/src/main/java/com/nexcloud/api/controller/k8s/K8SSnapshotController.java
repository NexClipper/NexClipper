/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.nexcloud.api.controller.k8s;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.service.k8s.K8sSnapShotService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/cluster/{clusterId}/kubernetes")
public class K8SSnapshotController {
	static final Logger logger = LoggerFactory.getLogger(K8SSnapshotController.class);
	@Autowired private K8sSnapShotService k8sSnapShotService;
	
	@ApiOperation(value="Pod Snapshot", httpMethod="GET", notes="Pod Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/pod/snapshot", method=RequestMethod.GET)
	public String getPod(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getPod(clusterId);
	}
	
	@ApiOperation(value="Secret Snapshot", httpMethod="GET", notes="Secret Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/secret/snapshot", method=RequestMethod.GET)
	public String getSecret(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getSecret(clusterId);
	}

	@ApiOperation(value="Endpoint Snapshot", httpMethod="GET", notes="Endpoint Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/endpoint/snapshot", method=RequestMethod.GET)
	public String getEndpoint(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getEndpoint(clusterId);
	}

	@ApiOperation(value="Container Snapshot", httpMethod="GET", notes="Container Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/container/snapshot", method=RequestMethod.GET)
	public String getContainer(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getContainer(clusterId);
	}

	@ApiOperation(value="Cluster Snapshot", httpMethod="GET", notes="Cluster Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/cluster/snapshot", method=RequestMethod.GET)
	public String getCluster(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getCluster(clusterId);
	}

	@ApiOperation(value="Version Snapshot", httpMethod="GET", notes="Version Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/version/snapshot", method=RequestMethod.GET)
	public String getVersion(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getVersion(clusterId);
	}

	@ApiOperation(value="Statefulset Snapshot", httpMethod="GET", notes="Statefulset Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/statefulset/snapshot", method=RequestMethod.GET)
	public String getStatefulset(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getStatefulset(clusterId);
	}

	@ApiOperation(value="Deployment Snapshot", httpMethod="GET", notes="Deployment Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/deployment/snapshot", method=RequestMethod.GET)
	public String getDeployment(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getDeployment(clusterId);
	}

	@ApiOperation(value="Service Snapshot", httpMethod="GET", notes="Service Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/service/snapshot", method=RequestMethod.GET)
	public String getService(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getService(clusterId);
	}

	@ApiOperation(value="Daemonset Snapshot", httpMethod="GET", notes="Daemonset Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/daemonset/snapshot", method=RequestMethod.GET)
	public String getDaemonset(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getDaemonset(clusterId);
	}

	@ApiOperation(value="Node Snapshot", httpMethod="GET", notes="Node Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/node/snapshot", method=RequestMethod.GET)
	public String getNode(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getNode(clusterId);
	}

	@ApiOperation(value="Namespace Snapshot", httpMethod="GET", notes="Namespace Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/namespace/snapshot", method=RequestMethod.GET)
	public String getNamespace(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getNamespace(clusterId);
	}

	@ApiOperation(value="Replicaset Snapshot", httpMethod="GET", notes="Replicaset Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/replicaset/snapshot", method=RequestMethod.GET)
	public String getReplicaset(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getReplicaset(clusterId);
	}
	@ApiOperation(value="Component Status Snapshot", httpMethod="GET", notes="Component Status Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/component/status/snapshot", method=RequestMethod.GET)
	public String getComponentStatus(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getComponentStatus(clusterId);
	}
	@ApiOperation(value="Event Snapshot", httpMethod="GET", notes="Event Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/event/snapshot", method=RequestMethod.GET)
	public String getevent(@PathVariable(value="clusterId", required=false) String clusterId) {
		return k8sSnapShotService.getevent(clusterId);
	}
	
}
