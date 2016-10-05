package org.school.service;

import java.util.List;

import org.school.model.Course;
import org.school.model.Subject;
import org.school.response.MessageList;
import org.springframework.validation.BindingResult;

public interface CourseService {

	List<Course> getAllCourses();
	
	Course getCourse(int courseId);
	
	Course getCourse(String description);
	
	List<Subject> getCourseSubjects(int courseId);
	
	boolean isCourseExists(String courseName);
	
	MessageList addCourse(Course course, BindingResult result);

	MessageList updateCourse(int id, Course course, BindingResult result);
	
	boolean deleteCourse(int id);
	
}
