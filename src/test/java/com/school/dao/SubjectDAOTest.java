package com.school.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.school.dao.SubjectDAO;
import org.school.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/config/hibernate-config.xml", "/WEB-INF/config/servlet-config.xml",
		"/WEB-INF/config/security-config.xml" })
@TransactionConfiguration(transactionManager="txManager")
public class SubjectDAOTest {

	@Autowired
	SubjectDAO subjectDao;
	
	@Test
	@Transactional
	public void testGetAllSubjects() {
		List<Subject> subjects = subjectDao.getAllSubjects();
		System.out.println(subjects);
		List<Integer> a = new ArrayList<Integer>();
		a.add(1);
		
		Ball b = new Ball();
		System.out.println(b.getName());
	}
	
}

class Shape {
	private String name;
	
	Shape() {
		this.name="shape";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

class Ball extends Shape {
	private String name;
	
	Ball() {
		this.name="ball";
	}
}