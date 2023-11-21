package org.chatbot.app.Alfred.telegram;

import org.chatbot.app.Alfred.telegram.types.MessageSender;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramMessageSender extends DefaultAbsSender implements MessageSender {

    protected TelegramMessageSender() {
        super(new DefaultBotOptions(),
            System.getenv("TOKEN"));
    }

    protected SendMessage buildSendMessage(Long chatId, String message){
        return SendMessage
            .builder()
            .chatId(chatId)
            .text(message)
            .build();
    }
    protected SendMessage buildSendMessageWithKeyboardMarkup(Long chatId, String message,
        ReplyKeyboardMarkup markup) {
        return SendMessage
            .builder()
            .chatId(chatId)
            .text(message)
            .replyMarkup(markup)
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
    public void sendMsgWithMarkup(Long chatId, String message, ReplyKeyboardMarkup markup){
        try {
            execute(buildSendMessageWithKeyboardMarkup(chatId, message, markup));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
