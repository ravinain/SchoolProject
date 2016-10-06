package org.school.dao;

import java.util.List;

import org.school.model.Subject;

public interface SubjectDao {

  List<Subject> getAllSubjects();

  Subject getSubject(int id);

  Subject getSubject(String name);

  Subject saveSubject(Subject subject);

  Subject updateSubject(Subject subject);

  void deleteSubject(int id);

  boolean isSubjectExists(int id);

  boolean isSubjectExists(String name);

}
