package com.edersondev.mscommonservices.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edersondev.mscommonservices.dto.person.PersonCreateDTO;
import com.edersondev.mscommonservices.dto.person.PersonDTO;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.model.enums.Gender;
import com.edersondev.mscommonservices.repository.PersonRepository;
import com.edersondev.mscommonservices.service.exception.ResourceNotFoundException;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	public PersonDTO create(PersonCreateDTO dto) {
		Person person = this.objFromDTO(dto);
		person = repository.save(person);
		return new PersonDTO(person);
	}
	
	public PersonDTO update(Long id, PersonCreateDTO dto) {
		Person person = this.objFromDTO(dto);
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
	
	private Person objFromDTO(PersonCreateDTO dto) {
		Person person = new Person();
		person.setName(dto.getName());
		person.setBirthday(dto.getBirthday());
		person.setGender(Gender.valueOf(dto.getGender()));
		return person;
	}
}
