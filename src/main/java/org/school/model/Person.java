package org.school.model;

import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public class Person {

  @NotEmpty
  private String name;

  private int age;
  private String gender;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(final int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(final String gender) {
    this.gender = gender;
  }
}
