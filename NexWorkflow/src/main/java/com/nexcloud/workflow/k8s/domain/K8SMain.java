package com.nexcloud.workflow.k8s.domain;

import java.util.ArrayList;
import java.util.List;

public class K8SMain {
	private String kind;
	
	private String apiVersion;
	
	private Metadata metadata;
	
	private List<Item> items;
	
	private Item item;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public Metadata getMetadata() {
		if( metadata == null )
			metadata = new Metadata();
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<Item> getItems() {
		if( items == null )
			items = new ArrayList<Item>();
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item getItem() {
		if( item == null )
			item = new Item();
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
