package com.edersondev.mscommonservices.controller;


import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edersondev.mscommonservices.dto.PersonDTO;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.model.enums.Gender;
import com.edersondev.mscommonservices.service.PersonService;

@RestController
@RequestMapping(value = "persons")
@Validated
public class PersonController {

	private static final String ID = "/{id}";

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PersonService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<PersonDTO>> findAll(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir,
			@RequestParam(value = "search", required = false) String search
	) {
		Page<PersonDTO> list = service.findAll(pageNo, pageSize, sortBy, sortDir, search).map(entity -> mapperPersonToDTO(entity));
		return ResponseEntity.ok(list);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> create(@Valid @RequestBody PersonDTO dto){
		PersonDTO person = mapperPersonToDTO(service.create(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(person.getId()).toUri();
		return ResponseEntity.created(uri).body(person);
	}
	
	@PutMapping(value = ID,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody PersonDTO dto){
		service.update(id, dto);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = ID)
	public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
		PersonDTO person = mapperPersonToDTO(service.findById(id));
		return ResponseEntity.ok().body(person);
	}
	
	@DeleteMapping(value = ID)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	private PersonDTO mapperPersonToDTO(Person person) {
		Converter<Gender,Integer> genderConverter = ctx -> ctx.getSource() == null ? null : ctx.getSource().getCode();
		mapper.typeMap(Person.class, PersonDTO.class)
			.addMappings(m -> m.using(genderConverter)
				.map(Person::getGender, PersonDTO::setGender)
			);
		return mapper.map(person, PersonDTO.class);
	}
}
