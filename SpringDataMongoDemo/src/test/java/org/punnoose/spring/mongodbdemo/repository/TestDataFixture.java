package org.punnoose.spring.mongodbdemo.repository;

import java.util.GregorianCalendar;

import org.punnoose.spring.mongodbdemo.domain.Order;
import static org.punnoose.spring.mongodbdemo.domain.builder.OrderBuilder.anOrder;

public class TestDataFixture {

	public static Order gerDummyOrder() {
		return anOrder().withId(1L)
				.withDate(new GregorianCalendar().getTime())
				.withTotalCost(100.0D)
				.havingLineItem(100L, "cell phone", 2L, 75.0D)
				.havingLineItem(100L, "cell phone charger", 1L, 25.0D)
				.build();
	}

}
