package org.chatbot.app.Alfred.telegram;

import java.util.HashMap;
import org.chatbot.app.Alfred.telegram.controller.Session;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


public class TelegramBot extends TelegramLongPollingBot {
    private final HashMap<Long, Session> sessions = new HashMap<>();
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && !sessions.containsKey(update.getMessage().getChatId())) {
            sessions.put(update.getMessage().getChatId(), new Session());
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println("received update:");
            sessions.get(update.getMessage().getChatId())
                .handleCommand(new TelegramContext(update));
        } else if (update.hasCallbackQuery()) {
            sessions.get(update.getCallbackQuery().getMessage().getChatId())
                .handleCallbackQuery(new TelegramContext(update));
        }
    }

   @Override
    public String getBotUsername() {
        return "PersonalMusicalButlerBot";
    }
    @Override
    public String getBotToken() {
        return System.getenv("TOKEN");
    }
}