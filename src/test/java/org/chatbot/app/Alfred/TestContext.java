package org.chatbot.app.Alfred;

import org.chatbot.app.Alfred.telegram.types.Context;

public class TestContext implements Context {
    private String text;
    private  Long chatId;
    private String userName;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
