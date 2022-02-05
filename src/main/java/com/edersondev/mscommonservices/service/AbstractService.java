package com.edersondev.mscommonservices.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edersondev.mscommonservices.service.exception.ResourceNotFoundException;

public abstract class AbstractService<Repository extends JpaRepository<Entity,Long>,Entity> {

	@Autowired
	protected Repository repository;
	
	public Page<Entity> findAll(Integer pageNo,Integer pageSize,String sortBy,String sortDir) {
		if(pageSize > 100) { pageSize = 100; }
		Sort sort = (sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return repository.findAll(pageable);
	}
	
	public Entity show(Long id) {
		Optional<Entity> entity = repository.findById(id);
		return entity.orElseThrow(() -> new ResourceNotFoundException());
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException();
		}
	}
	
}
