package com.edersondev.mscommonservices.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edersondev.mscommonservices.model.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
