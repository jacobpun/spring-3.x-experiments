package org.punnoose.spring.mongodbdemo.repository;

import java.util.List;

import org.punnoose.spring.mongodbdemo.domain.aggregation.OrderSummaryPerCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

@Repository
public class OrderAggregationRepository {
	private static final String COLLECTION_NAME = "orders";

	@Autowired
	private MongoOperations mongo;

	public List<OrderSummaryPerCustomer> getOrderSummary() {
		AggregationOperation group = Aggregation.group("customer")
										.count().as("orderCount")
										.sum("price").as("totalPrice")
										.min("price").as("minimumPrice")
										.max("price").as("maximumPrice")
										.avg("price").as("averagePrice")
										.addToSet("lineItems.itemName").as("productsOrdered");
		Aggregation aggregation = Aggregation.newAggregation(group);
		AggregationResults<OrderSummaryPerCustomer> result = this.mongo.aggregate(aggregation, COLLECTION_NAME, OrderSummaryPerCustomer.class);
		return result.getMappedResults();
	}
}