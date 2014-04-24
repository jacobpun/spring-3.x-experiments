package org.punnoose.cachedemo.dao;

import org.punnoose.cachedemo.domain.Person;

public class PersonDaoImpl implements PersonDao{

	public Person getPerson(String name){
		return new Person(name);
	}
}