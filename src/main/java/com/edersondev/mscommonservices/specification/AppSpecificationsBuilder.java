package com.edersondev.mscommonservices.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.edersondev.mscommonservices.util.SearchCriteria;


public class AppSpecificationsBuilder<T> {

	private final List<SearchCriteria> params;
	
	public AppSpecificationsBuilder() {
		params = new ArrayList<SearchCriteria>();
	}
	
	public AppSpecificationsBuilder<T> with(String key, String operation, Object value) {
		params.add(new SearchCriteria(key,operation,value));
		return this;
	}
	
	public Specification<T> build() {
		if(params.size() == 0) {
			return null;
		}
		Specification<T> result = new AppSpecification<T>(params.get(0));
		for (int i = 1; i < params.size(); i++) {
			result = params.get(i).isOrPredicate()
					? Specification.where(result).or(new AppSpecification<T>(params.get(i)))
					: Specification.where(result).and(new AppSpecification<T>(params.get(i)));
		}
		return result;
	}
}
