package org.school.controller;

import java.util.List;

import javax.validation.Valid;

import org.school.exception.RestException;
import org.school.model.Course;
import org.school.model.Subject;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.CourseService;
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
 * 
 * @author cdacr
 * Controller of Course API.
 */
@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	MessageSource messageSource;

	/**
	 * Request mapping to get all courses.
	 * @return {@link ResponseEntity} 
	 */
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getAllCourses() {
		List<Course> courses = courseService.getAllCourses();
		if (courses.isEmpty()) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_COURSE_FOUND, null, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}

	/**
	 * Return course detail of input id.
	 * @param id 
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value = "/course/{id:[1-9]{1}[0-9]*}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getCourse(@PathVariable("id") int id) {
		Course course = courseService.getCourse(id);
		if (course == null) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_COURSE_FOUND_BY_ID, new String[]{String.valueOf(id)}, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList,
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
	
	/**
	 * Method returns course details based on input course description.
	 * @param description 
	 * @return {@link ResponseEntity} 
	 */
	@RequestMapping(value = "/course/{description}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getCourse(@PathVariable("description") String description) {
		Course course = courseService.getCourse(description);
		if (course == null) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_COURSE_FOUND_BY_NAME, new String[]{description}, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList,
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@RequestMapping(value = "/course/{id:[1-9]{1}[0-9]*}/subjects", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getCourseSubjects(@PathVariable("id") int id) {
		List<Subject> subjects = courseService.getCourseSubjects(id);
		if (subjects.isEmpty()) {
			MessageList messageList = context.getBean(MessageList.class);
			Message msg = context.getBean(Message.class);
			msg.setField(messageSource.getMessage(MessageConstant.ERROR, null, null));
			msg.setMessage(messageSource.getMessage(MessageConstant.NO_SUBJECT_FOUND_COURSE, new String[]{String.valueOf(id)}, null));
			messageList.addMessage(msg);
			return new ResponseEntity<MessageList>(messageList,
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}

	@RequestMapping(value = "/course/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addCourse(@Valid @RequestBody Course course, BindingResult result) {
		MessageList messageList = courseService.addCourse(course, result);
		if (!messageList.getMessages().isEmpty()) {
			return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/course/update/{id:[1-9]{1}[0-9]*}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> updateCourse(@PathVariable("id") int id, @Valid @RequestBody Course course,
			BindingResult result) {
		MessageList messageList = courseService.updateCourse(id, course, result);
		if (!messageList.getMessages().isEmpty()) {
			return new ResponseEntity<MessageList>(messageList, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@RequestMapping(value = "/course/delete/{id:[1-9]{1}[0-9]*}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Void> deleteCourse(@PathVariable("id") int id) {
		boolean delFlag = courseService.deleteCourse(id);
		if (!delFlag) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
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
