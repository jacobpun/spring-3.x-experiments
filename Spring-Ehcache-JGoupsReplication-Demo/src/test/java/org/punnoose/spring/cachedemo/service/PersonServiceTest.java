package org.punnoose.spring.cachedemo.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.punnoose.spring.cachedemo.domain.Person;
import org.punnoose.spring.cachedemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		org.punnoose.spring.cachedemo.config.RootContext.class,
		org.punnoose.spring.cachedemo.config.CacheContext.class })
@ActiveProfiles("test")
public class PersonServiceTest {
	
	@Autowired
	PersonService personService;

	@Test
	public void should_retrieve_from_cache() {
		
		PersonRepository mockRepo = mock(PersonRepository.class);
		when(mockRepo.get(Mockito.anyLong())).thenReturn(getDummyPerson());
		
		personService.setPersonRepositry(mockRepo);
		
		Person savedPerson = getDummyPerson();
		personService.save(savedPerson);
		
		Person retrievedPerson = personService.findOne(getDummyPerson().getId());
		
		assertThat(retrievedPerson, equalTo(savedPerson));
		verify(mockRepo, times(1)).put(savedPerson.getId(), savedPerson);
		verify(mockRepo, never()).get(Mockito.anyLong());
	}

	private Person getDummyPerson() {
		return new Person(1L, "Joe");
	}
}