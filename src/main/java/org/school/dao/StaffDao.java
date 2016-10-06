package org.school.dao;

import java.util.List;

import org.school.model.Staff;

/**
 * Staff DAO.
 * @author cdacr
 */
public interface StaffDao {

  /**
   * Add Staff.
   * @param staff
   *          {@link Staff}
   * @return {@link Staff}
   */
  Staff addStaff(Staff staff);

  /**
   * Delete Staff.
   * @param id
   *          Staff Id
   */
  void deleteStaff(int id);

  /**
   * Fetch Staff.
   * @param id
   *          Staff Id
   * @return {@link Staff}
   */
  Staff getStaff(int id);

  /**
   * Fetch Staff.
   * @param id
   *          Staff Name.
   * @return {@link Staff}
   */
  Staff getStaff(String id);

  /**
   * Fetch All Staffs.
   * @return {@link List} of {@link Staff}
   */
  List<Staff> getStaffs();

  /**
   * Checks whether Staff exists or not based on Staff Id.
   * @param id
   *          Staff Id
   * @return TRUE|FALSE
   */
  boolean isStaffExists(int id);

  /**
   * Checks whether Staff exists or not based on Staff Name.
   * @param name
   *          Staff Name
   * @return TRUE|FALSE
   */
  boolean isStaffExists(String name);

  /**
   * Update Staff.
   * @param staff
   *          {@link Staff}
   * @return {@link Staff}
   */
  Staff updateStaff(Staff staff);

}
