package org.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Role.
 * @author cdacr
 */
@Entity
@Table
public final class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotEmpty
  private String name;

  @ManyToOne
  @JoinTable(
      name = "role_staff",
      joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "staff_id", referencedColumnName = "id"
      )
  )
  @JsonIgnore
  private Staff staff;

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
   * get staff.
   * @return {@link Staff}
   */
  public Staff getStaff() {
    return staff;
  }

  /**
   * set staff.
   * @param staff
   *          {@link Staff}
   */
  public void setStaff(final Staff staff) {
    this.staff = staff;
  }
}
