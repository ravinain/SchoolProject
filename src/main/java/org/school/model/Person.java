package org.school.model;

import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Person.
 * @author cdacr
 */
@MappedSuperclass
public class Person {

  /** Name. */
  @NotEmpty
  private String name;

  /** Age. */
  private int age;
  /** Gender. */
  private String gender;

  /**
   * get name.
   * @return name
   */
  public final String getName() {
    return name;
  }

  /**
   * set name.
   * @param name
   *          name
   */
  public final void setName(final String name) {
    this.name = name;
  }

  /**
   * get age.
   * @return age
   */
  public final int getAge() {
    return age;
  }

  /**
   * set age.
   * @param age
   *          age
   */
  public final void setAge(final int age) {
    this.age = age;
  }

  /**
   * get gender.
   * @return gender
   */
  public final String getGender() {
    return gender;
  }

  /**
   * set gender.
   * @param gender
   *          gender
   */
  public final void setGender(final String gender) {
    this.gender = gender;
  }
}
