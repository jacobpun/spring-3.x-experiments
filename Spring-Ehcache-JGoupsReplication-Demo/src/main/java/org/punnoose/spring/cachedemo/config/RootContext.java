package org.punnoose.spring.cachedemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {
		org.punnoose.spring.cachedemo.service.ComponentScanMarker.class,
		org.punnoose.spring.cachedemo.repository.ComponentScanMarker.class })
public class RootContext {

}