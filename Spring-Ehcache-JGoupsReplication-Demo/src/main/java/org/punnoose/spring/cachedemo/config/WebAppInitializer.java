package org.punnoose.spring.cachedemo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootContext.class, CacheContext.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {DispatcherServletContext.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/*"};
	}
}