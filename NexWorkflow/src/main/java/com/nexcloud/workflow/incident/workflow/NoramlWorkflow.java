package com.nexcloud.workflow.incident.workflow;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexcloud.api.influxdb.domain.Influxdb;
import com.nexcloud.api.influxdb.domain.Result;
import com.nexcloud.api.influxdb.domain.Series;
import com.nexcloud.db.domain.Rule;
import com.nexcloud.rule.RuleMap;
import com.nexcloud.rule.domain.Notification;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;
import com.nexcloud.util.rest.RestAPI;
import com.nexcloud.util.rest.RestResponse;
import com.nexcloud.workflow.host.domain.Host;
import com.nexcloud.workflow.host.domain.HostMap;
import com.nexcloud.workflow.incident.domain.Assurance;
import com.nexcloud.workflow.redis.service.RedisService;

@Configuration
@PropertySource("classpath:application.properties")
public class NoramlWorkflow extends IncidentWorkflow implements Runnable {

	static final Logger logger 				= LoggerFactory.getLogger(NoramlWorkflow.class);

    private RedisService redisService;
    
    private String influxdb_endpoint;
    
    private String kafka_host;
    
    private String kafka_port;
	
	private static NoramlWorkflow thisObj 	= null;
	
	private boolean isProcessing			= false;
	
	private Thread thread					= null;
	
	public NoramlWorkflow( )
	{
		
	}
	
