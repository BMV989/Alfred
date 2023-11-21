package org.chatbot.app.Alfred.telegram.commands;

import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;

public class InfoCommand implements Command {

    @Override
    public void execute(MessageSender sender, Context ctx) {
        sender.sendMsg(ctx.getChatId(),
                "this bot was created by Bebralover team");
    }

    @Override
    public String getCommandName() {
        return "/info";
    }
}
