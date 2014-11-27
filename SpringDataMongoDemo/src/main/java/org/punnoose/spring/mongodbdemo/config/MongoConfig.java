package org.punnoose.spring.mongodbdemo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteConcernResolver;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

@Configuration
@EnableMongoRepositories(basePackages = "org.punnoose.spring.mongodbdemo.repository")
@ComponentScan(basePackages = { "org.punnoose.spring.mongodbdemo.repository",
		"org.punnoose.spring.mongodbdemo.domain" })
@Profile("prod")
public class MongoConfig {

	@Value("${db.url}")
	private String dbUrl;

	@Value("${db.name}")
	private String dbName;

	@Bean
	public MongoFactoryBean mongo() {
		MongoFactoryBean mongo = new MongoFactoryBean();
		mongo.setHost(dbUrl);
		return mongo;
	}

	@Bean
	public MongoOperations mongoTemplate(Mongo mongo) {
		MongoTemplate template = new MongoTemplate(mongo, dbName);
		template.setWriteConcernResolver(writeConcernResolver());
		template.setReadPreference(ReadPreference.secondaryPreferred());
		return template;
	}

	@Bean
	public WriteConcernResolver writeConcernResolver() {
		Map<String, WriteConcern> writeConcernMap = new HashMap<String, WriteConcern>();
		writeConcernMap.put("orders", WriteConcern.JOURNAL_SAFE);
		return new OrderWriteConcernResolver(writeConcernMap);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer props = new PropertySourcesPlaceholderConfigurer();
		props.setLocations(new Resource[] { new ClassPathResource(
				"application-prod.properties") });
		return props;
	}
}