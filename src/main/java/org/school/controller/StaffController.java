package org.school.controller;

import java.util.List;

import javax.validation.Valid;

import org.school.exception.RestException;
import org.school.model.Staff;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.StaffService;
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
public final class StaffController {

  /** StaffService. */
  @Autowired
  StaffService staffService;

  /** ApplicationContext. */
  @Autowired
  private ApplicationContext context;

  /** MessageSource. */
  @Autowired
  private MessageSource messageSource;

  /**
   * Fetch all staffs.
   *
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/staffs", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> getStaffs() {
    final List<Staff> staffs = staffService.getStaffs();
    if (staffs.isEmpty()) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(
          messageSource.getMessage(MessageConstant.NO_STAFF_FOUND, null, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);

    }
    return new ResponseEntity<List<Staff>>(staffs, HttpStatus.OK);
  }

  /**
   *
   * @param id.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/staff/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> getStaff(
      @PathVariable("id") final int id) {
    final Staff staff = staffService.getStaff(id);
    if (staff == null) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(
          messageSource.getMessage(MessageConstant.NO_STAFF_FOUND_BY_ID,
              new String[] { String.valueOf(id) }, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Staff>(staffService.getStaff(id), HttpStatus.OK);
  }

  /**
   *
   * @param staff.
   * @param result.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/staff/add", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> addStaff(
      @RequestBody @Valid final Staff staff, final BindingResult result) {
    final MessageList messageList = staffService.addStaff(staff, result);
    if (!messageList.getMessages().isEmpty()) {
      return new ResponseEntity<MessageList>(messageList,
          HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }

  /**
   *
   * @param id.
   * @param staff.
   * @param result.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/staff/update/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<?> updateStaff(
      @PathVariable("id") final int id, @Valid @RequestBody final Staff staff,
      final BindingResult result) {
    final MessageList messageList = staffService.updateStaff(id, staff, result);
    if (!messageList.getMessages().isEmpty()) {
      return new ResponseEntity<MessageList>(messageList,
          HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Staff>(staff, HttpStatus.OK);
  }

  /**
   *
   * @param id.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/staff/delete/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<?> deleteStaff(
      @PathVariable("id") final int id) {
    if (!staffService.deleteStaff(id)) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(
          messageSource.getMessage(MessageConstant.NO_STAFF_FOUND_BY_ID,
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
    return new ResponseEntity<MessageList>(messageList,
        HttpStatus.INTERNAL_SERVER_ERROR);
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
    return new ResponseEntity<MessageList>(messageList,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
