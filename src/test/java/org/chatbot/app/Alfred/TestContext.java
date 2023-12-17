package org.chatbot.app.Alfred;

import org.chatbot.app.Alfred.database.SqliteDB;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MusicService;
import org.chatbot.app.Alfred.youtube.Items;

public class TestContext implements Context {
    private String text;
    private  Long chatId;
    private Items[] otv;
    private String userName;
    private String callbackQueryData;
    private Long callbackQueryChatId;
    private Integer callbackQueryMessageId;
    private final MusicService ms = new TestYoutube();
    public static final SqliteDB db = new SqliteDB("./src/test/resources/history.db");
    protected TestContext(String text, Long chatId, String userName) {
        this.chatId = chatId;
        this.text = text;
        this.userName = userName;
    }
    protected TestContext(String text, Long chatId, String userName, String callbackQueryData,
        Long callbackQueryChatId, Integer callbackQueryMessageId) {
        this.chatId = chatId;
        this.text = text;
        this.userName = userName;
        this.callbackQueryData = callbackQueryData;
        this.callbackQueryChatId = callbackQueryChatId;
        this.callbackQueryMessageId = callbackQueryMessageId;
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

    @Override
    public String getCallbackQueryData() {
        return callbackQueryData;
    }
    public void setCallbackQueryData(String data) {
        this.callbackQueryData = data;
    }
    @Override
    public Long getCallbackQueryChatId() {
        return this.callbackQueryChatId;
    }
    public void getCallbackQueryChatId(long id) {
        this.callbackQueryChatId = id;
    }
    @Override
    public Integer getCallbackQueryMessageId() {
        return this.callbackQueryMessageId;
    }

    public void setCallbackQueryMessageId(Integer callbackQueryMessageId) {
        this.callbackQueryMessageId = callbackQueryMessageId;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public void setOtv(Items[] items) {
        this.otv = items;
    }
    @Override
    public Items[] getOtv() {
        return  this.otv;
    }

    @Override
    public Integer getMessageId() {
        return this.callbackQueryMessageId;
    }
    public void setMessageId(Integer messageId) {
        this.callbackQueryMessageId = messageId;
    }
}
