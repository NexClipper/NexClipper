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
package com.nexcloud.api.controller.cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nexcloud.api.service.cluster.ClusterService;
import com.nexcloud.rdb.domain.mysql.Cluster;
@RestController
@RequestMapping(value = "/api/v1/cluster")
public class ClusterController {
	static final Logger logger = LoggerFactory.getLogger(ClusterController.class);
	
	@Autowired private ClusterService clusterService;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String addCluster(
			@RequestParam(value="clusterName") String clusterName, 
			@RequestParam(value="description") String description) {
		boolean result = false;
		result = clusterService.addCluster(new Cluster(0, clusterName, description,  'Y'));
		if (result) {
			return "success";
		} else {
			return "fail";
		}
	}
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getCluster() {
		return clusterService.getCluster();
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteCluster(
			@RequestParam(value="clusterId") int clusterId) {
		if (clusterService.deleteCluster(clusterId))
			return "success";
		else 
			return "fail";
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateCluster(
			@RequestParam(value="clusterId") int clusterId,
			@RequestParam(value="clusterName") String clusterName, 
			@RequestParam(value="description") String description) {
		if (clusterService.updateCluster(new Cluster(clusterId, description, clusterName, 'Y')))
			return "success";
		else 
			return "fail";
	}
}
