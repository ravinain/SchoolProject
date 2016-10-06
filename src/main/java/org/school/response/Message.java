package org.school.response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Message {
  private String field;
  private String message;

  public Message() {
  }

  public Message(final String field, final String message) {
    this.field = field;
    this.message = message;
  }

  public String getField() {
    return field;
  }

  public void setField(final String field) {
    this.field = field;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }
}
