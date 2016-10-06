package org.school.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Subject.
 * @author cdacr
 */
@Entity
@Table
public final class Subject implements Comparable<Subject> {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotEmpty
  private String description;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "subject_staff", joinColumns = {
      @JoinColumn(name = "subject_id", referencedColumnName = "id") }, inverseJoinColumns = {
          @JoinColumn(name = "staff_id", referencedColumnName = "id") })
  @JsonIgnore
  private Set<Staff> staffs = new HashSet<Staff>();

  @ManyToMany(mappedBy = "subjects", fetch = FetchType.EAGER)
  @Cascade({ CascadeType.DELETE, CascadeType.SAVE_UPDATE })
  @JsonIgnore
  private Set<Course> courses = new HashSet<Course>();

  @ManyToMany(mappedBy = "subjects", fetch = FetchType.EAGER)
  @Cascade({ CascadeType.DELETE, CascadeType.SAVE_UPDATE })
  @JsonIgnore
  private Set<Student> students = new HashSet<Student>();

  /**
   * get Staff.
   * @return {@link Set} of {@link Staff}
   */
  public Set<Staff> getStaffs() {
    return staffs;
  }

  /**
   * set Staff.
   * @param staffs
   *          {@link Set} of {@link Staff}
   */
  public void setStaffs(final Set<Staff> staffs) {
    this.staffs = staffs;
  }

  /**
   * get id.
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * set id.
   * @param id
   *          id
   */
  public void setId(final int id) {
    this.id = id;
  }

  /**
   * get Description.
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * set description.
   * @param description
   *          description
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * get courses.
   * @return {@link Set} of {@link Course}
   */
  public Set<Course> getCourses() {
    return courses;
  }

  /**
   * set courses.
   * @param courses
   *          {@link Set} of {@link Course}
   */
  public void setCourses(final Set<Course> courses) {
    this.courses = courses;
  }

  /**
   * get students.
   * @return {@link Set} of {@link Student}
   */
  public Set<Student> getStudents() {
    return students;
  }

  /**
   * set students.
   * @param students
   *          {@link Set} of {@link Student}
   */
  public void setStudents(final Set<Student> students) {
    this.students = students;
  }

  /**
   * {@inheritDoc}}.
   */
  @Override
  public boolean equals(final Object obj) {
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    final Subject subject = (Subject) obj;

    return this.getId() == subject.getId();
  }

  /**
   * {@inheritDoc}}.
   */
  @Override
  public int hashCode() {
    return 22 * 7 + this.id;
  }

  /**
   * Compare subjects.
   * @param obj
   *          {@link Subject}
   * @return {@link Integer}
   */
  public int compareTo(final Subject obj) {
    return this.id - obj.id;
  }
}
