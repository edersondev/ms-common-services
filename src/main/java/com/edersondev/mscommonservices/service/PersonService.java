package com.edersondev.mscommonservices.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edersondev.mscommonservices.dto.DocumentDTO;
import com.edersondev.mscommonservices.dto.PersonDTO;
import com.edersondev.mscommonservices.model.entity.Document;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.model.enums.DocumentType;
import com.edersondev.mscommonservices.model.enums.Gender;
import com.edersondev.mscommonservices.repository.PersonRepository;

@Service
public class PersonService extends AbstractService<PersonRepository,Person> {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private DocumentService documentService;
	
	@Transactional
	public Person create(PersonDTO dto) {
		Person person = repository.save(mapperDtoToPerson(dto));
		createDocument(dto,person);
		return person;
	}
	
	private Document createDocument(PersonDTO dto, Person person) {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setNumber(dto.getDocumentNumber());
		documentDTO.setDocumentType(DocumentType.CPF);
		return documentService.create(documentDTO, person);
	}
	
	public Person update(Long id, PersonDTO dto) {
		findById(id);
		dto.setId(id);
		Person person = mapperDtoToPerson(dto);
		person.setUpdatedAt(Instant.now());
		return repository.save(person);
	}
	
	private Person mapperDtoToPerson(PersonDTO dto) {
		Converter<Integer,Gender> genderConverter = ctx -> ctx.getSource() == null ? null : Gender.of(ctx.getSource());
		mapper.typeMap(PersonDTO.class, Person.class)
			.addMappings(m -> m.using(genderConverter)
					.map(PersonDTO::getGender, Person::setGender)
			);
		return mapper.map(dto, Person.class);
	}
	
}
