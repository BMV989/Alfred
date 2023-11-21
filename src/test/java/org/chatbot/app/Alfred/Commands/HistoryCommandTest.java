package org.chatbot.app.Alfred.Commands;

import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;

public class HistoryCommandTest implements Command {

    @Override
    public void execute(MessageSender sender, Context ctx) {
        sender.sendMsg(ctx.getChatId(), "Your search query history");
    }

    @Override
    public String getCommandName() {
        return "/history";
    }
}
