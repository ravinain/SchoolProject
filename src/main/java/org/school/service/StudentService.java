package org.school.service;

import java.util.List;

import org.school.model.Student;
import org.school.response.MessageList;
import org.springframework.validation.BindingResult;

public interface StudentService {

  List<Student> getStudents();

  Student getStudent(int id);

  MessageList addStudent(Student student, BindingResult result);

  MessageList updateStudent(int id, Student student, BindingResult result);

  boolean deleteStudent(int id);

  boolean isStudentExists(int id);

}
