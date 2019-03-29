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
package com.nexcloud.fullfillment.k8s.domain;
public class Version {
	private String major;
	
	private String minor;
	
	private String gitVersion;
	
	private String gitCommit;
	
	private String gitTreeState;
	
	private String buildDate;
	
	private String goVersion;
	
	private String compiler;
	
	private String platform;

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public String getGitVersion() {
		return gitVersion;
	}

	public void setGitVersion(String gitVersion) {
		this.gitVersion = gitVersion;
	}

	public String getGitCommit() {
		return gitCommit;
	}

	public void setGitCommit(String gitCommit) {
		this.gitCommit = gitCommit;
	}

	public String getGitTreeState() {
		return gitTreeState;
	}

	public void setGitTreeState(String gitTreeState) {
		this.gitTreeState = gitTreeState;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}

	public String getGoVersion() {
		return goVersion;
	}

	public void setGoVersion(String goVersion) {
		this.goVersion = goVersion;
	}

	public String getCompiler() {
		return compiler;
	}

	public void setCompiler(String compiler) {
		this.compiler = compiler;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
