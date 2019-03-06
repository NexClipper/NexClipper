package com.nexcloud.api.service.log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexcloud.rdb.mapper.mysql.LogRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class LogService{
	@Autowired private LogRepository logRepository;
	
	public String getLogList(){
		return Mysql.resultToResponse(logRepository.getLogList());
	}
}
