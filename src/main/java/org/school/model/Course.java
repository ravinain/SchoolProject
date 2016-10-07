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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Course.
 * @author cdacr
 */
@Entity
@Table
public final class Course {

  /** Course ID. */
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  /** Course Description. */
  @NotEmpty
  private String description;

  /** Subjects associated with Course. */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "subject_course",
      joinColumns = @JoinColumn(
          name = "subject_id", referencedColumnName = "id"
      ), inverseJoinColumns = @JoinColumn(
          name = "course_id", referencedColumnName = "id"
      )
  )
  private Set<Subject> subjects = new HashSet<Subject>();

  /** Students association with Course. */
  @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
  @Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
  @JsonIgnore
  private Set<Student> students = new HashSet<Student>();

  /**
   * ID.
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
   * @return Description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set Description.
   * @param description
   *          description
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * get subjects.
   * @return {@link Set} of {@link Subject}
   */
  public Set<Subject> getSubjects() {
    return subjects;
  }

  /**
   * set subjects.
   * @param subjects
   *          {@link Set}
   */
  public void setSubjects(final Set<Subject> subjects) {
    this.subjects = subjects;
  }

  /**
   * get students.
   * @return {@link Set}
   */
  public Set<Student> getStudents() {
    return students;
  }

  /**
   * set students.
   * @param students
   *          students
   */
  public void setStudents(final Set<Student> students) {
    this.students = students;
  }
}
