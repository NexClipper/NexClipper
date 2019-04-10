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
package com.nexcloud.workflow.incident.workflow;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;

import com.nexcloud.api.kafka.Producer;
import com.nexcloud.api.rabitmq.Publish;
import com.nexcloud.rule.domain.Notification;
import com.nexcloud.util.Mail;
import com.nexcloud.util.rest.HttpAPI;
import com.nexcloud.util.rest.RestClient.Method;

public class IncidentWorkflow{
	/**
	 * Assurance Worklfow execute
	 * @param object
	 */
	public void process( )
	{
		
	}
	
	
	/**
	 * Assurance Workflow Process Start or Stop
	 * @return
	 * 	true : starting
	 * 	flase: stopped
	 */
	public boolean isProcessing( )
	{
		return true;
	}
	
	
	/**
	 * Assurance Workflow Process Start
	 */
	public void start( )
	{
		
	}
	
	/**
	 * Mail 전송
	 * @param title
	 * @param content
	 */
	public void sendMail( String receive_email, String title, String content )
	{
		
		try{
			Mail.sendMail(receive_email, title, content);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Slack 전송
	 * @param title
	 * @param content
	 */
	public void sendSlack( String slack_token, String slack_channel, String content )
	{
		
		try{
			//String restURL			= "https://slack.com/api/chat.postMessage?token="+slack_token+"&channel="+slack_channel+"&text="+URLEncoder.encode(content)+"&as_user=Nexclipper&pretty=1";
			if( slack_channel.trim().startsWith("#") )
				slack_channel		= slack_channel.substring(1);
			String restURL			= "https://slack.com/api/chat.postMessage?token="+URLEncoder.encode(slack_token)+"&channel="+URLEncoder.encode(slack_channel)+"&text="+URLEncoder.encode(content)+"&as_user=Nexclipper&pretty=1";
			HttpAPI.request(restURL, MediaType.TEXT_PLAIN_TYPE, Method.GET, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * evnet데이터 Kafka send
	 * @param sendData
	 * @param data
	 */
	public void kafkSend( String kafka_host, String kafka_port,String kafka_topic, String data )
	{
		
		Producer	prdocuer 	= Producer.getInstance();
		prdocuer.send(kafka_host, kafka_port, kafka_topic, data);
	}
	
	/**
	 * RabbitMQ Send
	 */
	public void rabbitSend(String rabbitmp_host, String rabbitmp_port, String rabbitmq_username, String rabbitmq_password, String topic, String data )
	{
		
		try{
			Publish	publish		 		= null;
			publish 					= Publish.getInstance( rabbitmp_host, rabbitmp_port, rabbitmq_username, rabbitmq_password);			
			
			publish.put( topic, data );
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public String makeHTML( Notification notification )
	{
		String html = "";
		/*
		html += "<!doctype html>";
		html += "<html lang='en'>  ";
		html += " <head> ";
		html += "  <meta charset='UTF-8'>";
		html += "  <meta name='Generator' content='EditPlus®'>";
		html += "  <meta name='Author' content=''> ";
		html += "  <meta name='Keywords' content=''> ";
		html += "  <meta name='Description' content=''> ";
		html += "  <title>Incidnet</title> ";
		html += " </head> ";
		html += " <body> ";
		html += "  <p style='font-size:20px;font-weight:bold;text-align:left;padding:10px 0;margin:0'>";
		html += "	Alarm Information ";
		html += "	  </p> ";
		html += "  <table border='1' width='610' cellpadding='0' cellspacing='0'>";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Alarm Level ";
		html += "		  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;font-weight:bold;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getSeverity()+ "";
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Host Name ";
		html += "		  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;font-weight:bold;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getHost_name();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Host IP ";
		html += "		  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;font-weight:bold;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getTarget_ip();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Target ";
		html += "		  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+ notification.getTarget_system() +" > "+ notification.getTarget();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Metric ";
		html += "		  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#e60000;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getMetric();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Condition ";
		html += "	  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getCondition();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Alarm detection time  ";
		html += "	  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;text-align:left;padding:6px;font-size:13px;line-height:1.4'> ";
		html += "		"+notification.getStart_time();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "  </table> ";
		html += "<br> ";
		html += "  <p style='font-size:20px;font-weight:bold;text-align:left;padding:10px 0;margin:0'>";
		html += "	Alarm Message ";
		html += "	  </p> ";
		html += "  <table border='1' width='610' cellpadding='0' cellspacing='0'>";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Alarm ID ";
		html += "		  </th>";
		html += "	  <td width='445' style='background:#fff;color:#000;font-weight:bold;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getId();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "		<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Alarm Message ";
		html += "	  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html +=	" 		"+notification.getContents();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "  </table> ";
		html += " </body> ";
		html += "</html> ";
		*/
		
		/*
		html += "<!doctype html>";
		html += "<html lang='en'>  ";
		html += " <head> ";
		html += "  <meta charset='UTF-8'>";
		html += "  <meta name='Generator' content='EditPlus®'>";
		html += "  <meta name='Author' content=''> ";
		html += "  <meta name='Keywords' content=''> ";
		html += "  <meta name='Description' content=''> ";
		html += "  <title>Incidnet</title> ";
		html += " </head> ";
		html += " <body> ";
		
		html += "  <p style='font-size:20px;font-weight:bold;text-align:left;padding:10px 0;margin:0'>";
		html += "	Alarm Message ";
		html += "	  </p> ";
		html += "  <table border='1' width='610' cellpadding='0' cellspacing='0'>";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Alarm ID ";
		html += "		  </th>";
		html += "	  <td width='445' style='background:#fff;color:#000;font-weight:bold;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getId();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Alarm Message ";
		html += "	  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html +=	" 		"+notification.getContents();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "  </table> ";
		
		html += "<br> ";
		
		html += "  <p style='font-size:20px;font-weight:bold;text-align:left;padding:10px 0;margin:0'>";
		html += "	Alarm Information ";
		html += "	  </p> ";
		html += "  <table border='1' width='610' cellpadding='0' cellspacing='0'>";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Alarm Level ";
		html += "		  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;font-weight:bold;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getSeverity()+ "";
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Host Name ";
		html += "		  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;font-weight:bold;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getHost_name();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Host IP ";
		html += "		  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;font-weight:bold;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getTarget_ip();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Target ";
		html += "		  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+ notification.getTarget_system() +" > "+ notification.getTarget();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Metric ";
		html += "		  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#e60000;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getMetric();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Condition ";
		html += "	  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;text-align:left;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		"+notification.getCondition();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "	<tr> ";
		html += "	  <th width='165' style='background:#c6d9f1;color:#000;text-align:center;padding:6px;font-size:13px;line-height:1.4'>";
		html += "		Alarm detection time  ";
		html += "	  </th> ";
		html += "	  <td width='445' style='background:#fff;color:#000;text-align:left;padding:6px;font-size:13px;line-height:1.4'> ";
		html += "		"+notification.getStart_time();
		html += "	  </td> ";
		html += "	</tr> ";
		html += "  </table> ";
		
		
		html += " </body> ";
		html += "</html> ";
		
		*/
		
		html += " <p><!doctype html></p>";
		html += " <p></p>";
		html += " <p style='font-size: 20px; font-weight: bold; text-align: left; padding: 10px 0; margin: 0;'><img src='https://www.nexclipper.com/wp-content/uploads/2018/10/logo2.png' alt='' width='436' height='80' /></p>";
		html += " <p style='font-size: 20px; font-weight: bold; text-align: left; padding: 10px 0; margin: 0;'>Alarm Message</p>";
		html += " <table border='0' width='610' cellspacing='0' cellpadding='0'>";
		html += " <tbody>";
		html += " <tr>";
		html += " <th style='background: #c6d9f1; color: #000; text-align: center; padding: 6px; font-size: 13px; line-height: 1.4;' width='165'>Alarm Message</th>";
		html += " <td style='background: #fff; color: #000; font-weight: bold; text-align: left; padding: 6px; font-size: 13px; line-height: 1.4;' width='445'>"+notification.getContents()+"&nbsp;</td>";
		html += " </tr>";
		html += " <tr>";
		html += " <th style='background: #c6d9f1; color: #000; text-align: center; padding: 6px; font-size: 13px; line-height: 1.4;' width='165'>Alarm ID</th>";
		html += " <td style='background: #fff; color: #000; text-align: left; padding: 6px; font-size: 13px; line-height: 1.4;' width='445'>"+notification.getId()+"</td>";
		html += " </tr>";
		html += " </tbody>";
		html += " </table>";
		html += " <hr />";
		html += " <p style='font-size: 20px; font-weight: bold; text-align: left; padding: 10px 0; margin: 0;'>Alarm Information</p>";
		html += " <table border='0.5' width='610' cellspacing='0' cellpadding='0'>";
		html += " <tbody>";
		html += " <tr>";
		html += " <th style='background-color: #c6d9f1; padding: 6px; font-size: 13px; line-height: 1.4;' width='165'>Alarm Level</th>";
		html += " <td style='background: #fff; color: #000; font-weight: bold; text-align: left; padding: 6px; font-size: 13px; line-height: 1.4;' width='445'>"+notification.getSeverity()+ "</td>";
		html += " </tr>";
		html += " <tr>";
		html += " <th style='background: #c6d9f1; color: #000; text-align: center; padding: 6px; font-size: 13px; line-height: 1.4;' width='165'>Host Name</th>";
		html += " <td style='background: #fff; color: #000; font-weight: bold; text-align: left; padding: 6px; font-size: 13px; line-height: 1.4;' width='445'>"+notification.getHost_name()+"</td>";
		html += " </tr>";
		html += " <tr>";
		html += " <th style='background: #c6d9f1; color: #000; text-align: center; padding: 6px; font-size: 13px; line-height: 1.4;' width='165'>Host IP</th>";
		html += " <td style='background: #fff; color: #000; font-weight: bold; text-align: left; padding: 6px; font-size: 13px; line-height: 1.4;' width='445'>"+notification.getTarget_ip()+"</td>";
		html += " </tr>";
		html += " <tr>";
		html += " <th style='background: #c6d9f1; color: #000; text-align: center; padding: 6px; font-size: 13px; line-height: 1.4;' width='165'>Target</th>";
		html += " <td style='background: #fff; color: #000; text-align: left; padding: 6px; font-size: 13px; line-height: 1.4;' width='445'>"+ notification.getTarget_system() +" &gt; "+ notification.getTarget()+"</td>";
		html += " </tr>";
		html += " <tr>";
		html += " <th style='background: #c6d9f1; color: #000; text-align: center; padding: 6px; font-size: 13px; line-height: 1.4;' width='165'>Metric</th>";
		html += " <td style='background: #fff; color: #e60000; text-align: left; padding: 6px; font-size: 13px; line-height: 1.4;' width='445'>"+notification.getMetric()+"</td>";
		html += " </tr>";
		html += " <tr>";
		html += " <th style='background: #c6d9f1; color: #000; text-align: center; padding: 6px; font-size: 13px; line-height: 1.4;' width='165'>Condition</th>";
		html += " <td style='background: #fff; color: #000; text-align: left; padding: 6px; font-size: 13px; line-height: 1.4;' width='445'>"+notification.getCondition()+"</td>";
		html += " </tr>";
		html += " <tr>";
		html += " <th style='background: #c6d9f1; color: #000; text-align: center; padding: 6px; font-size: 13px; line-height: 1.4;' width='165'>Alarm detection time</th>";
		html += " <td style='background: #fff; color: #000; text-align: left; padding: 6px; font-size: 13px; line-height: 1.4;' width='445'>"+notification.getStart_time()+"</td>";
		html += " </tr>";
		html += " </tbody>";
		html += " </table>";
		html += " <p>&nbsp;For more detail, go to <a href='https://server.nexclipper.com' target='_blank' rel='noopener'>server</a> &nbsp;</p>";
		html += " <p>&nbsp;</p>";
		
		return html;
	}
}
