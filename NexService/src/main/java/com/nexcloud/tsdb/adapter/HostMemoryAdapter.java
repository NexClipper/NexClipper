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
public interface HostMemoryAdapter {
	public String getActualFreeMemory(String hostIp, String startTime, String time, int limit);
	
	public String getActualUsedMemory(String hostIp, String startTime, String time, int limit);
	
	public String getFreeMemory(String hostIp, String startTime, String time, int limit);
	
	public String getFreePercentMemory(String hostIp, String startTime, String time, int limit);
	
	public String getUsedMemory(String hostIp, String startTime, String time, int limit);
	
	public String getUsedPercentMemory(String hostIp, String startTime, String time, int limit);
	
	public String getTotalMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapFreeMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapFreePercentMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapUsedMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapUsedPercentMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapTotalMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapPagein(String hostIp, String startTime, String time, int limit);
	
	public String getSwapPageout(String hostIp, String startTime, String time, int limit);
}
