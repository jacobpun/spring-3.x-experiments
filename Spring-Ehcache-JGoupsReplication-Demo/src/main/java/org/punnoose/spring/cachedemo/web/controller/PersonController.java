package org.punnoose.spring.cachedemo.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.punnoose.spring.cachedemo.domain.Person;
import org.punnoose.spring.cachedemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/ping", method = GET)
	public @ResponseBody String ping() {
		return "pong";
	}

	
	@RequestMapping(value = "/persons", method = POST)
	public @ResponseBody Person save(@RequestBody Person person) {
		return getPersonService().save(person);
	}

	@RequestMapping(value = "/persons/{id}", method = GET)
	public @ResponseBody Person getOne(@PathVariable("id") Long id) {
		return getPersonService().findOne(id);

	}

	@RequestMapping(value = "/persons/{id}", method = DELETE)
	public void remove(@PathVariable("id") Long id) {
		getPersonService().remove(id);
	}


	public PersonService getPersonService() {
		return personService;
	}


	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
}