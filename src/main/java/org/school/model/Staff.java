package org.school.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Staff extends Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Min(100)
  private double salary;

  @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
  @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE })
  @NotEmpty
  private Set<Role> roles = new HashSet<Role>();

  @ManyToMany(mappedBy = "staffs", fetch = FetchType.EAGER)
  @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE })
  private Set<Subject> subjects = new HashSet<Subject>();

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(final Set<Role> roles) {
    this.roles = roles;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(final double salary) {
    this.salary = salary;
  }

  public Set<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(final Set<Subject> subjects) {
    this.subjects = subjects;
  }

  @Override
  public String toString() {
    return "Staff ID : " + id;
  }
}
