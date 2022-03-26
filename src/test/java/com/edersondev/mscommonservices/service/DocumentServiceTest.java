package com.edersondev.mscommonservices.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.edersondev.mscommonservices.dto.DocumentDTO;
import com.edersondev.mscommonservices.model.entity.Document;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.model.enums.DocumentType;
import com.edersondev.mscommonservices.repository.DocumentRepository;

@SpringBootTest
class DocumentServiceTest {

	private static final DocumentType TYPE = DocumentType.CPF;

	private static final String DOC_NUMBER = "123456";

	private static final long ID = 1L;

	@InjectMocks
	private DocumentService service;
	
	@Mock
	private DocumentRepository repository;
	
	@Spy
	private ModelMapper mapper;
	
	private Person person;
	private DocumentDTO dto;
	private Document document;
	private List<Document> listDocuments;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startDocument();
	}

	@Test
	void whenFindAllByPersonThenReturnSuccess() {
		when(repository.findAllByPerson(any(Person.class))).thenReturn(listDocuments);
		List<Document> response = service.findAllByPerson(1L);
		
		assertNotNull(response);
		assertEquals(ID, response.get(0).getId());
		assertEquals(DOC_NUMBER, response.get(0).getNumber());
		assertEquals(TYPE, response.get(0).getDocumentType());
	}

	@Test
	void whenCreateDocumentThenReturnSuccess() {
		when(repository.save(any(Document.class))).thenReturn(document);
		Document response = service.create(dto, person);
		
		assertNotNull(response);
		assertEquals(ID, response.getId());
		assertEquals(DOC_NUMBER, response.getNumber());
		assertEquals(TYPE, response.getDocumentType());
	}

	@Test
	void testCheckExistsByNumber() {
		fail("Not yet implemented");
	}

	void startDocument() {
		person = new Person();
		person.setId(ID);
		
		dto = new DocumentDTO();
		dto.setId(ID);
		dto.setNumber(DOC_NUMBER);
		dto.setDocumentType(TYPE);
		
		document = new Document();
		document.setId(ID);
		document.setNumber(DOC_NUMBER);
		document.setDocumentType(TYPE);
		
		listDocuments = List.of(document);
	}
}
