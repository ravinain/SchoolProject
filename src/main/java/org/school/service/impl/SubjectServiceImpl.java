package org.school.service.impl;

import java.util.List;

import org.school.dao.SubjectDao;
import org.school.model.Subject;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service(value = "subjectService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public final class SubjectServiceImpl implements SubjectService {

  @Autowired
  SubjectDao subjectDao;

  @Autowired
  MessageSource messageSource;

  public List<Subject> getSubjects() {
    return subjectDao.getAllSubjects();
  }

  public Subject getSubject(final int id) {
    return subjectDao.getSubject(id);
  }

  public Subject getSubject(final String name) {
    return subjectDao.getSubject(name);
  }

  // @Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
  public MessageList saveSubject(final Subject subject,
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
    } else if (!isSubjectExists(subject.getDescription())) {
      subjectDao.saveSubject(subject);
    } else {
      final Message message = new Message();
      message.setField("subject");
      message.setMessage("Subject already exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  // @Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
  public MessageList updateSubject(final Subject subject,
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
    } else if (isSubjectExists(subject.getId())) {
      subjectDao.updateSubject(subject);
    } else {
      final Message message = new Message();
      message.setField("subject");
      message
          .setMessage("Subject ID : " + subject.getId() + ", does not exists!");
      messageList.addMessage(message);
    }
    return messageList;
  }

  // @Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
  public boolean deleteSubject(final int id) {
    boolean delFlag = false;
    if (isSubjectExists(id)) {
      delFlag = true;
      subjectDao.deleteSubject(id);
    }
    return delFlag;
  }

  public boolean isSubjectExists(final int id) {
    return subjectDao.isSubjectExists(id);
  }

  public boolean isSubjectExists(final String name) {
    return subjectDao.isSubjectExists(name);
  }

}
