package org.school.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.school.dao.RoleDao;
import org.school.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "roleDao")
public class RoleDaoImpl implements RoleDao {

  @Autowired
  private SessionFactory sessionFactory;

  public Role saveRole(final Role role) {
    final Session session = sessionFactory.getCurrentSession();
    session.save(role);
    return role;
  }

  @Transactional
  public Role updateRole(final Role role) {
    final Session session = sessionFactory.getCurrentSession();
    session.merge(role);
    return role;
  }

  @Transactional
  public void deleteRole(final int id) {
    final Session session = sessionFactory.getCurrentSession();
    final Role role = (Role) session.get(Role.class, id);
    session.delete(role);
  }

  public List<Role> getRoles() {
    final Session session = sessionFactory.getCurrentSession();
    return new ArrayList<Role>(new LinkedHashSet<Role>(session.createCriteria(Role.class).list()));
  }

  public Role getRole(final int id) {
    final Session session = sessionFactory.getCurrentSession();
    return (Role) session.get(Role.class, id);
  }

  public boolean isRoleExists(final String name) {
    final Session session = sessionFactory.getCurrentSession();
    final List<Role> roles = session.createCriteria(Role.class).add(Restrictions.eq("name", name))
        .list();
    return !roles.isEmpty();
  }

  public boolean isRoleExists(final int id) {
    final Session session = sessionFactory.getCurrentSession();
    final List<Role> roles = session.createCriteria(Role.class).add(Restrictions.eq("id", id))
        .list();
    return !roles.isEmpty();
  }

  public Role getRole(final String name) {
    final Session session = sessionFactory.getCurrentSession();
    final List<Role> roles = session.createCriteria(Role.class).add(Restrictions.eq("name", name))
        .list();
    return roles.isEmpty() ? null : roles.get(0);
  }

}
