package org.chatbot.app.Alfred.telegram.commands;

import org.chatbot.app.Alfred.database.SqliteDB;
import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;

public class HistoryCommand implements Command {
    public static final String HISTORY_PATH = "src/main/resources/history.db";
    private final SqliteDB db = new SqliteDB();
    @Override
    public void execute(MessageSender sender, Context ctx) {
        StringBuilder ans = new StringBuilder();
        for (String e : db.getLastFiveQueries(ctx.getChatId())) {
            ans.append(e).append("\n");
        }
        sender.sendMsg(ctx.getChatId(), String.format(
            "Your search query history:\n%s", ans));

    }

    @Override
    public String getCommandName() {
        return "/history";
    }
}
