package org.punnoose.springdatajpademo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.punnoose.springdatajpademo.domain.Person;
import org.punnoose.springdatajpademo.domain.QPerson;
import org.punnoose.springdatajpademo.dto.PersonDto;
import org.punnoose.springdatajpademo.repository.PersonRepository;
import org.punnoose.springdatajpademo.service.sortcriteria.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Expression;
import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.StringPath;

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

	
	
	/**
	 * Query and Sort by method name Example
	 */
	@Transactional(readOnly = true)
	public List<PersonDto> getByFirstNameOrderByFirstNameDescLastNameAsc(
			String firstName) {
		List<Person> personEntities = personRepository
				.getByFirstNameOrderByFirstNameDescLastNameAsc(firstName);
		return toPersonDtos(personEntities);
	}

	
	
	@Transactional(readOnly = true)
	public long getTotalPersonsCount() {
		return personRepository.count();
	}

	
	
	/**
	 * This method uses @Query
	 */
	@Transactional(readOnly = true)
	public List<PersonDto> getByFirstNameLike(String firstName) {
		List<Person> personEntities = personRepository
				.getByFirstNameLike(getLikePattern(firstName));
		return toPersonDtos(personEntities);
	}

	
	
	/**
	 * This method uses Query DSL
	 */
	@Transactional(readOnly = true)
	public List<PersonDto> getPersonsHavingPhoneOrEmail(
			List<SortCriteria> sortCriteria) {
		QPerson qperson = QPerson.person;

		BooleanExpression personHasPhone = qperson.phone.isNotNull();
		BooleanExpression personHasEmail = qperson.email.isNotNull();

		OrderSpecifier<?>[] orderSpecs = toOrderSpecs(sortCriteria);

		Iterable<Person> personEntities = personRepository.findAll(
				personHasPhone.or(personHasEmail), orderSpecs);
		return toPersonDtos(personEntities);
	}

	
	
	private OrderSpecifier<?>[] toOrderSpecs(List<SortCriteria> sortCriteria) {

		List<OrderSpecifier<?>> orderSpecs = new ArrayList<>();

		CollectionUtils.collect(sortCriteria, new Transformer() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Object transform(Object objectTobeTransformed) {
				SortCriteria item = (SortCriteria) objectTobeTransformed;
				Expression<String> propertyExp = new StringPath(item
						.getProperty());
				return new OrderSpecifier(getSortOrder(item), propertyExp);
			}

			private Order getSortOrder(SortCriteria item) {
				return Order.valueOf(item.getDirection().name());
			}

		}, orderSpecs);

		return orderSpecs.toArray(new OrderSpecifier<?>[orderSpecs.size()]);
	}

	
	
	private List<PersonDto> toPersonDtos(Iterable<Person> personEntities) {
		List<PersonDto> personDtos = new ArrayList<PersonDto>();
		CollectionUtils.collect(personEntities.iterator(),
				new PersonEntityToDtoTransformer(), personDtos);
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
		return (Person) new PersonDtoToEntityTransformer().transform(personDto);
	}

	
	
	/**
	 * Utility method to convert Entity to Dto
	 * 
	 * @param personEntity
	 * @return
	 */
	private PersonDto toPersonDto(Person personEntity) {
		return (PersonDto) new PersonEntityToDtoTransformer().transform(personEntity);
	}

	
	
	private static class PersonEntityToDtoTransformer implements Transformer {
		@Override
		public Object transform(Object objectTobeTransformed) {
			Person personEntity = (Person) objectTobeTransformed;
			PersonDto personDto = new PersonDto(personEntity.getUserId(),
					personEntity.getFirstName(), personEntity.getLastName());

			personDto.setEmail(personEntity.getEmail());
			personDto.setPhone(personEntity.getPhone());

			return personDto;
		}
	}

	
	
	private static class PersonDtoToEntityTransformer implements Transformer {
		@Override
		public Object transform(Object objectTobeTransformed) {
			PersonDto personDto = (PersonDto) objectTobeTransformed;
			Person personEntity = new Person();
			personEntity.setFirstName(personDto.getFirstName());
			personEntity.setLastName(personDto.getLastName());
			personEntity.setPhone(personDto.getPhone());
			personEntity.setEmail(personDto.getEmail());
			return personEntity;
		}
	}
}