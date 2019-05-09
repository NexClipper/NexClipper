/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
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
import com.nexcloud.agent.domain.Header;
import com.nexcloud.agent.domain.ResponseData;
import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.redis.RedisCluster;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;
import com.nexcloud.workflow.docker.domain.Containers;
import com.nexcloud.workflow.docker.domain.Docker;
import com.nexcloud.workflow.host.domain.Host;
import com.nexcloud.workflow.k8s.domain.Container;
import com.nexcloud.workflow.k8s.domain.ContainerStatus;
import com.nexcloud.workflow.k8s.domain.Item;
import com.nexcloud.workflow.k8s.domain.K8SAPI;
import com.nexcloud.workflow.k8s.domain.K8SCluster;
import com.nexcloud.workflow.k8s.domain.K8SMain;
import com.nexcloud.workflow.k8s.domain.Resource;

import akka.actor.UntypedActor;

@Component
@Scope("prototype")
public class JsonK8SAPIParserActor extends UntypedActor{

	static final Logger 	logger 				= LoggerFactory.getLogger(JsonK8SAPIParserActor.class);
	private Map<String, Containers> containerMap= new HashMap<String, Containers>();
	private Map<String, String> hostNameInfo	= new HashMap<String, String>();
	private Map<String, String> hostInfo		= new HashMap<String, String>();
	private Map<String, Integer> hostCpuInfo	= new HashMap<String, Integer>();
	
	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	public synchronized void onReceive(Object message){
		// TODO Auto-generated method stub
		SendData sendData									= null; 
        ConsumerRecords<String, String> records 			= null;
        
		try{
			sendData										= (SendData)message;
	        
			// kafka
			if( "kafka".equals(sendData.getBroker()) )
			{
				records											= sendData.getRecords();
				
		        for (ConsumerRecord<String, String> record : records)
		        {
	    			if( record.value() == null || "".equals(record.value())) continue;
	    			
	    			exec( sendData, record.value() );
		        }
			}
			
			// Rabbit MQ
			else
			{
				exec( sendData, sendData.getJson() );
			}
			
			// End of First Time Check
		}catch(Exception e){
			logger.error("jsonK8SAPIParser Exception::", e);
		}
	}
	
