package org.school.service.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import org.school.dao.StaffDao;
import org.school.model.Staff;
import org.school.response.MessageList;
import org.school.service.StaffService;
import org.school.util.test.AbstractUnitTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;

/**
 * Staff Service Test.
 * @author cdacr
 *
 */
public class StaffServiceTest extends AbstractUnitTest {

  /** */
  private StaffService staffService;

  /** */
  @Mock
  private BindingResult result;

  /** */
  @Mock
  private StaffDao staffDao;

  /**
   * Setup.
   * @throws Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    staffService =
        (StaffService) unwrapProxy(getContext().getBean(StaffService.class));
  }

  /**
   * Test get staffs.
   */
  @Test
  public final void testGetStaffs() {
    setPrivateFields();
    final List<Staff> staffs = new ArrayList<Staff>();
    staffs.add(new Staff());
    staffs.add(new Staff());

    when(staffDao.getStaffs()).thenReturn(staffs);

    final List<Staff> actualStaffs = staffService.getStaffs();

    assertNotNull(actualStaffs);
    assertFalse(actualStaffs.isEmpty());
    assertThat(actualStaffs.size(), is(2));
  }

  /**
   * Test get staff by id.
   */
  @Test
  public final void testGetStaff() {
    setPrivateFields();
    final Staff staff = new Staff();
    staff.setId(1);
    staff.setName("Abbc");

    when(staffDao.getStaff(1)).thenReturn(staff);

    final Staff actualStaff = staffService.getStaff(1);

    assertNotNull(actualStaff);
    assertEquals(actualStaff.getId(), staff.getId());
    assertEquals(actualStaff.getName(), staff.getName());
  }

  /**
   * Test add staff success.
   */
  @Test
  public final void testAddStaffSuccess() {
    setPrivateFields();
    final Staff staff = new Staff();
    staff.setName("Abbc");

    final Staff expectedStaff = new Staff();
    expectedStaff.setId(1);
    expectedStaff.setName("Abbc");

    when(result.hasErrors()).thenReturn(false);
    when(staffDao.isStaffExists(staff.getName())).thenReturn(false);
    when(staffDao.addStaff(staff)).thenReturn(expectedStaff);

    final MessageList messages = staffService.addStaff(staff, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertTrue(messages.getMessages().isEmpty());
  }

  /**
   * Test add already existing staff.
   */
  @Test
  public final void testAlreadyExistingAddStaff() {
    setPrivateFields();
    final Staff staff = new Staff();
    staff.setName("Abbc");

    final Staff expectedStaff = new Staff();
    expectedStaff.setId(1);
    expectedStaff.setName("Abbc");

    when(result.hasErrors()).thenReturn(false);
    when(staffDao.isStaffExists(staff.getName())).thenReturn(true);
    when(staffDao.addStaff(staff)).thenReturn(expectedStaff);

    final MessageList messages = staffService.addStaff(staff, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertTrue(!messages.getMessages().isEmpty());
  }

  /**
   * Test update staff success.
   */
  @Test
  public final void testUpdateStaffSuccess() {
    setPrivateFields();
    final Staff staff = new Staff();
    staff.setId(1);
    staff.setName("Abbbcc");

    when(result.hasErrors()).thenReturn(false);
    when(staffDao.isStaffExists(1)).thenReturn(true);
    when(staffDao.updateStaff(staff)).thenReturn(staff);

    final MessageList messages = staffService.updateStaff(1, staff, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertTrue(messages.getMessages().isEmpty());
  }

  /**
   * Test update not existing staff.
   */
  @Test
  public final void testNotExistingUpdateStaff() {
    setPrivateFields();
    final Staff staff = new Staff();
    staff.setId(1);
    staff.setName("Abbbcc");

    when(result.hasErrors()).thenReturn(false);
    when(staffDao.isStaffExists(1)).thenReturn(false);
    when(staffDao.updateStaff(staff)).thenReturn(staff);

    final MessageList messages = staffService.updateStaff(1, staff, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertTrue(!messages.getMessages().isEmpty());
  }

  /**
   * Test delete staff success.
   */
  @Test
  public final void testDeleteStaffSuccess() {
    setPrivateFields();
    when(staffDao.isStaffExists(1)).thenReturn(true);
    doNothing().when(staffDao).deleteStaff(1);

    final boolean flag = staffService.deleteStaff(1);
    assertTrue(flag);
  }

  /**
   * Test delete non existing staff.
   */
  @Test
  public final void testDeleteNonExistingStaff() {
    setPrivateFields();
    when(staffDao.isStaffExists(1)).thenReturn(false);
    doNothing().when(staffDao).deleteStaff(1);

    final boolean flag = staffService.deleteStaff(1);
    assertFalse(flag);
  }

  /**
   * Set Private Fields dependencies.
   */
  private void setPrivateFields() {
    ReflectionTestUtils.setField(staffService, "staffDao", staffDao,
        StaffDao.class);
    ReflectionTestUtils.setField(staffService, "context", getContext(),
        ApplicationContext.class);
  }

}
