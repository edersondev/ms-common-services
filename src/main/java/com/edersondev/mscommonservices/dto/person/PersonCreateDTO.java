package com.edersondev.mscommonservices.dto.person;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.sun.istack.NotNull;

public class PersonCreateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 3, max = 150)
	private String name;
	
	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	@PastOrPresent
	private LocalDate birthday;
	
	private Integer gender;
	
	@NotBlank
	private String documentNumber;
	
	public PersonCreateDTO() {}

	public PersonCreateDTO(String name, LocalDate birthday, Integer gender, String documentNumber) {
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.documentNumber = documentNumber;
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

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
}
