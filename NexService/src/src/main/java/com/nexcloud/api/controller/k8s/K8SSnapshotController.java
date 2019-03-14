package com.nexcloud.api.controller.k8s;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.service.k8s.K8sSnapShotService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/kubernetes")
public class K8SSnapshotController {
	static final Logger logger = LoggerFactory.getLogger(K8SSnapshotController.class);
	@Autowired private K8sSnapShotService k8sSnapShotService;
	
	@ApiOperation(value="Pod Snapshot", httpMethod="GET", notes="Pod Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/pod/snapshot", method=RequestMethod.GET)
	public String getPod() {
		return k8sSnapShotService.getPod();
	}
	
	@ApiOperation(value="Secret Snapshot", httpMethod="GET", notes="Secret Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/secret/snapshot", method=RequestMethod.GET)
	public String getSecret() {
		return k8sSnapShotService.getSecret();
	}

	@ApiOperation(value="Endpoint Snapshot", httpMethod="GET", notes="Endpoint Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/endpoint/snapshot", method=RequestMethod.GET)
	public String getEndpoint() {
		return k8sSnapShotService.getEndpoint();
	}

	@ApiOperation(value="Container Snapshot", httpMethod="GET", notes="Container Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/container/snapshot", method=RequestMethod.GET)
	public String getContainer() {
		return k8sSnapShotService.getContainer();
	}

	@ApiOperation(value="Cluster Snapshot", httpMethod="GET", notes="Cluster Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/cluster/snapshot", method=RequestMethod.GET)
	public String getCluster() {
		return k8sSnapShotService.getCluster();
	}

	@ApiOperation(value="Version Snapshot", httpMethod="GET", notes="Version Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/version/snapshot", method=RequestMethod.GET)
	public String getVersion() {
		return k8sSnapShotService.getVersion();
	}

	@ApiOperation(value="Statefulset Snapshot", httpMethod="GET", notes="Statefulset Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/statefulset/snapshot", method=RequestMethod.GET)
	public String getStatefulset() {
		return k8sSnapShotService.getStatefulset();
	}

	@ApiOperation(value="Deployment Snapshot", httpMethod="GET", notes="Deployment Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/deployment/snapshot", method=RequestMethod.GET)
	public String getDeployment() {
		return k8sSnapShotService.getDeployment();
	}

	@ApiOperation(value="Service Snapshot", httpMethod="GET", notes="Service Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/service/snapshot", method=RequestMethod.GET)
	public String getService() {
		return k8sSnapShotService.getService();
	}

	@ApiOperation(value="Daemonset Snapshot", httpMethod="GET", notes="Daemonset Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/daemonset/snapshot", method=RequestMethod.GET)
	public String getDaemonset() {
		return k8sSnapShotService.getDaemonset();
	}

	@ApiOperation(value="Node Snapshot", httpMethod="GET", notes="Node Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/node/snapshot", method=RequestMethod.GET)
	public String getNode() {
		return k8sSnapShotService.getNode();
	}

	@ApiOperation(value="Namespace Snapshot", httpMethod="GET", notes="Namespace Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/namespace/snapshot", method=RequestMethod.GET)
	public String getNamespace() {
		return k8sSnapShotService.getNamespace();
	}

	@ApiOperation(value="Replicaset Snapshot", httpMethod="GET", notes="Replicaset Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/replicaset/snapshot", method=RequestMethod.GET)
	public String getReplicaset() {
		return k8sSnapShotService.getReplicaset();
	}
	@ApiOperation(value="Replicaset Snapshot", httpMethod="GET", notes="Replicaset Snapshot")
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/component/status/snapshot", method=RequestMethod.GET)
	public String getComponentStatus() {
		return k8sSnapShotService.getComponentStatus();
	}
	
}
