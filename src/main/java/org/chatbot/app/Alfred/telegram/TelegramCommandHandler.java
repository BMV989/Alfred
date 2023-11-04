package org.chatbot.app.Alfred.telegram;

import org.chatbot.app.Alfred.telegram.controller.TelegramBot;

public class TelegramCommandHandler {
    private final MessageSender messageSender;
    private String text;

    private final Long chatId;
    private final String command;
    private final String user;

    public TelegramCommandHandler(Long chatId, String command, String user) {
        this.chatId = chatId;
        this.command = command;
        this.user = user;
        this.messageSender = new TelegramBot();
    }
    public TelegramCommandHandler(Long chatId, String command, String user,
        MessageSender messageSender) {
        this.chatId = chatId;
        this.command = command;
        this.user = user;
        this.messageSender = messageSender;
    }

    public void handleCommand() {
        String key = command.split(" ")[0];
        if (command.split(" ").length > 1) {
            this.text = command.replace(key+" ", "");
        }
        switch (key) {
            case "/start":
                messageSender.sendMsg(chatId,String.format("Hello @%s! What can I do for you?", user));
                break;
            case "/info":
                messageSender.sendMsg(chatId,"this bot was created by Bebralover team");
                break;
            case "/help":
                messageSender.sendMsg(chatId,"""
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
                messageSender.sendMsg(chatId, search.getResult());
                break;
            case "/history":
                messageSender.sendMsg(chatId,"Your search query history:");
                History history = new History(chatId);
                messageSender.sendMsg(chatId, history.getAns());
                break;
            default:
                messageSender.sendMsg(chatId,"There's no such command!/help");
                break;
        }
    }

}
