package com.edersondev.mscommonservices.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edersondev.mscommonservices.dto.document.DocumentCreateDTO;
import com.edersondev.mscommonservices.dto.person.PersonCreateDTO;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.repository.PersonRepository;

@Service
public class PersonService extends AbstractService<PersonRepository,Person> {
	
	@Autowired
	private DocumentService documentService;
	
	@Transactional
	public Person create(PersonCreateDTO dto) {
		Person person = repository.save(new Person(dto));
		DocumentCreateDTO documentDTO = new DocumentCreateDTO(dto.getDocumentNumber(),1);
		documentService.create(documentDTO, person);
		return person;
	}
	
	public void update(Long id, PersonCreateDTO dto) {
		Person person = this.show(id);
		person.populateObjFromDto(dto);
		person.setUpdatedAt(Instant.now());
		repository.save(person);
	}
	
}
