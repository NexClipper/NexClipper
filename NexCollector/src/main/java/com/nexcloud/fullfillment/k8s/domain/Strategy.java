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
public class Strategy {
	private String type;
	
	private Strategy rollingUpdate;
	
	private String maxUnavailable;
	
	private String maxSurge;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Strategy getRollingUpdate() {
		if( rollingUpdate == null )
			rollingUpdate = new Strategy();
		return rollingUpdate;
	}

	public void setRollingUpdate(Strategy rollingUpdate) {
		this.rollingUpdate = rollingUpdate;
	}

	public String getMaxUnavailable() {
		return maxUnavailable;
	}

	public void setMaxUnavailable(String maxUnavailable) {
		this.maxUnavailable = maxUnavailable;
	}

	public String getMaxSurge() {
		return maxSurge;
	}

	public void setMaxSurge(String maxSurge) {
		this.maxSurge = maxSurge;
	}
}
