package org.school.service.impl;

import java.util.List;

import org.school.dao.RoleDao;
import org.school.model.Role;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service(value = "roleService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public final class RoleServiceImpl implements RoleService {

  @Autowired
  MessageSource messageSource;

  @Autowired
  RoleDao roleDao;

  // @Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
  public MessageList saveRole(final Role role, final BindingResult result) {
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
    } else if (!roleDao.isRoleExists(role.getName())) {
      roleDao.saveRole(role);
    } else {
      final Message message = new Message();
      message.setField("role");
      message.setMessage("Role already exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  // @Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
  public MessageList updateRole(final Role role, final BindingResult result) {
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
    } else if (roleDao.isRoleExists(role.getId())) {
      roleDao.updateRole(role);
    } else {
      final Message message = new Message();
      message.setField("role");
      message.setMessage("Role does not exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  // @Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
  public boolean deleteRole(final int id) {
    boolean delFlag = false;
    if (roleDao.isRoleExists(id)) {
      roleDao.deleteRole(id);
      delFlag = true;
    }
    return delFlag;
  }

  public List<Role> getRoles() {
    return roleDao.getRoles();
  }

  public Role getRole(final int id) {
    return roleDao.getRole(id);
  }

  public Role getRole(final String name) {
    return roleDao.getRole(name);
  }

}
