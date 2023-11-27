package org.chatbot.app.Alfred;

import java.io.IOException;
import org.chatbot.app.Alfred.database.SqliteDB;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MusicService;

public class TestContext implements Context {
    private String text;
    private  Long chatId;
    private String userName;
    private final MusicService ms = new TestYoutube();
    public static final SqliteDB db = new SqliteDB("./src/test/resources/history.db");
    protected TestContext(String text, Long chatId, String userName) {
        this.chatId = chatId;
        this.text = text;
        this.userName = userName;
    }
    protected TestContext(Long chatId, String userName) {
        this.chatId = chatId;
        this.text = null;
        this.userName = userName;
    }
    @Override
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Long getChatId() {
        return chatId;
    }
    public void setChatId(Long chaId){
        this.chatId = chaId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public SqliteDB getDB() {
        return db;
    }

    @Override
    public MusicService getMS() {
        return ms;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
