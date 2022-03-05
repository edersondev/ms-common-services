package com.edersondev.mscommonservices.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.edersondev.mscommonservices.model.enums.Gender;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 150)
	private String name;
	
	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	@PastOrPresent
	private LocalDate birthday;
	
	private Gender gender;
	
	@NotNull
	private String documentNumber;

}
