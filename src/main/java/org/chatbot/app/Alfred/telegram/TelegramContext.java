package org.chatbot.app.Alfred.telegram;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramContext implements Context {
    private final Update update;
    public TelegramContext(Update update) {
        this.update = update;
    }

    @Override
    public String getText() {
        return update.getMessage().getText();
    }

    @Override
    public Long getChatId() {
        return update.getMessage().getChatId();
    }

    @Override
    public String getUserName() {
        return update.getMessage().getChat().getUserName();
    }
}
