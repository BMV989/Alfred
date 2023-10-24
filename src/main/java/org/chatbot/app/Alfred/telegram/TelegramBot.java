package org.chatbot.app.Alfred.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramBot extends TelegramLongPollingBot {
    private Update up;
    @Override
    public void onUpdateReceived(Update update) {
        /*
        создать разные методы для update, чтобы разбить его JSON
        формат на текст, картинки, файлы и т.д.
        Условно здесь будет проверка не update.getMessage().hasText(),
        а просто hasMessage или что-то типо того, причём желательно
        это всё в отдельный класс вынести
        */
        this.up = update;
        System.out.println(up);
        handleUpdate();
    }
    private void handleUpdate() {
        if (up.hasMessage() && up.getMessage().hasText()) {
            String message = up.getMessage().getText();
            System.out.println(message);
            TelegramCommandHandler handler = new TelegramCommandHandler();
            handler.handleCommand(up.getMessage().getText(), up.getMessage().getChatId());
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
        return "PersonalMusicalButlerBot";
    }
    @Override
    public String getBotToken() {
        return System.getenv("TOKEN");
    }
}