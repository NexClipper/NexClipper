package com.nexcloud.api.akka.actor;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.redis.RedisCluster;
import com.nexcloud.fullfillment.docker.domain.Containers;
import com.nexcloud.fullfillment.docker.domain.Docker;
import com.nexcloud.fullfillment.domain.common.Header;
import com.nexcloud.fullfillment.domain.common.ResponseData;
import com.nexcloud.fullfillment.host.domain.Host;
import com.nexcloud.fullfillment.k8s.domain.Condition;
import com.nexcloud.fullfillment.k8s.domain.Container;
import com.nexcloud.fullfillment.k8s.domain.ContainerStatus;
import com.nexcloud.fullfillment.k8s.domain.Item;
import com.nexcloud.fullfillment.k8s.domain.K8SAPI;
import com.nexcloud.fullfillment.k8s.domain.K8SCluster;
import com.nexcloud.fullfillment.k8s.domain.K8SMain;
import com.nexcloud.fullfillment.k8s.domain.Resource;
import com.nexcloud.util.Const;
import com.nexcloud.util.SendDataLoader;
import com.nexcloud.util.Util;

import akka.actor.UntypedActor;

@Component
@Scope("prototype")
public class JsonK8SAPIParserActor extends UntypedActor{

	static final Logger 	logger 							= LoggerFactory.getLogger(JsonK8SAPIParserActor.class);
	
	private String data;
	
	private Map<String, Containers> containerMap			= new HashMap<String, Containers>();
	private Map<String, String> hostInfo					= new HashMap<String, String>();
	private Map<String, Integer> hostCpuInfo				= new HashMap<String, Integer>();
	
