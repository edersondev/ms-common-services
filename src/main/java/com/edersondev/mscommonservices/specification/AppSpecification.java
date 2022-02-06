package com.edersondev.mscommonservices.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.edersondev.mscommonservices.util.SearchCriteria;


public class AppSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = 1L;
	
	private SearchCriteria criteria;
	
	public AppSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
            	return builder.like(
            			builder.upper(root.<String>get(criteria.getKey())), 
            			"%" + criteria.getValue().toString().toUpperCase() + "%"
            	);
            } else {
            	List<Long> items = new ArrayList<>();
            	String strValue = (String)criteria.getValue();
            	if(strValue.contains(",")) {
        			for(String item : Arrays.asList(strValue.split("\\s*,\\s*"))) {
        				items.add(Long.parseLong(item));
        			}
        			return builder.in(root.get(criteria.getKey())).value(items);
        		}
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
		return null;
	}
}
