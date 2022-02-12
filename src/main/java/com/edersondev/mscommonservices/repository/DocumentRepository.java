package com.edersondev.mscommonservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edersondev.mscommonservices.model.entity.Document;
import com.edersondev.mscommonservices.model.entity.Person;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	boolean existsByNumber(String documentNumber);
	
	List<Document> findAllByPerson(Person person);
}
