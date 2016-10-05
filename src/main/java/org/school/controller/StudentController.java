package org.school.controller;

import java.util.List;

import javax.validation.Valid;

import org.school.exception.RestException;
import org.school.model.Student;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.StudentService;
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
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value="/students", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getStudents() {
		List<Student> students = studentService.getStudents();
		if(students.isEmpty()) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_STUDENT_FOUND, null, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
	
	@RequestMapping(value="/student/{id:[1-9]{1}[0-9]*}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getStudent(@PathVariable("id") int id) {
		Student student = studentService.getStudent(id);
		if(student == null) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_STUDENT_FOUND_BY_ID, new String[]{String.valueOf(id)}, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@RequestMapping(value="/student/add", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addStudent(@RequestBody @Valid Student student, BindingResult result) {
		MessageList messageList = studentService.addStudent(student, result);
		if(messageList.getMessages().isEmpty()) {
			return new ResponseEntity<Student>(student, HttpStatus.CREATED);
		}
		return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/student/update/{id:[1-9]{1}[0-9]*}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> updateStudent(@PathVariable("id") int id, @Valid @RequestBody Student student, BindingResult result) {
		MessageList messageList = studentService.updateStudent(id, student, result); 
		if(messageList.getMessages().isEmpty()) {
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		}
		return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/student/delete/{id:[1-9]{1}[0-9]*}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
		if(!studentService.deleteStudent(id)) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_ROLE_FOUND_BY_ID, new String[]{String.valueOf(id)}, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Integer>(id, HttpStatus.OK);
	}
	
	@ExceptionHandler(RestException.class)
	public ResponseEntity<?> handleException(RestException restException) {
		restException.printStackTrace();
		MessageList messageList = context.getBean(MessageList.class);
		Message msg = context.getBean(Message.class);
		msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
		msg.setMessage(restException.getErrorMsg());
		messageList.addMessage(msg);
		return new ResponseEntity<MessageList>(messageList,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAllException(Exception exception) {
		String errorMsg = exception.getMessage() == null ? "Exception occurred, see log for details."
				: exception.getMessage();
		exception.printStackTrace();
		MessageList messageList = context.getBean(MessageList.class);
		Message msg = context.getBean(Message.class);
		msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
		msg.setMessage(errorMsg);
		messageList.addMessage(msg);
		return new ResponseEntity<MessageList>(messageList, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
