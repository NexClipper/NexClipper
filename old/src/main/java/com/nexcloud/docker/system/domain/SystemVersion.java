package com.nexcloud.docker.system.domain;

public class SystemVersion {
	private String Version;
	private String ApiVersion;
	private String MinAPIVersion;
	private String GitCommit;
	private String GoVersion;
	private String Os;
	private String Arch;
	private String KernelVersion;
	private String BuildTime;

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getApiVersion() {
		return ApiVersion;
	}

	public void setApiVersion(String apiVersion) {
		ApiVersion = apiVersion;
	}

	public String getMinAPIVersion() {
		return MinAPIVersion;
	}

	public void setMinAPIVersion(String minAPIVersion) {
		MinAPIVersion = minAPIVersion;
	}

	public String getGitCommit() {
		return GitCommit;
	}

	public void setGitCommit(String gitCommit) {
		GitCommit = gitCommit;
	}

	public String getGoVersion() {
		return GoVersion;
	}

	public void setGoVersion(String goVersion) {
		GoVersion = goVersion;
	}

	public String getOs() {
		return Os;
	}

	public void setOs(String os) {
		Os = os;
	}

	public String getArch() {
		return Arch;
	}

	public void setArch(String arch) {
		Arch = arch;
	}

	public String getKernelVersion() {
		return KernelVersion;
	}

	public void setKernelVersion(String kernelVersion) {
		KernelVersion = kernelVersion;
	}

	public String getBuildTime() {
		return BuildTime;
	}

	public void setBuildTime(String buildTime) {
		BuildTime = buildTime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** SystemInfo *****\n");
		sb.append("getVersion       = " + getVersion() + "\n");
		sb.append("getApiVersion    = " + getApiVersion() + "\n");
		sb.append("getMinAPIVersion = " + getMinAPIVersion() + "\n");
		sb.append("getGitCommit     = " + getGitCommit() + "\n");
		sb.append("getGoVersion     = " + getGoVersion() + "\n");
		sb.append("getOs            = " + getOs() + "\n");
		sb.append("getArch          = " + getArch() + "\n");
		sb.append("getKernelVersion = " + getKernelVersion() + "\n");
		sb.append("getBuildTime     = " + getBuildTime() + "\n");
		return sb.toString();
	}
}
