package org.chatbot.app.Alfred.telegram;

public class TelegramCommandHandler {
    private final TelegramBot tg = new TelegramBot();
    /*
    нужно исправить на void, ибо команды вообще разное возращают,
    обычно метод send message с самим сообщением, либо делают запросы
    по апишкам, файлам на локал машине, бд в идиале.
    (выполнение: 100%)
     */

    /*
    нужно добавить команду /search, у которой есть заглушка, она
    возвращает, что ничего не найдено, пока апишки не подключены,
    собственно в этом заглушка. И нужна команда /history, которая
    возвращает все запросы /search, тобишь /search дожен запоминать
    все запросы и складывать их в файл в папке resources, а history
    получает доступ к этому файлу и для текущего юзера получает его
    историю запросов. Хранить хоть в CSV, JSON, или просто в строке.
    (выполнение: 0%)
    */
    public void handleCommand(String command, Long chatId) {
        switch (command) {
            case "/start" -> tg.sendMsg(chatId,"Hello user! What can I do for you?");
            case "/info" -> tg.sendMsg(chatId,"this bot was created by Bebralover team");
            case "/help" -> tg.sendMsg(chatId,"""
                this bot has such commands:
                /help
                /info""");
            default -> tg.sendMsg(chatId,"There's no such command!/help");
        };
    }

}
