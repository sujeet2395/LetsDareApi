package com.learn.springbootrest.apikey.util;

public interface JwtSubject {
	
	public String convertSubjectToJson(Object subjectContainer);
	public <T> Object getSubjectFromJson(String subjectContainerStr, Class<T> targetClass);

}
