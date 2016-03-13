package org.pk.notification.notification.service;

import org.pk.notification.notification.Notification;


public interface NotificationSenderService {
	public boolean send(Notification notification);
}
