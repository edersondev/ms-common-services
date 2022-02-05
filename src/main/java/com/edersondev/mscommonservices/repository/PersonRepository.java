package com.edersondev.mscommonservices.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edersondev.mscommonservices.dto.person.PersonCreateDTO;
import com.edersondev.mscommonservices.model.entity.Person;
import com.edersondev.mscommonservices.model.enums.Gender;

public interface PersonRepository extends JpaRepository<Person,Long> {

	default Person dtoToEntity(PersonCreateDTO dto) {
		Person person = new Person();
		person.setName(dto.getName());
		person.setBirthday(dto.getBirthday());
		person.setGender(Gender.valueOf(dto.getGender()));
		return person;
	}
}
