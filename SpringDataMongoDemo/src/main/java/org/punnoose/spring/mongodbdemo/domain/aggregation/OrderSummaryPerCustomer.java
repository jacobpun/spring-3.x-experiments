package org.punnoose.spring.mongodbdemo.domain.aggregation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;

public class OrderSummaryPerCustomer {
	@Id
	private String customerName;
	private long orderCount;
	private Double totalPrice;
	private Double averagePrice;
	private Double minimumPrice;
	private Double maximumPrice;
	private List<String> productsOrdered;
	private boolean productListCorrected = false;

	public OrderSummaryPerCustomer() {
		super();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,new String[] {"productsOrdered"});
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, new String[] {"productsOrdered"});
	}

	@Override
	public String toString() {
		return "OrderSummaryPerCustomer [customerName=" + customerName
				+ ", orderCount=" + orderCount + ", totalPrice=" + totalPrice
				+ ", averagePrice=" + averagePrice + ", minimumPrice="
				+ minimumPrice + ", maximumPrice=" + maximumPrice
				+ ", productsOrdered=" + getProductsOrdered() + "]";
	}

	public Double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public Double getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(Double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public Double getMaximumPrice() {
		return maximumPrice;
	}

	public void setMaximumPrice(Double maximumPrice) {
		this.maximumPrice = maximumPrice;
	}

	public List<String> getProductsOrdered() {

		if (!productListCorrected) {
			List<String> correcctedList = new ArrayList<String>();
			String regex = "\".*?\"";
			Pattern pattern = Pattern.compile(regex);

			for (String productString : productsOrdered) {
				Matcher matcher = pattern.matcher(productString);
				while (matcher.find()) {
					correcctedList.add(matcher.group().replaceAll("\"", ""));
				}
			}
			productListCorrected=true;
			if(correcctedList.size()!=0)
				productsOrdered = correcctedList;
		}

		return productsOrdered;
	}

	public void setProductsOrdered(List<String> productsOrdered) {
		this.productsOrdered = productsOrdered;
	}
}