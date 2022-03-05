package com.edersondev.mscommonservices.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edersondev.mscommonservices.dto.DocumentDTO;
import com.edersondev.mscommonservices.dto.PersonDTO;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.model.enums.DocumentType;
import com.edersondev.mscommonservices.repository.PersonRepository;

@Service
public class PersonService extends AbstractService<PersonRepository,Person> {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private DocumentService documentService;
	
	@Transactional
	public Person create(PersonDTO dto) {
		Person person = repository.save(mapper.map(dto, Person.class));
		
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setNumber(dto.getDocumentNumber());
		documentDTO.setDocumentType(DocumentType.CPF);
		
		documentService.create(documentDTO, person);
		return person;
	}
	
	public Person update(Long id, PersonDTO dto) {
		findById(id);
		dto.setId(id);
		Person person = mapper.map(dto, Person.class);
		person.setUpdatedAt(Instant.now());
		return repository.save(person);
	}
	
}
