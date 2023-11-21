package org.chatbot.app.Alfred;

import org.chatbot.app.Alfred.database.SqliteDB;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.chatbot.app.Alfred.telegram.TelegramBot;


public class App {

    public static void main(String[] args) {
        SqliteDB db = new SqliteDB();
        try {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
