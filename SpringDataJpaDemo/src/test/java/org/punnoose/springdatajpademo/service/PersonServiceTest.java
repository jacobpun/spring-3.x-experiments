package org.punnoose.springdatajpademo.service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.punnoose.springdatajpademo.config.JpaConfiguration;
import org.punnoose.springdatajpademo.dto.PersonDto;
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

	private static final String LAST_NAME = "Pullolickal";
	private static final String FIRST_NAME = "Punnoose";

	@Autowired
	private PersonService personService;

	@Test
	@Rollback
	public void should_save_person() {
		long initialCount = personService.getTotalPersonsCount();

		saveDummyUser();

		long finalCount = personService.getTotalPersonsCount();

		assertThat(finalCount, is(initialCount + 1));
	}

	@Test
	@Rollback
	public void should_get_person_by_first_name() {
		saveDummyUser();
		List<PersonDto> personDto = personService.getByFirstName(FIRST_NAME);
		assertThat(personDto.get(0).getFirstName(), is(FIRST_NAME));
	}

	@Test
	@Rollback
	public void testQueryInRepository() {
		saveDummyUser();
		List<PersonDto> personDto = personService
				.getByFirstNameLike(FIRST_NAME);
		assertThat(personDto.get(0).getFirstName(), is(FIRST_NAME));
	}

	@Test
	@Rollback
	public void testQuryDsl() {
		saveUserWithEmail();
		saveUserWithPhone();
		saveUserWithEmailAndPhone();
		saveUserWithNoEmailAndNoPhone();
		List<PersonDto> personDto = personService.getPersonsHavingPhoneOrEmail();
		assertThat(personDto.size(), is(3));
	}

	private void saveUserWithNoEmailAndNoPhone() {
		final int DUMMY_ID = 0;
		PersonDto personDto = new PersonDto(DUMMY_ID,
				"with_no_phone_and_no_email_user_fname",
				"with_no_phone_and_no_email_user_lname");
		personService.savePerson(personDto);
	}

	private void saveUserWithEmailAndPhone() {
		final int DUMMY_ID = 0;
		PersonDto personDto = new PersonDto(DUMMY_ID,
				"with_phone_and_email_user_fname",
				"with_phone_and_email_user_lname");
		personDto.setPhone("some phone");
		personDto.setPhone("some email");
		personService.savePerson(personDto);
	}

	private void saveUserWithPhone() {
		final int DUMMY_ID = 0;
		PersonDto personDto = new PersonDto(DUMMY_ID, "with_phone_user_fname",
				"with_phone_user_lname");
		personDto.setPhone("some phone");
		personService.savePerson(personDto);
	}

	private void saveUserWithEmail() {
		final int DUMMY_ID = 0;
		PersonDto personDto = new PersonDto(DUMMY_ID, "with_email_user_fname",
				"with_email_user_lname");
		personDto.setEmail("some email");
		personService.savePerson(personDto);
	}

	private void saveDummyUser() {
		final int DUMMY_ID = 0;
		PersonDto personDto = new PersonDto(DUMMY_ID, FIRST_NAME, LAST_NAME);
		personDto.setEmail("punnoosekuttyj@yahoo.com");
		personService.savePerson(personDto);
	}
}