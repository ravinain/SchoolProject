package org.school.service;

import java.util.List;

import org.school.model.Subject;
import org.school.response.MessageList;
import org.springframework.validation.BindingResult;

/**
 * Subject Service.
 * @author cdacr
 */
public interface SubjectService {

  /**
   * Fetch All Subjects.
   * @return {@link List} of {@link Subject}
   */
  List<Subject> getSubjects();

  /**
   * Fetch Subject.
   * @param id
   *          Subject Id
   * @return {@link Subject}
   */
  Subject getSubject(int id);

  /**
   * Fetch Subject.
   * @param name
   *          Subject Name
   * @return {@link Subject}
   */
  Subject getSubject(String name);

  /**
   * Add Subject.
   * @param subject
   *          {@link Subject}
   * @param result
   *          {@link BindingResult}
   * @return {@link MessageList}
   */
  MessageList saveSubject(Subject subject, BindingResult result);

  /**
   * Update Subject.
   * @param subject
   *          {@link Subject}
   * @param result
   *          {@link BindingResult}
   * @return {@link MessageList}
   */
  MessageList updateSubject(Subject subject, BindingResult result);

  /**
   * Delete Subject.
   * @param id
   *          Subject Id
   * @return TRUE|FALSE
   */
  boolean deleteSubject(int id);

  /**
   * Check whether subject exists or not based on Subject Id.
   * @param id
   *          Subject Id
   * @return TRUE|FALSE
   */
  boolean isSubjectExists(int id);

  /**
   * Check whether subject exists or not based on Subject Name.
   * @param name
   *          Subject Name
   * @return TRUE|FALSE
   */
  boolean isSubjectExists(String name);
}
