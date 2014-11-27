package org.punnoose.spring.email.main;

import org.punnoose.spring.email.config.EmailConfig;
import org.punnoose.spring.email.service.EmailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				EmailConfig.class);
		EmailService emailService = (EmailService) ctx.getBean("emailService");
		emailService.sendEmail("Cow Jump over the moon", "Hey!!", "punnoosekuttyj@yahoo.com");
	}
}