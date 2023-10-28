package org.chatbot.app.Alfred.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramBot extends TelegramLongPollingBot {
    private Update update;
    @Override
    public void onUpdateReceived(Update update) {
        /*
        создать разные методы для update, чтобы разбить его JSON
        формат на текст, картинки, файлы и т.д.
        Условно здесь будет проверка не update.getMessage().hasText(),
        а просто hasMessage или что-то типо того, причём желательно
        это всё в отдельный класс вынести
        (выполнение: 50 на 50, вроде все ок)
        */
        this.update = update;
        System.out.println(update);
        handleUpdate();
    }
    private void handleUpdate() {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            System.out.println(message);
            TelegramCommandHandler handler = new TelegramCommandHandler();
            handler.handleCommand(update.getMessage().getText(), update.getMessage().getChatId());
        }
    }

    protected SendMessage getMessage(Long chatId, String message){
        return SendMessage
                .builder()
                .chatId(chatId)
                .text(message)
                .build();
    }
    public void sendMsg(Long chatId, String message) {
        try {
            execute(getMessage(chatId, message));
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