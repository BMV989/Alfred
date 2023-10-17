package org.chatbot.app.Alfred.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            System.out.println(message);
            TelegramCommandHandler handler = new TelegramCommandHandler();
            sendMsg(update.getMessage().getChatId(), handler.handleCommand(message));
        }
    }

    public void sendMsg(Long chatId, String message) {
        SendMessage sendMessage = SendMessage
            .builder()
            .chatId(chatId)
            .text(message)
            .build();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    @Override
    public String getBotUsername() {
        return System.getenv("BOT_USERNAME");
    }
    @Override
    public String getBotToken() {
        return System.getenv("TOKEN");
    }
}