package org.punnoose.spring.email.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan(basePackages = { "org.punnoose.spring.email.service" })
@PropertySource("classpath:mail.properties")
public class EmailConfig {
	@Bean
	public MailSender mailSender(Environment env) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		final String HOST = "mailserver.host";
		final String PORT = "mailserver.port";
		final String USERNAME = "mailserver.username";
		final String PASSWORD = "mailserver.password";
		final String PROTOCOL = "mailserver.protocol";

		final String SMTP_AUTH = "mail.smtp.auth";
		final String SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
		final String SMTP_QUITWAIT = "mail.smtp.quitwait";

		mailSender.setHost(env.getProperty(HOST));
		mailSender.setPort(Integer.parseInt(env.getProperty(PORT)));
		mailSender.setUsername(env.getProperty(USERNAME));
		mailSender.setPassword(env.getProperty(PASSWORD));
		mailSender.setProtocol(env.getProperty(PROTOCOL));

		Properties javaMailProps = new Properties();
		javaMailProps.setProperty(SMTP_AUTH, env.getProperty(SMTP_AUTH));
		javaMailProps.setProperty(SMTP_STARTTLS_ENABLE,	env.getProperty(SMTP_STARTTLS_ENABLE));
		javaMailProps.setProperty(SMTP_QUITWAIT, env.getProperty(SMTP_QUITWAIT));

		mailSender.setJavaMailProperties(javaMailProps);

		return mailSender;
	}
}