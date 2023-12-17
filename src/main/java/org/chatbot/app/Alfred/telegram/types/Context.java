package org.chatbot.app.Alfred.telegram.types;

import org.chatbot.app.Alfred.database.SqliteDB;
import org.chatbot.app.Alfred.youtube.Items;

public interface Context {
    String getText();
    Long getChatId();
    String getUserName();
    SqliteDB getDB();
    MusicService getMS();
    String getCallbackQueryData();
    Long getCallbackQueryChatId();
    Integer getCallbackQueryMessageId();
    void setOtv(Items[] items);
    Items[] getOtv();
    Integer getMessageId();
}
