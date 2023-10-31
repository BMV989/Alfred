package org.chatbot.app.Alfred.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramCommandHandler {
    private final TelegramBot tg = new TelegramBot();
    private final Update context;
    private String text;
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
    public TelegramCommandHandler(Update context) {
        this.context = context;
    }

    public void handleCommand() {
        Long chatId = context.getMessage().getChatId();
        String command = context.getMessage().getText();
        String key = command.split(" ")[0];
        if (command.split(" ").length > 1) {
            // проблема с тем что берем только 1 индекс (больше одно слова не читает)
            this.text = command.split(" ")[1];
        }
        switch (key) {
            case "/start":
                tg.sendMsg(chatId,String.format("Hello @%s! What can I do for you?", context
                    .getMessage().getChat().getUserName()));
                break;
            case "/info":
                tg.sendMsg(chatId,"this bot was created by Bebralover team");
                break;
            case "/help":
                tg.sendMsg(chatId,"""
                this bot has such commands:
                /help
                /info""");
                break;
            case "/search":
                Search search = new Search(text, context.getMessage().getChatId());
                search.smartSearch();
                tg.sendMsg(chatId, search.getResult());
                break;
            case "/history":
                // TODO: implement me :)
                break;
            default:
                tg.sendMsg(chatId,"There's no such command!/help");
                break;
        }
    }

}
