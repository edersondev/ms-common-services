package com.edersondev.mscommonservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edersondev.mscommonservices.dto.person.PersonCreateDTO;
import com.edersondev.mscommonservices.dto.person.PersonDTO;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.model.enums.Gender;
import com.edersondev.mscommonservices.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	public PersonDTO create(PersonCreateDTO dto) {
		Person person = this.objFromDTO(dto);
		person = repository.save(person);
		return new PersonDTO(person);
	}
	
	private Person objFromDTO(PersonCreateDTO dto) {
		Person person = new Person();
		person.setName(dto.getName());
		person.setBirthday(dto.getBirthday());
		person.setGender(Gender.valueOf(dto.getGender()));
		return person;
	}
}
