package com.edersondev.mscommonservices.model.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.edersondev.mscommonservices.model.enums.Gender;
import com.edersondev.mscommonservices.model.enums.converter.GenderConverter;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "tb_person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDate birthday;
	
	@Convert(converter = GenderConverter.class)
	@Column(columnDefinition="smallint")
	private Gender gender;
	
	@OneToMany(mappedBy = "person",cascade = CascadeType.REMOVE)
	private List<Document> documents = new ArrayList<>();
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Instant createdAt = Instant.now();
	
	private Instant updatedAt;
	
}
