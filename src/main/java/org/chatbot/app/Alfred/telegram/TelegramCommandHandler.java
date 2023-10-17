package org.chatbot.app.Alfred.telegram;
public class TelegramCommandHandler {
    public String handleCommand(String command) {
        return switch (command) {
            case "/start" -> "Hello user! What can I do for you?";
            case "/info" -> "this bot was created by Bebralover team";
            case "/help" -> """
                this bot has such commands:
                /help
                /info""";
            default -> "There's no such command!/help";
        };
    }
}
