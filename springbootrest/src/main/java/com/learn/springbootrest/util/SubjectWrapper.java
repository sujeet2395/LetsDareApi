package com.learn.springbootrest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.springbootrest.apikey.util.JwtSubject;

@Service
public class SubjectWrapper implements JwtSubject {
	
	
	private ObjectMapper objMapper;
	
	public SubjectWrapper(ObjectMapper objMapper) {
		this.objMapper=objMapper;
	}

	@Override
	public String convertSubjectToJson(Object subjectContainer) {
		String jsonString="";
		try {
			jsonString=objMapper.writeValueAsString(subjectContainer);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}

	@Override
	public <T> Object getSubjectFromJson(String subjectContainerStr, Class<T> targetClass) {
		Object subjectTarget=null;
		try {
			subjectTarget = objMapper.readValue(subjectContainerStr, targetClass);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subjectTarget;
	}
	
}
