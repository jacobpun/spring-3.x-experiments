package org.punnoose.spring.mongodbdemo.repository;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.punnoose.spring.mongodbdemo.domain.Order;
import org.punnoose.spring.mongodbdemo.domain.aggregation.OrderSummaryPerCustomer;
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
	}

	@Test
	public void should_get_order_summary() {
		saveOrder(TestDataFixture.firstOrderByAlex());
		saveOrder(TestDataFixture.secondOrderByAlex());
		saveOrder(TestDataFixture.firstOrderByGeorge());
		saveOrder(TestDataFixture.firstOrderByJoe());
		saveOrder(TestDataFixture.secondOrderByJoe());

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
	public void should_get_order_summary_for_a_user() {
		saveOrder(TestDataFixture.firstOrderByAlex());
		saveOrder(TestDataFixture.secondOrderByAlex());
		saveOrder(TestDataFixture.firstOrderByGeorge());
		saveOrder(TestDataFixture.firstOrderByJoe());
		saveOrder(TestDataFixture.secondOrderByJoe());

		OrderSummaryPerCustomer orderSummary = aggrRepository.getOrderSummary("Alex");
		
		assertThat(
				orderSummary,
				equalTo(
						TestDataFixture.orderSummaryForAlex()
				));
	}

	
	
	private void saveOrder(Order order) {
		repository.save(order);
	}
}