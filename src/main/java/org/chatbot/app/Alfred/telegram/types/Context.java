package org.chatbot.app.Alfred.telegram.types;

public interface Context {
    String getText();
    Long getChatId();
    String getUserName();

}
