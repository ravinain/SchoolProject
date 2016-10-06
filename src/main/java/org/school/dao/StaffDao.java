package org.school.dao;

import java.util.List;

import org.school.model.Staff;

public interface StaffDao {

  Staff addStaff(Staff staff);

  void deleteStaff(int id);

  Staff getStaff(int id);

  Staff getStaff(String id);

  List<Staff> getStaffs();

  boolean isStaffExists(int id);

  boolean isStaffExists(String name);

  Staff updateStaff(Staff staff);

}
