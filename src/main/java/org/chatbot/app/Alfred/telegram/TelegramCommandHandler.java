package org.chatbot.app.Alfred.telegram;

public class TelegramCommandHandler {
    TelegramBot Tg = new TelegramBot();
    /*
    нужно исправить на void, ибо команды вообще разное возращают,
    обычно метод send message с самим сообщением, либо делают запросы
    по апишкам, файлам на локал машине, бд в идиале.
     */
    public void handleCommand(String command, Long chatId) {
        switch (command) {
            case "/start" -> Tg.sendMsg(chatId,"Hello user! What can I do for you?");
            case "/info" -> Tg.sendMsg(chatId,"this bot was created by Bebralover team");
            case "/help" -> Tg.sendMsg(chatId,"""
                this bot has such commands:
                /help
                /info""");
            default -> Tg.sendMsg(chatId,"There's no such command!/help");
        };
    }

}
