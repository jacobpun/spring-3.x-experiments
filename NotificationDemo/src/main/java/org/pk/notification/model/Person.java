package org.pk.notification.model;

import org.pk.notification.notification.Notifiable;

public class Person implements Notifiable{
	private String name;
	
	public Person(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Person: [" + getName() + "]"; 
	}
}