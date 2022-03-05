package com.edersondev.mscommonservices.model.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.edersondev.mscommonservices.model.enums.DocumentType;
import com.edersondev.mscommonservices.model.enums.converter.DocumentTypeConverter;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "tb_document")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(length = 20, nullable = false)
	private String number;
	
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Instant createdAt = Instant.now();
	
	private Instant updatedAt;
	
	@Convert(converter = DocumentTypeConverter.class)
	@Column(nullable = false, length = 10)
	private DocumentType documentType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_person", nullable = false)
	private Person person;
	
}
