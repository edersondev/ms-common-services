package com.edersondev.mscommonservices.controller;


import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@PostMapping(produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> create(@Valid @RequestBody PersonCreateDTO dto){
		PersonDTO person = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
		return ResponseEntity.created(uri).body(person);
	}
	
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody PersonCreateDTO dto){
		service.update(id, dto);
		return ResponseEntity.noContent().build();
	}
}
