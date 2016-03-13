package org.pk.notification.notification;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Notify {
	Class<? extends NotificationBuilder<? extends Notifiable>> notificationBuilder();
	int param() default -1;
}