package com.nexcloud.util;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication
@MapperScan(value = {"com.nexcloud.rdb.mapper.mysql"})
public class MapperSessionFactory {
	
    /**
     * SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //sessionFactory.setTypeAliasesPackage("com.nexcloud.rdb.domain.mysql");
        Resource[] res = new PathMatchingResourcePatternResolver()
        		.getResources("classpath:mapper-xml/mysql/*Mapper.xml");
        
        sessionFactory.setMapperLocations(res);
        
        return sessionFactory.getObject();
    }
}
