package org.punnoose.spring.mongodbdemo.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Date;
import java.util.List;

import org.punnoose.spring.mongodbdemo.domain.aggregation.OrderSummaryPerCustomer;
import org.punnoose.spring.mongodbdemo.exception.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory
			.getLogger(OrderAggregationRepository.class);

	public List<OrderSummaryPerCustomer> getOrderSummary() {

		AggregationOperation group = Aggregation.group("customer").count()
				.as("orderCount").sum("price").as("totalPrice").min("price")
				.as("minimumPrice").max("price").as("maximumPrice")
				.avg("price").as("averagePrice").addToSet("lineItems.itemName")
				.as("productsOrdered");
		Aggregation aggregation = Aggregation.newAggregation(group);
		AggregationResults<OrderSummaryPerCustomer> result = this.mongo
				.aggregate(aggregation, COLLECTION_NAME,
						OrderSummaryPerCustomer.class);

		List<OrderSummaryPerCustomer> orderSumamryList = result
				.getMappedResults();

		// MDC.put("X-UserId", "Some Dummy Text");
		logger.debug("Fetched {} Order Summary Lists", orderSumamryList.size());

		return orderSumamryList;
	}

	public OrderSummaryPerCustomer getOrderSummary(String customerName,
			Date sinceDate) throws ItemNotFoundException {

		AggregationOperation match = Aggregation.match(where("customer")
				.is(customerName).and("date").gt(sinceDate));

		AggregationOperation group = Aggregation.group("customer").count()
				.as("orderCount").sum("price").as("totalPrice").min("price")
				.as("minimumPrice").max("price").as("maximumPrice")
				.avg("price").as("averagePrice").addToSet("lineItems.itemName")
				.as("productsOrdered");

		Aggregation aggregation = Aggregation.newAggregation(match, group);

		AggregationResults<OrderSummaryPerCustomer> result = this.mongo
				.aggregate(aggregation, COLLECTION_NAME,
						OrderSummaryPerCustomer.class);

		if (gotResults(result)) {
			OrderSummaryPerCustomer orderSumamry = result.getMappedResults()
					.get(0);
			logger.debug("Order Summary Lists for Customer {} is {}",
					customerName, orderSumamry);
			return orderSumamry;
		} else {
			logger.error("No order summary found for the customer {} since {}",
					customerName, sinceDate);
			throw new ItemNotFoundException(String.format(
					"No order summary found for the customer %s since %s",
					customerName, sinceDate));
		}
	}

	private boolean gotResults(
			AggregationResults<OrderSummaryPerCustomer> result) {
		return result.getMappedResults().size() >= 1;
	}
}