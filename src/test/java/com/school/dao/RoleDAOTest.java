package com.school.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.school.dao.RoleDAO;
import org.school.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/config/hibernate-config.xml", "/WEB-INF/config/servlet-config.xml",
"/WEB-INF/config/security-config.xml" })
@TransactionConfiguration(transactionManager="txManager")
public class RoleDAOTest {

	@Autowired
	RoleDAO roleDao;
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void testAddRole() {
		Role role = new Role();
		role.setName("Test");
		
		if(!roleDao.isRoleExists(role.getName())) {
			role = roleDao.saveRole(role);
			assertTrue(role.getId() > 0);
		}
	}
	
	@Test
	@Transactional
	public void testUpdateRole() {
		Role role = new Role();
		role.setName("Test Update");
		
		if(!roleDao.isRoleExists(role.getName())) {
			role = roleDao.saveRole(role);
			assertTrue(role.getId() > 0);
			
			role.setName("Updated Test");
			
			assertTrue(roleDao.isRoleExists(role.getId()));
			
			role = roleDao.updateRole(role);
			
			Role role2 = roleDao.getRole(role.getId());
			
			assertEquals(role.getName(), role2.getName());
		}
	}
	
	@Test
	@Transactional
	public void testDeleteRole() {
		Role role = new Role();
		role.setName("Delete Test");
		
		if(!roleDao.isRoleExists(role.getName())) {
			role = roleDao.saveRole(role);
			assertTrue(role.getId() > 0);
			
			roleDao.deleteRole(role.getId());
			
			assertTrue(!roleDao.isRoleExists(role.getId()));
		}
	}
	
	@Test
	@Transactional
	public void testGetRoles() {
		Role role = new Role();
		role.setName("New Test");
		
		if(!roleDao.isRoleExists(role.getName())) {
			role = roleDao.saveRole(role);
			assertTrue(role.getId() > 0);
		}
		
		List<Role> roles = roleDao.getRoles();
		assertTrue(!roles.isEmpty());
	}
	
	@Test
	@Transactional
	public void testGetRole() {
		Role role = new Role();
		role.setName("Get Role");
		
		if(!roleDao.isRoleExists(role.getName())) {
			role = roleDao.saveRole(role);
			assertTrue(role.getId() > 0);
			
			Role role2 = roleDao.getRole(role.getId());
			assertNotNull(role2);
			assertEquals(role.getName(), role2.getName());
		}
		
		//TODO: implement get role by name functionality
	}
}
