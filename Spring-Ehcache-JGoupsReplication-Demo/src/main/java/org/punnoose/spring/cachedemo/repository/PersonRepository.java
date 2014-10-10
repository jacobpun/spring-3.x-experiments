package org.punnoose.spring.cachedemo.repository;

import java.util.concurrent.ConcurrentHashMap;

import org.punnoose.spring.cachedemo.domain.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

	private ConcurrentHashMap<Long, Person> personRepositry = new ConcurrentHashMap<Long, Person>();

	public void put(Long id, Person person) {
		personRepositry.put(id, person);
	}

	public Person get(Long id) {
		return personRepositry.get(id);
	}

	public void remove(Long id) {
		personRepositry.remove(id);
	}

}