package org.school.controller.test;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
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
import org.school.controller.StaffController;
import org.school.model.Staff;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.StaffService;
import org.school.util.test.AbstractUnitTest;
import org.school.util.test.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

/**
 * Test Staff Controller.
 * @author cdacr
 */
public class StaffControllerTest extends AbstractUnitTest {

  /** */
  @Autowired
  private ApplicationContext context;
  /** */
  private MockMvc mockMvc;

  /** */
  @Mock
  private StaffService staffService;

  /** */
  private StaffController staffController;

  /**
   * Setup.
   * @throws Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    staffController = context.getBean(StaffController.class);
    mockMvc = standaloneSetup(staffController).build();
  }

  /**
   * Test get staffs success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetStaffs() throws Exception {
    setPrivateFields();
    final List<Staff> staffs = new ArrayList<Staff>();
    staffs.add(new Staff());
    staffs.add(new Staff());

    when(staffService.getStaffs()).thenReturn(staffs);
    mockMvc.perform(get("/staffs.json")).andExpect(status().isOk());
  }

  /**
   * Test get staffs not found.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetStaffsNotFound() throws Exception {
    setPrivateFields();
    final List<Staff> staffs = new ArrayList<Staff>();
    when(staffService.getStaffs()).thenReturn(staffs);
    mockMvc.perform(get("/staffs.json")).andExpect(status().isNotFound());
  }

  /**
   * Test get staff by id success.
   * @throws Exception
   *           Exception
   */
  @Test
  @Ignore
  public final void testGetStaff() throws Exception {
    fail("Not yet implemented"); // TODO
  }

  /**
   * Test add staff success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testAddStaff() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    // messages.getMessages().add(context.getBean(Message.class));
    when(staffService.addStaff(any(Staff.class), any(BindingResult.class)))
        .thenReturn(messages);
    final Staff staff = new Staff();
    staff.setName("Test");
    mockMvc
        .perform(post("/staff/add.json").content(TestUtil.asJsonString(staff))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  /**
   * Test add staff bad request.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testAddStaffBadRequest() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    messages.getMessages().add(context.getBean(Message.class));
    when(staffService.addStaff(any(Staff.class), any(BindingResult.class)))
        .thenReturn(messages);
    final Staff staff = new Staff();
    staff.setName("Test");
    mockMvc
        .perform(post("/staff/add.json").content(TestUtil.asJsonString(staff))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test update staff success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testUpdateStaff() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    // messages.getMessages().add(context.getBean(Message.class));
    when(staffService.updateStaff(anyInt(), any(Staff.class),
        any(BindingResult.class))).thenReturn(messages);
    final Staff staff = new Staff();
    staff.setName("Test");
    mockMvc
        .perform(
            put("/staff/update/1.json").content(TestUtil.asJsonString(staff))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  /**
   * Test update staff bad request.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testUpdateStaffBadRequest() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    messages.getMessages().add(context.getBean(Message.class));
    when(staffService.updateStaff(anyInt(), any(Staff.class),
        any(BindingResult.class))).thenReturn(messages);
    final Staff staff = new Staff();
    staff.setName("Test");
    mockMvc
        .perform(
            put("/staff/update/1.json").content(TestUtil.asJsonString(staff))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test delete staff success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testDeleteStaff() throws Exception {
    setPrivateFields();
    when(staffService.deleteStaff(anyInt())).thenReturn(true);
    mockMvc.perform(delete("/staff/delete/1.json")).andExpect(status().isOk());
  }

  /**
   * Test delete staff not found.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testDeleteStaffNotFound() throws Exception {
    setPrivateFields();
    when(staffService.deleteStaff(anyInt())).thenReturn(false);
    mockMvc.perform(delete("/staff/delete/1.json"))
        .andExpect(status().isNotFound());
  }

  /**
   * Set Private Fields dependencies.
   */
  private void setPrivateFields() {
    ReflectionTestUtils.setField(staffController, "staffService", staffService,
        StaffService.class);
  }
}
