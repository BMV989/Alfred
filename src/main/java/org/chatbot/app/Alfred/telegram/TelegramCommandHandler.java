package org.chatbot.app.Alfred.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramCommandHandler {
    private final TelegramBot tg = new TelegramBot();
    private String text;
    private boolean test = false;

    private final Long chatId;
    private final String command;
    private final String user;

    public TelegramCommandHandler(Long chatId, String command, String user) {
        this.chatId = chatId;
        this.command = command;
        this.user = user;
    }
    public SendMessage getMessage(){
        return tg.getMessage();
    }

    public void isTest(boolean test){
        this.test = test;
    }

    public void handleCommand() {
        Long chatId = this.chatId;
        String command = this.command;
        String user = this.user;
        String key = command.split(" ")[0];
        if (command.split(" ").length > 1) {
            // проблема с тем что берем только 1 индекс (больше одно слова не читает)
            this.text = command.replace(key+" ", "");
        }
        switch (key) {
            case "/start":
                tg.sendMsg(chatId,String.format("Hello @%s! What can I do for you?", user), test);
                break;
            case "/info":
                tg.sendMsg(chatId,"this bot was created by Bebralover team", test);
                break;
            case "/help":
                tg.sendMsg(chatId,"""
                this bot has such commands:
                /help
                /info""", test);
                break;
            case "/search":
                Search search = new Search(text, chatId);
                search.smartSearch();
                tg.sendMsg(chatId, search.getResult(), test);
                break;
            case "/history":
                tg.sendMsg(chatId,"Your search query history:", test);
                History history = new History(chatId);
                tg.sendMsg(chatId, history.getAns(), test);
                break;
            default:
                tg.sendMsg(chatId,"There's no such command!/help", test);
                break;
        }
    }

}
