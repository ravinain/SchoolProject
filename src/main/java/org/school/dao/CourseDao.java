package org.school.dao;

import java.util.List;

import org.school.model.Course;

/**
 * Course DAO.
 * @author cdacr
 */
public interface CourseDao {

  /**
   * Fetch All course.
   * @return {@link List} of {@link Course}
   */
  List<Course> getAllCourse();

  /**
   * Fetch course detail of input course id.
   * @param id
   *          Course Id
   * @return {@link Course}
   */
  Course getCourse(int id);

  /**
   * Fetch course detail of input course description.
   * @param description
   *          Course Description/Name
   * @return {@link Course}
   */
  Course getCourse(String description);

  /**
   * Save course and return the same Course object with id.
   * @param course
   *          {@link Course}
   * @return {@link Course}
   */
  Course saveCourse(Course course);

  /**
   * Update course and return the updated Course object.
   * @param course
   *          {@link Course}
   * @return {@link Course}
   */
  Course updateCourse(Course course);

  /**
   * Delete course of input id.
   * @param id
   *          Course Id
   */
  void deleteCourse(int id);

  /**
   * Checks whether Course exists or not based on Course Id.
   * @param id
   *          Course Id.
   * @return TRUE|FALSE
   */
  boolean isCourseExists(int id);

  /**
   * Checks whether Course exists or not based on Course Description.
   * @param description
   *          Course Description
   * @return TRUE|FALSE
   */
  boolean isCourseExists(String description);

}
