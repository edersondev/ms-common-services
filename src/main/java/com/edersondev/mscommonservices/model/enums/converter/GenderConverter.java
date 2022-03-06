package com.edersondev.mscommonservices.model.enums.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.edersondev.mscommonservices.model.enums.Gender;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Gender gender) {
		if(gender == null) { return null; }
		return gender.getCode();
	}

	@Override
	public Gender convertToEntityAttribute(Integer code) {
		if(code == null) { return null; }
		return Gender.of(code);
	}

}
