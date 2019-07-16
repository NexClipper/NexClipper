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
package com.nexcloud.api.service.cluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexcloud.rdb.domain.mysql.Cluster;
import com.nexcloud.rdb.mapper.mysql.ClusterRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class ClusterService{
	@Autowired private ClusterRepository clusterRepository;
	@Autowired private Mysql mysql;

	public boolean addCluster(Cluster cluster){
		return clusterRepository.addCluster(cluster);
	}
	public String getCluster(){
		return mysql.resultToResponse(clusterRepository.getCluster());
	}
	public boolean updateCluster(Cluster cluster) {
		return clusterRepository.updateCluster(cluster);
	}
	public boolean deleteCluster(int clusterId) {
		return clusterRepository.deleteCluster(clusterId);
	}
}
