package com.edersondev.mscommonservices.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.edersondev.mscommonservices.dto.person.PersonCreateDTO;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.repository.PersonRepository;

@Service
public class PersonService extends AbstractService<PersonRepository,Person> {
	
	public Person create(PersonCreateDTO dto) {
		Person person = new Person(dto);
		return repository.save(person);
	}
	
	public void update(Long id, PersonCreateDTO dto) {
		Person person = this.show(id);
		person.populateObjFromDto(dto);
		person.setUpdatedAt(Instant.now());
		repository.save(person);
	}
	
}
