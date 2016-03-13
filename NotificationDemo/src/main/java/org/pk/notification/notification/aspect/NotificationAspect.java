package org.pk.notification.notification.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.pk.notification.notification.Notifiable;
import org.pk.notification.notification.NotificationBuilder;
import org.pk.notification.notification.Notify;
import org.pk.notification.notification.service.NotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NotificationAspect {
	@Autowired
	private NotificationSenderService notificationService;
	
	@Around("@annotation(notifyAction)")
	public Object doNotifyAdvice(ProceedingJoinPoint pjp, Notify notifyAction) throws Throwable {	
		Object retValue = pjp.proceed();		
		NotificationBuilder<Notifiable> builder = (NotificationBuilder<Notifiable>) notifyAction.notificationBuilder().newInstance();		
	
		if(notifyAction.param() == 0) {
			builder.notifyAbout((Notifiable)retValue);
		} else {
			builder.notifyAbout((Notifiable)getObjectToNotifyAbout(pjp, notifyAction.param()));
		}
		
		notificationService.send(builder.build());
		return retValue;
	}

	private Object getObjectToNotifyAbout(ProceedingJoinPoint pjp, int param) {
		return pjp.getArgs()[param -1];
	}
}