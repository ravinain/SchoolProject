package org.school.service.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.school.util.test.TestUtil.unwrapProxy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.school.dao.RoleDao;
import org.school.model.Role;
import org.school.response.MessageList;
import org.school.service.RoleService;
import org.school.util.test.AbstractUnitTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;

/**
 * Role Service Test.
 * @author cdacr
 *
 */
public class RoleServiceTest extends AbstractUnitTest {

  /** */
  private RoleService roleService;

  /** */
  @Mock
  private BindingResult result;

  /** */
  @Mock
  private RoleDao roleDao;

  /**
   * Setup.
   * @throws Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    roleService =
        (RoleService) unwrapProxy(getContext().getBean(RoleService.class));
  }

  /**
   * Test save role.
   */
  @Test
  public final void testSaveRole() {
    setPrivateFields();
    final Role role = new Role();
    role.setName("Test Role");

    final Role retRole = new Role();
    role.setId(1);
    role.setName("Test Role");

    when(result.hasErrors()).thenReturn(false);
    when(roleDao.isRoleExists(role.getName())).thenReturn(false);
    when(roleDao.saveRole(role)).thenReturn(retRole);

    final MessageList messages = roleService.saveRole(role, result);
    assertNotNull(messages);
    assertTrue(messages.getMessages().isEmpty());
  }

  /**
   * Test already existing role save.
   */
  @Test
  public final void testAlreadyExistingSaveRole() {
    setPrivateFields();
    final Role role = new Role();
    role.setName("Test Role");

    final Role retRole = new Role();
    role.setId(1);
    role.setName("Test Role");

    when(result.hasErrors()).thenReturn(false);
    when(roleDao.isRoleExists(role.getName())).thenReturn(true);
    when(roleDao.saveRole(role)).thenReturn(retRole);

    final MessageList messages = roleService.saveRole(role, result);
    assertNotNull(messages);
    assertTrue(!messages.getMessages().isEmpty());
  }

  /**
   * Update Role success testing.
   */
  @Test
  public final void testUpdateRole() {
    setPrivateFields();
    final Role role = new Role();
    role.setId(1);
    role.setName("Testing Role");

    when(result.hasErrors()).thenReturn(false);
    when(roleDao.isRoleExists(role.getId())).thenReturn(true);
    when(roleDao.updateRole(role)).thenReturn(role);

    final MessageList messages = roleService.updateRole(role, result);
    assertNotNull(messages);
    assertTrue(messages.getMessages().isEmpty());
  }

  /**
   * Update not exist Role.
   */
  @Test
  public final void testNotExistsUpdateRole() {
    setPrivateFields();
    final Role role = new Role();
    role.setId(1);
    role.setName("Testing Role");

    when(result.hasErrors()).thenReturn(false);
    when(roleDao.isRoleExists(role.getId())).thenReturn(false);
    when(roleDao.updateRole(role)).thenReturn(role);

    final MessageList messages = roleService.updateRole(role, result);
    assertNotNull(messages);
    assertTrue(!messages.getMessages().isEmpty());
  }

  /**
   * Test delete existing role.
   */
  @Test
  public final void testDeleteRole() {
    setPrivateFields();

    when(roleDao.isRoleExists(1)).thenReturn(true);
    doNothing().when(roleDao).deleteRole(1);

    final boolean flag = roleService.deleteRole(1);
    assertTrue(flag);
  }

  /**
   * Test delete non existing role.
   */
  @Test
  public final void testNotExistDeleteRole() {
    setPrivateFields();

    when(roleDao.isRoleExists(1)).thenReturn(false);
    doNothing().when(roleDao).deleteRole(1);

    final boolean flag = roleService.deleteRole(1);
    assertTrue(!flag);
  }

  /**
   * Test to get all roles.
   */
  @Test
  public final void testGetRoles() {
    setPrivateFields();

    final List<Role> roles = new ArrayList<Role>();
    roles.add(new Role());
    roles.add(new Role());

    when(roleDao.getRoles()).thenReturn(roles);

    final List<Role> actualRoles = roleService.getRoles();
    assertNotNull(roles);
    assertThat(actualRoles.size(), is(2));
  }

  /**
   * Test get role by role id.
   */
  @Test
  public final void testGetRoleInt() {
    setPrivateFields();

    final Role role = new Role();
    role.setId(1);
    role.setName("Test Role");

    when(roleDao.getRole(1)).thenReturn(role);

    final Role actualRole = roleService.getRole(1);
    assertNotNull(actualRole);
    assertThat(actualRole.getId(), is(role.getId()));
    assertThat(actualRole.getName(), is(role.getName()));
  }

  /**
   * Test get role by role name.
   */
  @Test
  public final void testGetRoleString() {
    setPrivateFields();

    final Role role = new Role();
    role.setId(1);
    role.setName("Test Role");

    when(roleDao.getRole("Test Role")).thenReturn(role);

    final Role actualRole = roleService.getRole("Test Role");
    assertNotNull(actualRole);
    assertThat(actualRole.getId(), is(role.getId()));
    assertThat(actualRole.getName(), is(role.getName()));
  }

  /**
   * Set Private Fields dependencies.
   */
  private void setPrivateFields() {
    ReflectionTestUtils.setField(roleService, "roleDao", roleDao,
        RoleDao.class);
    ReflectionTestUtils.setField(roleService, "context", getContext(),
        ApplicationContext.class);
  }
}
