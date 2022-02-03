package com.edersondev.mscommonservices.model.controlleradvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MethodArgumentNotValid {

	private Map<String,Set<String>> message = new HashMap<>();
	
	public MethodArgumentNotValid() {
	}
	
	public Map<String, Set<String>> getMessage() {
		return message;
	}

	public void setMessage(String field, String msg) {
		if(message.get(field) == null) {
			message.put(field, new HashSet<String>(Arrays.asList(msg)));
		}
		message.get(field).add(msg);
	}
}
