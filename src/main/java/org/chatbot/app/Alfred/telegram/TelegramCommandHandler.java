package org.chatbot.app.Alfred.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TelegramCommandHandler {
    private final TelegramBot tg;
    private String text;

    private final Long chatId;
    private final String command;
    private final String user;

    public TelegramCommandHandler(Long chatId, String command, String user) {
        this.chatId = chatId;
        this.command = command;
        this.user = user;
        this.tg = new TelegramBot();
    }
    public  TelegramCommandHandler(Long chatId, String command, String user, boolean test) {
        this.chatId = chatId;
        this.command = command;
        this.user = user;
        this.tg = new TelegramBot(test);
    }
    public SendMessage getMessage(){
        return tg.getMessage();
    }


    public void handleCommand() {
        String key = command.split(" ")[0];
        if (command.split(" ").length > 1) {
            // проблема с тем что берем только 1 индекс (больше одно слова не читает)
            this.text = command.replace(key+" ", "");
        }
        switch (key) {
            case "/start":
                tg.sendMsg(chatId,String.format("Hello @%s! What can I do for you?", user));
                break;
            case "/info":
                tg.sendMsg(chatId,"this bot was created by Bebralover team");
                break;
            case "/help":
                tg.sendMsg(chatId,"""
                this bot has such commands:
                /help
                /info
                /search
                /start
                /history""");
                break;
            case "/search":
                Search search = new Search(text, chatId);
                search.smartSearch();
                tg.sendMsg(chatId, search.getResult());
                break;
            case "/history":
                tg.sendMsg(chatId,"Your search query history:");
                History history = new History(chatId);
                tg.sendMsg(chatId, history.getAns());
                break;
            default:
                tg.sendMsg(chatId,"There's no such command!/help");
                break;
        }
    }

}
