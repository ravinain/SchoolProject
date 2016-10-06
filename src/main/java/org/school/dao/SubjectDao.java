package org.school.dao;

import java.util.List;

import org.school.model.Subject;

/**
 * Subject DAO.
 * @author cdacr
 */
public interface SubjectDao {

  /**
   * Fetch All Subjects.
   * @return {@link List} of {@link Subject}
   */
  List<Subject> getAllSubjects();

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
   * @return {@link Subject}
   */
  Subject saveSubject(Subject subject);

  /**
   * Update Subject.
   * @param subject
   *          {@link Subject}
   * @return {@link Subject}
   */
  Subject updateSubject(Subject subject);

  /**
   * Delete subject.
   * @param id
   *          Subject Id
   */
  void deleteSubject(int id);

  /**
   * Checks whether Subject exists or not based on Subject Id.
   * @param id
   *          Subject Id
   * @return TRUE|FALSE
   */
  boolean isSubjectExists(int id);

  /**
   * Checks whether Subject exists or not based on Subject Name.
   * @param name
   *          Subject Name
   * @return TRUE|FALSE
   */
  boolean isSubjectExists(String name);

}
