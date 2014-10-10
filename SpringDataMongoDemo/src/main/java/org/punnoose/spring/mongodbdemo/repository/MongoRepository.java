package org.punnoose.spring.mongodbdemo.repository;

import org.punnoose.spring.mongodbdemo.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepository {

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
}