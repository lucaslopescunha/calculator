package com.cunha.calculator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cunha.calculator.service.PersonService;
import com.cunha.calculator.vo.PersonVO;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/pessoa")
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping(value = "/{id}",produces = { "application/json", "application/xml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO person = service.findById(id);
		
		person.add(linkTo(
                methodOn(PersonController.class)
                .findById(id))
                .withSelfRel());
		
		return person;
	}

	@GetMapping(produces = { "application/json", "application/xml" })
	public List<PersonVO> findAll() {
		List<PersonVO> list = new ArrayList<>();
		list.add(new PersonVO(1l, "Lucas"));
		list.add(new PersonVO(2l, "Lucas"));
		return list;
	}

	@DeleteMapping(value = "/{id}" )
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = RequestMethod.POST, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public PersonVO create(@RequestBody PersonVO personVO) {
		personVO=service.create(personVO);
		personVO.add(linkTo(
                methodOn(PersonController.class)
                .create(personVO))
                .withSelfRel());

		return personVO;
	}

	@PutMapping
	public PersonVO update(@RequestBody PersonVO personVO) {

		return service.create(personVO);
	}
}
