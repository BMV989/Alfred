package org.chatbot.app.Alfred.telegram;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.chatbot.app.Alfred.telegram.commands.HelpCommand;
import org.chatbot.app.Alfred.telegram.commands.HistoryCommand;
import org.chatbot.app.Alfred.telegram.commands.InfoCommand;
import org.chatbot.app.Alfred.telegram.commands.SearchCommand;
import org.chatbot.app.Alfred.telegram.commands.StartCommand;
import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;

public class TelegramCommandHandler {
    private final MessageSender messageSender;
    private final List<Command> commandsList = new ArrayList<>() {{
        add(new StartCommand());
        add(new SearchCommand());
        add(new HistoryCommand());
        add(new InfoCommand());
        add(new HelpCommand());
    }};
    private final Context ctx;
    private final HashMap<String, Command> commands = new HashMap<>() {{
        for (Command c : commandsList) {
            put(c.getCommandName(), c);
        }
    }};

    public TelegramCommandHandler(Context ctx) {
        this.messageSender = new TelegramMessageSender();
        this.ctx = ctx;
    }
    public TelegramCommandHandler(Context ctx,
        MessageSender messageSender) {
        this.messageSender = messageSender;
        this.ctx = ctx;
    }
    public void handleCommand() {
        String key = ctx.getText().split(" ")[0];
        if(commands.containsKey(key)) {
            commands.get(key).execute(messageSender, ctx);
        } else {
            messageSender.sendMsg(ctx.getChatId(), "There's no such command!/help");
        }
    }

}
