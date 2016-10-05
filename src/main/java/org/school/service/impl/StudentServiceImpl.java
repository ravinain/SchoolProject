package org.school.service.impl;

import java.util.List;

import org.school.dao.StudentDAO;
import org.school.model.Student;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service(value="studentService")
@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
public class StudentServiceImpl implements StudentService {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private StudentDAO studentDao;
	
	public List<Student> getStudents() {
		return studentDao.getStudents();
	}

	public Student getStudent(int id) {
		return studentDao.getStudent(id);
	}

//	@Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
	public MessageList addStudent(Student student, BindingResult result) {
		MessageList messageList = new MessageList();
		if(result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError fieldError:fieldErrors) {
				Message message = new Message();
				message.setField(fieldError.getField());
				message.setMessage(messageSource.getMessage(fieldError.getCodes()[0], null, null));
				messageList.addMessage(message);
			}
		} else 	if(!isStudentExists(student.getId())) {
			studentDao.addStudent(student);
		} else {
			Message message = new Message();
			message.setField("student");
			message.setMessage("Student already exists!");
			messageList.addMessage(message);
		}
		return messageList;
	}

//	@Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
	public MessageList updateStudent(int id, Student student, BindingResult result) {
		MessageList messageList = new MessageList();
		if(result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError fieldError:fieldErrors) {
				Message message = new Message();
				message.setField(fieldError.getField());
				message.setMessage(messageSource.getMessage(fieldError.getCodes()[0], null, null));
				messageList.addMessage(message);
			}
		} else 	if(isStudentExists(student.getId())) {
			studentDao.updateStudent(student);
		} else {
			Message message = new Message();
			message.setField("student");
			message.setMessage("Student does not exists!");
			messageList.addMessage(message);
		}
		return messageList;
	}

//	@Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
	public boolean deleteStudent(int id) {
		boolean delFlag = false;
		if(isStudentExists(id)) {
			studentDao.deleteStudent(id);
			delFlag = true;
		}
		return delFlag;
	}

	public boolean isStudentExists(int id) {
		return studentDao.isStudentExists(id);
	}

}
