package com.nexcloud.api.service.log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexcloud.rdb.mapper.mysql.LogRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class LogService{
	@Autowired private LogRepository logRepository;
	@Autowired private Mysql mysql;
	public String getLogList(){
		return mysql.resultToResponse(logRepository.getLogList());
	}
}
