package org.school.service;

import java.util.List;

import org.school.model.Course;
import org.school.model.Subject;
import org.school.response.MessageList;
import org.springframework.validation.BindingResult;

/**
 * Course Service.
 * @author cdacr
 *
 */
public interface CourseService {

  /**
   * Fetch All Courses.
   * @return {@link List} of {@link Course}
   */
  List<Course> getAllCourses();

  /**
   * Fetch Course.
   * @param courseId
   *          Course Id
   * @return {@link Course}
   */
  Course getCourse(int courseId);

  /**
   * Fetch Course.
   * @param description
   *          Course Description
   * @return {@link Course}
   */
  Course getCourse(String description);

  /**
   * Fetch All subjects associated with input Course Id.
   * @param courseId
   *          Course Id
   * @return {@link List} of {@link Subject}
   */
  List<Subject> getCourseSubjects(int courseId);

  /**
   * Checks whether course exists or not based on input Course Name.
   * @param courseName
   *          Course Name
   * @return TRUE|FALSE
   */
  boolean isCourseExists(String courseName);

  /**
   * Add Course.
   * @param course
   *          {@link Course}
   * @param result
   *          {@link BindingResult}
   * @return {@link MessageList}
   */
  MessageList addCourse(Course course, BindingResult result);

  /**
   * Update Course.
   * @param id
   *          Course Id
   * @param course
   *          {@link Course}
   * @param result
   *          {@link BindingResult}
   * @return {@link MessageList}
   */
  MessageList updateCourse(int id, Course course, BindingResult result);

  /**
   * Delete Course.
   * @param id
   *          Course Id
   * @return TRUE|FALSE
   */
  boolean deleteCourse(int id);

}
