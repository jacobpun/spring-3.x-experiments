package org.pk.notification.notification;

public interface NotificationBuilder<T extends Notifiable> {
	public void notifyAbout(T object);
	public Notification build();
}
