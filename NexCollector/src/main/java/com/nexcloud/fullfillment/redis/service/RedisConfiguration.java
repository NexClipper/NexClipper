package com.nexcloud.fullfillment.redis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@PropertySource("classpath:application.properties")
public class RedisConfiguration {

	@Value("${spring.redis.host}")
	private String redisHostName;
	
	@Value("${spring.redis.port}")
	private int redisPort;
	
	@Value("${spring.redis.password}")
	private String redisPassword;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(redisHostName);
		factory.setPort(redisPort);
		factory.setPassword(redisPassword);
		factory.setUsePool(true);
		factory.afterPropertiesSet();
		return factory;
	}
	
	@Bean
	 public StringRedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		 StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		 stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
		 return stringRedisTemplate;
	 }
}