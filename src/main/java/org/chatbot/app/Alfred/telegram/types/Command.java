package org.chatbot.app.Alfred.telegram.types;

import java.io.IOException;

public interface Command {
    void execute(MessageSender sender, Context ctx) throws IOException;
    String getCommandName();

}
