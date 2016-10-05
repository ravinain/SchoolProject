package org.school.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.school.dao.CourseDAO;
import org.school.model.Course;
import org.school.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="courseDao")
public class CourseDAOImpl implements CourseDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Course> getAllCourse() {
		Session session = sessionFactory.getCurrentSession();
		return new ArrayList<Course>(new HashSet<Course>(session.createCriteria(Course.class).list()));
	}

	public Course getCourse(int id) {
		Session session = sessionFactory.getCurrentSession();
		Course course = (Course)session.get(Course.class, id);
		return course;
	}

	public Course getCourse(String description) {
		Session session = sessionFactory.getCurrentSession();
		List<Course> courses= session.createCriteria(Course.class).add(Restrictions.eq("description", description)).list();
		if(courses.isEmpty()) {
			return null;
		}
		return courses.get(0);
	}

	public Course saveCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.save(course);
		return course;
	}

	public Course updateCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(course);
		return course;
	}

	public void deleteCourse(int id) {
		Session session = sessionFactory.getCurrentSession();
		Course course = this.getCourse(id);
		Set<Subject> subjects = course.getSubjects();
		
		for(Subject subject:subjects) {
			Set<Course> courses = subject.getCourses();
			for(Course c:courses) {
				if(c.getId() == course.getId()) {
					courses.remove(c);
					session.merge(subject);
					break;
				}
			}
		}
		session.delete(course);
	}

	public boolean isCourseExists(int id) {
		Course course = this.getCourse(id);
		return course != null;
	}

	public boolean isCourseExists(String description) {
		Session session = sessionFactory.getCurrentSession();
		List<Course> courses= session.createCriteria(Course.class).add(Restrictions.eq("description", description)).list();
		return !courses.isEmpty();
	}

}
