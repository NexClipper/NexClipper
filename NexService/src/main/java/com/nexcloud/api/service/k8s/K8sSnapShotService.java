package com.nexcloud.api.service.k8s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexcloud.mdb.client.redis.RedisClient;
import com.nexcloud.util.consts.REDIS;
@Service
public class K8sSnapShotService {
	@Autowired private RedisClient redisClient;
	
	public String getSecret() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.SECRET);
	}
	public String getEndpoint() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.ENDPOINT);
	}
	public String getContainer() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.CONTAINER);
	}
	public String getCluster() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.CLUSTER);
	}
	public String getVersion() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.VERSION);
	}
	public String getStatefulset() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.STATEFULSET);
	}
	public String getDeployment() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.DEPLOYMENT);
	}
	public String getPod() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.POD);
	}
	public String getService() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.SERVICE);
	}
	public String getDaemonset() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.DAEMONSET);
	}
	public String getNode() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.NODE);
	}
	public String getNamespace() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.NAMESPACE);
	}
	public String getReplicaset() {
		return redisClient.get(REDIS.HASH.K8S, REDIS.KEY.REPLICASET);
	}
}
