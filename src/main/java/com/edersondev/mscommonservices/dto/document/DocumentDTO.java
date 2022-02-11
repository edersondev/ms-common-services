package com.edersondev.mscommonservices.dto.document;

import java.io.Serializable;

import com.edersondev.mscommonservices.model.entity.Document;

public class DocumentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String number;
	private Integer documentType;
	
	public DocumentDTO() {
		
	}

	public DocumentDTO(Long id, String number, Integer documentType) {
		this.id = id;
		this.number = number;
		this.documentType = documentType;
	}
	
	public DocumentDTO(Document entity) {
		id = entity.getId();
		number = entity.getNumber();
		documentType = entity.getDocumentType().getCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getDocumentType() {
		return documentType;
	}

	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}
	
}
