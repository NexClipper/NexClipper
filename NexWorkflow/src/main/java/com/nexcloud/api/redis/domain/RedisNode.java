package com.nexcloud.api.redis.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.nexcloud.workflow.mesos.domain.node.MesosNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedisNode {
	private List<MesosNode> nodes;
	
	private MesosNode node;

	public List<MesosNode> getNodes() {
		if( nodes == null )
			nodes = new ArrayList<MesosNode>();
		return nodes;
	}

	public void setNodes(List<MesosNode> nodes) {
		this.nodes = nodes;
	}

	public MesosNode getNode() {
		if( node == null )
			node = new MesosNode();
		return node;
	}

	public void setNode(MesosNode node) {
		this.node = node;
	}
}
