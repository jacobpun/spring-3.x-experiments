package org.pk.notification.service;

import org.pk.notification.model.Person;
import org.pk.notification.notification.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{
	
	private Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Notify(notificationBuilder = PersonCreatedNotificationBuilder.class, param = 1)
	public boolean createPerson(Person p) {
		logger.info("Created Person: [{}]", p);
		return true;
	}
}