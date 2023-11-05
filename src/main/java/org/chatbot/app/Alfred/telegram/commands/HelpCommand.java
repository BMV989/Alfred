package org.chatbot.app.Alfred.telegram.commands;

import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;

public class HelpCommand implements Command {
    @Override
    public void execute(MessageSender sender, Context ctx) {
        sender.sendMsg(ctx.getChatId(),"""
                this bot has such commands:
                /help
                /info
                /search
                /start
                /history""");
    }

    @Override
    public String getCommandName() {
        return "/help";
    }
}
