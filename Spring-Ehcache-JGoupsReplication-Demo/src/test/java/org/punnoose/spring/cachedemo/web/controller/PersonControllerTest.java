package org.punnoose.spring.cachedemo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.punnoose.spring.cachedemo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration()
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		org.punnoose.spring.cachedemo.config.RootContext.class,
		org.punnoose.spring.cachedemo.config.DispatcherServletContext.class,
		org.punnoose.spring.cachedemo.config.CacheContext.class })
@ActiveProfiles("test")
public class PersonControllerTest {

	@Autowired
	PersonController controller;

	@Test
	public void testPing() throws Exception {

		MockMvc mockMvc = standaloneSetup(controller).setMessageConverters(
				new MappingJackson2HttpMessageConverter()).build();

		mockMvc.perform(get("/ping")).andExpect(status().isOk());
	}

	@Test
	public void should_save_person() throws Exception {

		MockMvc mockMvc = standaloneSetup(controller).setMessageConverters(
				new MappingJackson2HttpMessageConverter()).build();

		mockMvc.perform(
					post("/persons")
					.content(toJsonString(getDummyPerson()))
					.contentType(TestUtil.APPLICATION_JSON_UTF8)
				).andExpect(status().isOk());

	}

	@Test
	public void should_get_person() throws Exception {
		
	}

	private Person getDummyPerson() {
		return new Person(1L, "Joe");
	}
	
	private byte[] toJsonString(Person person) throws IOException {
		return TestUtil.convertObjectToJsonBytes(person);
	}
}

class TestUtil {

	public static byte[] convertObjectToJsonBytes(Object object)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		// mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
}