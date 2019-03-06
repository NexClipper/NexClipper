package com.nexcloud.rdb.mapper.mysql;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.nexcloud.rdb.domain.mysql.Rule;

@Repository("RuleMapper")
public interface RuleRepository {
	public List<Rule> getRuleList();
}
