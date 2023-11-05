package org.chatbot.app.Alfred.telegram;


import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramCommandHandler {
    private final MessageSender messageSender;
    private final Context ctx;
    private String text;

    public TelegramCommandHandler(Context ctx) {
        this.messageSender = new TelegramMessageSender();
        this.ctx = ctx;
    }
    public TelegramCommandHandler(Context ctx,
        MessageSender messageSender) {
        this.messageSender = messageSender;
        this.ctx = ctx;
    }

    public void handleCommand() {
        String command = ctx.getText();
        Long chatId = ctx.getChatId();
        String user = ctx.getUserName();
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
