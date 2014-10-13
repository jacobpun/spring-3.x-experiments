package org.punnoose.spring.mongodbdemo.mainprogram;


import java.util.GregorianCalendar;

import org.punnoose.spring.mongodbdemo.config.MongoConfig;
import org.punnoose.spring.mongodbdemo.domain.Order;
import org.punnoose.spring.mongodbdemo.domain.builder.OrderBuilder;
import org.punnoose.spring.mongodbdemo.repository.OrderRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A demo program to see how sharding is working. Before running this program,
 * execute the shell script shardsetup.sh in order to setup the shard
 * 
 * @author "Punnoose Pullolickal"
 *
 */
public class BulkLoadingForShardDemo {

	public static void main(String[] args) {
		RandomString randomString = new RandomString(10);
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.registerShutdownHook();
		ctx.getEnvironment().setActiveProfiles("prod");
		ctx.register(MongoConfig.class);
		ctx.refresh();
		OrderRepository repo = ctx.getBean(OrderRepository.class);
		
		for (int i = 100000; i < 100010; i++) {
			System.out.println(i);
			Order order = OrderBuilder.anOrder()
					.withCustomerName(randomString.nextString())
					.withDate(new GregorianCalendar().getTime()).withId(i)
					.withTotalCost(100)
					.havingLineItem(1L, randomString.nextString(), 1L, 50.0D)
					.havingLineItem(1L, randomString.nextString(), 1L, 50.0D)
					.build();
			repo.save(order);
		}
		
	}
}