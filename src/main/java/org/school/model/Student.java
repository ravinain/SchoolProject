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

@Entity
@Table
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "id")
public class Student extends Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotNull
  @Valid
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
  private Course course;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "student_subject", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
  private Set<Subject> subjects = new HashSet<Subject>();

  public Course getCourse() {
    return course;
  }

  public void setCourse(final Course course) {
    this.course = course;
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public Set<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(final Set<Subject> subjects) {
    this.subjects = subjects;
  }

  @Override
  public String toString() {
    return "Student : " + this.id;
  }
}
