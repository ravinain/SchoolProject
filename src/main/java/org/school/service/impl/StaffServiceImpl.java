package org.school.service.impl;

import java.util.List;

import org.school.dao.StaffDao;
import org.school.model.Staff;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @see StaffService.
 * @author cdacr
 */
@Service(value = "staffService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public final class StaffServiceImpl implements StaffService {

  /** Staff Dao. */
  @Autowired
  private StaffDao staffDao;

  /** Message Source. */
  @Autowired
  private MessageSource messageSource;

  /**
   * @see StaffService.
   */
  public List<Staff> getStaffs() {
    return staffDao.getStaffs();
  }

  /**
   * @see StaffService.
   */
  public Staff getStaff(final int id) {
    return staffDao.getStaff(id);
  }

  /**
   * @see StaffService.
   */
  public MessageList addStaff(final Staff staff, final BindingResult result) {
    final MessageList messageList = new MessageList();
    if (result != null && result.hasErrors()) {
      final List<FieldError> fieldErrors = result.getFieldErrors();
      for (final FieldError fieldError : fieldErrors) {
        final Message message = new Message();
        message.setField(fieldError.getField());
        message.setMessage(
            messageSource.getMessage(fieldError.getCodes()[0], null, null));
        messageList.addMessage(message);
      }
    } else if (!staffDao.isStaffExists(staff.getName())) {
      staffDao.addStaff(staff);
    } else {
      final Message message = new Message();
      message.setField("staff");
      message.setMessage("Staff already exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  /**
   * @see StaffService.
   */
  public MessageList updateStaff(final int id, final Staff staff,
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
    } else if (staffDao.isStaffExists(id)) {
      staffDao.updateStaff(staff);
    } else {
      final Message message = new Message();
      message.setField("staff");
      message.setMessage("Staff ID : " + staff.getId() + ", does not exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  /**
   * @see StaffService.
   */
  public boolean deleteStaff(final int id) {
    boolean delFlag = false;
    if (staffDao.isStaffExists(id)) {
      delFlag = true;
      staffDao.deleteStaff(id);
    }
    return delFlag;
  }
}
