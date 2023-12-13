package org.chatbot.app.Alfred.telegram.controller;

import org.chatbot.app.Alfred.telegram.TelegramCommandHandler;
import org.chatbot.app.Alfred.telegram.TelegramContext;

public class Session {
    private final TelegramCommandHandler handler;
    public Session() {

        this.handler = new TelegramCommandHandler();
    }
    public void handleCommand(TelegramContext ctx) {
        System.out.println(ctx.getText());
        handler.handleCommand(ctx);
    }
    public void handleCallbackQuery(TelegramContext ctx) {
        handler.handleCallbackQuery(ctx);
    }
}
