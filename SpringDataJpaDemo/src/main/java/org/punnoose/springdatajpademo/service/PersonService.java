package org.punnoose.springdatajpademo.service;

import java.util.ArrayList;
import java.util.List;

import org.punnoose.springdatajpademo.domain.Person;
import org.punnoose.springdatajpademo.domain.QPerson;
import org.punnoose.springdatajpademo.dto.PersonDto;
import org.punnoose.springdatajpademo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.expr.BooleanExpression;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public PersonDto savePerson(PersonDto personDto) {
		return toPersonDto(personRepository.save(toPersonEntity(personDto)));
	}

	@Transactional(readOnly = true)
	public Person getById(long id) {
		return personRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<PersonDto> getByFirstName(String firstName) {
		List<PersonDto> personDtos = new ArrayList<PersonDto>();
		for (Person person : personRepository.getByFirstName(firstName)) {
			personDtos.add(toPersonDto(person));
		}
		return personDtos;
	}

	@Transactional(readOnly = true)
	public long getTotalPersonsCount() {
		return personRepository.count();
	}

	/*
	 * This method uses @Query
	 */
	@Transactional(readOnly = true)
	public List<PersonDto> getByFirstNameLike(String firstName) {
		List<Person> persons = personRepository
				.getByFirstNameLike(getLikePattern(firstName));
		return toPersonDtos(persons);
	}

	/*
	 * This method uses Query DSL
	 */
	@Transactional(readOnly = true)
	public List<PersonDto> getPersonsHavingPhoneOrEmail() {
		QPerson qperson = QPerson.person;
		BooleanExpression personHasPhone = qperson.phone.isNotNull();
		BooleanExpression personHasEmail = qperson.email.isNotNull();
		Iterable<Person> persons = personRepository.findAll(personHasPhone
				.or(personHasEmail));
		return toPersonDtos(persons);
	}

	private List<PersonDto> toPersonDtos(Iterable<Person> persons) {
		List<PersonDto> personDtos = new ArrayList<PersonDto>();
		for (Person person : persons) {
			personDtos.add(toPersonDto(person));
		}
		return personDtos;
	}

	private String getLikePattern(String paramName) {
		return "%" + paramName + "%";
	}

	/**
	 * Utility method to convert DTO to Entity
	 * 
	 * @param personDto
	 * @return
	 */
	private Person toPersonEntity(PersonDto personDto) {
		Person person = new Person();
		person.setFirstName(personDto.getFirstName());
		person.setLastName(personDto.getLastName());
		person.setPhone(personDto.getPhone());
		person.setEmail(personDto.getEmail());
		return person;
	}

	/**
	 * Utility method to convert Entity to DTO
	 * 
	 * @param personDto
	 * @return
	 */
	private PersonDto toPersonDto(Person person) {
		PersonDto personDto = new PersonDto(person.getUserId(),
				person.getFirstName(), person.getLastName());
		personDto.setEmail(person.getEmail());
		personDto.setPhone(person.getPhone());
		return personDto;
	}
}