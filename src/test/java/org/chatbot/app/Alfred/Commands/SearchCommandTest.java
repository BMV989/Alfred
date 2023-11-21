package org.chatbot.app.Alfred.Commands;

import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;

public class SearchCommandTest implements Command {

    @Override
    public void execute(MessageSender sender, Context ctx) {
        sender.sendMsg(ctx.getChatId(), "for search please provide query");
    }

    @Override
    public String getCommandName() {
        return "/search";
    }
}
