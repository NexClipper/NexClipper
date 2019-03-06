package com.nexcloud.api.akka.actor;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexcloud.agent.domain.AgentStatus;
import com.nexcloud.agent.domain.Header;
import com.nexcloud.agent.domain.ResponseData;
import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.redis.RedisCluster;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;
import com.nexcloud.workflow.docker.domain.Containers;
import com.nexcloud.workflow.docker.domain.Docker;

import akka.actor.UntypedActor;

@Component
@Scope("prototype")
public class JsonDockerParserActor extends UntypedActor{

	static final Logger 	logger 				= LoggerFactory.getLogger(JsonDockerParserActor.class);
	
	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	public void onReceive(Object message){
		// TODO Auto-generated method stub
		SendData sendData						= null; 
		RedisCluster redisCluster				= null;
		String msg								= null;
		ConsumerRecords<String, String> records 			= null;
		Gson gson 								= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        List<String> ips						= null; 
		try{
			/**
	         * Kafka receive data
	         */
	        sendData							= (SendData)message;
	        
	        // Redis Cluster connection
	        redisCluster						= RedisCluster.getInstance(sendData.getRedis_host(), Integer.parseInt(sendData.getRedis_port()));

	        
	        /**
	         * Agent metric data
	         */
	        ResponseData resData				= null;
	        Header header						= null;
	        String body							= null;
	        
	        
	        records								= sendData.getRecords();
	        msg									= "";
	        
			Docker docker						= null; 
			
			String dataStatus					= null;
			AgentStatus status					= null;
			
	        for (ConsumerRecord<String, String> record : records)
	        {
    			if( record.value() == null || "".equals(record.value())) continue;
    			
    			resData							= Util.JsonTobean(record.value(), ResponseData.class);
    			
		        header							= resData.getHeader();
				body							= resData.getBody();
				body							= new String(Util.decompress(Util.hexStringToByteArray(body)));
				
				docker							= Util.JsonTobean(body, Docker.class);
				
				// Host의 Docker info
				if( docker != null && docker.getDocker_info() != null )
				{
					if( docker.getDocker_info().getContainersRunning() == null )
					{
						docker.getDocker_info().setContainersRunning(docker.getDocker_info().getContainers());
						docker.getDocker_info().setContainersPaused(0);
						docker.getDocker_info().setContainersStopped(0);
					}
					// Docker container info
					for( Containers container : docker.getContainers() )
					{
						container.setCommand((container.getCommand().replaceAll("\\\\","")).replaceAll("\"",""));
						container.setCommand((container.getCommand().replaceAll("\n","")).replaceAll("\r",""));
					}
				}
				
				// Redis Save
				// data size가 너무 커  필요 없다고 생각되는 데이터 삭제
				for( Containers container : docker.getContainers() )
				{
					container.setProcess(null);
					container.setEnv(null);
					container.setVolume(null);
					container.setCommand((container.getCommand().replaceAll("\\\\","")).replaceAll("\"",""));
					container.setCommand((container.getCommand().replaceAll("\n","")).replaceAll("\r",""));
				}
				
				
				// Host Mpa Redis In/Out
				String data							= redisCluster.get(Const.HOST, Const.LIST);
				ips					  				= gson.fromJson(data, new TypeToken<List<String>>(){}.getType());
				if( ips == null )
					ips								= new ArrayList<String>();
				
				for( String ip : ips )
				{
					// Inactive상태인 Redis데이터 삭제
					dataStatus						= null;
					status							= null;
					
					dataStatus						= redisCluster.get(Const.AGENT_STATUS, ip );
		            
		            if( dataStatus == null )  continue;
		            
					status							= Util.JsonTobean(dataStatus, AgentStatus.class);
					
					if( !Const.INACTIVE.equals(status.getStatus()) ) continue;
					
					redisCluster.remove(Const.DOCKER, ip);
				}
	        }
			
			// End of First Time Check
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(Util.makeStackTrace(e));
		}
	}
}