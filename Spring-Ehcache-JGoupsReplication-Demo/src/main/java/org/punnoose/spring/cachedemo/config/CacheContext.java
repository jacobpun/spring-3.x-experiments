package org.punnoose.spring.cachedemo.config;

import net.sf.ehcache.CacheManager;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheContext {

	@Bean
	public EhCacheCacheManager cacheManager(CacheManager cacheManager) {
		return new EhCacheCacheManager(cacheManager);
	}

	@Bean
	@Profile("test")
	public EhCacheManagerFactoryBean ehcacheTest() {
		EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheFactoryBean.setConfigLocation(new ClassPathResource(
				"ehcache_basic.xml"));
		ehCacheFactoryBean.setShared(true);
		return ehCacheFactoryBean;
	}
	
	@Bean
	public EhCacheManagerFactoryBean ehcache1() {
		EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheFactoryBean.setConfigLocation(new ClassPathResource(
				"ehcache.xml"));
		ehCacheFactoryBean.setShared(true);
		return ehCacheFactoryBean;
	}
}