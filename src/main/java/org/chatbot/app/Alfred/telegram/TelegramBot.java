package org.chatbot.app.Alfred.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramBot extends TelegramLongPollingBot implements MessageSender {
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("update receive");
        System.out.println(update);
        handleUpdate(update);
    }
    private void handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            System.out.println(message);
            TelegramCommandHandler handler = new TelegramCommandHandler(update
                .getMessage().getChatId(), message, update.getMessage().getChat().getUserName());
            handler.handleCommand();
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
    // Возможно имя бота сделать переменной окружения?
    @Override
    public String getBotUsername() {
        return "PersonalMusicalButlerBot";
    }
    @Override
    public String getBotToken() {
        return System.getenv("TOKEN");
    }
}