package org.school.service.impl;

import java.util.List;

import org.school.dao.RoleDao;
import org.school.model.Role;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * {@inheritDoc}.
 */
@Service(value = "roleService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public final class RoleServiceImpl implements RoleService {

  /** */
  @Autowired
  private ApplicationContext context;

  /** Message Source. */
  @Autowired
  private MessageSource messageSource;

  /** Role DAO. */
  @Autowired
  private RoleDao roleDao;

  /**
   * {@inheritDoc}.
   */
  public MessageList saveRole(final Role role, final BindingResult result) {
    final MessageList messageList = context.getBean(MessageList.class);
    if (result.hasErrors()) {
      final List<FieldError> fieldErrors = result.getFieldErrors();
      for (final FieldError fieldError : fieldErrors) {
        final Message message = context.getBean(Message.class);
        message.setField(fieldError.getField());
        message.setMessage(
            messageSource.getMessage(fieldError.getCodes()[0], null, null));
        messageList.addMessage(message);
      }
    } else if (!roleDao.isRoleExists(role.getName())) {
      roleDao.saveRole(role);
    } else {
      final Message message = context.getBean(Message.class);
      message.setField("role");
      message.setMessage("Role already exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  /**
   * {@inheritDoc}.
   */
  public MessageList updateRole(final Role role, final BindingResult result) {
    final MessageList messageList = new MessageList();
    if (result.hasErrors()) {
      final List<FieldError> fieldErrors = result.getFieldErrors();
      for (final FieldError fieldError : fieldErrors) {
        final Message message = context.getBean(Message.class);
        message.setField(fieldError.getField());
        message.setMessage(
            messageSource.getMessage(fieldError.getCodes()[0], null, null));
        messageList.addMessage(message);
      }
    } else if (roleDao.isRoleExists(role.getId())) {
      roleDao.updateRole(role);
    } else {
      final Message message = context.getBean(Message.class);
      message.setField("role");
      message.setMessage("Role does not exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  /**
   * {@inheritDoc}.
   */
  public boolean deleteRole(final int id) {
    boolean delFlag = false;
    if (roleDao.isRoleExists(id)) {
      roleDao.deleteRole(id);
      delFlag = true;
    }
    return delFlag;
  }

  /**
   * {@inheritDoc}.
   */
  public List<Role> getRoles() {
    return roleDao.getRoles();
  }

  /**
   * {@inheritDoc}.
   */
  public Role getRole(final int id) {
    return roleDao.getRole(id);
  }

  /**
   * {@inheritDoc}.
   */
  public Role getRole(final String name) {
    return roleDao.getRole(name);
  }

}
