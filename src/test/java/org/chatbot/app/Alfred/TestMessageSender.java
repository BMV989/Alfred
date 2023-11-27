package org.chatbot.app.Alfred;

import java.util.List;
import org.chatbot.app.Alfred.telegram.types.MessageSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class TestMessageSender implements MessageSender {
    private SendMessage msg;

    @Override
    public void sendMsg(Long chatId, String message) {
        msg = SendMessage.builder().chatId(chatId).text(message).build();
    }

    @Override
    public void sendMsgWithMarkup(Long chatId, String message, ReplyKeyboardMarkup markup) {
        msg = SendMessage.builder().chatId(chatId).text(message).replyMarkup(markup).build();
    }
    public SendMessage getMsg() {
        return msg;
    }
}
