package org.punnoose.cachedemo.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.punnoose.cachedemo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:beans.xml")
public class PersonDaoTest {
	@Autowired
	PersonDao dao;
	
	@Test
	public void testCache() {
		Person person1 = dao.getPerson("John");
		Person person2 = dao.getPerson("John");
		assertSame(person1, person2);
	}
}