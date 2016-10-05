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

@Controller
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getRoles() {
		List<Role> roles = roleService.getRoles();
		if (roles.isEmpty()) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_ROLE_FOUND, null, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Role>>(roleService.getRoles(), HttpStatus.OK);
	}

	@RequestMapping(value = "/role/{id:[1-9]{1}[0-9]*}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getRole(@PathVariable("id") int id) {
		Role role = roleService.getRole(id);
		if (role == null) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_ROLE_FOUND_BY_ID, new String[]{String.valueOf(id)}, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/role/{name}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getRole(@PathVariable("name") String name) {
		Role role = roleService.getRole(name);
		if (role == null) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_ROLE_FOUND_BY_NAME, new String[]{name}, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@RequestMapping(value = "/role/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addRole(@Valid @RequestBody Role role, BindingResult result) {
		MessageList messageList = roleService.saveRole(role, result);
		if (!messageList.getMessages().isEmpty()) {
			return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/role/update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> updateRole(@PathVariable("id") int id, @Valid @RequestBody Role role, BindingResult result) {
		MessageList messageList = roleService.updateRole(role, result);
		if (!messageList.getMessages().isEmpty()) {
			return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@RequestMapping(value = "/role/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteRole(@PathVariable("id") int id) {
		boolean delFlag = roleService.deleteRole(id);
		if (!delFlag) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_ROLE_FOUND_BY_ID, new String[]{String.valueOf(id)}, null));
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
