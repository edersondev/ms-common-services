package com.edersondev.mscommonservices.dto.person;

import java.io.Serializable;
import java.time.LocalDate;

import com.edersondev.mscommonservices.model.entity.Person;

public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private LocalDate birthday;
	private Integer gender;

	public PersonDTO() {}

	public PersonDTO(Long id, String name, LocalDate birthday, Integer gender) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
	}
	
	public PersonDTO(Person entity) {
		id = entity.getId();
		name = entity.getName();
		birthday = entity.getBirthday();
		gender = (entity.getGender() == null ? null : entity.getGender().getCode());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
}
