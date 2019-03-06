package com.nexcloud.workflow.mesos.domain.task;

import java.util.ArrayList;
import java.util.List;

public class MesosTasks {

	List<MesosTask> list;

	public List<MesosTask> getList() {
		if( list == null )
			list = new ArrayList<MesosTask>();
		return list;
	}

	public void setList(List<MesosTask> list) {
		this.list = list;
	}
	
	
}
