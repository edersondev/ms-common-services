package com.edersondev.mscommonservices.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

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
import com.edersondev.mscommonservices.service.exception.ResourceNotFoundException;

@SpringBootTest
class PersonServiceTest {

	private static final DocumentType DOCUMENT_TYPE = DocumentType.CPF;

	private static final String DOCUMENT_NUMBER = "123456";

	private static final int GENDER_INT = 1;

	private static final Gender GENDER = Gender.MALE;

	private static final String NAME = "Beltrano Brown";

	private static final LocalDate BIRTHDAY = LocalDate.now();

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
	private Optional<Person> optionalPerson;
	
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
		when(documentRepository.save(any())).thenReturn(document);
		
		DocumentService docService = mock(DocumentService.class);
		when(docService.create(any(), any())).thenReturn(document);
		
		Person response = service.create(personDto);
		
		assertNotNull(response);
		assertEquals(Person.class, response.getClass());
		assertEquals(ID,response.getId());
		assertEquals(NAME,response.getName());
		assertEquals(BIRTHDAY,response.getBirthday());
		assertEquals(GENDER,response.getGender());
	}
	
	@Test
	void whenCreateDocumentThenReturnSuccess() {
		when(documentRepository.save(any())).thenReturn(document);
		
		DocumentService docService = mock(DocumentService.class);
		when(docService.create(any(), any())).thenReturn(document);
		
		Document response = docService.create(documentDto, person);
		
		assertNotNull(response);
		assertEquals(Document.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(DOCUMENT_NUMBER, response.getNumber());
	}

	@Test
	void whenUpdateThenReturnSuccess() {
		when(repository.findById(anyLong())).thenReturn(optionalPerson);
		
		person.setGender(null);
		when(repository.save(any())).thenReturn(person);
		
		personDto.setGender(null);
		Person response = service.update(ID, personDto);
		
		assertNotNull(response);
		assertEquals(Person.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(BIRTHDAY, response.getBirthday());
		assertNull(response.getGender());
	}
	
	@Test
	void whenUpdateThenReturnResourceNotFoundException() {
		when(repository.findById(anyLong())).thenThrow(new ResourceNotFoundException());
		try {
			service.update(ID, personDto);
		} catch (Exception ex) {
			assertEquals(ResourceNotFoundException.class, ex.getClass());
		}
	}
	
	@Test
	void whenFindByIdThenReturnSuccess() {
		when(repository.findById(anyLong())).thenReturn(optionalPerson);
		Person response = service.findById(ID);
		
		assertNotNull(response);
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(BIRTHDAY, response.getBirthday());
		assertEquals(GENDER, response.getGender());
	}
	
	@Test
	void whenFindByIdThenReturnResourceNotFoundException() {
		when(repository.findById(anyLong())).thenThrow(new ResourceNotFoundException());
		try {
			service.findById(ID);
		} catch (Exception ex) {
			assertEquals(ResourceNotFoundException.class, ex.getClass());
		}
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
		person.setBirthday(BIRTHDAY);
		person.setName(NAME);
		person.setGender(GENDER);
		
		personDto.setId(ID);
		personDto.setName(NAME);
		personDto.setBirthday(BIRTHDAY);
		personDto.setGender(GENDER_INT);
		personDto.setDocumentNumber(DOCUMENT_NUMBER);
		
		document.setId(ID);
		document.setNumber(DOCUMENT_NUMBER);
		document.setDocumentType(DOCUMENT_TYPE);
		
		documentDto.setId(ID);
		documentDto.setNumber(DOCUMENT_NUMBER);
		documentDto.setDocumentType(DOCUMENT_TYPE);
		
		Person opPerson = new Person();
		opPerson.setId(ID);
		opPerson.setBirthday(BIRTHDAY);
		opPerson.setName(NAME);
		opPerson.setGender(GENDER);
		optionalPerson = Optional.of(opPerson);
		
	}

}
