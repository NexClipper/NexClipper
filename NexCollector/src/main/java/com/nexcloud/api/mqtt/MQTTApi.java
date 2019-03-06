package com.nexcloud.api.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MQTTApi implements MqttCallback{
	static final Logger 	logger 		= LoggerFactory.getLogger(MQTTApi.class);
	
	private static String Broker;
	private static String Client_ID;
	private static String UserName;
	private static String Passwd;
	private static MqttAsyncClient Client;
	private static MqttMessage message;
	private static MemoryPersistence persistence;
	private static MqttConnectOptions connOpts;
	private static String topic;
		
	public MQTTApi(String broker, String client_id,String username, String passwd){
		this.Broker 	= broker;
		this.Client_ID 	= client_id;
		this.UserName 	= username;
		this.Passwd 	= passwd;
	}
	
	public void init(String topic){
		this.topic = topic;
		this.persistence = new MemoryPersistence();
		try {
			Client = new MqttAsyncClient(this.Broker, this.Client_ID, this.persistence);
			Client.setCallback(this);

			connOpts = new MqttConnectOptions();
			if(Client_ID!=null && Passwd != null){
				connOpts.setUserName(this.UserName);
				connOpts.setPassword(this.Passwd.toCharArray());
			}
			connOpts.setCleanSession(true);
			logger.error("Connecting to broker: "+this.Broker);
			
			Client.connect(connOpts);

			logger.debug("Connected");
			
			message = new MqttMessage();
		} catch(MqttException me) {
			logger.error("reason "+me.getReasonCode());
			logger.error("msg "+me.getMessage());
			logger.error("loc "+me.getLocalizedMessage());
			logger.error("cause "+me.getCause());
			logger.error("excep "+me);
			
			logger.error("MQTT Init error", me);
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		 try {
			Client.disconnect();
			Client.close();
		 } catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("disconnect error", e);
		}
	}
	
	public void publish(String msg, int qos){
		message.setQos(qos);
		message.setPayload(msg.getBytes());
		
		try {
			Client.publish(topic, message);
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("publish error", e);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("publish error", e);
		}
	}
	
	public void subscribe(int qos){
		try {
			Client.subscribe(topic,qos);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("subscribe error", e);
		}
	}
	
	public String getTopic(){
		return topic;
	}
	
	@Override
	public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
    	logger.error("Message arrived : " + new String(mqttMessage.getPayload(), "UTF-8"));
    }

	@Override
	public void connectionLost(Throwable cause) {
		 logger.error("Lost Connection." + cause.getCause());	
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		logger.error("Message with " + iMqttDeliveryToken + " delivered.");
	}

}
