package org.punnoose.spring.mongodbdemo.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Order {
	
	@Id
	private Long orderNumber;
	
	@Field("date")
	private Date orderDate;
	
	@Field("price")
	private Double totalCost;
	
	private List<OrderLineItem> lineItems;
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public List<OrderLineItem> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<OrderLineItem> lineItems) {
		this.lineItems = lineItems;
	}
	public Long getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}
}