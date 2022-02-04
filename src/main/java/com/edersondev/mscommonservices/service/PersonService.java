package com.edersondev.mscommonservices.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	public Page<PersonDTO> findAll(Integer pageNo,Integer pageSize,String sortBy,String sortDir) {
		if(pageSize > 100) { pageSize = 100; }
		Sort sort = (sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Person> page = repository.findAll(pageable); 
		return page.map(entity -> new PersonDTO(entity));
	}
	
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
