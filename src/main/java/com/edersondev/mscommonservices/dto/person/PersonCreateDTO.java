package com.edersondev.mscommonservices.dto.person;

import java.io.Serializable;
import java.time.LocalDate;

public class PersonCreateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private LocalDate birthday;
	private Integer gender;
	
	public PersonCreateDTO() {}

	public PersonCreateDTO(String name, LocalDate birthday, Integer gender) {
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
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
