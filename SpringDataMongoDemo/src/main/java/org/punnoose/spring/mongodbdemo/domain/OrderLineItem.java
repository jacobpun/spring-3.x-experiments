package org.punnoose.spring.mongodbdemo.domain;

import org.springframework.data.mongodb.core.mapping.Field;

public class OrderLineItem {

	@Field("itemNumber")
	private Long itemNumber;
	
	@Field("itemNamer")
	private String itemName;
	
	@Field("itemQuantity")
	private Long itemQuantity;
	
	@Field("price")
	private Double totalCost;
	
	public Long getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(Long itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Long getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
}