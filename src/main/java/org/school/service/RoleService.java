package org.school.service;

import java.util.List;

import org.school.model.Role;
import org.school.response.MessageList;
import org.springframework.validation.BindingResult;


public interface RoleService {

	MessageList saveRole(Role role, BindingResult result);
	
	MessageList updateRole(Role role, BindingResult result);
	
	boolean deleteRole(int id);
	
	List<Role> getRoles();
	
	Role getRole(int id);
	
	Role getRole(String name);
}
