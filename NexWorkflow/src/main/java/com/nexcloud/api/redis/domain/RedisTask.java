package com.nexcloud.api.redis.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.nexcloud.workflow.mesos.domain.task.MesosTask;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedisTask {
	private List<MesosTask> tasks;

	private MesosTask task;

	public List<MesosTask> getTasks() {
		if( tasks == null )
			tasks = new ArrayList<MesosTask>();
		return tasks;
	}

	public void setTasks(List<MesosTask> tasks) {
		this.tasks = tasks;
	}

	public MesosTask getTask() {
		if( task == null )
			task = new MesosTask();
		return task;
	}

	public void setTask(MesosTask task) {
		this.task = task;
	}
}
