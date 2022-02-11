package com.edersondev.mscommonservices.model.enums;

public enum DocumentType {
	CPF(1),RG(2),PASSPORT(3);
	
	private int code;
	
	private DocumentType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static DocumentType valueOf(int code) {
		for(DocumentType value : DocumentType.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid document type");
	}
}
