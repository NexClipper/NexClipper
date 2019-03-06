package com.nexcloud.rdb.mapper.mysql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexcloud.rdb.domain.mysql.Command;

@Repository("CommandMapper")
public interface CommandRepository {
	public List<Command> getCommandList();
}
