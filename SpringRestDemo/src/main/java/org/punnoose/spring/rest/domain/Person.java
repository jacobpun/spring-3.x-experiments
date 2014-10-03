package org.punnoose.spring.rest.domain;

public class Person {

	private String name;
	private String country="CA";
	
	
	public Person(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

}
