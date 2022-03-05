package com.edersondev.mscommonservices.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.edersondev.mscommonservices.model.enums.DocumentType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class DocumentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull
	private String number;
	
	@NotNull
	private DocumentType documentType;
	
}
