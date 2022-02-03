package com.edersondev.mscommonservices.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edersondev.mscommonservices.dto.person.PersonCreateDTO;
import com.edersondev.mscommonservices.dto.person.PersonDTO;
import com.edersondev.mscommonservices.service.PersonService;

@RestController
@RequestMapping(value = "persons")
@Validated
public class PersonController {

	@Autowired
	private PersonService service;
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<PersonDTO> create(@Valid @RequestBody PersonCreateDTO dto){
		PersonDTO person = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
		return ResponseEntity.created(uri).body(person);
	}
}
