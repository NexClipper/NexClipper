package com.nexcloud.rule;



import java.util.HashMap;
import java.util.Map;

import com.nexcloud.rule.domain.Notification;

public class RuleMap{
	private Map<String, Notification> notification;

	public Map<String, Notification> getNotification() {
		if( notification == null )
			notification = new HashMap<String, Notification>();
		return notification;
	}

	public void setNotification(Map<String, Notification> notification) {
		this.notification = notification;
	}
}
