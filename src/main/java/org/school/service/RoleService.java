package org.school.service;

import java.util.List;

import org.school.model.Role;
import org.school.response.MessageList;
import org.springframework.validation.BindingResult;

/**
 * Role Service.
 * @author cdacr
 */
public interface RoleService {

  /**
   * Add Role.
   * @param role
   *          {@link Role}
   * @param result
   *          {@link BindingResult}
   * @return {@link MessageList}
   */
  MessageList saveRole(Role role, BindingResult result);

  /**
   * Update Role.
   * @param role
   *          {@link Role}
   * @param result
   *          {@link BindingResult}
   * @return {@link MessageList}
   */
  MessageList updateRole(Role role, BindingResult result);

  /**
   * Delete Role.
   * @param id
   *          Role Id
   * @return TRUE|FALSE
   */
  boolean deleteRole(int id);

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
}
