package org.chatbot.app.Alfred.telegram.types;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface MessageSender {
    void sendMsg(Long chatId, String message);
    void sendMsgWithMarkup(Long chatId, String message, ReplyKeyboardMarkup markup);
}
