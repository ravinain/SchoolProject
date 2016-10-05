package org.school.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.school.dao.StudentDAO;
import org.school.model.Course;
import org.school.model.Student;
import org.school.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "studentRepo")
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Student addStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.save(student);
		return student;
	}

	public void deleteStudent(int id) {
		Session session = sessionFactory.getCurrentSession();
		Student student = (Student) session.get(Student.class, id);
		
		//Remove student from Subject's student property
		Set<Subject> subjects = student.getSubjects();
		for (Subject subject : subjects) {
			subject.getStudents().clear();;
		}
		//Remove student from course's Student property
		student.getCourse().getStudents().clear();
		session.merge(student);
		
		session.delete(student);
	}

	public Student getStudent(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Student) session.get(Student.class, id);
	}

	public boolean isStudentExists(int id) {
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = session.createCriteria(Student.class).add(Restrictions.eq("id", id)).list();
		return !students.isEmpty();
	}

	public Student updateStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(student);
		return student;
	}

	public List<Student> getStudents() {
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = new ArrayList<Student>();
		students.addAll(new LinkedHashSet<Student>(session.createCriteria(Student.class).list()));
		return students;
	}

}
