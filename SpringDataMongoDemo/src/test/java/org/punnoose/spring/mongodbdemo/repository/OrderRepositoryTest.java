package org.punnoose.spring.mongodbdemo.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.punnoose.spring.mongodbdemo.domain.Order;
import org.punnoose.spring.mongodbdemo.domain.OrderLineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		org.punnoose.spring.mongodbdemo.config.MongoTestConfig.class,
		org.punnoose.spring.mongodbdemo.config.MongoConfig.class })
@ActiveProfiles("test")
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository repository;

	@Before
	public void init() {
		repository.removeAll();
	}

	@Test
	public void should_save_order() {
		Long initialCount = repository.totalOrdersCount();
		saveOrder(TestDataFixture.gerDummyOrder());
		Long countAfterSave = repository.totalOrdersCount();
		assertThat(countAfterSave, equalTo(initialCount + 1));
	}

	@Test
	public void should_find_orders_by_customer_name() throws Exception {
		Order orderTobeSaved = TestDataFixture.gerDummyOrder();
		saveOrder(orderTobeSaved);
		List<Order> ordersByCustomer = repository
				.getAllOrdersByCustomerName(orderTobeSaved.getCustomerName());
		assertThat(ordersByCustomer.size(), equalTo(1));
		assertThat(ordersByCustomer.get(0), equalTo(orderTobeSaved));
	}

	@Test
	public void should_add_order_lines_to_order() throws Exception {

		Order orderToBeSaved = TestDataFixture.gerDummyOrder();
		saveOrder(orderToBeSaved);
		repository.addLineItemToOrder(orderToBeSaved.getOrderNumber(),
				new OrderLineItem(3L, "new item", 1L, 10.0D));

		Order order = repository.getOne(orderToBeSaved.getOrderNumber());
		assertThat(
				order.getLineItems().size(),
				equalTo(orderToBeSaved.getLineItems().size() + 1));
	}

	private void saveOrder(Order order) {
		repository.save(order);
	}
}