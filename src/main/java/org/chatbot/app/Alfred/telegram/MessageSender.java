package org.chatbot.app.Alfred.telegram;

public interface MessageSender {
    void sendMsg(Long chatId, String message);
}
