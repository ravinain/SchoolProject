package org.school.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * MessageList.
 * @author cdacr
 */
@Component
@Scope(value = "prototype")
public final class MessageList {

  /** Messages. */
  private final List<Message> messages = new ArrayList<Message>();

  /**
   * Get All Messages.
   * @return {@link List} of {@link Message}
   */
  public List<Message> getMessages() {
    return messages;
  }

  /**
   * Add message.
   * @param message
   *          {@link Message}
   */
  public void addMessage(final Message message) {
    this.messages.add(message);
  }

}
