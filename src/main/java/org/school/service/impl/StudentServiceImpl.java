package org.school.service.impl;

import java.util.List;

import org.school.dao.StudentDao;
import org.school.model.Student;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @see StudentService.
 * @author cdacr
 */
@Service(value = "studentService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public final class StudentServiceImpl implements StudentService {

  /** Message Source. */
  @Autowired
  private MessageSource messageSource;

  /** Student DAO. */
  @Autowired
  private StudentDao studentDao;

  /**
   * @see StudentService.
   */
  public List<Student> getStudents() {
    return studentDao.getStudents();
  }

  /**
   * @see StudentService.
   */
  public Student getStudent(final int id) {
    return studentDao.getStudent(id);
  }

  /**
   * @see StudentService.
   */
  public MessageList addStudent(final Student student,
      final BindingResult result) {
    final MessageList messageList = new MessageList();
    if (result.hasErrors()) {
      final List<FieldError> fieldErrors = result.getFieldErrors();
      for (final FieldError fieldError : fieldErrors) {
        final Message message = new Message();
        message.setField(fieldError.getField());
        message.setMessage(
            messageSource.getMessage(fieldError.getCodes()[0], null, null));
        messageList.addMessage(message);
      }
    } else if (!isStudentExists(student.getId())) {
      studentDao.addStudent(student);
    } else {
      final Message message = new Message();
      message.setField("student");
      message.setMessage("Student already exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  /**
   * @see StudentService.
   */
  public MessageList updateStudent(final int id, final Student student,
      final BindingResult result) {
    final MessageList messageList = new MessageList();
    if (result.hasErrors()) {
      final List<FieldError> fieldErrors = result.getFieldErrors();
      for (final FieldError fieldError : fieldErrors) {
        final Message message = new Message();
        message.setField(fieldError.getField());
        message.setMessage(
            messageSource.getMessage(fieldError.getCodes()[0], null, null));
        messageList.addMessage(message);
      }
    } else if (isStudentExists(student.getId())) {
      studentDao.updateStudent(student);
    } else {
      final Message message = new Message();
      message.setField("student");
      message.setMessage("Student does not exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  /**
   * @see StudentService.
   */
  public boolean deleteStudent(final int id) {
    boolean delFlag = false;
    if (isStudentExists(id)) {
      studentDao.deleteStudent(id);
      delFlag = true;
    }
    return delFlag;
  }

  /**
   * @see StudentService.
   */
  public boolean isStudentExists(final int id) {
    return studentDao.isStudentExists(id);
  }

}
