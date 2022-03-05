package com.edersondev.mscommonservices.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.edersondev.mscommonservices.repository.PersonRepository;

@SpringBootTest
public class PersonServiceTests {
	
	@InjectMocks
	private PersonService service;
	
	@Mock
	private PersonRepository repository;
	
	@BeforeEach
	void setup() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
}
