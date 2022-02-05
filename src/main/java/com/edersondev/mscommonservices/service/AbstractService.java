package com.edersondev.mscommonservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<Repository extends JpaRepository<Entity,Long>,Entity> {

	@Autowired
	protected Repository repository;
	
	public Page<Entity> findAll(Integer pageNo,Integer pageSize,String sortBy,String sortDir) {
		if(pageSize > 100) { pageSize = 100; }
		Sort sort = (sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return repository.findAll(pageable);
	}
	
}
