package org.chatbot.app.Alfred.telegram.commands;

import static org.chatbot.app.Alfred.telegram.commands.HistoryCommand.HISTORY_PATH;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.chatbot.app.Alfred.database.SqliteDB;
import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;
import org.chatbot.app.Alfred.youtube.YotubeDataClass;
import org.chatbot.app.Alfred.youtube.Youtube;

public class SearchCommand implements Command {
    private final SqliteDB db = new SqliteDB();
    private YotubeDataClass otv;
    @Override
    public void execute(MessageSender sender, Context ctx) {
        String header = "Search results:";
        String key = ctx.getText().split(" ")[0];
        String text =  ctx.getText().split(" ").length > 1 ? ctx.getText()
            .replace(key+" ",
            "") : null;
        if (text == null) {
            sender.sendMsg(ctx.getChatId(), "for search please provide query");
            return;
        }
        try{
            Youtube resp = new Youtube(text);
            //resp.getResponse();
            this.otv = resp.findResult();
            System.out.println(text);
            System.out.println(this.otv.items[3].id.videoId);
            System.out.println(this.otv.items[3].snippet.channelTitle);
            System.out.println(this.otv.items[3].snippet.title);
            System.out.println(this.otv.items[3].snippet.publishTime);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        db.insert(ctx.getChatId(), text);

        //sender.sendMsg(ctx.getChatId(),"Sry, nothing was found for now :(");
        if (otv.items.length == 0){
            sender.sendMsg(ctx.getChatId(),"Sry, nothing was found for now :(");
        }
        else{
            sender.sendMsg(ctx.getChatId(), header);
            for (int i = 0; i < otv.items.length; i++){
                sender.sendMsg(ctx.getChatId(), otv.items[i].snippet.title.replace("&quot;", "\"") +
                        "\n" + otv.items[i].snippet.channelTitle.replace("&quot;", "\"") + "\n" +
                        "https://www.youtube.com/watch?v=" + otv.items[i].id.videoId
                + "\n" + otv.items[0].snippet.publishTime);
            }
        }
    }

    @Override
    public String getCommandName() {
        return "/search";
    }
}
