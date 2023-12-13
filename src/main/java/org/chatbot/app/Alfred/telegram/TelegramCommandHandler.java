package org.chatbot.app.Alfred.telegram;


import java.io.IOException;
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
import org.chatbot.app.Alfred.youtube.Items;

public class TelegramCommandHandler {
    private Items[] items;
    private final MessageSender messageSender;
    private final List<Command> commandsList = new ArrayList<>() {{
        add(new StartCommand());
        add(new SearchCommand());
        add(new HistoryCommand());
        add(new InfoCommand());
        add(new HelpCommand());
    }};
    private final HashMap<String, Command> commands = new HashMap<>() {{
        for (Command c : commandsList) {
            put(c.getCommandName(), c);
        }
    }};
    public TelegramCommandHandler() {
        this.messageSender = new TelegramMessageSender();
    }

    public TelegramCommandHandler(
        MessageSender messageSender) {
        this.messageSender = messageSender;
    }
    public void handleCommand(Context ctx) {
        String key = ctx.getText().split(" ")[0];
        if(commands.containsKey(key)) {
            try {
                commands.get(key).execute(messageSender, ctx);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            messageSender.sendMsg(ctx.getChatId(), "There's no such command!/help");
        }
        if (ctx.getOtv() != null) {
            this.items = ctx.getOtv();
        }
    }
    public void handleCallbackQuery(Context ctx) {
        // handle Callback Query
    }

}
