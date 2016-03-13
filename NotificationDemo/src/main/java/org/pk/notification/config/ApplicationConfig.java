package org.pk.notification.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {
		org.pk.notification.notification.service.ComponentScanPlaceHolder.class, 
		org.pk.notification.notification.aspect.ComponentScanPlaceHolder.class,
		org.pk.notification.service.ComponentScanPlaceHolder.class})
public class ApplicationConfig {
}