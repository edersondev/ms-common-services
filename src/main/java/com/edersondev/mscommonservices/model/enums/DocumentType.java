package com.edersondev.mscommonservices.model.enums;

public enum DocumentType {
	CPF("Cpf"),RG("Rg"),PASSPORT("Passport");

	private String code;
	
	private DocumentType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
