package org.punnoose.spring.mongodbdemo.domain.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.punnoose.spring.mongodbdemo.domain.Order;
import org.punnoose.spring.mongodbdemo.domain.OrderLineItem;

public class OrderBuilder {

	private long id;
	private Date orderDate;
	private double totalCost;
	private String customerName;
	
	private List<OrderLineItem> lineItems = new ArrayList<OrderLineItem>();

	public static OrderBuilder anOrder() {
		return new OrderBuilder();
	}

	public OrderBuilder withId(long id) {
		this.id = id;
		return this;
	}

	public OrderBuilder withDate(Date time) {
		this.orderDate = time;
		return this;
	}


	public OrderBuilder withTotalCost(double cost) {
		this.totalCost = cost;
		return this;
	}

	public OrderBuilder withCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}

	public OrderBuilder havingLineItem(long itemNumber, String itemName,
			Long quantity, Double price) {
		OrderLineItem item = new OrderLineItem();
		item.setItemName(itemName);
		item.setItemNumber(itemNumber);
		item.setItemQuantity(quantity);
		item.setTotalCost(price);
		this.lineItems.add(item);
		return this;
	}

	public Order build() {
		Order order = new Order();
		order.setOrderNumber(this.id);
		order.setOrderDate(this.orderDate);
		order.setTotalCost(totalCost);
		order.setLineItems(lineItems);
		order.setCustomerName(customerName);
		return order;
	}
}