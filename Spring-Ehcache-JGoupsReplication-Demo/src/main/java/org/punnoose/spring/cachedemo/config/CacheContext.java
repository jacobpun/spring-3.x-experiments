package org.punnoose.spring.cachedemo.config;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheContext {

	@Bean
	public EhCacheCacheManager cacheManager(CacheManager cacheManager) {
		
		//TODO
		MBeanServer mBeanServer = mbeanServer();
		ManagementService.registerMBeans(cacheManager, mBeanServer, false, false, false, true);
		
		return new EhCacheCacheManager(cacheManager);
	}

	@Bean
	public EhCacheManagerFactoryBean ehcache() {
		EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheFactoryBean.setConfigLocation(new ClassPathResource(
				"ehcache.xml"));
		ehCacheFactoryBean.setShared(true);
		
		return ehCacheFactoryBean;
	}

	@Bean
	public MBeanServer mbeanServer() {
		return ManagementFactory.getPlatformMBeanServer();
	}
}