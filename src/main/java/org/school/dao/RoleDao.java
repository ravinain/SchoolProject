package org.school.dao;

import java.util.List;

import org.school.model.Role;

/**
 * Role DAO.
 * @author cdacr
 */
public interface RoleDao {

  /**
   * Save Role.
   * @param role
   *          {@link Role}
   * @return {@link Role}
   */
  Role saveRole(Role role);

  /**
   * Update Role.
   * @param role
   *          {@link Role}
   * @return {@link Role}
   */
  Role updateRole(Role role);

  /**
   * Delete Role.
   * @param id
   *          Role Id
   */
  void deleteRole(int id);

  /**
   * Fetch All Roles.
   * @return {@link List} of {@link Role}
   */
  List<Role> getRoles();

  /**
   * Fetch Role.
   * @param id
   *          Role Id
   * @return {@link Role}
   */
  Role getRole(int id);

  /**
   * Fetch Role.
   * @param name
   *          Role Name
   * @return {@link Role}
   */
  Role getRole(String name);

  /**
   * Checks whether Role exists or not based on Role Name.
   * @param name
   *          Role Name
   * @return TRUE|FALSE
   */
  boolean isRoleExists(String name);

  /**
   * Checks whether Role exists or not based on Role Id.
   * @param id
   *          Role Id
   * @return TRUE|FALSE
   */
  boolean isRoleExists(int id);
}
