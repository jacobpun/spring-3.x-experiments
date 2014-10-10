package org.punnoose.spring.mongodbdemo.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.punnoose.spring.mongodbdemo.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {org.punnoose.spring.mongodbdemo.config.MongoTestConfig.class})
@ActiveProfiles("test")
public class MongoRepositoryIntegrationTest {

	@Autowired
	private MongoRepository repository;
	
	@Test
	public void should_save_order() {
		
		Long initialCount = repository.totalOrdersCount();

		Order order = TestDataFixture.gerDummyOrder();
		repository.save(order);
		
		Long countAfterSave = repository.totalOrdersCount();
		
		assertThat(countAfterSave, equalTo(initialCount+1));
	
	}
}