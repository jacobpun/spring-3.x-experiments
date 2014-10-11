package org.punnoose.spring.mongodbdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.foursquare.fongo.Fongo;
import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories(basePackages = "org.punnoose.spring.mongodbdemo.repository")
@ComponentScan(basePackages = { "org.punnoose.spring.mongodbdemo.repository",
		"org.punnoose.spring.mongodbdemo.domain" })
@Profile("test")
public class MongoTestConfig {

	@Value("${db.url}")
	private String dbUrl;

	@Value("${db.name}")
	private String dbName;

	@Bean
	public Mongo mongo() {
		return new Fongo(dbUrl).getMongo();
	}

	@Bean
	public MongoOperations mongoTemplate(Mongo mongo) {
		return new MongoTemplate(mongo, dbName);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer props = new PropertySourcesPlaceholderConfigurer();
		props.setLocations(new Resource[] { new ClassPathResource(
				"application-prod.properties") });
		return props;
	}
}