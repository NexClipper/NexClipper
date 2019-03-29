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
package com.nexcloud.tsdb.adapter;
public interface K8sPodAdapter {
	public String getCpuUsed(String startTime, String time, int limit);
	public String getCpuUsed(String pod, String startTime, String time, int limit);
	
	public String getCpuUsedPercent(String startTime, String time, int limit);
	public String getCpuUsedPercent(String pod, String startTime, String time, int limit);
	
	public String getCpuLimit(String startTime, String time, int limit);
	public String getCpuLimit(String pod, String startTime, String time, int limit);
	
	public String getCpuRequest(String startTime, String time, int limit);
	public String getCpuRequest(String pod, String startTime, String time, int limit);
	
	
	public String getMemoryUsed(String startTime, String time, int limit);
	public String getMemoryUsed(String pod, String startTime, String time, int limit);
	
	public String getMemoryUsedPercent(String startTime, String time, int limit);
	public String getMemoryUsedPercent(String pod, String startTime, String time, int limit);
	
	public String getMemoryLimit(String startTime, String time, int limit);
	public String getMemoryLimit(String pod, String startTime, String time, int limit);
	
	public String getMemoryRequest(String startTime, String time, int limit);
	public String getMemoryRequest(String pod, String startTime, String time, int limit);
}
