package org.chatbot.app.Alfred.telegram.types;

import org.chatbot.app.Alfred.database.SqliteDB;
import org.chatbot.app.Alfred.youtube.Youtube;

public interface Context {
    String getText();
    Long getChatId();
    String getUserName();
    SqliteDB getDB();
    MusicService getMS();

}
