package com.edersondev.mscommonservices.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edersondev.mscommonservices.dto.document.DocumentDTO;
import com.edersondev.mscommonservices.service.DocumentService;

@RestController
@RequestMapping(value = "/persons/documents")
public class DocumentController {

	@Autowired
	private DocumentService service;
	
	@GetMapping(value = "/{idPerson}")
	public ResponseEntity<List<DocumentDTO>> findAll(@PathVariable Long idPerson) {
		List<DocumentDTO> list = service.findAllByPerson(idPerson)
				.stream().map(entity -> new DocumentDTO(entity)).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
}
