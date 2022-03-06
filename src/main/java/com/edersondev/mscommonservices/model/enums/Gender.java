package com.edersondev.mscommonservices.model.enums;

import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Gender {
	MALE(1),FEMALE(2);
	
	private Integer code;
	
	public static Gender of(Integer gender) {
		return Stream.of(Gender.values())
				.filter(g -> g.getCode() == gender)
				.findFirst()
				.orElse(null);
	}
}
