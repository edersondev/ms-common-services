package com.edersondev.mscommonservices.exception;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.edersondev.mscommonservices.model.controlleradvice.MethodArgumentNotValid;
import com.edersondev.mscommonservices.service.exception.BusinessRuleException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public MethodArgumentNotValid handleNotValidException(MethodArgumentNotValidException ex) {
		MethodArgumentNotValid responseMsg = new MethodArgumentNotValid();
		BindingResult bindingResult = ex.getBindingResult();
		for (ObjectError error : bindingResult.getAllErrors()) {
			FieldError field = (FieldError) error;
			responseMsg.setMessage(field.getField(),error.getDefaultMessage());
		}
		return responseMsg;
	}
	
	@ExceptionHandler(DateTimeParseException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public MethodArgumentNotValid handleDateTimeParseException(DateTimeParseException ex) {
		MethodArgumentNotValid responseMsg = new MethodArgumentNotValid();
		responseMsg.setMessage(String.valueOf(ex.getErrorIndex()),ex.getMessage());
		return responseMsg;
	}
	
	@ExceptionHandler(BusinessRuleException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public MethodArgumentNotValid handleBusinessRuleException(BusinessRuleException ex) {
		MethodArgumentNotValid responseMsg = new MethodArgumentNotValid();
		responseMsg.setMessage(ex.getFieldName(),ex.getMessage());
		return responseMsg;
	}
}
