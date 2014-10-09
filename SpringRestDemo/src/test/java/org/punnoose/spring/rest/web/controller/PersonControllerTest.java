package org.punnoose.spring.rest.web.controller;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.punnoose.spring.rest.domain.Person;
import org.punnoose.spring.rest.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { org.punnoose.spring.rest.config.PersonWebAppInitializer.class })
public class PersonControllerTest {

	private static final Person FAKE_PERSON_1 = new Person("Tom");
	private static final Person FAKE_PERSON_2 = new Person("Joe");

	@Test
	public void testGetAll() throws Exception {

		PersonService mockPersonService = mock(PersonService.class);
		when(mockPersonService.getAll()).thenReturn(
				Arrays.asList(FAKE_PERSON_1, FAKE_PERSON_2));

		PersonController controller = new PersonController();
		controller.setService(mockPersonService);

		MockMvc mockMvc = standaloneSetup(controller).setMessageConverters(
				new MappingJackson2HttpMessageConverter()).build();

		mockMvc.perform(get("/persons").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(
						jsonPath(
								"$[*].name",
								containsInAnyOrder(FAKE_PERSON_1.getName(),
										FAKE_PERSON_2.getName())));
	}
}

final class TestUtil {
	private TestUtil() {
	}

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
}