	/**
	 * Get Instance 
	 * @return
	 */
	public synchronized static NoramlWorkflow getInstance(){
		if ( thisObj == null ){
			//logger.debug("CustomWorkflow getInstance 실행!!!!!!!!!!!!!!!");
			try {
				thisObj = new NoramlWorkflow();
			}catch(IndexOutOfBoundsException e){
				logger.error("CustomWorkflow Class getInstance IndexOutOfBoundsException Error = " +e);
			}catch(NullPointerException  e){
				logger.error("CustomWorkflow Class getInstance NullPointerException Error = " +e);
			} catch(Exception e) {
				logger.error("CustomWorkflow Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj; 		
	}
	
	
	public void goRunning( String influxdb_endpoint, String kafka_host, String kafka_port, RedisService redisService )
	{
		this.influxdb_endpoint	= influxdb_endpoint;
		this.kafka_host			= kafka_host;
		this.kafka_port			= kafka_port;
		this.redisService 		= redisService;
		thisObj.setProcessing(true);
		
		/*
		Thread thread			= new Thread( thisObj );
		thread.start();
		*/
		
		Runnable r 				= thisObj;
		thread					= null;
		thread					= new Thread( r );
		thread.start();
	}
	
	public  Thread.State getState()
	{
		return thread.getState();
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try {
				process();
				Thread.sleep(300); // 1초마다 Looping
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				try {
					Thread.sleep(1000); // 1초마다 Looping
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		}
	}
	
	/**
	 * Assurance Worklfow execute
	 */
	public void process(  )
	{
		String data			= null;
		String query		= null;
		try{
			//logger.error("Process Start>>");
			// Redis에 있는 rule에의해 체크된 정보 조회
			data									= redisService.get(Const.INCIDENT, Const.ALL_INCIDENT);
			RuleMap ruleMap							= Util.JsonTobean(data, RuleMap.class);
			if( ruleMap == null )
				ruleMap								= new RuleMap();
			
			Map<String, Notification> notiMap		= ruleMap.getNotification();
			

			data									= redisService.get(Const.INCIDENT, Const.ASSURANCE_NORMAL);
			//System.out.println("data::::::::::"+data);
			
			Assurance assurance						= Util.JsonTobean(data, Assurance.class);
			
			Map<String, Double> nodeCPUMap			= assurance.getNodeCPU();
			Map<String, String> map					= assurance.getTaskIP();
			Map<String, String> nodeMap				= assurance.getNodeIP();
			List<Rule> rules						= assurance.getRules();
			
			String hostData							= null;
			HostMap hostMap							= null;
			Map<String, Host> hMap					= null;
			
			String current_time						= Util.getTime();
			
			Gson gson 								= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
			List<String> ips					  	= null; 
			
			// 룰정보 파싱
			for( Rule rule : rules )
			{
				current_time						= Util.getTime();
				
				String target_system				= rule.getTarget_system();
				String target						= rule.getTarget();
				String type							= rule.getType().toLowerCase();
				String datasource					= rule.getData_source().toLowerCase();
				String []tables						= Util.split(rule.getTable().toLowerCase(), Const.TABLE_TOKEN);
				String metric						= rule.getMetric().toLowerCase();
				String []groupBys					= Util.split(Util.nullToEmpty(rule.getGroup_by().toLowerCase()), Const.COLUMN_TOKEN);
				String condition					= rule.getCondition().toLowerCase();
				String message						= rule.getMessage();
				String math							= rule.getMath();
				String new_engine					= rule.getNew_engine();
				String notify						= rule.getNotify();
				String slack_token					= rule.getSlack_token();
				String slack_channel				= rule.getSlack_channel();
				String severity						= rule.getSeverity();
			
				// Rule Type이 Metric ( InfluxDB )일경우
				if("metric".equals(type))
				{
					// Metric용 테이블이 여러개 일경우
					for( String table : tables )
					{
						String column				= "";
						String selector				= "mean";
						String where				= "";
						String groupBy				= "";
						String []buffer				= null;
						double load_condition		= 0d;
						
						// Metric Column
						
						if( metric.indexOf("+") != -1 )
							selector 			= "";

						
						if ( math == null || "".equals(math) )
						{
							if( !"".equals(selector) )
							{
								if( "".equals(column) )
									column				= selector+"("+"\""+metric+"\")";
								else
									column				+= ","+selector+"("+"\""+metric+"\")";
							}
							else
							{
								if( "".equals(column) )
									column				= metric;
								else
									column				+= ","+metric;
							}
						}
						// 수학 계산이 있는경우
						else
						{

						}
						
						// 조건절
						// mean Method를 사용하는 일반 조건절
						if( metric.indexOf("/") == -1 && (math == null || "".equals(math)))
						{
							if( condition.indexOf("and") != -1 )
							{
								buffer					= Util.split(condition, Const.AND_TOKEN);
								for( String dummy : buffer )
								{
									if( dummy.indexOf("m") != -1 ) continue;
									
									where 			+= " AND " + metric + " " + dummy;
									
									if( metric.indexOf("load") != -1 )
									{
										String[]arrStr 	= Util.split(dummy.trim(), "><=");
										load_condition = Double.parseDouble(arrStr[0].trim());
									}
								}
							}
							else if( condition.indexOf("or") != -1 )
							{
								buffer					= Util.split(condition, Const.OR_TOKEN);
								
								for( String dummy : buffer )
								{
									if( dummy.indexOf("m") != -1 ) continue;
									
									where 			+= " OR " + metric + " " + dummy;
									
									if( metric.indexOf("load") != -1 )
									{
										String[]arrStr 	= Util.split(dummy.trim(), "><=");
										load_condition = Double.parseDouble(arrStr[0].trim());
									}
								}
							}
							else
							{
								where 				= " AND " + metric + " " + condition;
								
								if( metric.indexOf("load") != -1 )
								{
									String[]arrStr 	= Util.split(condition.trim(), "><=");
									load_condition = Double.parseDouble(arrStr[0].trim());
								}
							}
						}
						// 
						else if((math == null || "".equals(math)))
						{
							if( condition.indexOf("and") != -1 )
							{
								buffer					= Util.split(condition, Const.AND_TOKEN);
								for( String dummy : buffer )
								{
									if( dummy.indexOf("m") != -1 ) continue;
									
									where 			+= " AND "+"\""+ metric + "\" " + dummy;
								}
							}
							else if( condition.indexOf("or") != -1 )
							{
								buffer					= Util.split(condition, Const.OR_TOKEN);
								
								for( String dummy : buffer )
								{
									if( dummy.indexOf("m") != -1 ) continue;
									
									where 			+= " OR "+"\""+ metric + "\" " + dummy;
								}
							}
							else
							{
								where 				= " AND "+"\""+ metric + "\" " + condition;
							}
						}
						
						
						// Group By 절
						for( String dummy : groupBys )
						{
							// Group By절이 있으경우
							if( !"".equals(dummy.trim()) )
							{
								if( "".equals(groupBy) )
									groupBy 			= ""+dummy.trim()+"";
								else
									groupBy 			+= ","+""+dummy.trim()+"";
							}
						}

						if( !"".equals(groupBy) )
							query 						= "SELECT "+column +" FROM "+table+" WHERE time > now() - 10s "+where+" GROUP BY "+groupBy+" fill(null) order by time desc limit 1";
						else
							query 						= "SELECT "+column +" FROM "+table+" WHERE time > now() - 10s "+where+" GROUP BY fill(null) order by time desc limit 1";
						
						query							= URLEncoder.encode(query);
						
						String 					url		= influxdb_endpoint + "/query?q="+query+"&db="+datasource;
						RestResponse<Result> 	response= RestAPI.getInstance( ).get(null, url, Result.class);
						Result 					result	= response.getResponse();
						
						String validIp = "^([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}$";
						
						// influx조회후 Memory에 있는 Event Map에 신규로 생성 및 시간 Update
						for( Influxdb influx : result.getResults() )
						{
							for( Series series : influx.getSeries() )
							{
								Map<String, String> tags		= series.getTags();
								String tag						= null;
								String host_name				= "";
								String host_ip					= null;
								
								if( tags != null )
								{
									for( String dummy : groupBys )
									{
										if( dummy.trim().toLowerCase().startsWith("host") || dummy.trim().toLowerCase().startsWith("node") || dummy.trim().toLowerCase().startsWith("agent") )
										{
											if( !dummy.trim().toLowerCase().startsWith("agent_id") )
												host_name = tags.get(dummy.trim());
										}

										
										if( !dummy.trim().toLowerCase().startsWith("agent_id") )
										{
											if( tag == null)
												tag					= tags.get(dummy.trim());
											else
												tag					+= "_"+tags.get(dummy.trim());
											
											if( Pattern.matches(validIp, tags.get(dummy.trim())) ) 
												host_ip 			= tags.get(dummy.trim());
											
										}
									}
									
									if( tag == null ) continue;
									
									if( "".equals(tag ) || tag.startsWith("_") ) continue;
									
									String notiKey					= datasource+"_"+table+"_"+metric+"_"+tag;
									

									// Host name Check
									hostData						= redisService.get(Const.HOST, Const.LIST);
									ips					  			= gson.fromJson(hostData, new TypeToken<List<String>>(){}.getType());
									hMap							= new HashMap<String, Host>();
									Host host						= null;
									for( String ip : ips )
									{
										data						= redisService.get(Const.HOST, ip);
										host						= null;
										host						= Util.JsonTobean(data, Host.class);
										hMap.put(ip, host);
									}
									
									
									Notification notification 	= notiMap.get(notiKey);
									
									if( notification == null )
									{
										notification			= new Notification();
										notification.setSeverity(severity);
										for( List<Object> values : series.getValues() )
										{
											if( metric.indexOf("load") != -1 )
											{
												double alloCateCpu = nodeCPUMap.get(tag.trim());
												if( (Double.parseDouble(""+values.get(1)) / alloCateCpu ) <= load_condition ) continue;
											}
			
											notification.setTime(""+values.get(0));

											notification.setTime(current_time);
											notification.setStart_time(current_time);
											
											
											
											// IP정보 세팅
											//if( tag.trim().toLowerCase().equals("node_ip") )
											
											// influxdb결과중 tag값이 IP 형식이라면.. 해당값 세팅
											if( Pattern.matches(validIp, tag.trim()) )
											{
												notification.setTarget_ip(tag);
											}
											else
											{
												//logger.error(nodeMap+"::"+ host_name);
												// Host명으로 되어 있을때 
												if( host_ip != null )
													notification.setTarget_ip(host_ip);
												else
												{
													if( !"".equals(host_name))
														notification.setTarget_ip(nodeMap.get(host_name));
												}
											}
											
											if( hMap.get(notification.getTarget_ip()) != null )
												notification.setHost_name(hMap.get(notification.getTarget_ip()).getHost_name());
											else
												notification.setHost_name("");
											
											notification.setId(tag);
											notification.setTarget_system(target_system);
											notification.setTarget(target);
											notification.setStatus("S");
											notification.setSend_yn(Const.SEND_N);
											if( message.indexOf("%s") != -1 )
												notification.setContents(Util.printf(message,tag));
											else
												notification.setContents(message);
											
											notification.setMetric(metric);
											notification.setCondition(condition);
											
											notification.setSlack_token(slack_token);
											notification.setSlack_channel(slack_channel);
											
											notiMap.put(notiKey, notification);
										}
									}
									// Event Check시간 Update
									else
									{
										//logger.error("data:::"+ Util.beanToJson(notification));
										
										for( List<Object> values : series.getValues() )
										{
											if( metric.indexOf("load") != -1 )
											{
												double alloCateCpu = nodeCPUMap.get(tag.trim());
												if( (Double.parseDouble(""+values.get(1)) / alloCateCpu ) <= load_condition ) continue;
											}
											
											notification.setTarget_system(target_system);
											notification.setTarget(target);
											notification.setTime(current_time);
											notiMap.put(notiKey, notification);
											
											//logger.error("init data:::"+ Util.beanToJson(notification));
											//notification = null;
										}
									}
								} // end of if( tags != null )
							} // end of for( Series series : influx.getSeries() )
						} // end of for( Influxdb influx : result.getResults() )
						
						
						// Event End Check
						// Start Time과 현재 시간이 10초이상 차이날경우 Event Finish로 생각함
						// Event가 종료되면 notification kafka에 전송
						// Rule마다 체크함
						List<String> keys = new ArrayList<String>();
						Map<String, Notification> eventMap	= notiMap;
						if (eventMap.size() > 0 )
						{
					        for( String key : eventMap.keySet() )
					        {
					        	Notification notification		= eventMap.get(key);
					        	
					        	// 현재 조회 하는 룰과 일치하지 않은 데이터는 Skip
					        	if( !target_system.equals(notification.getTarget_system()) || !target.equals(notification.getTarget()) ) continue;
					        	
					        	// 일정시간 지속 시간이 필요 한 경우
					        	// CPU, Memory등
					        	// Master elect 시간
					        	if( condition.indexOf("m") != -1 )
					        	{
						        	buffer						= Util.split(condition, Const.OR_TOKEN+Const.AND_TOKEN);
									
									for( String dummy : buffer )
									{
										if( dummy.indexOf("m") == -1 ) continue;
										
										
										// 시간을 초(second)로 변환
										long period				= Long.parseLong(Util.split(dummy, "<>=m")[0].trim())*60;
										
							        	// 현재 시간과의 시간차 계산 ( 10초이상 차이나면 Event는 종료됐다고 판단함 )
							        	long start_time					= Util.getDate(notification.getStart_time(), Const.DATE_FORMAT).getTime();
							        	long before_time				= Util.getDate(notification.getTime(), Const.DATE_FORMAT).getTime();
							        	long curr_time					= Util.getDate(current_time, Const.DATE_FORMAT).getTime();
							        	
							        	long time_term 					= (long) ((curr_time - before_time) / 1000);
							        	long event_term					= (long) ((curr_time - start_time) / 1000);
							        	
							        	// Event가 종료돼다고 판단 ( 30초동안 정해진 사용량을 초과하지 않을경우 )
							        	if( time_term >= 30 )
							        	{
							        		// 이벤트 시작부터 종료시간 까지의 시간이 rule에 지정된 시간보다 클경우 이벤트 종료 데이터 update
							        		if( event_term > period )
							        		{
							        			notification.setStatus("F");
							        			notification.setFinish_time(Util.getTime());
							        			// Kafka notification topic 전송
							        			send( this.kafka_host, this.kafka_port, Const.INCIDENT_TOPIC, Util.beanToJson(notification));
							        		}
							        		keys.add(key);
							        	}
							        	else
							        	{
							        		// 이벤트 시작부터 종료시간 까지의 시간이 rule에 지정된 시간보다 클경우 이벤트 종료 데이터 insert
							        		if( event_term > period && Const.SEND_N.equals(notification.getSend_yn()) )
							        		{
							        			notification.setSend_yn(Const.SEND_Y);
							        			send( this.kafka_host, this.kafka_port, Const.INCIDENT_TOPIC, Util.beanToJson(notification));
							        			
							        			String mailfrom		= makeHTML( notification );
							        			
							        			// Nofitication 메일전송
							        			if( notify != null && notify.equals("email") )
							        			{
							        				String emails					= redisService.get(Const.EMAIL, Const.LIST);
							        				sendMail(emails, "["+notification.getSeverity()+"] "+ notification.getTarget()+" Alarm ",mailfrom);
							        			}
							        			
							        			// Slack
							        			else if( notify != null && notify.equals("slack") )
							        			{
							        				// Slack 전송
							        				sendSlack(notification.getSlack_token(), notification.getSlack_channel(),notification.getContents());
							        			}
							        			
							        			// Email + slack
							        			else if( notify != null && notify.equals("all") )
							        			{
							        				// Email 전송
							        				String emails					= redisService.get(Const.EMAIL, Const.LIST);
							        				sendMail(emails, "["+notification.getSeverity()+"] "+ notification.getTarget()+" Alarm ",mailfrom);
							        				
							        				// Slack 전송
							        				sendSlack(notification.getSlack_token(), notification.getSlack_channel(),notification.getContents());
							        			}
							        		}
							        	}
									}
					        	}
					        	// 일정 지속시간이 필요 없고 이정 사용량에 도달 할 경우 이벤트 생성
					        	// 디스크 사용량등
					        	else
					        	{
					        		// 현재 시간과의 시간차 계산 ( 10초이상 차이나면 Event는 종료됐다고 판단함 )
						        	long start_time					= Util.getDate(notification.getStart_time(), Const.DATE_FORMAT).getTime();
						        	long before_time				= Util.getDate(notification.getTime(), Const.DATE_FORMAT).getTime();
						        	long curr_time					= Util.getDate(current_time, Const.DATE_FORMAT).getTime();
						        	
						        	long time_term 					= (long) ((curr_time - before_time) / 1000);
						        	
						        	// Event가 종료돼다고 판단 ( 30초동안 정해진 사용량을 초과하지 않을경우 )
						        	if( time_term >= 30 )
						        	{
						        		/*
					        			if( notification.getStart_time() == null || "".equals(notification.getStart_time()))
					        				notification.setStart_time(Util.longToDateString(Util.addDate(0, 0, 0, 0, -10, 0), Const.DATE_FORMAT));
					        			*/
						        		
						        		notification.setStatus("F");
					        			notification.setFinish_time(Util.getTime());
					        			// Kafka notification topic 전송
					        			send( this.kafka_host, this.kafka_port, Const.INCIDENT_TOPIC, Util.beanToJson(notification));
						        		
						        		keys.add(key);
						        	}
						        	else
						        	{
						        		// 이벤트 시작부터 종료시간 까지의 시간이 rule에 지정된 시간보다 클경우 이벤트 종료 데이터 insert
						        		if( Const.SEND_N.equals(notification.getSend_yn()) )
						        		{
						        			notification.setSend_yn(Const.SEND_Y);
						        			send( this.kafka_host, this.kafka_port, Const.INCIDENT_TOPIC, Util.beanToJson(notification));
						        			
						        			String mailfrom		= makeHTML( notification );
						        			
						        			// Nofitication 메일전송
						        			if( notify != null && notify.equals("email") )
						        			{
						        				String emails					= redisService.get(Const.EMAIL, Const.LIST);
						        				sendMail(emails, "["+notification.getSeverity()+"] "+ notification.getTarget()+" Alarm ",mailfrom);
						        			}
						        			
						        			// Slack
						        			else if( notify != null && notify.equals("slack") )
						        			{
						        				// Slack 전송
						        				sendSlack(notification.getSlack_token(), notification.getSlack_channel(),notification.getContents());
						        			}
						        			
						        			// Email + slack
						        			else if( notify != null && notify.equals("all") )
						        			{
						        				// Email 전송
						        				String emails					= redisService.get(Const.EMAIL, Const.LIST);
						        				sendMail(emails, "["+notification.getSeverity()+"] "+ notification.getTarget()+" Alarm ",mailfrom);
						        				
						        				// Slack 전송
						        				sendSlack(notification.getSlack_token(), notification.getSlack_channel(),notification.getContents());
						        			}
						        		}
						        	}
					        	}
					        	
					        	// Set Notification
					        	eventMap.put(key, notification);
					        }
					        
					        for( String key : keys )
					        {
					        	//logger.error("deletekey::::"+key+"::"+(notiMap.get(key)).getSend_yn()+":"+(notiMap.get(key)).getStart_time()+":"+(notiMap.get(key)).getFinish_time());
					        	eventMap.remove(key);
					        }
					        
					        //notiMap.setMap(eventMap);
					        ruleMap.setNotification(eventMap);
					        redisService.put(Const.INCIDENT, Const.ALL_INCIDENT, Util.beanToJson(ruleMap));
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//logger.error("Process End>>");
	}

	public boolean isProcessing() {
		return isProcessing;
	}

	public void setProcessing(boolean isProcessing) {
		this.isProcessing = isProcessing;
	}
}
