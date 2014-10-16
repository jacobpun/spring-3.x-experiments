package org.punnoose.spring.mongodbdemo.repository;

import static org.punnoose.spring.mongodbdemo.domain.builder.OrderBuilder.anOrder;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.punnoose.spring.mongodbdemo.domain.Order;
import org.punnoose.spring.mongodbdemo.domain.aggregation.OrderSummaryPerCustomer;

public class TestDataFixture {

	public static Order firstOrderByAlex() {
		GregorianCalendar orderDate = new GregorianCalendar();
		orderDate.set(2014, Calendar.JANUARY, 1);
		return anOrder().withId(1L)
				.withDate(orderDate.getTime())
				.withTotalCost(100.0D)
				.withCustomerName("Alex")
				.havingLineItem(100L, "cell phone", 2L, 75.0D)
				.havingLineItem(100L, "cell phone charger", 1L, 25.0D)
				.build();
	}

	public static Order secondOrderByAlex() {
		GregorianCalendar orderDate = new GregorianCalendar();
		orderDate.set(2014, Calendar.MARCH, 15);
		
		return anOrder().withId(2L)
				.withDate(orderDate.getTime())
				.withTotalCost(50.0D)
				.withCustomerName("Alex")
				.havingLineItem(100L, "television", 2L, 50.0D)
				.build();
	}

	public static Order firstOrderByGeorge() {
		return anOrder().withId(3L)
				.withDate(new GregorianCalendar().getTime())
				.withTotalCost(50.0D)
				.withCustomerName("George")
				.havingLineItem(100L, "television", 2L, 50.0D)
				.build();
	}

	public static Order firstOrderByJoe() {
		return anOrder().withId(4L)
				.withDate(new GregorianCalendar().getTime())
				.withTotalCost(50.0D)
				.withCustomerName("Joe")
				.havingLineItem(100L, "cell phone", 2L, 50.0D)
				.build();
	}

	public static Order secondOrderByJoe() {
		return anOrder().withId(5L)
				.withDate(new GregorianCalendar().getTime())
				.withTotalCost(10.0D)
				.withCustomerName("Joe")
				.havingLineItem(100L, "battery", 2L, 5.0D)
				.build();
	}

	public static OrderSummaryPerCustomer orderSummaryForAlex() {
		OrderSummaryPerCustomer orderSummaryForAlex = new OrderSummaryPerCustomer();
		orderSummaryForAlex.setCustomerName("Alex");
		orderSummaryForAlex.setOrderCount(2);
		orderSummaryForAlex.setMinimumPrice(50.0);
		orderSummaryForAlex.setMaximumPrice(100.0);
		orderSummaryForAlex.setAveragePrice(75.0);
		orderSummaryForAlex.setTotalPrice(150.0);
		orderSummaryForAlex.setProductsOrdered(Arrays.asList(new String[]{"television", "cell phone","cell phone charger"}));
		return orderSummaryForAlex;
	}

	public static OrderSummaryPerCustomer orderSummaryForAlexSinceFeb1_2014() {
		OrderSummaryPerCustomer orderSummaryForAlex = new OrderSummaryPerCustomer();
		orderSummaryForAlex.setCustomerName("Alex");
		orderSummaryForAlex.setOrderCount(1);
		orderSummaryForAlex.setMinimumPrice(50.0);
		orderSummaryForAlex.setMaximumPrice(50.0);
		orderSummaryForAlex.setAveragePrice(50.0);
		orderSummaryForAlex.setTotalPrice(50.0);
		orderSummaryForAlex.setProductsOrdered(Arrays.asList(new String[]{"television"}));
		return orderSummaryForAlex;
	}

	public static OrderSummaryPerCustomer orderSummaryForGeorge() {
		OrderSummaryPerCustomer orderSummaryForGeorge = new OrderSummaryPerCustomer();
		orderSummaryForGeorge.setCustomerName("George");
		orderSummaryForGeorge.setOrderCount(1);
		orderSummaryForGeorge.setMinimumPrice(50.0);
		orderSummaryForGeorge.setMaximumPrice(50.0);
		orderSummaryForGeorge.setAveragePrice(50.0);
		orderSummaryForGeorge.setTotalPrice(50.0);
		orderSummaryForGeorge.setProductsOrdered(Arrays.asList(new String[]{"television"}));
		return orderSummaryForGeorge;

	}
	
	public static OrderSummaryPerCustomer orderSummaryForJoe() {
		OrderSummaryPerCustomer orderSummaryForJoe = new OrderSummaryPerCustomer();
		orderSummaryForJoe.setCustomerName("Joe");
		orderSummaryForJoe.setOrderCount(2);
		orderSummaryForJoe.setMinimumPrice(10.0);
		orderSummaryForJoe.setMaximumPrice(50.0);
		orderSummaryForJoe.setAveragePrice(30.0);
		orderSummaryForJoe.setTotalPrice(60.0);
		orderSummaryForJoe.setProductsOrdered(Arrays.asList(new String[]{"battery", "cell phone"}));
		return orderSummaryForJoe;
	}
}