	private long actor_start;
	private int actor_count;
	
	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	public void onReceive(Object message){
		// TODO Auto-generated method stub
		SendData sendData											= null; 
		RedisCluster redisCluster									= null;
		String msg													= "";
		
		String pattern 												= "#####.###";
        DecimalFormat dformat 										= new DecimalFormat( pattern );
        
        ConsumerRecords<String, String> records 					= null;
        
        actor_start													= System.currentTimeMillis();
        
        Gson gson 													= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		
		try{
			/**
	         * Kafka receive data
	         */
	        sendData												= (SendData)message;
	        
	        // Redis Cluster connection
	        redisCluster											= RedisCluster.getInstance(sendData.getRedis_host(), Integer.parseInt(sendData.getRedis_port()));

	        /**
	         * Agent metric data
	         */
	        ResponseData resData									= null;
	        Header header											= null;
	        String body												= null;
	        
	        records													= sendData.getRecords();
	        
	        actor_count												= records.count();
	        
	        msg														= "";
	        
			Host host												= null;
			Docker docker											= null;
			
			K8SDaemonSetThread daemonsetThread						= K8SDaemonSetThread.getInstance();
			K8SDeploymentThread deploymentThread					= K8SDeploymentThread.getInstance();
			K8SEndpointThread endpointThread						= K8SEndpointThread.getInstance();
			K8SNamespaceThread namespaceThread						= K8SNamespaceThread.getInstance();
			K8SStatefulsetThread statefulThread						= K8SStatefulsetThread.getInstance();
			K8SReplicasetThread replicaThread						= K8SReplicasetThread.getInstance();
			
	        for (ConsumerRecord<String, String> record : records)
	        {
    			if( record.value() == null || "".equals(record.value())) continue;
    			
		        resData												= Util.JsonTobean(record.value(), ResponseData.class);
		        header												= resData.getHeader();
				body												= resData.getBody();
				
				body												= new String(Util.decompress(Util.hexStringToByteArray(body)));
				
				// Host List
		        data												= redisCluster.get(Const.HOST, Const.LIST);
		        List<String> ips					  				= null; 
		        
		        // Docker snapshot info map save start
				containerMap.clear();
				docker												= null;
				Containers saveContainer							= null;
				
				if( data != null )
				{
					hostInfo.clear();
					hostCpuInfo.clear();
					
					ips					  							= gson.fromJson(data, new TypeToken<List<String>>(){}.getType());
			        
					for( String ip : ips )
					{
						// Host 상세 정보 Redis get
						data										= redisCluster.get(Const.HOST, ip);
						host										= Util.JsonTobean(data, Host.class);
						hostInfo.put(host.getHost_name(), ip);
						hostCpuInfo.put(host.getHost_name(), host.getCpu().getCpu_total());
						
						
						data										= redisCluster.get(Const.DOCKER, ip);
			            docker										= Util.JsonTobean(data, Docker.class);
			            if( docker == null ) continue;

			            for( Containers container :  docker.getContainers() )
			            {
			            	if( !"Kubernetes".equals(container.getType()) ) continue;
			            	if( !"container".equals(container.getLabels().getKubernetes_docker_type() )) continue;
			            	
			            	
			            	saveContainer							= null;
			            	saveContainer							= new Containers();
			            	
			            	saveContainer.setCpuPercent(container.getCpuPercent());
			            	saveContainer.setMemPercent(container.getMemPercent());
			            	saveContainer.setUsed_mem(container.getUsed_mem());
			            	saveContainer.setLimit_mem(container.getLimit_mem());
			            	
			            	// Key : Container+Pod+Namespace
			            	containerMap.put(container.getLabels().getKubernetes_container_name()+"_"+container.getLabels().getKubernetes_pod_name()+"_"+container.getLabels().getKubernetes_pod_namespace(), saveContainer );
			            }
					}
				}
				
				K8SAPI k8s											= Util.JsonTobean(body, K8SAPI.class);
				
				if(k8s.getDaemonset().getItems().size() <= 0 ) continue;
				
				K8SMain main										= null;

				
				if( k8s != null )
				{
					// Daemonset
					daemonsetThread.set(k8s.getDaemonset().getItems());
					
					// Deployment
					deploymentThread.set( k8s.getDeployment().getItems());
					
					// Endpoint
					endpointThread.set(k8s.getEndpoint().getItems());
										
					// Nameapce
					namespaceThread.set(k8s.getNamespace().getItems());
					
					// Pod & Container
					main											= k8s.getPod();
					Double limit_cpu								= 0d;
					Double limit_mem								= 0d;
					Double request_cpu								= 0d;
					Double request_mem								= 0d;
					Float cpu_used_percent							= 0f;
					Float mem_used_percent							= 0f;
					Float cpu_used									= 0f;
					Double mem_used									= 0d;
					Double mem_limit								= 0d;
					
					Double pod_limit_cpu							= 0d;
					Double pod_limit_mem							= 0d;
					Double pod_request_cpu							= 0d;
					Double pod_request_mem							= 0d;
					Float  pod_cpu_used_percent						= 0f;
					Float  pod_mem_used_percent						= 0f;
					Float  pod_cpu_used								= 0f;
					Double pod_mem_used								= 0d;
					Double pod_mem_limit							= 0d;
					
					double cluster_used_cpus						= 0;
					double cluster_used_mem							= 0;
					int cluster_used_pod						 	= 0;
					
					Map<String, ContainerStatus> conStatus			= new HashMap<String, ContainerStatus>();
					
					for( Item item : main.getItems() )
					{
						for( ContainerStatus containerStatus :  item.getStatus().getContainerStatuses() )
						{
							// Ready
							int ready								= 0;
							if( containerStatus.getReady() != null && containerStatus.getReady() )
								ready 								= 1;
							
							
							// Restart Total Count
							int restart								= containerStatus.getRestartCount();
							
							// Status Runing 1 or 0
							int running								= 0;
							if( containerStatus.getState().getRunning() != null )
								running								= 1;
							
							// Status Terminated 1 or 0
							int terminated							= 0;
							if( containerStatus.getState().getTerminated() != null )
								terminated							= 1;
							
							// Terminated reason Completed
							int terminated_complted					= 0;
							if( containerStatus.getState().getTerminated() != null && "Completed".equals(containerStatus.getState().getTerminated().getReason()) )
								terminated_complted					= 1;
							
							// Terminated reason ContainerCannotRun
							int terminated_container_cannot_run		= 0;
							if( containerStatus.getState().getTerminated() != null && "ContainerCannotRun".equals(containerStatus.getState().getTerminated().getReason()) )
								terminated_container_cannot_run		= 1;
							
							// Terminated reason Error
							int terminated_error					= 0;
							if( containerStatus.getState().getTerminated() != null && "Error".equals(containerStatus.getState().getTerminated().getReason()) )
								terminated_error					= 1;
							
							// Terminated reason OOMKilled
							int terminated_oomkilled	= 0;
							if( containerStatus.getState().getTerminated() != null && "OOMKilled".equals(containerStatus.getState().getTerminated().getReason()) )
								terminated_oomkilled				= 1;
							
							// Status Waiting 1 or 0
							int waiting								= 0;
							if( containerStatus.getState().getWaiting() != null )
								waiting								= 1;
							
							// Waiting reason ContainerCreating
							int waiting_container_creating			= 0;
							if( containerStatus.getState().getWaiting() != null && "ContainerCreating".equals(containerStatus.getState().getWaiting().getReason()) )
								waiting_container_creating			= 1;
							
							// Waiting reason CrashLoopBackOff
							int waiting_crash_loop_backoff			= 0;
							if( containerStatus.getState().getWaiting() != null && "CrashLoopBackOff".equals(containerStatus.getState().getWaiting().getReason()) )
								waiting_crash_loop_backoff			= 1;
							
							// Waiting reason ErrImagePull
							int waiting_errimagepull				= 0;
							if( containerStatus.getState().getWaiting() != null && "ErrImagePull".equals(containerStatus.getState().getWaiting().getReason()) )
								waiting_errimagepull				= 1;
							
							// Waiting reason ImagePullBackOff
							int waiting_image_pull_backoff			= 0;
							if( containerStatus.getState().getWaiting() != null && "ImagePullBackOff".equals(containerStatus.getState().getWaiting().getReason()) )
								waiting_image_pull_backoff			= 1;
							
							ContainerStatus con						= new ContainerStatus();
							con.setReadyCount(ready);
							con.setRestartCount(restart);
							con.setRunning(running);
							con.setTerminated(terminated);
							con.setTerminated_completed(terminated_complted);
							con.setTerminated_container_cannot_run(terminated_container_cannot_run);
							con.setTerminated_error(terminated_error);
							con.setTerminated_oomkilled(terminated_oomkilled);
							con.setWaiting(waiting);
							con.setWaiting_container_creating(waiting_container_creating);
							con.setWaiting_crash_loop_backoff(waiting_crash_loop_backoff);
							con.setWaiting_errimagepull(waiting_errimagepull);
							con.setWaiting_image_pull_backoff(waiting_image_pull_backoff);
							conStatus.put(item.getMetadata().getName()+"_"+containerStatus.getName(), con);
							
							con										= null;
						}
					}
					
					Map<String, Resource> nodeResourceMap			= new HashMap<String, Resource>();
					Resource nodeResource							= null; 
					
					ContainerStatus containerStatus					= null;
					for( Item item : main.getItems() )
					{
						pod_limit_cpu								= 0d;
						pod_limit_mem								= 0d;
						pod_request_cpu								= 0d;
						pod_request_mem								= 0d;
						pod_cpu_used_percent						= 0f;
						pod_mem_used_percent						= 0f;
						pod_cpu_used								= 0f;
						pod_mem_used								= 0d;
						pod_mem_limit								= 0d;
						
						// Container
						for( Container container : item.getSpec().getContainers() )
						{
							limit_cpu								= 0d;
							limit_mem								= 0d;
							request_cpu								= 0d;
							request_mem								= 0d;
							cpu_used_percent						= 0f;
							mem_used_percent						= 0f;
							cpu_used								= 0f;
							mem_used								= 0d;
							mem_limit								= 0d;
							
							msg										+= "k8s_container,pod="+item.getMetadata().getName()+",container="+container.getName()+",namespace="+item.getMetadata().getNamespace()+",node_name="+item.getSpec().getNodeName()+",node_ip="+hostInfo.get(item.getSpec().getNodeName());
							
							if( container.getResources() != null && container.getResources().getLimits() != null)
							{
								limit_cpu							= Double.parseDouble(container.getResources().getLimits().getCpu());
								msg									+= " limit_cpu="+limit_cpu;
							}
							else 
							{
								limit_cpu							= 0d;
								msg									+= " limit_cpu=0";
							}
							
							if( container.getResources() != null && container.getResources().getRequests() != null )
							{
								request_cpu							= Double.parseDouble(container.getResources().getRequests().getCpu());
								msg									+= ",request_cpu="+request_cpu;
							}
							else 
							{
								request_cpu							= 0d;
								msg									+= ",request_cpu=0";
							}
							
							if( container.getResources() != null && container.getResources().getLimits() != null )
							{
								limit_mem							= Double.parseDouble(container.getResources().getLimits().getMemory() );
								msg									+= ",limit_mem="+limit_mem;
							}
							else 
							{
								limit_mem							= 0d;
								msg									+= ",limit_mem=0";
							}
							
							if( container.getResources() != null && container.getResources().getRequests() != null )
							{
								request_mem							= Double.parseDouble(container.getResources().getRequests().getMemory());
								msg									+= ",request_mem="+request_mem;
							}
							else 
							{
								request_mem							= 0d;
								msg									+= ",request_mem=0";
							}
							
							pod_limit_cpu							+= limit_cpu;
							pod_request_cpu							+= request_cpu;
							pod_limit_mem							+= limit_mem;
							pod_request_mem							+= request_mem;
							
							Containers containers					= containerMap.get(container.getName()+"_"+item.getMetadata().getName()+"_"+item.getMetadata().getNamespace());
							if( containers != null )
							{
								pod_cpu_used_percent				+= containers.getCpuPercent();
								pod_mem_used_percent				+= containers.getMemPercent();
								if( containers.getCpuPercent() > 0 )
									pod_cpu_used					+= (containers.getCpuPercent()/100);
								
								pod_mem_used						+= Double.parseDouble(Long.toString(containers.getUsed_mem()));
								pod_mem_limit						+= Double.parseDouble(Long.toString(containers.getLimit_mem()));
								
								
								cpu_used_percent					= containers.getCpuPercent();
								mem_used_percent					= containers.getMemPercent();
								if( containers.getCpuPercent() > 0 )
									cpu_used						= containers.getCpuPercent()/100;
								else
									cpu_used						= 0f;
								
								cpu_used							= Float.parseFloat(dformat.format(cpu_used));
								mem_used							= Double.parseDouble(dformat.format((Double.parseDouble(Long.toString(containers.getUsed_mem()))/(1024*1024*1024))));
								
								msg									+=",cpu_used="+cpu_used+",cpu_used_percent="+cpu_used_percent+",mem_used="+mem_used+",mem_used_percent="+mem_used_percent;
								
								container.getResources().getLimits().setCpu(""+limit_cpu);
								container.getResources().getLimits().setMemory(""+limit_mem);
								
								container.getResources().getRequests().setCpu(""+request_cpu);
								container.getResources().getRequests().setMemory(""+request_mem);
								
								container.getResources().getUsed().setCpu(""+cpu_used);
								container.getResources().getUsed().setMemory(""+mem_used);
								
								container.getResources().getUsed_percent().setCpu(""+cpu_used_percent);
								container.getResources().getUsed_percent().setMemory(""+mem_used_percent);
							}
							
							containerStatus							= conStatus.get(item.getMetadata().getName()+"_"+container.getName());
							
							// Status Ready 1  or 0
							msg										+= ",ready="+containerStatus.getReadyCount();
							
							// Restart Total Count
							msg										+= ",restart_total="+containerStatus.getRestartCount();
							
							// Status Runing 1 or 0
							msg										+= ",running="+containerStatus.getRunning();
							
							// Status Terminated 1 or 0
							msg										+= ",terminated="+containerStatus.getTerminated();
							
							// Terminated reason Completed
							msg										+= ",terminated_completed="+containerStatus.getTerminated_completed();
							
							// Terminated reason ContainerCannotRun
							msg										+= ",terminated_containercannotrun="+containerStatus.getTerminated_container_cannot_run();
							
							// Terminated reason Error
							msg										+= ",terminated_error="+containerStatus.getTerminated_error();
							
							// Terminated reason OOMKilled
							msg										+= ",terminated_oomkilled="+containerStatus.getTerminated_oomkilled();
							
							// Status Waiting 1 or 0
							msg										+= ",waiting="+containerStatus.getWaiting();
							
							// Waiting reason ContainerCreating
							msg										+= ",waiting_containercreating="+containerStatus.getWaiting_container_creating();
							
							// Waiting reason CrashLoopBackOff
							msg										+= ",waiting_crashloopbackoff="+containerStatus.getWaiting_crash_loop_backoff();
							
							// Waiting reason ErrImagePull
							msg										+= ",waiting_errimagepull="+containerStatus.getWaiting_errimagepull();
							
							// Waiting reason ImagePullBackOff
							msg										+= ",waiting_imagepullbackoff="+containerStatus.getWaiting_image_pull_backoff()+"\n";
						}
						
						// POD
						msg 										+= "k8s_pod,pod="+item.getMetadata().getName()+",namespace="+item.getMetadata().getNamespace()+",node_name="+item.getSpec().getNodeName()+",node_ip="+hostInfo.get(item.getSpec().getNodeName())+",pod_ip="+item.getStatus().getPodIP();
						
						// Pod phase Failed
						if( item.getStatus().getPhase() != null && "Failed".equals(item.getStatus().getPhase()))
							msg 									+= " phase_failed=1";
						else
							msg 									+= " phase_failed=0";
						
						// Pod phase Pending
						if( item.getStatus().getPhase() != null && "Pending".equals(item.getStatus().getPhase()))
							msg 									+= ",phase_pending=1";
						else
							msg 									+= ",phase_pending=0";
						
						// Pod phase Running
						if( item.getStatus().getPhase() != null && "Running".equals(item.getStatus().getPhase()))
							msg 									+= ",phase_running=1";
						else
							msg 									+= ",phase_running=0";
						
						// Pod phase Succeeded
						if( item.getStatus().getPhase() != null && "Succeeded".equals(item.getStatus().getPhase()))
							msg 									+= ",phase_succeeded=1";
						else
							msg 									+= ",phase_succeeded=0";
						
						// Pod phase Unknown
						if( item.getStatus().getPhase() != null && "Unknown".equals(item.getStatus().getPhase()))
							msg 									+= ",phase_unknown=1";
						else
							msg 									+= ",phase_unknown=0";
						
						
						int ready_false								= 0;
						int ready_true								= 0;
						int ready_unknown							= 0;
						
						int scheduled_false							= 0;
						int scheduled_true							= 0;
						int scheduled_unknown						= 0;
						for( Condition condition : item.getStatus().getConditions() )
						{
							if( condition.getType() != null && "Ready".equals(condition.getType()))
							{
								
								if( "True".equals(condition.getStatus()) )
									ready_true				= 1;
								else if( "False".equals(condition.getStatus()) )
									ready_false				= 1;
								else if( "Unknown".equals(condition.getStatus()) )
									ready_unknown			= 1;
							}
							
							else if( condition.getType() != null && "Unschedulable".equals(condition.getType()))
							{
								if( "True".equals(condition.getStatus()) )
									scheduled_true			= 1;
								else if( "False".equals(condition.getStatus()) )
									scheduled_false			= 1;
								else if( "Unknown".equals(condition.getStatus()) )
									scheduled_unknown		= 1;
							}
						}
						
						
						// Pod Status Reday False
						msg 							+= ",ready_false="+ready_false;
						// Pod Status Reday True
						msg 							+= ",ready_true="+ready_true;
						
						// Pod Status Reday unknown
						msg 							+= ",ready_unknown="+ready_unknown;
						
						// Pod Status scheduled false
						msg 							+= ",scheduled_false="+scheduled_false;
						
						// Pod Status scheduled True
						msg 							+= ",scheduled_true="+scheduled_true;
						
						// Pod Status scheduled unknown
						msg 							+= ",scheduled_unknown="+scheduled_unknown;

						cluster_used_cpus				+= Double.parseDouble(Float.toString(pod_cpu_used));
						cluster_used_mem				+= pod_mem_used;
						cluster_used_pod++;
						
						pod_cpu_used					= Float.parseFloat(dformat.format(pod_cpu_used));
						
						try{
							pod_cpu_used_percent		= Float.parseFloat(dformat.format(((pod_cpu_used/hostCpuInfo.get(item.getSpec().getNodeName()))*100)*hostCpuInfo.get(item.getSpec().getNodeName())));
						}catch(Exception e){
							pod_cpu_used_percent		= 0f;
						}
						
						if( pod_mem_limit >0 && pod_mem_used > 0 )
						{
							pod_mem_used_percent			= Float.parseFloat(dformat.format(Float.parseFloat(Double.toString((pod_mem_used/pod_mem_limit)*100))));
							pod_mem_used					= Double.parseDouble(dformat.format(pod_mem_used/(1024*1024*1024)));
						}
						
						item.getResource().getLimits().setCpu(""+pod_limit_cpu);
						item.getResource().getLimits().setMemory(""+pod_limit_mem);
						
						item.getResource().getRequests().setCpu(""+pod_request_cpu);
						item.getResource().getRequests().setMemory(""+pod_request_mem);
						
						item.getResource().getUsed().setCpu(""+pod_cpu_used);
						item.getResource().getUsed().setMemory(""+pod_mem_used);
						
						item.getResource().getUsed_percent().setCpu(""+pod_cpu_used_percent);
						item.getResource().getUsed_percent().setMemory(""+pod_mem_used_percent);
						
						// Node Used & Used percent
						nodeResource					= null;
						if( nodeResourceMap.get(item.getSpec().getNodeName()) == null )
						{
							nodeResource				= new Resource();
							
							nodeResource.getUsed().setCpu(""+pod_cpu_used);
							nodeResource.getUsed().setMemory(""+pod_mem_used);
						}
						else
						{
							nodeResource 				= nodeResourceMap.get(item.getSpec().getNodeName());
							
							Double node_cpu_used		= Double.parseDouble(nodeResource.getUsed().getCpu())+Double.parseDouble(Float.toString(pod_cpu_used));
							Double node_mem_used		= Double.parseDouble(nodeResource.getUsed().getMemory())+pod_mem_used;
							
							nodeResource.getUsed().setCpu(""+node_cpu_used);
							nodeResource.getUsed().setMemory(""+node_mem_used);
						}
						nodeResourceMap.put(item.getSpec().getNodeName(), nodeResource);
						
						
						msg 							+= ",limit_cpu="+pod_limit_cpu+",request_cpu="+pod_request_cpu+",cpu_used="+pod_cpu_used+",cpu_used_percent="+pod_cpu_used_percent;
						msg 							+= ",limit_mem="+pod_limit_mem+",request_mem="+pod_request_mem+",mem_used="+pod_mem_used+",mem_used_percent="+pod_mem_used_percent+"\n";
					}
					
					cluster_used_cpus					= Double.parseDouble(dformat.format(cluster_used_cpus));
					cluster_used_mem					= Double.parseDouble(dformat.format(cluster_used_mem/(1024*1024*1024)));
					
					
					// Node & Cluster
					int cluster_total_cpus				= 0;
					double cluster_total_mem			= 0;
					int cluster_total_pod			 	= 0;
					main								= k8s.getNode();
					for( Item item : main.getItems() )
					{
						int unschedulable				= 0;
						if( item.getSpec().getUnschedulable() != null && item.getSpec().getUnschedulable() )
							unschedulable				= 1;
						
						int diskpressure_false			= 0;
						int diskpressure_true			= 0;
						int diskpressure_unknown		= 0;
						
						int memorypressure_false		= 0;
						int memorypressure_true			= 0;
						int memorypressure_unknown		= 0;
						
						int networkunavailable_false	= 0;
						int networkunavailable_true		= 0;
						int networkunavailable_unknown	= 0;
						
						int outofdisk_false				= 0;
						int outofdisk_true				= 0;
						int outofdisk_unknown			= 0;
						
						int ready_false					= 0;
						int ready_true					= 0;
						int ready_unknown				= 0;
						
						for( Condition condition : item.getStatus().getConditions() )
						{
							if( "DiskPressure".equals(condition.getType()) )
							{
								//True, False, Unknown.
								if( "True".equals(condition.getStatus()) )
									diskpressure_true	= 1;
								else if( "False".equals(condition.getStatus()) )
									diskpressure_false	= 1;
								else if( "Unknown".equals(condition.getStatus()) )
									diskpressure_unknown= 1;
							}
							
							else if( "MemoryPressure".equals(condition.getType()) )
							{
								//True, False, Unknown.
								if( "True".equals(condition.getStatus()) )
									memorypressure_true	= 1;
								else if( "False".equals(condition.getStatus()) )
									memorypressure_false= 1;
								else if( "Unknown".equals(condition.getStatus()) )
									memorypressure_unknown= 1;
							}
							
							else if( "NetworkUnavailable".equals(condition.getType()) )
							{
								//True, False, Unknown.
								if( "True".equals(condition.getStatus()) )
									networkunavailable_true	= 1;
								else if( "False".equals(condition.getStatus()) )
									networkunavailable_false= 1;
								else if( "Unknown".equals(condition.getStatus()) )
									networkunavailable_unknown= 1;
							}
							
							else if( "OutOfDisk".equals(condition.getType()) )
							{
								//True, False, Unknown.
								if( "True".equals(condition.getStatus()) )
									outofdisk_true			= 1;
								else if( "False".equals(condition.getStatus()) )
									outofdisk_false			= 1;
								else if( "Unknown".equals(condition.getStatus()) )
									outofdisk_unknown		= 1;
							}
							
							else if( "Ready".equals(condition.getType()) )
							{
								//True, False, Unknown.
								if( "True".equals(condition.getStatus()) )
									ready_true				= 1;
								else if( "False".equals(condition.getStatus()) )
									ready_false				= 1;
								else if( "Unknown".equals(condition.getStatus()) )
									ready_unknown		= 1;
							}
						}
						
						msg 							+= "k8s_node,node_name="+item.getMetadata().getName()+",node_ip="+hostInfo.get(item.getMetadata().getName());
						msg 							+= " unschedulable="+unschedulable+",allocate_cpu="+item.getStatus().getCapacity().getCpu()+",allocate_mem="+item.getStatus().getAllocatable().getMemory()+",allocate_pod="+item.getStatus().getAllocatable().getPods()+",total_cpu="+item.getStatus().getCapacity().getCpu()+",total_mem="+item.getStatus().getCapacity().getMemory()+",total_pod="+item.getStatus().getCapacity().getPods();
						
						Resource resource	 			= nodeResourceMap.get(item.getMetadata().getName());
						if( resource != null )
						{
							double node_cpu_used_percent= 0d;
							double node_mem_used_percent= 0d;
							
							node_cpu_used_percent		= Double.parseDouble(dformat.format((Double.parseDouble(resource.getUsed().getCpu())/Double.parseDouble(item.getStatus().getCapacity().getCpu()))*100));
							node_mem_used_percent		= Double.parseDouble(dformat.format((Double.parseDouble(resource.getUsed().getMemory())/Double.parseDouble(item.getStatus().getCapacity().getMemory()))*100));
							
							msg 						+= ",cpu_used="+Double.parseDouble(dformat.format(Double.parseDouble(resource.getUsed().getCpu())))+",cpu_used_percent="+node_cpu_used_percent+",mem_used="+Double.parseDouble(dformat.format(Double.parseDouble(resource.getUsed().getMemory())))+",mem_used_percent="+node_mem_used_percent;
						}
						
						msg								+= ",diskpressure_false="+diskpressure_false;
						msg 							+= ",diskpressure_true="+diskpressure_true;
						msg 							+= ",diskpressure_unknown="+diskpressure_unknown;
						
						msg								+= ",memorypressure_false="+memorypressure_false;
						msg 							+= ",memorypressure_true="+memorypressure_true;
						msg 							+= ",memorypressuree_unknown="+memorypressure_unknown;
						
						msg								+= ",networkunavailable_false="+networkunavailable_false;
						msg 							+= ",networkunavailable_true="+networkunavailable_true;
						msg 							+= ",networkunavailable_unknown="+networkunavailable_unknown;
						
						msg								+= ",outofdisk_false="+outofdisk_false;
						msg 							+= ",outofdisk_true="+outofdisk_true;
						msg 							+= ",outofdisk_unknown="+outofdisk_unknown;
						
						msg								+= ",ready_false="+ready_false;
						msg 							+= ",ready_true="+ready_true;
						msg 							+= ",ready_unknown="+ready_unknown+"\n";
						
						item.getMetadata().setNode_name(item.getMetadata().getName());
						item.getMetadata().setNode_ip(hostInfo.get(item.getMetadata().getName()));
						
						cluster_total_cpus				+= Integer.parseInt(item.getStatus().getCapacity().getCpu());
						cluster_total_mem				+= Double.parseDouble(item.getStatus().getAllocatable().getMemory());
						cluster_total_pod				+= Integer.parseInt(item.getStatus().getAllocatable().getPods());
					}
					

					/** 
					 * Cluster Resource Set
					 */
					K8SCluster cluster					= k8s.getK8sCluster();
					cluster.setCpu(cluster_total_cpus);
					cluster.setUsed_cpu(cluster_used_cpus);
					cluster.setMem(Double.parseDouble(dformat.format(cluster_total_mem)));
					cluster.setUsed_mem(cluster_used_mem);
					cluster.setPod(cluster_total_pod);
					cluster.setUsed_pod(cluster_used_pod);
					
					if( cluster_used_cpus > 0 && cluster_total_cpus > 0 )
						cluster.setUsed_percent_cpu(Double.parseDouble(dformat.format((cluster_used_cpus/Double.parseDouble(""+cluster_total_cpus))*100)));
					else
					{
						cluster.setUsed_percent_cpu(0.0);
					}
					
					if( cluster_used_mem > 0 && cluster_total_mem > 0 )
						cluster.setUsed_percent_mem(Double.parseDouble(dformat.format((cluster_used_mem/cluster_total_mem)*100)));
					else
						cluster.setUsed_percent_mem(0.0);
					
					if( cluster_used_pod > 0 && cluster_total_pod > 0 )
						cluster.setUsed_percent_pod(Double.parseDouble(dformat.format((Double.parseDouble(""+cluster_used_pod)/Double.parseDouble(""+cluster_total_pod))*100)));
					else
						cluster.setUsed_percent_pod(0.0);
					
					msg 								+= "k8s_cluster";
					msg									+= " cpu_total="+cluster.getCpu()+",cpu_used="+cluster.getUsed_cpu()+",cpu_used_percent="+cluster.getUsed_percent_cpu();
					msg									+= ",mem_total="+cluster.getMem()+",mem_used="+cluster.getUsed_mem()+",mem_used_percent="+cluster.getUsed_percent_mem();
					msg									+= ",pod_total="+cluster.getPod()+",pod_used="+cluster.getUsed_pod()+",pod_used_percent="+cluster.getUsed_percent_pod()+"\n";

					// Replicaset
					replicaThread.set(k8s.getReplicaset().getItems());
					// Statefulset
					statefulThread.set(k8s.getStatefulset().getItems());
				}
	        }
	        

			// Influx db Send
	        this.send(msg);
			
			// End of First Time Check
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(Util.makeStackTrace(e));
		}
	}
	
	/**
	 * Influx DB Write
	 * @param influxDB
	 * @param msg
	 */
	public void send( String msg )
	{
		SendDataLoader.getInstance().set(msg);
		
		logger.error("Kubernetes Data Parsing Timestamp :["+actor_count+"] "+(System.currentTimeMillis() - actor_start));
	}
}