package org.school.dao;

import java.util.List;

import org.school.model.Student;

public interface StudentDao {

  Student addStudent(Student student);

  void deleteStudent(int id);

  Student getStudent(int id);

  List<Student> getStudents();

  boolean isStudentExists(int id);

  Student updateStudent(Student student);

}
