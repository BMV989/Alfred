package org.chatbot.app.Alfred.telegram.types;

import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public interface MessageSender {
    void sendMsg(Long chatId, String message);
    void sendAudio(Long chatId, InputFile audioFile, InputFile thumbnailFile, String title);
    void sendMsgWithMarkup(Long chatId, String message, ReplyKeyboardMarkup markup);
    void sendMsgWithInlineMarkup(Long chatId,  String message, InlineKeyboardMarkup markup);
    void editMsg(Long chatId, String message, Integer messageId, InlineKeyboardMarkup markup);
}
