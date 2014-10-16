package org.punnoose.spring.mongodbdemo.exception;

public class ItemNotFoundException extends Exception {

	public ItemNotFoundException(String exceptionString) {
		super(exceptionString);
	}
}
