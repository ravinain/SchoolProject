package com.school.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.school.dao.RoleDao;
import org.school.dao.StaffDao;
import org.school.dao.SubjectDao;
import org.school.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config/hibernate-config.xml", "/config/servlet-config.xml",
    "/config/security-config.xml" })
@TransactionConfiguration(transactionManager = "txManager")
public class StaffDaoTest {

  @Autowired
  StaffDao staffDao;

  @Autowired
  RoleDao roleDao;

  @Autowired
  SubjectDao subjectDao;

  @Test
  @Ignore
  @Transactional
  public void testGetAllStaffs() {
    final List<Staff> staffs = staffDao.getStaffs();
    System.out.println(staffs);
  }

  @Test
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void testSaveStaff() {
    /*
     * Staff staff = new Staff(); staff.setName("Test2"); staff.setAge(26);
     * staff.setSalary(20000);
     * 
     * Set<Role> roles = new HashSet<Role>(); roles.add(roleDao.getRole(3));
     * 
     * staff.setRoles(roles);
     * 
     * Set<Subject> subjects = new HashSet<Subject>();
     * 
     * subjects.add(subjectDao.getSubject(1));
     * 
     * staff.setSubjects(subjects);
     * 
     * staffDao.addStaff(staff); System.out.println(staff.getId());
     * System.out.println(staffDao.getStaff(staff.getId()));
     */

  }
}
