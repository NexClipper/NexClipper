package com.nexcloud.util;
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

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	public static boolean sendMail(String mailTo, String title, String content){
		boolean ret = true;
		
		Properties props = new Properties(); 
		
		
		props.put("mail.smtp.host", "smtp.gmail.com"); 
		props.put("mail.smtp.port", "25"); 
		props.put("mail.debug", "true"); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.EnableSSL.enable","true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
		props.setProperty("mail.smtp.socketFactory.fallback", "false");   
		props.setProperty("mail.smtp.port", "465");   
		props.setProperty("mail.smtp.socketFactory.port", "465");
         
        Authenticator auth = new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
            	//return new PasswordAuthentication("admin@nexcloud.co.kr", "sprtmzmfflvj~!@#");
            	return new PasswordAuthentication("support@nexclipper.com", "ncloud2370");
            	//return new PasswordAuthentication("travelbooks2", "tbuqhujdywbhcgfj"); 
            }
        };
        
        Session session = Session.getDefaultInstance(props,auth);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session); 
        try{
	        //message.setSender(new InternetAddress( "NOTIFICATOR<travelbooks2@gmail.com>" ));
        	message.setSender(new InternetAddress( "NOTIFICATOR<support@nexclipper.com>" )); 

	        // 이메일 제목
	        message.setSubject(title, "UTF-8");
             
            // 이메일 내용 
	        message.setText(content, "UTF-8");
             
            // 이메일 헤더 
	        message.setHeader("content-Type", "text/html");
	        
	        if (mailTo.indexOf(",") > 0)
	        	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
	        else
	        	message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
	        
	        Transport.send(message);
	        
        }catch (AddressException e) {
            e.printStackTrace();
            ret = false;
        }catch (MessagingException e) {
            e.printStackTrace();
            ret = false;
        }
        
        return ret;
	}
}

