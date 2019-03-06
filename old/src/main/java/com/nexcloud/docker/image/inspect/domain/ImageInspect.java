package com.nexcloud.docker.image.inspect.domain;

import java.util.ArrayList;

public class ImageInspect {
	private String Id;
	private ArrayList<Object> RepoTags = new ArrayList<Object>();
	private ArrayList<Object> RepoDigests = new ArrayList<Object>();
	private String Created;
	private String Container;
	private ContainerConfig ContainerConfig;
	private String DockerVersion;
	private String Author;
	private String Architecture;
	private String Os;
	private Long Size;
	private Long VirtualSize;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public ArrayList<Object> getRepoTags() {
		return RepoTags;
	}

	public void setRepoTags(ArrayList<Object> repoTags) {
		RepoTags = repoTags;
	}

	public ArrayList<Object> getRepoDigests() {
		return RepoDigests;
	}

	public void setRepoDigests(ArrayList<Object> repoDigests) {
		RepoDigests = repoDigests;
	}

	public String getCreated() {
		return Created;
	}

	public void setCreated(String created) {
		Created = created;
	}

	public String getContainer() {
		return Container;
	}

	public void setContainer(String container) {
		Container = container;
	}

	public ContainerConfig getContainerConfig() {
		return ContainerConfig;
	}

	public void setContainerConfig(ContainerConfig containerConfig) {
		ContainerConfig = containerConfig;
	}

	public String getDockerVersion() {
		return DockerVersion;
	}

	public void setDockerVersion(String dockerVersion) {
		DockerVersion = dockerVersion;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getArchitecture() {
		return Architecture;
	}

	public void setArchitecture(String architecture) {
		Architecture = architecture;
	}

	public String getOs() {
		return Os;
	}

	public void setOs(String os) {
		Os = os;
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
		sb.append("***** Image Inspect *****\n");
		sb.append("Id              = " + getId() + "\n");
		sb.append("RepoTags        = " + getRepoTags() + "\n");
		sb.append("RepoDigests     = " + getRepoDigests() + "\n");
		sb.append("Created         = " + getCreated() + "\n");
		sb.append("Container       = " + getContainer() + "\n");
		sb.append("ContainerConfig = " + getContainerConfig() + "\n");
		sb.append("DockerVersion   = " + getDockerVersion() + "\n");
		sb.append("Author          = " + getAuthor() + "\n");
		sb.append("Architecture    = " + getArchitecture() + "\n");
		sb.append("Os              = " + getOs() + "\n");
		sb.append("Size            = " + getSize() + "\n");
		sb.append("VirtualSize     = " +getVirtualSize () + "\n");

		return sb.toString();
	}
}
