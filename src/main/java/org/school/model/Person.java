package org.school.model;

import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Person.
 * @author cdacr
 */
@MappedSuperclass
public class Person {

  @NotEmpty
  private String name;

  private int age;
  private String gender;

  /**
   * get name.
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * set name.
   * @param name
   *          name
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * get age.
   * @return age
   */
  public int getAge() {
    return age;
  }

  /**
   * set age.
   * @param age
   *          age
   */
  public void setAge(final int age) {
    this.age = age;
  }

  /**
   * get gender.
   * @return gender
   */
  public String getGender() {
    return gender;
  }

  /**
   * set gender.
   * @param gender
   *          gender
   */
  public void setGender(final String gender) {
    this.gender = gender;
  }
}
