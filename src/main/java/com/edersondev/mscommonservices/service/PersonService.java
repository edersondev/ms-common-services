package com.edersondev.mscommonservices.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edersondev.mscommonservices.dto.person.PersonCreateDTO;
import com.edersondev.mscommonservices.dto.person.PersonDTO;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.repository.PersonRepository;
import com.edersondev.mscommonservices.service.exception.ResourceNotFoundException;

@Service
public class PersonService extends AbstractService<PersonRepository,Person> {
	
	public PersonDTO create(PersonCreateDTO dto) {
		Person person = repository.dtoToEntity(dto);
		person = repository.save(person);
		return new PersonDTO(person);
	}
	
	public PersonDTO update(Long id, PersonCreateDTO dto) {
		Person person = repository.dtoToEntity(dto);
		person.setId(id);
		person.setUpdatedAt(Instant.now());
		person = repository.save(person);
		return new PersonDTO(person);
	}
	
	public PersonDTO show(Long id) {
		Optional<Person> optPerson = repository.findById(id);
		Person person = optPerson.orElseThrow(() -> new ResourceNotFoundException());
		return new PersonDTO(person);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException();
		}
	}
}
