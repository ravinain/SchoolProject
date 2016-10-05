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
	
	@RequestMapping(value="/subjects", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getAllSubjects() {
		List<Subject> subjects = subjectService.getSubjects();
		if(subjects.isEmpty()) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_SUBJECT_FOUND, null, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/subject/{id:[1-9]{1}[0-9]*}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getSubject(@PathVariable("id") int id) {
		Subject subject = subjectService.getSubject(id);
		if(subject == null) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_SUBJECT_FOUND_BY_ID, new String[]{String.valueOf(id)}, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<Subject>(subject, HttpStatus.OK);
	}

	@RequestMapping(value = "/subject/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addSubject(@Valid @RequestBody Subject subject, BindingResult result) {
		MessageList messageList = subjectService.saveSubject(subject, result);
		if (!messageList.getMessages().isEmpty()) {
			return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/subject/update/{id:[1-9]{1}[0-9]*}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> updateSubject(@PathVariable("id") int id, @Valid @RequestBody Subject subject, BindingResult result) {
		MessageList messageList = subjectService.updateSubject(subject, result);
		if (!messageList.getMessages().isEmpty()) {
			return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Subject>(subject, HttpStatus.OK);
	}

	@RequestMapping(value = "/subject/delete/{id:[1-9]{1}[0-9]*}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> deleteSubject(@PathVariable("id") int id) {
		boolean delFlag = subjectService.deleteSubject(id);
		if (!delFlag) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_SUBJECT_FOUND_BY_ID, new String[]{String.valueOf(id)}, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
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
