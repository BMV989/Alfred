package org.chatbot.app.Alfred.telegram.commands;

import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;

public class StartCommand implements Command {

    @Override
    public void execute(MessageSender sender, Context ctx) {
        sender.sendMsg(ctx.getChatId(),String.format("Hello @%s! What can I do for you?",
            ctx.getUserName()));
    }

    @Override
    public String getCommandName() {
        return "/start";
    }
}
