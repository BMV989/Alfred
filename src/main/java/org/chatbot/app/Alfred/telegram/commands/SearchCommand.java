package org.chatbot.app.Alfred.telegram.commands;

import static org.chatbot.app.Alfred.telegram.commands.HistoryCommand.HISTORY_PATH;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.chatbot.app.Alfred.database.SqliteDB;
import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;

public class SearchCommand implements Command {
    private final SqliteDB db = new SqliteDB();

    @Override
    public void execute(MessageSender sender, Context ctx) {
        String key = ctx.getText().split(" ")[0];
        String text =  ctx.getText().split(" ").length > 1 ? ctx.getText()
            .replace(key+" ",
            "") : null;
        if (text == null) {
            sender.sendMsg(ctx.getChatId(), "for search please provide query");
            return;
        }
        db.insert(ctx.getChatId(), text);
        sender.sendMsg(ctx.getChatId(),"Sry, nothing was found for now :(");
    }

    @Override
    public String getCommandName() {
        return "/search";
    }
}
