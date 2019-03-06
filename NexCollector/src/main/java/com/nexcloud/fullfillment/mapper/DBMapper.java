package com.nexcloud.fullfillment.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("dbMapper")
public interface DBMapper {	
	public int insertLog( Map<String, Object> map ) throws Exception;
}
