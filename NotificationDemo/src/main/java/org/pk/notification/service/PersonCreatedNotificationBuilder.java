package org.pk.notification.service;

import org.pk.notification.model.Person;
import org.pk.notification.notification.Notification;
import org.pk.notification.notification.NotificationBuilder;

public class PersonCreatedNotificationBuilder implements NotificationBuilder<Person>{
	private Person createdPerson;
	
	public Notification build() {
		return new Notification("Notification for the creation of person [" + createdPerson + "]");
	}

	public void notifyAbout(Person object) {
		createdPerson = object;		
	}	
}