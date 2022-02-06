package com.edersondev.mscommonservices.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.edersondev.mscommonservices.service.exception.ResourceNotFoundException;
import com.edersondev.mscommonservices.specification.AppSpecificationsBuilder;


public abstract class AbstractService<Repository extends JpaRepository<Entity,Long> & JpaSpecificationExecutor<Entity>,Entity> {

	@Autowired
	protected Repository repository;
	
	public Page<Entity> findAll(Integer pageNo,Integer pageSize,String sortBy,String sortDir, String search) {
		if(pageSize > 100) { pageSize = 100; }
		Sort sort = (sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Specification<Entity> spec = this.getSpecification(search);
		return repository.findAll(spec, pageable);
	}
	
	public Specification<Entity> getSpecification(String search) {
		AppSpecificationsBuilder<Entity> builder = new AppSpecificationsBuilder<Entity>();
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+(?:,\\w+)*);", Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher = pattern.matcher(search + ";");
		while(matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}
		return builder.build();
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
