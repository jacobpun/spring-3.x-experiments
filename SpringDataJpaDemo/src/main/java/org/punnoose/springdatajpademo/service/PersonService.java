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
		Person personEntity = personRepository.save(toPersonEntity(personDto));
		return toPersonDto(personEntity);
	}

	@Transactional(readOnly = true)
	public Person getById(long id) {
		return personRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<PersonDto> getByFirstName(String firstName) {
		List<Person> personEntities = personRepository
				.getByFirstName(firstName);
		return toPersonDtos(personEntities);
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
		List<Person> personEntities = personRepository
				.getByFirstNameLike(getLikePattern(firstName));
		return toPersonDtos(personEntities);
	}

	/*
	 * This method uses Query DSL
	 */
	@Transactional(readOnly = true)
	public List<PersonDto> getPersonsHavingPhoneOrEmail() {
		QPerson qperson = QPerson.person;
		BooleanExpression personHasPhone = qperson.phone.isNotNull();
		BooleanExpression personHasEmail = qperson.email.isNotNull();
		Iterable<Person> personEntities = personRepository
				.findAll(personHasPhone.or(personHasEmail));
		return toPersonDtos(personEntities);
	}

	private List<PersonDto> toPersonDtos(Iterable<Person> personEntities) {
		List<PersonDto> personDtos = new ArrayList<PersonDto>();
		for (Person person : personEntities) {
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
		Person personEntity = new Person();
		personEntity.setFirstName(personDto.getFirstName());
		personEntity.setLastName(personDto.getLastName());
		personEntity.setPhone(personDto.getPhone());
		personEntity.setEmail(personDto.getEmail());
		return personEntity;
	}

	/**
	 * Utility method to convert Entity to DTO
	 * 
	 * @param personDto
	 * @return
	 */
	private PersonDto toPersonDto(Person personEntity) {
		
		PersonDto personDto = new PersonDto(
				personEntity.getUserId(),
				personEntity.getFirstName(),
				personEntity.getLastName());
		
		personDto.setEmail(personEntity.getEmail());
		personDto.setPhone(personEntity.getPhone());
		
		return personDto;
	}
}