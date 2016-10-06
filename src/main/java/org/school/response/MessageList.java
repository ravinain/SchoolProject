package org.school.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public final class MessageList {

  private final List<Message> messages = new ArrayList<Message>();

  public List<Message> getMessages() {
    return messages;
  }

  public void addMessage(final Message message) {
    this.messages.add(message);
  }

}
