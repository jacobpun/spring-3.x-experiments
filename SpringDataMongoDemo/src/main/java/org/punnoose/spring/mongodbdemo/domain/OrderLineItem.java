package org.punnoose.spring.mongodbdemo.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

public class OrderLineItem {

	@Field("itemNumber")
	private Long itemNumber;
	
	@Field("itemName")
	private String itemName;
	
	@Field("itemQuantity")
	private Long itemQuantity;
	
	@Field("price")
	private Double totalCost;
	
	public OrderLineItem() {
		this(0L, null, 0L, 0.0D);
	}
	
	public OrderLineItem(Long itemNumber, String itemName, Long itemQuantity, Double totalCost) {
		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.totalCost = totalCost;
	}
	
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
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this,that);
	}
}