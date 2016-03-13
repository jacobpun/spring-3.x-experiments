package org.pk.notification.notification.service;

import org.pk.notification.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("notificationSenderService")
public class NotificationServiceImpl implements NotificationSenderService {
	private Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
	public boolean send(Notification notification) {
		logger.info("Sending Notification : [ {} ]", notification);
		return true;
	}
}