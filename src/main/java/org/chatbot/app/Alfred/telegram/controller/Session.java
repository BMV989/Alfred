package org.chatbot.app.Alfred.telegram.controller;

import org.chatbot.app.Alfred.telegram.TelegramCommandHandler;
import org.chatbot.app.Alfred.telegram.TelegramContext;

public class Session {
    public Session(TelegramContext ctx){
        System.out.println(ctx.getText());
        TelegramCommandHandler handler = new TelegramCommandHandler(ctx);
        handler.handleCommand();
    }
}
