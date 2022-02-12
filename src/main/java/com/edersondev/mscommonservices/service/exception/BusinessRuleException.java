package com.edersondev.mscommonservices.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BusinessRuleException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String fieldName;

	public BusinessRuleException() {
		super();
	}
	
	public BusinessRuleException(String message, String fieldName) {
		super(message);
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
