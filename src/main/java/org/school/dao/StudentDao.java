package org.school.dao;

import java.util.List;

import org.school.model.Student;

/**
 * Student DAO.
 * @author cdacr
 */
public interface StudentDao {

  /**
   * Add Student.
   * @param student
   *          {@link Student}
   * @return {@link Student}
   */
  Student addStudent(Student student);

  /**
   * Delete Student.
   * @param id
   *          Student Id
   */
  void deleteStudent(int id);

  /**
   * Fetch Student based on input Student Id.
   * @param id
   *          Student Id
   * @return {@link Student}
   */
  Student getStudent(int id);

  /**
   * Fetch All Students.
   * @return {@link List} of {@link Student}
   */
  List<Student> getStudents();

  /**
   * Checks whether Student exists or not based on input Student Id.
   * @param id
   *          Student Id
   * @return TRUE|FALSE
   */
  boolean isStudentExists(int id);

  /**
   * Update Student.
   * @param student
   *          {@link Student}
   * @return {@link Student}
   */
  Student updateStudent(Student student);

}
