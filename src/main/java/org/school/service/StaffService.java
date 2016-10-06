package org.school.service;

import java.util.List;

import org.school.model.Staff;
import org.school.response.MessageList;
import org.springframework.validation.BindingResult;

/**
 * Staff Service.
 * @author cdacr
 */
public interface StaffService {

  /**
   * Fetch All Staffs.
   * @return {@link List} of {@link Staff}
   */
  List<Staff> getStaffs();

  /**
   * Fetch Staff.
   * @param id
   *          Staff Id
   * @return {@link Staff}
   */
  Staff getStaff(int id);

  /**
   * Add Staff.
   * @param staff
   *          {@link Staff}
   * @param result
   *          {@link BindingResult}
   * @return {@link MessageList}
   */
  MessageList addStaff(Staff staff, BindingResult result);

  /**
   * Update Staff.
   * @param id
   *          Staff Id
   * @param staff
   *          {@link Staff}
   * @param result
   *          {@link BindingResult}
   * @return {@link MessageList}
   */
  MessageList updateStaff(int id, Staff staff, BindingResult result);

  /**
   * Delete Staff.
   * @param id
   *          Staff Id
   * @return TRUE|FALSE
   */
  boolean deleteStaff(int id);

}