	public void exec( SendData sendData, String data )
	{
		// TODO Auto-generated method stub
		RedisCluster redisCluster						= null;
		
		String nodeName									= null;
		String nodeIP									= null;
		
		String pattern 									= "#####.###";
        DecimalFormat dformat 							= new DecimalFormat( pattern );
        
        Gson gson 										= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        List<String> ips					  			= null; 
        
		try{
			// Redis Cluster connection
	        redisCluster								= RedisCluster.getInstance(sendData.getRedis_host(), Integer.parseInt(sendData.getRedis_port()));
	         
	        /**
	         * Agent metric data
	         */
	        ResponseData resData						= null;
	        Header header								= null;
	        String body									= null;
			
			Host host									= null;
			Docker docker								= null;
			
			K8SAPI k8s									= null;
			K8SMain main								= null;
			
			resData										= Util.JsonTobean(data, ResponseData.class);
	        header										= resData.getHeader();
			body										= resData.getBody();
			
			body										= new String(Util.decompress(Util.hexStringToByteArray(body)));

			// Host Map
	        data										= redisCluster.get(Const.HOST, Const.LIST);
	        
	        // Docker snapshot info map save start
			containerMap.clear();
			docker										= null;
			Containers saveContainer					= null;
			if( data != null )
			{
				hostInfo.clear();
				hostCpuInfo.clear();
				hostNameInfo.clear();
				
				ips					  					= gson.fromJson(data, new TypeToken<List<String>>(){}.getType());
				for( String ip : ips )
				{
					data								= redisCluster.get(Const.HOST, ip);
					host								= Util.JsonTobean(data, Host.class);
					
					hostInfo.put(host.getHost_name(), ip);
					hostCpuInfo.put(host.getHost_name(), host.getCpu().getCpu_total());
					hostNameInfo.put(ip, host.getHost_name());
					
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
			
			k8s									= Util.JsonTobean(body, K8SAPI.class);
			
			//if(k8s.getDaemonset().getItems().size() <= 0 ) continue;
			
			
			main								= null;
			
			if( k8s != null )
			{
				// Pod & Container
				main								= k8s.getPod();
				Double limit_cpu					= 0d;
				Double limit_mem					= 0d;
				Double request_cpu					= 0d;
				Double request_mem					= 0d;
				Float cpu_used_percent				= 0f;
				Float mem_used_percent				= 0f;
				Float cpu_used						= 0f;
				Double mem_used						= 0d;
				Double mem_limit					= 0d;
				
				Double pod_limit_cpu				= 0d;
				Double pod_limit_mem				= 0d;
				Double pod_request_cpu				= 0d;
				Double pod_request_mem				= 0d;
				Float  pod_cpu_used_percent			= 0f;
				Float  pod_mem_used_percent			= 0f;
				Float  pod_cpu_used					= 0f;
				Double pod_mem_used					= 0d;
				Double pod_mem_limit				= 0d;
				
				double cluster_used_cpus			= 0;
				double cluster_used_mem				= 0;
				int cluster_used_pod			 	= 0;
				
				Map<String, ContainerStatus> conStatus		= new HashMap<String, ContainerStatus>();
				
				for( Item item : main.getItems() )
				{
					for( ContainerStatus containerStatus :  item.getStatus().getContainerStatuses() )
					{
						// Ready
						int ready					= 0;
						if( containerStatus.getReady() != null && containerStatus.getReady() )
							ready 					= 1;
						
						
						// Restart Total Count
						int restart					= containerStatus.getRestartCount();
						
						// Status Runing 1 or 0
						int running					= 0;
						if( containerStatus.getState().getRunning() != null )
							running					= 1;
						
						// Status Terminated 1 or 0
						int terminated				= 0;
						if( containerStatus.getState().getTerminated() != null )
							terminated				= 1;
						
						// Terminated reason Completed
						int terminated_complted		= 0;
						if( containerStatus.getState().getTerminated() != null && "Completed".equals(containerStatus.getState().getTerminated().getReason()) )
							terminated_complted		= 1;
						
						// Terminated reason ContainerCannotRun
						int terminated_container_cannot_run		= 0;
						if( containerStatus.getState().getTerminated() != null && "ContainerCannotRun".equals(containerStatus.getState().getTerminated().getReason()) )
							terminated_container_cannot_run		= 1;
						
						// Terminated reason Error
						int terminated_error		= 0;
						if( containerStatus.getState().getTerminated() != null && "Error".equals(containerStatus.getState().getTerminated().getReason()) )
							terminated_error		= 1;
						
						// Terminated reason OOMKilled
						int terminated_oomkilled	= 0;
						if( containerStatus.getState().getTerminated() != null && "OOMKilled".equals(containerStatus.getState().getTerminated().getReason()) )
							terminated_oomkilled	= 1;
						
						// Status Waiting 1 or 0
						int waiting					= 0;
						if( containerStatus.getState().getWaiting() != null )
							waiting					= 1;
						
						// Waiting reason ContainerCreating
						int waiting_container_creating	= 0;
						if( containerStatus.getState().getWaiting() != null && "ContainerCreating".equals(containerStatus.getState().getWaiting().getReason()) )
							waiting_container_creating	= 1;
						
						// Waiting reason CrashLoopBackOff
						int waiting_crash_loop_backoff	= 0;
						if( containerStatus.getState().getWaiting() != null && "CrashLoopBackOff".equals(containerStatus.getState().getWaiting().getReason()) )
							waiting_crash_loop_backoff	= 1;
						
						// Waiting reason ErrImagePull
						int waiting_errimagepull	= 0;
						if( containerStatus.getState().getWaiting() != null && "ErrImagePull".equals(containerStatus.getState().getWaiting().getReason()) )
							waiting_errimagepull	= 1;
						
						// Waiting reason ImagePullBackOff
						int waiting_image_pull_backoff= 0;
						if( containerStatus.getState().getWaiting() != null && "ImagePullBackOff".equals(containerStatus.getState().getWaiting().getReason()) )
							waiting_image_pull_backoff= 1;
						
						ContainerStatus con			= new ContainerStatus();
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
						
						con							= null;
					}
				}
				
				Map<String, Resource> nodeResourceMap= new HashMap<String, Resource>();
				Resource nodeResource				= null; 
				
				ContainerStatus containerStatus		= null;
				for( Item item : main.getItems() )
				{
					pod_limit_cpu					= 0d;
					pod_limit_mem					= 0d;
					pod_request_cpu					= 0d;
					pod_request_mem					= 0d;
					pod_cpu_used_percent			= 0f;
					pod_mem_used_percent			= 0f;
					pod_cpu_used					= 0f;
					pod_mem_used					= 0d;
					pod_mem_limit					= 0d;
					
					nodeName						= item.getSpec().getNodeName();
					nodeIP							= hostInfo.get(nodeName);
					
					/**
					 * OKE같은 경우 Kubernetes Master의  node관리가 host ip로 관리되기ㅣ 때문에 
					 * 실제 host ip정보로 node name을 세팅한다 
					 */
					if( nodeIP == null )
					{
						nodeIP						= nodeName;
						nodeName					= hostNameInfo.get(nodeIP);
						item.getSpec().setNodeName(nodeName);
					}
					
					// Container
					for( Container container : item.getSpec().getContainers() )
					{
						limit_cpu						= 0d;
						limit_mem						= 0d;
						request_cpu						= 0d;
						request_mem						= 0d;
						cpu_used_percent				= 0f;
						mem_used_percent				= 0f;
						cpu_used						= 0f;
						mem_used						= 0d;
						mem_limit						= 0d;
						if( container.getResources() != null && container.getResources().getLimits() != null)
						{
							limit_cpu				= Double.parseDouble(container.getResources().getLimits().getCpu());
						}
						else 
						{
							limit_cpu				= 0d;
						}
						
						if( container.getResources() != null && container.getResources().getRequests() != null )
						{
							request_cpu				= Double.parseDouble(container.getResources().getRequests().getCpu());
						}
						else 
						{
							request_cpu				= 0d;
						}
						
						if( container.getResources() != null && container.getResources().getLimits() != null )
						{
							limit_mem				= Double.parseDouble(container.getResources().getLimits().getMemory() );
						}
						else 
						{
							limit_mem				= 0d;
						}
						
						if( container.getResources() != null && container.getResources().getRequests() != null )
						{
							request_mem				= Double.parseDouble(container.getResources().getRequests().getMemory());
						}
						else 
						{
							request_mem				= 0d;
						}
						
						pod_limit_cpu				+= limit_cpu;
						pod_request_cpu				+= request_cpu;
						pod_limit_mem				+= limit_mem;
						pod_request_mem				+= request_mem;
						
						Containers containers		= containerMap.get(container.getName()+"_"+item.getMetadata().getName()+"_"+item.getMetadata().getNamespace());
						if( containers != null )
						{
							pod_cpu_used_percent		+= containers.getCpuPercent();
							pod_mem_used_percent		+= containers.getMemPercent();
							if( containers.getCpuPercent() > 0 )
								pod_cpu_used			+= (containers.getCpuPercent()/100);
							
							pod_mem_used				+= Double.parseDouble(Long.toString(containers.getUsed_mem()));
							pod_mem_limit				+= Double.parseDouble(Long.toString(containers.getLimit_mem()));
							
							
							cpu_used_percent		= containers.getCpuPercent();
							mem_used_percent		= containers.getMemPercent();
							if( containers.getCpuPercent() > 0 )
								cpu_used			= containers.getCpuPercent()/100;
							else
								cpu_used			= 0f;
							
							cpu_used				= Float.parseFloat(dformat.format(cpu_used));
							mem_used				= Double.parseDouble(dformat.format((Double.parseDouble(Long.toString(containers.getUsed_mem()))/(1024*1024*1024))));
							
							container.getResources().getLimits().setCpu(""+limit_cpu);
							container.getResources().getLimits().setMemory(""+limit_mem);
							
							container.getResources().getRequests().setCpu(""+request_cpu);
							container.getResources().getRequests().setMemory(""+request_mem);
							
							container.getResources().getUsed().setCpu(""+cpu_used);
							container.getResources().getUsed().setMemory(""+mem_used);
							
							container.getResources().getUsed_percent().setCpu(""+cpu_used_percent);
							container.getResources().getUsed_percent().setMemory(""+mem_used_percent);
						}

						containerStatus				= conStatus.get(item.getMetadata().getName()+"_"+container.getName());
					}						

					
					cluster_used_cpus				+= Double.parseDouble(Float.toString(pod_cpu_used));
					cluster_used_mem				+= pod_mem_used;
					cluster_used_pod++;
					
					pod_cpu_used					= Float.parseFloat(dformat.format(pod_cpu_used));
					try{
						//pod_cpu_used_percent		= Float.parseFloat(dformat.format(((pod_cpu_used/hostCpuInfo.get(item.getSpec().getNodeName()))*100)*hostCpuInfo.get(item.getSpec().getNodeName())));
						pod_cpu_used_percent		= Float.parseFloat(dformat.format(((pod_cpu_used/hostCpuInfo.get(nodeName))*100)*hostCpuInfo.get(nodeName)));
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
					//if( nodeResourceMap.get(item.getSpec().getNodeName()) == null )
					if( nodeResourceMap.get(nodeName) == null )
					{
						nodeResource				= new Resource();
						
						nodeResource.getUsed().setCpu(""+pod_cpu_used);
						nodeResource.getUsed().setMemory(""+pod_mem_used);
					}
					else
					{
						//nodeResource 				= nodeResourceMap.get(item.getSpec().getNodeName());
						nodeResource 				= nodeResourceMap.get(nodeName);
						
						Double node_cpu_used		= Double.parseDouble(nodeResource.getUsed().getCpu())+Double.parseDouble(Float.toString(pod_cpu_used));
						Double node_mem_used		= Double.parseDouble(nodeResource.getUsed().getMemory())+pod_mem_used;
						
						nodeResource.getUsed().setCpu(""+node_cpu_used);
						nodeResource.getUsed().setMemory(""+node_mem_used);
					}
					//nodeResourceMap.put(item.getSpec().getNodeName(), nodeResource);
					nodeResourceMap.put(nodeName, nodeResource);
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
					nodeName						= item.getMetadata().getName();
					nodeIP							= hostInfo.get(nodeName);
					
					/**
					 * OKE같은 경우 Kubernetes Master의  node관리가 host ip로 관리되기ㅣ 때문에 
					 * 실제 host ip정보로 node name을 세팅한다 
					 */
					if( nodeIP == null )
					{
						nodeIP						= nodeName;
						nodeName					= hostNameInfo.get(nodeIP);
					}
					
					item.getMetadata().setNode_name(nodeName);
					item.getMetadata().setNode_ip(nodeIP);
					
					item.getMetadata().setName(nodeName);
					
					
					/*
					item.getMetadata().setNode_name(item.getMetadata().getName());
					item.getMetadata().setNode_ip(hostInfo.get(item.getMetadata().getName()));
					*/
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
				
				if( k8s.getDaemonset().getItems().size() > 0 )
				{
					redisCluster.put(Const.K8S, Const.K8S, Util.beanToJson(k8s));
					
					// Node
					redisCluster.put(Const.K8S, Const.NODE, Util.beanToJson(k8s.getNode()));
					
					// Deployment
					redisCluster.put(Const.K8S, Const.DEPLOYMENT, Util.beanToJson(k8s.getDeployment()));
					
					// Daemonset
					redisCluster.put(Const.K8S, Const.DAEMONSET, Util.beanToJson(k8s.getDaemonset()));
					
					// endpoint
					redisCluster.put(Const.K8S, Const.ENDPOINT, Util.beanToJson(k8s.getEndpoint()));
					
					// namespace
					redisCluster.put(Const.K8S, Const.NAMESPACE, Util.beanToJson(k8s.getNamespace()));
					
					// pod
					redisCluster.put(Const.K8S, Const.POD, Util.beanToJson(k8s.getPod()));
					
					// container
					redisCluster.put(Const.K8S, Const.CONTAINER, Util.beanToJson(k8s.getContainer()));
					
					// replicaset
					redisCluster.put(Const.K8S, Const.REPLICASET, Util.beanToJson(k8s.getReplicaset()));
					
					// secret
					redisCluster.put(Const.K8S, Const.SECRET, Util.beanToJson(k8s.getSecret()));
					
					// service
					redisCluster.put(Const.K8S, Const.SERVICE, Util.beanToJson(k8s.getService()));
					
					// statfulset
					redisCluster.put(Const.K8S, Const.STATEFULSET, Util.beanToJson(k8s.getStatefulset()));
					
					// cluster
					redisCluster.put(Const.K8S, Const.K8SCLUSTER, Util.beanToJson(k8s.getK8sCluster()));
					
					// version
					redisCluster.put(Const.K8S, Const.VERSION, Util.beanToJson(k8s.getVersion()));
					
					// Component Status
					redisCluster.put(Const.K8S, Const.COMPONENT_STATUS, Util.beanToJson(k8s.getComponentstatuses()));
					
					// Event
					redisCluster.put(Const.K8S, Const.EVENT, Util.beanToJson(k8s.getEvent()));
				}
			}
			//logger.error("jsonK8sParser End");
		}catch(Exception e){
			logger.error("jsonK8sParser Exception::", e);
		}
	}
}