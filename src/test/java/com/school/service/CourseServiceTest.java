package com.school.service;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.school.model.Course;
import org.school.model.Subject;
import org.school.service.CourseService;
import org.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config/hibernate-config.xml", "/config/servlet-config.xml",
		"/config/security-config.xml" })
public class CourseServiceTest {
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	SubjectService subjectService;
	
	@Test
	public void testAddCourse() {
		Course course = new Course();
		
		course.setDescription("Test Course");
		
		Set<Subject> subjects = course.getSubjects();
		subjects.add(subjectService.getSubject(1));
		
		courseService.addCourse(course, null);
	}

}
