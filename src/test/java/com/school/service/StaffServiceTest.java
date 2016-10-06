package com.school.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.school.service.RoleService;
import org.school.service.StaffService;
import org.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config/hibernate-config.xml", "/config/servlet-config.xml",
    "/config/security-config.xml" })
public class StaffServiceTest {

  @Autowired
  StaffService staffService;

  @Autowired
  RoleService roleService;

  @Autowired
  SubjectService subjectService;

  @Test
  public void testAddStaff() {

  }

}
