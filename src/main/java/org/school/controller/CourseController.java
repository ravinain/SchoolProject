package org.school.controller;

import java.util.List;

import javax.validation.Valid;

import org.school.exception.RestException;
import org.school.model.Course;
import org.school.model.Subject;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.CourseService;
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

/**
 * Controller of Course API.
 * @author cdacr
 */
@Controller
public final class CourseController {

  /** CourseService. */
  @Autowired
  private CourseService courseService;

  /** ApplicationContext. */
  @Autowired
  private ApplicationContext context;

  /** MessageSource. */
  @Autowired
  private MessageSource messageSource;

  /**
   * Request mapping to get all courses.
   *
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/courses", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> getAllCourses() {
    final List<Course> courses = courseService.getAllCourses();
    if (courses.isEmpty()) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource.getMessage(MessageConstant.NO_COURSE_FOUND,
          null, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
  }

  /**
   * Return course detail of input id.
   * @param id
   *          course id
   * @return {@link ResponseEntity}
   */
  @RequestMapping(
      value = "/course/{id:[1-9]{1}[0-9]*}", method = RequestMethod.GET
  )
  @ResponseBody
  public ResponseEntity<?> getCourse(@PathVariable("id") final int id) {
    final Course course = courseService.getCourse(id);
    if (course == null) {
      final MessageList messageList = context.getBean(MessageList.class);
      final String[] args = new String[] {String.valueOf(id)};
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource
          .getMessage(MessageConstant.NO_COURSE_FOUND_BY_ID, args, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Course>(course, HttpStatus.OK);
  }

  /**
   * Method returns course details based on input course description.
   * @param description
   *          course description
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/course/{description}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> getCourse(
      @PathVariable("description") final String description) {
    final Course course = courseService.getCourse(description);
    if (course == null) {
      final MessageList messageList = context.getBean(MessageList.class);
      final String[] args = new String[] {description};
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource
          .getMessage(MessageConstant.NO_COURSE_FOUND_BY_NAME, args, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Course>(course, HttpStatus.OK);
  }

  /**
   * Fetch subjects of associated course id.
   * @param id
   *          course id
   * @return {@link ResponseEntity}
   */
  @RequestMapping(
      value = "/course/{id:[1-9]{1}[0-9]*}/subjects", method = RequestMethod.GET
  )
  @ResponseBody
  public ResponseEntity<?> getCourseSubjects(@PathVariable("id") final int id) {
    final List<Subject> subjects = courseService.getCourseSubjects(id);
    if (subjects.isEmpty()) {
      final MessageList messageList = context.getBean(MessageList.class);
      final String[] args = new String[] {String.valueOf(id)};
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource
          .getMessage(MessageConstant.NO_SUBJECT_FOUND_COURSE, args, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
  }

  /**
   * Add new course.
   * @param course
   *          {@link Course}
   * @param result
   *          {@link BindingResult}
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/course/add", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> addCourse(@Valid @RequestBody final Course course,
      final BindingResult result) {
    final MessageList messageList = courseService.addCourse(course, result);
    if (!messageList.getMessages().isEmpty()) {
      return new ResponseEntity<MessageList>(messageList,
          HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }

  /**
   * Update course.
   * @param id
   *          course id
   * @param course
   *          {@link Course}
   * @param result
   *          {@link BindingResult}
   * @return {@link ResponseEntity}
   */
  @RequestMapping(
      value = "/course/update/{id:[1-9]{1}[0-9]*}", method = RequestMethod.PUT
  )
  @ResponseBody
  public ResponseEntity<?> updateCourse(@PathVariable("id") final int id,
      @Valid @RequestBody final Course course, final BindingResult result) {
    final MessageList messageList = courseService.updateCourse(id, course,
        result);
    if (!messageList.getMessages().isEmpty()) {
      return new ResponseEntity<MessageList>(messageList,
          HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Course>(course, HttpStatus.OK);
  }

  /**
   * Delete course.
   * @param id
   *          course id
   * @return {@link ResponseEntity}
   */
  @RequestMapping(
      value = "/course/delete/{id:[1-9]{1}[0-9]*}",
      method = RequestMethod.DELETE
  )
  @ResponseBody
  public ResponseEntity<Void> deleteCourse(@PathVariable("id") final int id) {
    final boolean delFlag = courseService.deleteCourse(id);
    if (!delFlag) {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  /**
   * REST Exception handler.
   * @param restException
   *          {@link RestException}
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
    return new ResponseEntity<MessageList>(messageList,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Exception handler other than {@link RestException}.
   * @param exception
   *          {@link Exception}
   * @return {@link ResponseEntity}
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleAllException(final Exception exception) {
    String errorMsg = "Exception occurred, see log for details.";
    if (exception.getMessage() != null) {
      errorMsg = exception.getMessage();
    }

    exception.printStackTrace();
    final MessageList messageList = context.getBean(MessageList.class);
    final Message msg = context.getBean(Message.class);
    msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
    msg.setMessage(errorMsg);
    messageList.addMessage(msg);
    return new ResponseEntity<MessageList>(messageList,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
