package org.punnoose.springdatajpademo.service;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.punnoose.springdatajpademo.service.PersonServiceTestDataFixture.DUMMY_PERSON_1;
import static org.punnoose.springdatajpademo.service.PersonServiceTestDataFixture.DUMMY_PERSON_2;
import static org.punnoose.springdatajpademo.service.PersonServiceTestDataFixture.PERSON_WITH_EMAIL;
import static org.punnoose.springdatajpademo.service.PersonServiceTestDataFixture.PERSON_WITH_EMAIL_AND_PHONE;
import static org.punnoose.springdatajpademo.service.PersonServiceTestDataFixture.PERSON_WITH_NO_EMAIL_AND_NO_PHONE;
import static org.punnoose.springdatajpademo.service.PersonServiceTestDataFixture.PERSON_WITH_PHONE;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.punnoose.springdatajpademo.config.JpaConfiguration;
import org.punnoose.springdatajpademo.dto.PersonDto;
import org.punnoose.springdatajpademo.service.sortcriteria.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfiguration.class)
@ActiveProfiles("dev")
@Transactional
public class PersonServiceTest {

	@Autowired
	private PersonService personService;

	@Test
	@Rollback
	public void testSave() {
		long initialCount = personService.getTotalPersonsCount();
		savePerson(DUMMY_PERSON_1);
		long finalCount = personService.getTotalPersonsCount();
		assertThat(finalCount, equalTo(initialCount + 1));
	}

	@Test
	@Rollback
	public void testGetAndSort() {
		savePerson(DUMMY_PERSON_2);
		savePerson(DUMMY_PERSON_1);
		List<PersonDto> personDtos = personService
				.getByFirstNameOrderByFirstNameDescLastNameAsc(DUMMY_PERSON_1
						.getFirstName());
		assertThat(personDtos, contains(DUMMY_PERSON_1));
	}

	@Test
	@Rollback
	public void testQueryAnnotationInRepository() {
		savePerson(DUMMY_PERSON_1);
		savePerson(DUMMY_PERSON_2);
		List<PersonDto> personDtos = personService.getByFirstNameLike("DUMMY");
		assertThat(personDtos,
				containsInAnyOrder(DUMMY_PERSON_1, DUMMY_PERSON_2));
	}

	@Test
	@Rollback
	public void testQuryDsl() {
		savePerson(PERSON_WITH_EMAIL);
		savePerson(PERSON_WITH_PHONE);
		savePerson(PERSON_WITH_EMAIL_AND_PHONE);
		savePerson(PERSON_WITH_NO_EMAIL_AND_NO_PHONE);

		@SuppressWarnings("serial")
		List<PersonDto> personDtos = personService
				.getPersonsHavingPhoneOrEmail(new ArrayList<SortCriteria>() {
					{
						add(new SortCriteria("lastName",
								SortCriteria.SortDirection.ASC));
						add(new SortCriteria("firstName",
								SortCriteria.SortDirection.DESC));
					}
				});
		assertThat(personDtos.size(), equalTo(3));
		assertThat(
				personDtos,
				contains(
						PERSON_WITH_EMAIL, 
						PERSON_WITH_EMAIL_AND_PHONE,
						PERSON_WITH_PHONE
				));
	}

	private void savePerson(PersonDto personDto) {
		personService.savePerson(personDto);
	}
}