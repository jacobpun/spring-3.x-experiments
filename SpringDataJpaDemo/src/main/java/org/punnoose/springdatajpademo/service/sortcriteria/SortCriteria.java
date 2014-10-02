package org.punnoose.springdatajpademo.service.sortcriteria;

public class SortCriteria {

	private String property;
	private SortDirection direction;

	public SortCriteria(String property, SortDirection direction) {
		setProperty(property);
		setDirection(direction);
	}

	public String getProperty() {
		return property;
	}

	private void setProperty(String property) {
		this.property = property;
	}

	public SortDirection getDirection() {
		return direction;
	}

	private void setDirection(SortDirection direction) {
		this.direction = direction;
	}
	
	public static enum SortDirection {
		ASC, DESC;
	}
}