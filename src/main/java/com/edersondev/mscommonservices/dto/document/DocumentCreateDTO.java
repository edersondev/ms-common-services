package com.edersondev.mscommonservices.dto.document;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DocumentCreateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String number;
	
	@NotNull
	private Integer documentType;
	
	public DocumentCreateDTO() {
		
	}

	public DocumentCreateDTO(String number, Integer documentType) {
		this.number = number;
		this.documentType = documentType;
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
