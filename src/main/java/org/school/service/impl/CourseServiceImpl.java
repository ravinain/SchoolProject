package org.school.service.impl;

import java.util.List;

import org.school.dao.CourseDao;
import org.school.model.Course;
import org.school.model.Subject;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service(value = "courseService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public final class CourseServiceImpl implements CourseService {

  @Autowired
  MessageSource messageSource;

  @Autowired
  CourseDao courseDao;

  @Autowired
  ApplicationContext context;

  public List<Course> getAllCourses() {
    return courseDao.getAllCourse();
  }

  public Course getCourse(final int courseId) {
    return courseDao.getCourse(courseId);
  }

  public List<Subject> getCourseSubjects(final int courseId) {
    return null;
  }

  public boolean isCourseExists(final String courseName) {
    return courseDao.isCourseExists(courseName);
  }

  // @Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
  public MessageList addCourse(final Course course, final BindingResult result) {
    final MessageList messageList = context.getBean(MessageList.class);
    if (result != null && result.hasErrors()) {
      final List<FieldError> fieldErrors = result.getFieldErrors();
      for (final FieldError fieldError : fieldErrors) {
        final Message message = context.getBean(Message.class);
        message.setField(fieldError.getField());
        message.setMessage(messageSource.getMessage(fieldError.getCodes()[0], null, "", null));
        messageList.addMessage(message);
      }
    } else if (!isCourseExists(course.getDescription())) {
      courseDao.saveCourse(course);
    } else {
      final Message message = context.getBean(Message.class);
      message.setField("course");
      message.setMessage("Course already exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  // @Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
  public MessageList updateCourse(final int id, final Course course, final BindingResult result) {
    final MessageList messageList = context.getBean(MessageList.class);
    if (result.hasErrors()) {
      final List<FieldError> fieldErrors = result.getFieldErrors();
      for (final FieldError fieldError : fieldErrors) {
        final Message message = context.getBean(Message.class);
        message.setField(fieldError.getField());
        message.setMessage(messageSource.getMessage(fieldError.getCodes()[0], null, "", null));
        messageList.addMessage(message);
      }
    } else if (courseDao.isCourseExists(course.getId())) {
      courseDao.updateCourse(course);
    } else {
      final Message message = context.getBean(Message.class);
      message.setField("course");
      message.setMessage("Course does not exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  // @Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
  public boolean deleteCourse(final int id) {
    boolean delFlag = false;
    if (courseDao.isCourseExists(id)) {
      courseDao.deleteCourse(id);
      delFlag = true;
    }
    return delFlag;
  }

  public Course getCourse(final String description) {
    return courseDao.getCourse(description);
  }
}
