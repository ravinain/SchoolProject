package org.school.controller;

import java.util.List;

import javax.validation.Valid;

import org.school.exception.RestException;
import org.school.model.Student;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.StudentService;
import org.school.util.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public final class StudentController {

  @Autowired
  private StudentService studentService;

  @Autowired
  private ApplicationContext context;

  @Autowired
  MessageSource messageSource;

  /**
   * Fetch all students details.
   *
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/students", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<?> getStudents() {
    final List<Student> students = studentService.getStudents();
    if (students.isEmpty()) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource.getMessage(MessageConstant.NO_STUDENT_FOUND, null, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
  }

  /**
   *
   * @param id.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/student/{id:[1-9]{1}[0-9]*}", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<?> getStudent(@PathVariable("id") final int id) {
    final Student student = studentService.getStudent(id);
    if (student == null) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource.getMessage(MessageConstant.NO_STUDENT_FOUND_BY_ID,
          new String[] { String.valueOf(id) }, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Student>(student, HttpStatus.OK);
  }

  /**
   *
   * @param student.
   * @param result.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/student/add", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<?> addStudent(@RequestBody @Valid final Student student,
      final BindingResult result) {
    final MessageList messageList = studentService.addStudent(student, result);
    if (messageList.getMessages().isEmpty()) {
      return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }
    return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
  }

  /**
   *
   * @param id.
   * @param student.
   * @param result.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/student/update/{id:[1-9]{1}[0-9]*}", method = RequestMethod.PUT)
  public @ResponseBody ResponseEntity<?> updateStudent(@PathVariable("id") final int id,
      @Valid @RequestBody final Student student, final BindingResult result) {
    final MessageList messageList = studentService.updateStudent(id, student, result);
    if (messageList.getMessages().isEmpty()) {
      return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
    return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
  }

  /**
   *
   * @param id.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/student/delete/{id:[1-9]{1}[0-9]*}", method = RequestMethod.DELETE)
  public @ResponseBody ResponseEntity<?> deleteStudent(@PathVariable("id") final int id) {
    if (!studentService.deleteStudent(id)) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource.getMessage(MessageConstant.NO_ROLE_FOUND_BY_ID,
          new String[] { String.valueOf(id) }, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Integer>(id, HttpStatus.OK);
  }

  /**
   *
   * @param restException.
   * @return {@link ResponseEntity}
   */
  @ExceptionHandler(RestException.class)
  public ResponseEntity<?> handleException(final RestException restException) {
    restException.printStackTrace();
    final MessageList messageList = context.getBean(MessageList.class);
    final Message msg = context.getBean(Message.class);
    msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
    msg.setMessage(restException.getErrorMsg());
    messageList.addMessage(msg);
    return new ResponseEntity<MessageList>(messageList, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   *
   * @param exception.
   * @return {@link ResponseEntity}
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleAllException(final Exception exception) {
    String errorMsg = "Exception occurred, see log for details.";
    if (exception.getMessage() != null) {
      errorMsg = exception.getMessage();
    }
    final MessageList messageList = context.getBean(MessageList.class);
    final Message msg = context.getBean(Message.class);
    msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
    msg.setMessage(errorMsg);
    messageList.addMessage(msg);
    return new ResponseEntity<MessageList>(messageList, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
