package org.punnoose.springdatajpademo.service;

import org.punnoose.springdatajpademo.dto.PersonDto;

public class PersonServiceTestDataFixture {

	public static PersonDto DUMMY_PERSON_1 = createDummyPerson1();
	public static PersonDto DUMMY_PERSON_2 = createDummyPerson2();
	public static PersonDto PERSON_WITH_NO_EMAIL_AND_NO_PHONE = createPersonWithNoEmailAndNoPhone();
	public static PersonDto PERSON_WITH_EMAIL_AND_PHONE = createPersonWithEmailAndPhone();
	public static PersonDto PERSON_WITH_PHONE = createPersonWithPhone();
	public static PersonDto PERSON_WITH_EMAIL = createPersonWithEmail();
	
	private static PersonDto createDummyPerson1() {
		final int DUMMY_ID = 0;
		PersonDto personDto = new PersonDto(DUMMY_ID, "DUMMY_USER_1_FNAME",
				"DUMMY_USER_1_LNAME");
		personDto.setEmail("DUMMY_USER_1_EMAIL");
		return personDto;
	}

	private static PersonDto createDummyPerson2() {
		final int DUMMY_ID = 0;
		PersonDto personDto = new PersonDto(DUMMY_ID, "DUMMY_USER_2_FNAME",
				"DUMMY_USER_2_LNAME");
		personDto.setEmail("DUMMY_USER_2_EMAIL");
		return personDto;
	}

	private static PersonDto createPersonWithNoEmailAndNoPhone() {
		final int DUMMY_ID = 0;
		PersonDto personDto = new PersonDto(DUMMY_ID,
				"with_no_phone_and_no_email_user_fname",
				"with_no_phone_and_no_email_user_lname");
		return personDto;
	}

	private static PersonDto createPersonWithEmailAndPhone() {
		final int DUMMY_ID = 0;
		PersonDto personDto = new PersonDto(DUMMY_ID,
				"with_phone_and_email_user_fname",
				"with_phone_and_email_user_lname");
		personDto.setPhone("some phone");
		personDto.setPhone("some email");
		return personDto;
	}

	private static PersonDto createPersonWithPhone() {
		final int DUMMY_ID = 0;
		PersonDto personDto = new PersonDto(DUMMY_ID, "with_phone_user_fname",
				"with_phone_user_lname");
		personDto.setPhone("some phone");
		return personDto;
	}

	private static PersonDto createPersonWithEmail() {
		final int DUMMY_ID = 0;
		PersonDto personDto = new PersonDto(DUMMY_ID, "with_email_user_fname",
				"with_email_user_lname");
		personDto.setEmail("some email");
		return personDto;
	}
}
