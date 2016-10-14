package org.school.controller.test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.school.controller.RoleController;
import org.school.model.Role;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.RoleService;
import org.school.util.test.AbstractUnitTest;
import org.school.util.test.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

/**
 * Test Role Controller.
 * @author cdacr
 *
 */
public class RoleControllerTest extends AbstractUnitTest {

  /** */
  @Autowired
  private ApplicationContext context;
  /** */
  private MockMvc mockMvc;

  /** */
  @Mock
  private RoleService roleService;

  /** */
  private RoleController roleController;

  /**
   * Setup.
   * @throws Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    roleController = context.getBean(RoleController.class);
    mockMvc = standaloneSetup(roleController).build();
  }

  /**
   * Test get roles success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetRoles() throws Exception {
    setPrivateFields();
    final List<Role> roles = new ArrayList<Role>();
    roles.add(new Role());
    roles.add(new Role());

    when(roleService.getRoles()).thenReturn(roles);
    mockMvc.perform(get("/roles.json")).andExpect(status().isOk());
  }

  /**
   * Test get roles not found.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetRolesNotFound() throws Exception {
    setPrivateFields();
    final List<Role> roles = new ArrayList<Role>();
    when(roleService.getRoles()).thenReturn(roles);
    mockMvc.perform(get("/roles.json")).andExpect(status().isNotFound());
  }

  /**
   * Test get role by id success.
   * @throws Exception
   *           Exception
   */
  @Test
  @Ignore
  public final void testGetRoleInt() throws Exception {
    setPrivateFields();
    final Role role = new Role();
    when(roleService.getRole(anyInt())).thenReturn(role);
    mockMvc.perform(get("/role/1.json")).andExpect(status().isOk());
    // TODO Need to fix
  }

  /**
   * Test get role by name success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetRoleString() throws Exception {
    setPrivateFields();
    final Role role = new Role();
    when(roleService.getRole(anyString())).thenReturn(role);
    mockMvc.perform(get("/role/test.json")).andExpect(status().isOk());
  }

  /**
   * Test get role by name not found.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetRoleStringNotFound() throws Exception {
    setPrivateFields();
    when(roleService.getRole(anyString())).thenReturn(null);
    mockMvc.perform(get("/role/test.json")).andExpect(status().isNotFound());
  }

  /**
   * Test add role success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testAddRole() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    // messages.getMessages().add(context.getBean(Message.class));
    when(roleService.saveRole(any(Role.class), any(BindingResult.class)))
        .thenReturn(messages);
    final Role role = new Role();
    role.setName("Test");
    mockMvc
        .perform(post("/role/add.json").content(TestUtil.asJsonString(role))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  /**
   * Test add role bad request.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testAddRoleBadRequest() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    messages.getMessages().add(context.getBean(Message.class));
    when(roleService.saveRole(any(Role.class), any(BindingResult.class)))
        .thenReturn(messages);
    final Role role = new Role();
    role.setName("Test");
    mockMvc
        .perform(post("/role/add.json").content(TestUtil.asJsonString(role))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test update role success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testUpdateRole() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    // messages.getMessages().add(context.getBean(Message.class));
    when(roleService.updateRole(any(Role.class), any(BindingResult.class)))
        .thenReturn(messages);
    final Role role = new Role();
    role.setName("Test");
    mockMvc
        .perform(put("/role/update/1.json").content(TestUtil.asJsonString(role))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  /**
   * Test update role bad request.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testUpdateRoleBadRequest() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    messages.getMessages().add(context.getBean(Message.class));
    when(roleService.updateRole(any(Role.class), any(BindingResult.class)))
        .thenReturn(messages);
    final Role role = new Role();
    role.setName("Test");
    mockMvc
        .perform(put("/role/update/1.json").content(TestUtil.asJsonString(role))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test delete role success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testDeleteRole() throws Exception {
    setPrivateFields();
    when(roleService.deleteRole(anyInt())).thenReturn(true);
    mockMvc.perform(delete("/role/delete/1.json")).andExpect(status().isOk());
  }

  /**
   * Test delete role not found.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testDeleteRoleNotFound() throws Exception {
    setPrivateFields();
    when(roleService.deleteRole(anyInt())).thenReturn(false);
    mockMvc.perform(delete("/role/delete/1.json"))
        .andExpect(status().isNotFound());
  }

  /**
   * Set Private Fields dependencies.
   */
  private void setPrivateFields() {
    ReflectionTestUtils.setField(roleController, "roleService", roleService,
        RoleService.class);
  }

}
