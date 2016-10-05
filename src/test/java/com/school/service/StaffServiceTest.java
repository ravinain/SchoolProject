package com.school.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.school.model.Role;
import org.school.model.Staff;
import org.school.model.Subject;
import org.school.service.RoleService;
import org.school.service.StaffService;
import org.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;

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
	
	/*@Autowired
	BindingResult result;*/
	
	@Test
	public void testAddStaff() {
		Staff staff = new Staff();
		staff.setName("Test15");
		staff.setAge(26);
		staff.setSalary(20000);
		
		Set<Role> roles = staff.getRoles();
		roles.add(roleService.getRole(3));
		
//		staff.setRoles(roles);
		
		Set<Subject> subjects = staff.getSubjects();
		
		subjects.add(subjectService.getSubject(1));
		
//		staff.setSubjects(subjects);
		
		staffService.addStaff(staff, null);
	}

}
