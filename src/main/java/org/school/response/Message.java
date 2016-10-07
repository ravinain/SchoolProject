package org.school.response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Message.
 * @author cdacr
 */
@Component
@Scope("prototype")
public final class Message {
  /** Field/Code. */
  private String field;
  /** Message. */
  private String message;

  /**
   * Default Constructor.
   */
  public Message() {
  }

  /**
   * Field and Message constructor.
   * @param field
   *          Field
   * @param message
   *          Message
   */
  public Message(final String field, final String message) {
    this.field = field;
    this.message = message;
  }

  /**
   * Get Field.
   * @return Field
   */
  public String getField() {
    return field;
  }

  /**
   * Set Field.
   * @param field
   *          Field
   */
  public void setField(final String field) {
    this.field = field;
  }

  /**
   * Get Message.
   * @return Message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Set Message.
   * @param message
   *          Message
   */
  public void setMessage(final String message) {
    this.message = message;
  }
}
