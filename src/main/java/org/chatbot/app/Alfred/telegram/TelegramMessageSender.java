package org.chatbot.app.Alfred.telegram;

import org.chatbot.app.Alfred.telegram.types.MessageSender;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
    @Override
    public void sendMsg(Long chatId, String message) {
        try {
            execute(buildSendMessage(chatId, message));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
