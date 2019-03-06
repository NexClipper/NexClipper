package com.nexcloud.docker.image.list.domain;

import java.util.List;

public class ImageList {
	private Integer Containers;
	private Long Created;
	private String Id;
	private String ParentID;
	private List<String> RepoDigests;
	private List<String> RepoTags;
	private Integer SharedSize;
	private Long Size;
	private Long VirtualSize;

	public Integer getContainers() {
		return Containers;
	}

	public void setContainers(Integer containers) {
		Containers = containers;
	}

	public Long getCreated() {
		return Created;
	}

	public void setCreated(Long created) {
		Created = created;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getParentID() {
		return ParentID;
	}

	public void setParentID(String parentID) {
		ParentID = parentID;
	}

	public List<String> getRepoDigests() {
		return RepoDigests;
	}

	public void setRepoDigests(List<String> repoDigests) {
		RepoDigests = repoDigests;
	}

	public List<String> getRepoTags() {
		return RepoTags;
	}

	public void setRepoTags(List<String> repoTags) {
		RepoTags = repoTags;
	}

	public Integer getSharedSize() {
		return SharedSize;
	}

	public void setSharedSize(Integer sharedSize) {
		SharedSize = sharedSize;
	}

	public Long getSize() {
		return Size;
	}

	public void setSize(Long size) {
		Size = size;
	}

	public Long getVirtualSize() {
		return VirtualSize;
	}

	public void setVirtualSize(Long virtualSize) {
		VirtualSize = virtualSize;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Image List *****\n");
		sb.append("Containers   = " + getContainers() + "\n");
		sb.append("Created      = " + getCreated() + "\n");
		sb.append("Id           = " + getId() + "\n");
		sb.append("ParentID     = " + getParentID() + "\n");
		sb.append("PrepoDigests = " + getRepoDigests() + "\n");
		sb.append("RepoTags     = " + getRepoTags() + "\n");
		sb.append("SharedSize   = " + getSharedSize() + "\n");
		sb.append("Size         = " + getSize() + "\n");
		sb.append("VirtualSzie  = " + getVirtualSize() + "\n");
		return sb.toString();
	}
}
