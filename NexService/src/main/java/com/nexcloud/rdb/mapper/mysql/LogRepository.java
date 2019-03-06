package com.nexcloud.rdb.mapper.mysql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexcloud.rdb.domain.mysql.Log;

@Repository("LogMapper")
public interface LogRepository {
	public List<Log> getLogList();
}
