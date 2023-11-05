package org.chatbot.app.Alfred.telegram.types;

public interface MessageSender {
    void sendMsg(Long chatId, String message);
}
