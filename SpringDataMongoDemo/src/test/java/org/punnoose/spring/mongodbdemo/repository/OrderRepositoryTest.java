package org.punnoose.spring.mongodbdemo.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

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
@ActiveProfiles("prod")
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
		saveOrder(TestDataFixture.firstOrderByAlex());
		Long countAfterSave = repository.totalOrdersCount();
		assertThat(countAfterSave, equalTo(initialCount + 1));
	}

	@Test
	public void should_find_orders_by_customer_name() throws Exception {
		Order orderTobeSaved = TestDataFixture.firstOrderByAlex();
		saveOrder(orderTobeSaved);
		List<Order> ordersByCustomer = repository
				.getAllOrdersByCustomerName(orderTobeSaved.getCustomerName());
		assertThat(ordersByCustomer.size(), equalTo(1));
		assertThat(ordersByCustomer.get(0), equalTo(orderTobeSaved));
	}

	@Test
	public void should_add_order_lines_to_order() throws Exception {

		Order toBeSavedOrder = TestDataFixture.firstOrderByAlex();
		saveOrder(toBeSavedOrder);

		OrderLineItem toBeAddedItem = new OrderLineItem(3L, "new item", 1L, 10.0D);
		repository.addLineItemToOrder(toBeSavedOrder.getOrderNumber(),
				toBeAddedItem);

		Order order = repository.getOne(toBeSavedOrder.getOrderNumber());
		
		assertThat(order.getLineItems().size(), 
				equalTo(toBeSavedOrder.getLineItems().size() + 1));
		assertThat(order.getTotalCost(), 
				equalTo(toBeSavedOrder.getTotalCost() + toBeAddedItem.getTotalCost()));
	}

	private void saveOrder(Order order) {
		repository.save(order);
	}
}