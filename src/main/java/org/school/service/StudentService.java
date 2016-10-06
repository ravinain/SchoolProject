package org.school.service;

import java.util.List;

import org.school.model.Student;
import org.school.response.MessageList;
import org.springframework.validation.BindingResult;

/**
 * Student Service.
 * @author cdacr
 */
public interface StudentService {

  /**
   * Fetch All Students.
   * @return {@link List} of {@link Student}
   */
  List<Student> getStudents();

  /**
   * Fetch Student.
   * @param id
   *          Student Id
   * @return {@link Student}
   */
  Student getStudent(int id);

  /**
   * Add Student.
   * @param student
   *          {@link Student}
   * @param result
   *          {@link BindingResult}
   * @return {@link MessageList}
   */
  MessageList addStudent(Student student, BindingResult result);

  /**
   * Update Student.
   * @param id
   *          Student Id
   * @param student
   *          {@link Student}
   * @param result
   *          {@link BindingResult}
   * @return {@link MessageList}
   */
  MessageList updateStudent(int id, Student student, BindingResult result);

  /**
   * Delete Student.
   * @param id
   *          Student Id
   * @return TRUE|FALSE
   */
  boolean deleteStudent(int id);

  /**
   * Check whether Student exists or not based on input Student Id.
   * @param id
   *          Student Id
   * @return TRUE|FALSE
   */
  boolean isStudentExists(int id);

}
