package org.school.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.school.dao.CourseDao;
import org.school.model.Course;
import org.school.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "courseDao")
public class CourseDaoImpl implements CourseDao {

  @Autowired
  private SessionFactory sessionFactory;

  public List<Course> getAllCourse() {
    final Session session = sessionFactory.getCurrentSession();
    return new ArrayList<Course>(new HashSet<Course>(session.createCriteria(Course.class).list()));
  }

  /**
   * @param id.
   * @return {@link Course}
   */
  public Course getCourse(final int id) {
    final Session session = sessionFactory.getCurrentSession();
    final Course course = (Course) session.get(Course.class, id);
    return course;
  }

  /**
   * @param description.
   * @return {@link Course}
   */
  public Course getCourse(final String description) {
    final Session session = sessionFactory.getCurrentSession();
    final List<Course> courses = session.createCriteria(Course.class)
        .add(Restrictions.eq("description", description)).list();
    if (courses.isEmpty()) {
      return null;
    }
    return courses.get(0);
  }

  /**
   * 
   * @param course.
   * @return {@link Course}
   */
  public Course saveCourse(final Course course) {
    final Session session = sessionFactory.getCurrentSession();
    session.save(course);
    return course;
  }

  /**
   * 
   * @param course.
   * @return {@link Course}
   */
  public Course updateCourse(final Course course) {
    final Session session = sessionFactory.getCurrentSession();
    session.merge(course);
    return course;
  }

  /**
   * 
   * @param id.
   */
  public void deleteCourse(final int id) {
    final Session session = sessionFactory.getCurrentSession();
    final Course course = this.getCourse(id);
    final Set<Subject> subjects = course.getSubjects();

    for (final Subject subject : subjects) {
      final Set<Course> courses = subject.getCourses();
      for (final Course c : courses) {
        if (c.getId() == course.getId()) {
          courses.remove(c);
          session.merge(subject);
          break;
        }
      }
    }
    session.delete(course);
  }

  public boolean isCourseExists(final int id) {
    final Course course = this.getCourse(id);
    return course != null;
  }

  /**
   * 
   * @param description.
   * @return {@link Boolean}
   */
  public boolean isCourseExists(final String description) {
    final Session session = sessionFactory.getCurrentSession();
    final List<Course> courses = session.createCriteria(Course.class)
        .add(Restrictions.eq("description", description)).list();
    return !courses.isEmpty();
  }

}
