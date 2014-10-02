package org.punnoose.spring.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = org.punnoose.spring.rest.web.controller.ComponentScanMarker.class)
public class WebConfig {

}