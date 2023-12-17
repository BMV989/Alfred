package org.chatbot.app.Alfred.telegram;

import org.chatbot.app.Alfred.database.SqliteDB;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MusicService;
import org.chatbot.app.Alfred.youtube.Items;
import org.chatbot.app.Alfred.youtube.Youtube;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramContext implements Context {
    private final Update update;
    private Items[] ytItems;
    private final SqliteDB db = new SqliteDB();
    private final MusicService ms = new Youtube();
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

    @Override
    public SqliteDB getDB() {
        return db;
    }

    @Override
    public MusicService getMS() {
        return ms;
    }

    @Override
    public String getCallbackQueryData() {
        return update.getCallbackQuery().getData();
    }

    @Override
    public Long getCallbackQueryChatId() {
        return update.getCallbackQuery().getMessage().getChatId();
    }

    @Override
    public Integer getCallbackQueryMessageId() {
        return update.getCallbackQuery().getMessage().getMessageId();
    }

    @Override
    public void setOtv(Items[] items) {
        this.ytItems = items;
    }

    @Override
    public Items[] getOtv() {
        return ytItems;
    }

    @Override
    public Integer getMessageId() {
        return update.getMessage().getMessageId();
    }
}
