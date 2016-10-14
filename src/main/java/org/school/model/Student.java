package org.school.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Student.
 * @author cdacr
 */
@Entity
@Table
public class Student extends Person {

  /** Student ID. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  /** Course. */
  @NotNull
  @Valid
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(
      name = "student_course",
      joinColumns = @JoinColumn(
          name = "student_id", referencedColumnName = "id"
      ), inverseJoinColumns = @JoinColumn(
          name = "course_id", referencedColumnName = "id"
      )
  )
  private Course course;

  /** Subjects. */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "student_subject",
      joinColumns = {
          @JoinColumn(name = "student_id", referencedColumnName = "id")},
      inverseJoinColumns = {
          @JoinColumn(name = "subject_id", referencedColumnName = "id")}
  )
  private Set<Subject> subjects = new HashSet<Subject>();

  /**
   * get Course.
   * @return {@link Course}
   */
  public Course getCourse() {
    return course;
  }

  /**
   * set Course.
   * @param course
   *          {@link Course}
   */
  public void setCourse(final Course course) {
    this.course = course;
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
   * get subjects.
   * @return {@link Set} of {@link Subject}
   */
  public Set<Subject> getSubjects() {
    return subjects;
  }

  /**
   * set subjects.
   * @param subjects
   *          {@link Subject}
   */
  public void setSubjects(final Set<Subject> subjects) {
    this.subjects = subjects;
  }
}
