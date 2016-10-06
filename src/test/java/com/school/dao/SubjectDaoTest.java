package com.school.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.school.dao.SubjectDao;
import org.school.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config/hibernate-config.xml", "/config/servlet-config.xml",
    "/config/security-config.xml" })
@TransactionConfiguration(transactionManager = "txManager")
public class SubjectDaoTest {

  @Autowired
  SubjectDao subjectDao;

  @Test
  @Transactional
  public void testGetAllSubjects() {
    final List<Subject> subjects = subjectDao.getAllSubjects();
    System.out.println(subjects);
    final List<Integer> a = new ArrayList<Integer>();
    a.add(1);

    final Ball b = new Ball();
    System.out.println(b.getName());
  }

}

class Shape {
  private String name;

  Shape() {
    this.name = "shape";
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

}

class Ball extends Shape {
  private final String name;

  Ball() {
    this.name = "ball";
  }
}
