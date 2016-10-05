package org.school.dao;

import java.util.List;

import org.school.model.Course;

public interface CourseDAO {
	
	List<Course> getAllCourse();
	
	Course getCourse(int id);
	
	Course getCourse(String description);
	
	Course saveCourse(Course course);
	
	Course updateCourse(Course course);
	
	void deleteCourse(int id);
	
	boolean isCourseExists(int id);
	
	boolean isCourseExists(String description);

}
