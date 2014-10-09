package org.punnoose.spring.cachedemo.service;

import org.punnoose.spring.cachedemo.domain.Person;
import org.punnoose.spring.cachedemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepositry;
	
	@CachePut(value="personCache", key="#result.id")
	public Person save(Person person) {
		getPersonRepositry().put(person.getId(), person);
		return person;
	}

	@Cacheable(value="personCache", unless="#result==null", key ="#id")
	public Person findOne(Long id) {
		return getPersonRepositry().get(id);
	}

	@CacheEvict(value= "personCache", key ="#id")
	public void remove(Long id) {
		getPersonRepositry().remove(id);
	}

	public PersonRepository getPersonRepositry() {
		return personRepositry;
	}

	public void setPersonRepositry(PersonRepository personRepositry) {
		this.personRepositry = personRepositry;
	}		
}