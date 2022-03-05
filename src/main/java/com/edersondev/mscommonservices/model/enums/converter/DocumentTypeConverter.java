package com.edersondev.mscommonservices.model.enums.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.edersondev.mscommonservices.model.enums.DocumentType;

@Converter(autoApply = true)
public class DocumentTypeConverter implements AttributeConverter<DocumentType, String> {

	@Override
	public String convertToDatabaseColumn(DocumentType documentType) {
		if(documentType == null) { return null; }
		return documentType.getCode();
	}
	
	@Override
	public DocumentType convertToEntityAttribute(String code) {
		if(code == null) { return null; }
		return Stream.of(DocumentType.values())
				.filter(dt -> dt.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid document type"));
	}
}
