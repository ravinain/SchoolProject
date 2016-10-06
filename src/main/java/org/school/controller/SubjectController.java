package org.school.controller;

import java.util.List;

import javax.validation.Valid;

import org.school.exception.RestException;
import org.school.model.Subject;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.SubjectService;
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
public class SubjectController {

  @Autowired
  SubjectService subjectService;

  @Autowired
  private ApplicationContext context;

  @Autowired
  MessageSource messageSource;

  /**
   * Fetch all subjects.
   *
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/subjects", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<?> getAllSubjects() {
    final List<Subject> subjects = subjectService.getSubjects();
    if (subjects.isEmpty()) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource.getMessage(MessageConstant.NO_SUBJECT_FOUND, null, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);

    }
    return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
  }

  /**
   *
   * @param id.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/subject/{id:[1-9]{1}[0-9]*}", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<?> getSubject(@PathVariable("id") final int id) {
    final Subject subject = subjectService.getSubject(id);
    if (subject == null) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource.getMessage(MessageConstant.NO_SUBJECT_FOUND_BY_ID,
          new String[] { String.valueOf(id) }, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);

    }
    return new ResponseEntity<Subject>(subject, HttpStatus.OK);
  }

  /**
   *
   * @param subject.
   * @param result.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/subject/add", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<?> addSubject(@Valid @RequestBody final Subject subject,
      final BindingResult result) {
    final MessageList messageList = subjectService.saveSubject(subject, result);
    if (!messageList.getMessages().isEmpty()) {
      return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }

  /**
   *
   * @param id.
   * @param subject.
   * @param result.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/subject/update/{id:[1-9]{1}[0-9]*}", method = RequestMethod.PUT)
  public @ResponseBody ResponseEntity<?> updateSubject(@PathVariable("id") final int id,
      @Valid @RequestBody final Subject subject, final BindingResult result) {
    final MessageList messageList = subjectService.updateSubject(subject, result);
    if (!messageList.getMessages().isEmpty()) {
      return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<Subject>(subject, HttpStatus.OK);
  }

  /**
   *
   * @param id.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/subject/delete/{id:[1-9]{1}[0-9]*}", method = RequestMethod.DELETE)
  public @ResponseBody ResponseEntity<?> deleteSubject(@PathVariable("id") final int id) {
    final boolean delFlag = subjectService.deleteSubject(id);
    if (!delFlag) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource.getMessage(MessageConstant.NO_SUBJECT_FOUND_BY_ID,
          new String[] { String.valueOf(id) }, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Void>(HttpStatus.OK);
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
    final String errorMsg = exception.getMessage() == null
        ? "Exception occurred, see log for details." : exception.getMessage();
    exception.printStackTrace();
    final MessageList messageList = context.getBean(MessageList.class);
    final Message msg = context.getBean(Message.class);
    msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
    msg.setMessage(errorMsg);
    messageList.addMessage(msg);
    return new ResponseEntity<MessageList>(messageList, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
