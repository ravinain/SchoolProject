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

@Entity
@Table
public class Course {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotEmpty
  private String description;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "subject_course", joinColumns = {
      @JoinColumn(name = "subject_id", referencedColumnName = "id") }, inverseJoinColumns = {
          @JoinColumn(name = "course_id", referencedColumnName = "id") })
  private Set<Subject> subjects = new HashSet<Subject>();

  @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
  @Cascade({ CascadeType.DELETE, CascadeType.SAVE_UPDATE })
  @JsonIgnore
  private Set<Student> students = new HashSet<Student>();

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public Set<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(final Set<Subject> subjects) {
    this.subjects = subjects;
  }

  public Set<Student> getStudents() {
    return students;
  }

  public void setStudents(final Set<Student> students) {
    this.students = students;
  }

  @Override
  public String toString() {
    return "Course : " + this.id;
  }
}
