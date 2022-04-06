package com.edersondev.mscommonservices.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.edersondev.mscommonservices.dto.PersonDTO;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.model.enums.Gender;
import com.edersondev.mscommonservices.service.PersonService;

@SpringBootTest
class PersonControllerTest {

	private static final String DOC_NUMBER = "123456";

	private static final int GENDER_INT = 1;

	private static final Gender GENDER_MALE = Gender.MALE;

	private static final String NAME = "Beltrano Brown";

	private static final LocalDate BIRTHDAY = LocalDate.now();

	private static final long ID = 1L;

	@InjectMocks
	private PersonController controller;
	
	@Mock
	private PersonService service;
	
	@Spy
	private ModelMapper mapper;
	
	private Person person = new Person();
	private PersonDTO dto = new PersonDTO();
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startPerson();
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyLong())).thenReturn(person);
		
		ResponseEntity<PersonDTO> response = controller.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(PersonDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME, response.getBody().getName());
		assertEquals(BIRTHDAY, response.getBody().getBirthday());
		assertEquals(GENDER_INT, response.getBody().getGender());
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}
	
	void startPerson() {
		person.setId(ID);
		person.setBirthday(BIRTHDAY);
		person.setName(NAME);
		person.setGender(GENDER_MALE);
		
		dto.setId(ID);
		dto.setBirthday(BIRTHDAY);
		dto.setName(NAME);
		dto.setGender(GENDER_INT);
		dto.setDocumentNumber(DOC_NUMBER);
	}

}
