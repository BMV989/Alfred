package org.chatbot.app.Alfred.telegram.controller;

import org.chatbot.app.Alfred.telegram.MessageSender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramBot extends TelegramLongPollingBot implements MessageSender {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println("received update:");
            System.out.println(update);
            new Session(update.getMessage().getChatId(),
                    update.getMessage().getText(),
                    update.getMessage().getChat().getUserName());
        }
    }

    protected SendMessage buildSendMessage(Long chatId, String message){
        return SendMessage
                .builder()
                .chatId(chatId)
                .text(message)
                .build();
    }
    @Override
    public void sendMsg(Long chatId, String message) {
        try {
            execute(buildSendMessage(chatId, message));
        } catch (TelegramApiException e) {
            e.printStackTrace();
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