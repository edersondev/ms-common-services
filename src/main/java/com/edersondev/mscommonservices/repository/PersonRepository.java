package com.edersondev.mscommonservices.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.edersondev.mscommonservices.model.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long>, JpaSpecificationExecutor<Person> {
	
}
