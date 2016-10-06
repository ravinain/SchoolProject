package org.school.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.school.dao.RoleDao;
import org.school.dao.StaffDao;
import org.school.dao.SubjectDao;
import org.school.model.Role;
import org.school.model.Staff;
import org.school.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "staffRepo")
public final class StaffDaoImpl implements StaffDao {

  @Autowired
  SessionFactory sessionFactory;

  @Autowired
  RoleDao roleDao;

  @Autowired
  SubjectDao subjectDao;

  public Staff addStaff(final Staff staff) {
    final Session session = sessionFactory.getCurrentSession();
    session.save(staff);
    for (final Role role : staff.getRoles()) {
      final Role role1 = roleDao.getRole(role.getId());
      role1.setStaff(staff);
    }
    for (final Subject subject : staff.getSubjects()) {
      final Subject subject1 = subjectDao.getSubject(subject.getId());
      subject1.getStaffs().add(staff);
    }
    session.merge(staff);
    return staff;
  }

  public void deleteStaff(final int id) {
    final Session session = sessionFactory.getCurrentSession();
    final Staff staff = (Staff) session.get(Staff.class, id);

    final Set<Subject> subjects = staff.getSubjects();
    for (final Subject subject : subjects) {
      subject.getStaffs().clear();
    }

    final Set<Role> roles = staff.getRoles();
    for (final Role role : roles) {
      role.setStaff(null);
    }
    session.merge(staff);
    session.delete(staff);
  }

  public Staff getStaff(final int id) {
    final Session session = sessionFactory.getCurrentSession();
    return (Staff) session.get(Staff.class, id);
  }

  public Staff getStaff(final String name) {
    final Session session = sessionFactory.getCurrentSession();
    final List<Staff> staffs = session.createCriteria(Staff.class)
        .add(Restrictions.eq("name", name)).list();
    return staffs.isEmpty() ? null : staffs.get(0);
  }

  public List<Staff> getStaffs() {
    final Session session = sessionFactory.getCurrentSession();
    return new ArrayList<Staff>(
        new LinkedHashSet<Staff>(session.createCriteria(Staff.class).list()));
  }

  public boolean isStaffExists(final int id) {
    return this.getStaff(id) != null;
  }

  public boolean isStaffExists(final String name) {
    return getStaff(name) != null;
  }

  public Staff updateStaff(Staff staff) {
    final Session session = sessionFactory.getCurrentSession();
    staff = (Staff) session.merge(staff);
    for (final Role role : staff.getRoles()) {
      final Role role1 = roleDao.getRole(role.getId());
      role1.setStaff(staff);
      session.merge(role1);
    }
    for (final Subject subject : staff.getSubjects()) {
      final Subject subject1 = subjectDao.getSubject(subject.getId());
      subject1.getStaffs().add(staff);
      session.merge(subject1);
    }
    return staff;
  }

}
