package org.punnoose.spring.mongodbdemo.repository;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.runner.RunWith;
import org.punnoose.spring.mongodbdemo.domain.Order;
import org.punnoose.spring.mongodbdemo.domain.aggregation.OrderSummaryPerCustomer;
import org.punnoose.spring.mongodbdemo.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		org.punnoose.spring.mongodbdemo.config.MongoTestConfig.class,
		org.punnoose.spring.mongodbdemo.config.MongoConfig.class })
@ActiveProfiles("prod")
public class OrderAggregationRepositoryTest {

	@Autowired
	private OrderAggregationRepository aggrRepository;

	@Autowired
	private OrderRepository repository;

	@Before
	public void init() {
		repository.removeAll();
		saveOrder(TestDataFixture.firstOrderByAlex());
		saveOrder(TestDataFixture.secondOrderByAlex());
		saveOrder(TestDataFixture.firstOrderByGeorge());
		saveOrder(TestDataFixture.firstOrderByJoe());
		saveOrder(TestDataFixture.secondOrderByJoe());
	}

	@Test
	public void should_get_order_summary() {
		List<OrderSummaryPerCustomer> orderSummaryList = aggrRepository.getOrderSummary();
		assertThat(
				orderSummaryList,
				containsInAnyOrder(
						TestDataFixture.orderSummaryForAlex(),
						TestDataFixture.orderSummaryForGeorge(), 
						TestDataFixture.orderSummaryForJoe()
				));
	}

	@Test
	public void should_get_order_summary_for_a_user() throws ItemNotFoundException {
		GregorianCalendar orderDate = new GregorianCalendar();
		orderDate.set(2014, Calendar.FEBRUARY, 1);
		OrderSummaryPerCustomer orderSummary = aggrRepository.getOrderSummary("Alex", orderDate.getTime());
		assertThat(
				orderSummary,
				equalTo(
						TestDataFixture.orderSummaryForAlexSinceFeb1_2014()
				));
	}

	@Test(expected=ItemNotFoundException.class)
	public void should_throw_item_not_found_exception_if_order_summary_not_found() throws ItemNotFoundException {
		GregorianCalendar orderDate = new GregorianCalendar();
		orderDate.set(2014, Calendar.APRIL, 1);
		aggrRepository.getOrderSummary("Alex", orderDate.getTime());
	}
	
	private void saveOrder(Order order) {
		repository.save(order);
	}
}