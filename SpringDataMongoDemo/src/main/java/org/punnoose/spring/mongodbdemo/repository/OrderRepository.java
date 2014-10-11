package org.punnoose.spring.mongodbdemo.repository;

import java.util.List;

import org.punnoose.spring.mongodbdemo.domain.Order;
import org.punnoose.spring.mongodbdemo.domain.OrderLineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class OrderRepository {

	private static final String COLLECTION_NAME = "orders";

	@Autowired
	private MongoOperations mongo;

	public void save(Order toBeSaved) {
		mongo.insert(toBeSaved, COLLECTION_NAME);
	}

	public Order getOne(Long id) {
		return mongo.findById(id, Order.class, COLLECTION_NAME);
	}

	public Long totalOrdersCount() {
		return mongo.getCollection(COLLECTION_NAME).count();
	}

	public List<Order> getAllOrdersByCustomerName(String customerName) {
		return mongo.find(
				query(where("customer").is(customerName)),
				Order.class,
				COLLECTION_NAME);
	}

	public void addLineItemToOrder(Long id, OrderLineItem item) {
		mongo.updateFirst(
				query(where("orderNumber").is(id)),
				new Update().push("lineItems", item), 
				Order.class,
				COLLECTION_NAME);
	}

	protected void removeAll() {
		mongo.remove(
				new Query(),
				COLLECTION_NAME);
	}
}