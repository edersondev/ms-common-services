package com.edersondev.mscommonservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edersondev.mscommonservices.dto.document.DocumentCreateDTO;
import com.edersondev.mscommonservices.model.entity.Document;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.repository.DocumentRepository;
import com.edersondev.mscommonservices.service.exception.BusinessRuleException;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository repository;
	
	public List<Document> findAllByPerson(Long idPerson){
		Person person = new Person();
		person.setId(idPerson);
		return repository.findAllByPerson(person);
	}
	
	public Document create(DocumentCreateDTO dto, Person person) {
		Document document = new Document(dto);
		this.checkExistsByNumber(dto.getNumber());
		document.setPerson(person);
		return repository.save(document);
	}
	
	public void checkExistsByNumber(String number) {
		boolean check = repository.existsByNumber(number);
		if(check) {
			throw new BusinessRuleException("document number alredy exists","document");
		}
	}
	
}
