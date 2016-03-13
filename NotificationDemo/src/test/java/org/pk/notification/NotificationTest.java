package org.pk.notification;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.pk.notification.config.ApplicationConfig;
import org.pk.notification.model.Person;
import org.pk.notification.notification.Notification;
import org.pk.notification.notification.service.NotificationSenderService;
import org.pk.notification.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(value= {"test"})
@ContextConfiguration(classes = {ApplicationConfig.class, NotificationTest.TestConfiguration.class})
public class NotificationTest {
	@Autowired
	private PersonService personService;

	@Autowired 
	private NotificationSenderService notificationSenderService;
	
	@Test
	public void testNotification() {
		String PERSON_NAME = "Rob";
		
		Person aPerson = new Person (PERSON_NAME);
		personService.createPerson(aPerson);
		
		ArgumentCaptor<Notification> argument = ArgumentCaptor.forClass(Notification.class);
		verify(notificationSenderService, times(1)).send(argument.capture());
		Assert.assertTrue(argument.getValue().getMessage().contains(PERSON_NAME));
	}
	
	@Configuration
	@Profile("test")
	static class TestConfiguration {		
		@Bean
		@Primary
		public NotificationSenderService notificationSenderMocked() {
			return Mockito.mock(NotificationSenderService.class);
		}
	}
}