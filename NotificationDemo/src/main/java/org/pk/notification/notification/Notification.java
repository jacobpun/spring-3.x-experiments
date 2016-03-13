package org.pk.notification.notification;

public class Notification {
	private String message;
	
	public Notification(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "Notification: [" + getMessage() + "]"; 
	}
}