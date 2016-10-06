package org.school.controller;

import java.util.List;

import javax.validation.Valid;

import org.school.exception.RestException;
import org.school.model.Role;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.RoleService;
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
 * Role Controller.
 *
 * @author cdacr
 */
@Controller
public final class RoleController {

  @Autowired
  RoleService roleService;

  @Autowired
  private ApplicationContext context;

  @Autowired
  MessageSource messageSource;

  /**
   * Returns all roles.
   *
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/roles", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> getRoles() {
    final List<Role> roles = roleService.getRoles();
    if (roles.isEmpty()) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource.getMessage(MessageConstant.NO_ROLE_FOUND, null, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Role>>(roleService.getRoles(), HttpStatus.OK);
  }

  /**
   * Returns role associated with input id.
   *
   * @param id.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/role/{id:[1-9]{1}[0-9]*}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> getRole(@PathVariable("id") final int id) {
    final Role role = roleService.getRole(id);
    if (role == null) {
      final MessageList messageList = context.getBean(MessageList.class);
      final String[] args = new String[] { String.valueOf(id) };
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource.getMessage(MessageConstant.NO_ROLE_FOUND_BY_ID, args, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Role>(role, HttpStatus.OK);
  }

  /**
   * Fetch role information associated with role name.
   *
   * @param name.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/role/{name}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> getRole(@PathVariable("name") final String name) {
    final Role role = roleService.getRole(name);
    if (role == null) {
      final MessageList messageList = context.getBean(MessageList.class);
      final String[] args = new String[] { name };
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource.getMessage(MessageConstant.NO_ROLE_FOUND_BY_NAME, args, null));
      messageList.addMessage(msg);
      return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Role>(role, HttpStatus.OK);
  }

  /**
   * Add new role in database.
   *
   * @param role.
   * @param result.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/role/add", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> addRole(@Valid @RequestBody final Role role, final BindingResult res) {
    final MessageList messageList = roleService.saveRole(role, res);
    if (!messageList.getMessages().isEmpty()) {
      return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }

  /**
   * Update role in DB.
   *
   * @param id.
   * @param role.
   * @param result.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/role/update/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<?> updateRole(@PathVariable("id") final int id,
      @Valid @RequestBody final Role role, final BindingResult result) {
    final MessageList messageList = roleService.updateRole(role, result);
    if (!messageList.getMessages().isEmpty()) {
      return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<Role>(role, HttpStatus.OK);
  }

  /**
   * Delete role in DB.
   *
   * @param id.
   * @return {@link ResponseEntity}
   */
  @RequestMapping(value = "/role/delete/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<?> deleteRole(@PathVariable("id") final int id) {
    final boolean delFlag = roleService.deleteRole(id);
    if (!delFlag) {
      final MessageList messageList = context.getBean(MessageList.class);
      final Message msg = context.getBean(Message.class);
      msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
      msg.setMessage(messageSource.getMessage(MessageConstant.NO_ROLE_FOUND_BY_ID,
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
