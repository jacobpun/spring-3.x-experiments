package org.punnoose.spring.rest.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.punnoose.spring.rest.domain.Person;
import org.punnoose.spring.rest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PersonController {

	@Autowired
	private PersonService service;

	@RequestMapping(method = GET, value = "/persons")
	@ResponseStatus(value = HttpStatus.OK)
	private @ResponseBody List<Person> getAll() {
		return getService().getAll();
	}

	public PersonService getService() {
		return service;
	}

	public void setService(PersonService service) {
		this.service = service;
	}
}