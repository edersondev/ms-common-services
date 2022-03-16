package com.edersondev.mscommonservices.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.test.context.SpringBootTest;

import com.edersondev.mscommonservices.dto.DocumentDTO;
import com.edersondev.mscommonservices.dto.PersonDTO;
import com.edersondev.mscommonservices.model.entity.Document;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.model.enums.DocumentType;
import com.edersondev.mscommonservices.model.enums.Gender;
import com.edersondev.mscommonservices.repository.DocumentRepository;
import com.edersondev.mscommonservices.repository.PersonRepository;

@SpringBootTest
class PersonServiceTest {

	private static final DocumentType DOCUMENT_TYPE = DocumentType.CPF;

	private static final String DOCUMENT_NUMBER = "123456";

	private static final int GENDER_INT = 1;

	private static final Gender GENDER = Gender.MALE;

	private static final String NAME = "Beltrano Brown";

	private static final LocalDate BITHDAY = LocalDate.now();

	private static final long ID = 1L;

	@InjectMocks
	private PersonService service;
	
	@Mock
	private DocumentService documentService;
	
	@Mock
	private DocumentRepository documentRepository;
	
	@Mock
	private PersonRepository repository;
	
	@Spy
	private ModelMapper mapper;
	
	@Mock
	private TypeMap<Integer,Gender> genderTm;
	
	private Person person = new Person();
	private PersonDTO personDto = new PersonDTO();
	
	private Document document = new Document();
	private DocumentDTO documentDto = new DocumentDTO();
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startPerson();
	}

	@Test
	void whenCreateThenReturnSuccess() {
		when(repository.save(any())).thenReturn(person);
		
		Person response = service.create(personDto);
		
		assertNotNull(response);
		assertEquals(Person.class, response.getClass());
		assertEquals(ID,response.getId());
		assertEquals(NAME,response.getName());
		assertEquals(BITHDAY,response.getBirthday());
		assertEquals(GENDER,response.getGender());
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSpecification() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}
	
	private void startPerson() {
		person.setId(ID);
		person.setBirthday(BITHDAY);
		person.setName(NAME);
		person.setGender(GENDER);
		
		personDto.setId(ID);
		personDto.setName(NAME);
		personDto.setBirthday(BITHDAY);
		personDto.setGender(GENDER_INT);
		personDto.setDocumentNumber(DOCUMENT_NUMBER);
		
		document.setId(ID);
		document.setNumber(DOCUMENT_NUMBER);
		document.setDocumentType(DOCUMENT_TYPE);
		
		documentDto.setId(ID);
		documentDto.setNumber(DOCUMENT_NUMBER);
		documentDto.setDocumentType(DOCUMENT_TYPE);
		
	}

}
