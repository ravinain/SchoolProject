package org.school.service;

import java.util.List;

import org.school.model.Subject;
import org.school.response.MessageList;
import org.springframework.validation.BindingResult;

public interface SubjectService {
	
	List<Subject> getSubjects();
	
	Subject getSubject(int id);
	
	Subject getSubject(String name);
	
	MessageList saveSubject(Subject subject, BindingResult result);
	
	MessageList updateSubject(Subject subject, BindingResult result);
	
	boolean deleteSubject(int id);
	
	boolean isSubjectExists(int id);
	
	boolean isSubjectExists(String name);
}
