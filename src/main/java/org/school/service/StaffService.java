package org.school.service;

import java.util.List;

import org.school.model.Staff;
import org.school.response.MessageList;
import org.springframework.validation.BindingResult;

public interface StaffService {

	List<Staff> getStaffs();

	Staff getStaff(int id);

	MessageList addStaff(Staff staff, BindingResult result);

	MessageList updateStaff(int id, Staff staff, BindingResult result);

	boolean deleteStaff(int id);

}
