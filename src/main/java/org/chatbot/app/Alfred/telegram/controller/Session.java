package org.chatbot.app.Alfred.telegram.controller;

import org.chatbot.app.Alfred.telegram.TelegramCommandHandler;

public class Session {
    public Session(Long chatId, String message, String userName){
        System.out.println(message);
        TelegramCommandHandler handler = new TelegramCommandHandler(
                chatId, message, userName);
        handler.handleCommand();
    }
}
