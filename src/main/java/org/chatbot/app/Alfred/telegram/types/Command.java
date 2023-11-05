package org.chatbot.app.Alfred.telegram.types;

public interface Command {
    void execute(MessageSender sender, Context ctx);
    String getCommandName();

}
