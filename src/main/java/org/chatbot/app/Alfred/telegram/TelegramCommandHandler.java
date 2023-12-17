package org.chatbot.app.Alfred.telegram;


import static org.chatbot.app.Alfred.telegram.commands.SearchCommand.getInlineKeyboardMarkup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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
    private final HashMap<Integer, ItemsArray> cache;
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
        this.cache = new HashMap<>();
    }

    public TelegramCommandHandler(MessageSender messageSender) {
        this.cache = new HashMap<>();
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
            cache.put(ctx.getMessageId(), new ItemsArray(ctx.getOtv()));
        }
    }
    public void handleCallbackQuery(Context ctx) {
        Integer messageId = ctx.getCallbackQueryMessageId() - 2;
        if (!(cache.containsKey(messageId))) {
            return;
        }
        if (Objects.equals(ctx.getCallbackQueryData(), "next")) {
            ItemsArray items = cache.get(messageId);
            Items item = items.next();
            messageSender.editMsg(ctx.getCallbackQueryChatId(),
                item.getSnippet().getTitle().replace("&quot;", "\"") +
                    "\n" + item.getSnippet().getChannelTitle().replace("&quot;", "\"") + "\n" +
                    "https://www.youtube.com/watch?v=" + item.getId().getVideoId()
                    + "\n" + item.getSnippet().getPublishTime(),
                ctx.getCallbackQueryMessageId(), getInlineKeyboardMarkup());
        }

        if (Objects.equals(ctx.getCallbackQueryData(), "prev")) {
            ItemsArray items = cache.get(messageId);
            Items item = items.prev();
            messageSender.editMsg(ctx.getCallbackQueryChatId(),
                item.getSnippet().getTitle().replace("&quot;", "\"") +
                    "\n" + item.getSnippet().getChannelTitle().replace("&quot;", "\"") + "\n" +
                    "https://www.youtube.com/watch?v=" + item.getId().getVideoId()
                    + "\n" + item.getSnippet().getPublishTime(),
                ctx.getCallbackQueryMessageId(), getInlineKeyboardMarkup());
        }
    }

}
