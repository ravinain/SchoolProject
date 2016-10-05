package org.school.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.school.dao.SubjectDAO;
import org.school.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "subjectDao")
public class SubjectDAOImpl implements SubjectDAO {

	@Autowired
	SessionFactory sessionFactory;

	public List<Subject> getAllSubjects() {
		Session session = sessionFactory.getCurrentSession();
		List<Subject> subList = new ArrayList<Subject>();
		subList.addAll(new LinkedHashSet(session.createCriteria(Subject.class).list()));
		return subList;
	}

	public Subject getSubject(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Subject) session.get(Subject.class, id);
	}

	public Subject getSubject(String name) {
		Session session = sessionFactory.getCurrentSession();
		List<Subject> subjects = session.createCriteria(Subject.class).add(Restrictions.eq("description", name)).list();
		return subjects.isEmpty() ? null : subjects.get(0);
	}

	public Subject saveSubject(Subject subject) {
		Session session = sessionFactory.getCurrentSession();
		session.save(subject);
		return subject;
	}

	public Subject updateSubject(Subject subject) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(subject);
		return subject;
	}

	public void deleteSubject(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getSubject(id));
	}

	public boolean isSubjectExists(int id) {
		return getSubject(id) != null;
	}

	public boolean isSubjectExists(String name) {
		return getSubject(name) != null;
	}
}
