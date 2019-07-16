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
package com.nexcloud.api.service.k8s;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexcloud.mdb.client.redis.RedisClient;
import com.nexcloud.util.consts.REDIS;
@Service
public class K8sSnapShotService {
	@Autowired private RedisClient redisClient;
	
	public String getSecret(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.SECRET);
	}
	public String getEndpoint(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.ENDPOINT);
	}
	public String getContainer(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.CONTAINER);
	}
	public String getCluster(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.CLUSTER);
	}
	public String getVersion(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.VERSION);
	}
	public String getStatefulset(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.STATEFULSET);
	}
	public String getDeployment(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.DEPLOYMENT);
	}
	public String getPod(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.POD);
	}
	public String getService(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.SERVICE);
	}
	public String getDaemonset(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.DAEMONSET);
	}
	public String getNode(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.NODE);
	}
	public String getNamespace(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.NAMESPACE);
	}
	public String getReplicaset(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.REPLICASET);
	}
	public String getComponentStatus(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.COMPONENT_STATUS);
	}
	public String getevent(String clusterId) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.K8S, REDIS.KEY.EVENT);
	}
}
