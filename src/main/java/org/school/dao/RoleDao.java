package org.school.dao;

import java.util.List;

import org.school.model.Role;

public interface RoleDao {

  Role saveRole(Role role);

  Role updateRole(Role role);

  void deleteRole(int id);

  List<Role> getRoles();

  Role getRole(int id);

  Role getRole(String name);

  boolean isRoleExists(String name);

  boolean isRoleExists(int id);
}
