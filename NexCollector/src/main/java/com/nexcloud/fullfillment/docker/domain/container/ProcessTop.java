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
package com.nexcloud.fullfillment.docker.domain.container;
import java.util.ArrayList;
import java.util.List;

public class ProcessTop {
	private List<List<String>> Processes;
	private List<String> Titles;
	
	public List<List<String>> getProcesses() {
		if( Processes == null )
			Processes = new ArrayList<List<String>>();
		return Processes;
	}
	
	public void setProcesses(List<List<String>> processes) {
		Processes = processes;
	}
	
	public List<String> getTitles() {
		if( Titles == null )
			Titles = new ArrayList<String>();
		return Titles;
	}
	
	public void setTitles(List<String> titles) {
		Titles = titles;
	}
}