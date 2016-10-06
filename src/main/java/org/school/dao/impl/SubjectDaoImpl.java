package org.school.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.school.dao.SubjectDao;
import org.school.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "subjectDao")
public class SubjectDaoImpl implements SubjectDao {

  @Autowired
  SessionFactory sessionFactory;

  public List<Subject> getAllSubjects() {
    final Session session = sessionFactory.getCurrentSession();
    final List<Subject> subList = new ArrayList<Subject>();
    subList.addAll(new LinkedHashSet(session.createCriteria(Subject.class).list()));
    return subList;
  }

  public Subject getSubject(final int id) {
    final Session session = sessionFactory.getCurrentSession();
    return (Subject) session.get(Subject.class, id);
  }

  public Subject getSubject(final String name) {
    final Session session = sessionFactory.getCurrentSession();
    final List<Subject> subjects = session.createCriteria(Subject.class)
        .add(Restrictions.eq("description", name)).list();
    return subjects.isEmpty() ? null : subjects.get(0);
  }

  public Subject saveSubject(final Subject subject) {
    final Session session = sessionFactory.getCurrentSession();
    session.save(subject);
    return subject;
  }

  public Subject updateSubject(final Subject subject) {
    final Session session = sessionFactory.getCurrentSession();
    session.merge(subject);
    return subject;
  }

  public void deleteSubject(final int id) {
    final Session session = sessionFactory.getCurrentSession();
    session.delete(getSubject(id));
  }

  public boolean isSubjectExists(final int id) {
    return getSubject(id) != null;
  }

  public boolean isSubjectExists(final String name) {
    return getSubject(name) != null;
  }
}
