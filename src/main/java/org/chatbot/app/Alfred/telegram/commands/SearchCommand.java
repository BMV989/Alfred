package org.chatbot.app.Alfred.telegram.commands;

import java.io.IOException;

import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;
import org.chatbot.app.Alfred.telegram.types.MusicService;
import org.chatbot.app.Alfred.youtube.YoutubeDataClass;
import org.chatbot.app.Alfred.youtube.Youtube;

public class SearchCommand implements Command {
    private YoutubeDataClass otv;
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
        MusicService resp = ctx.getMS();
        this.otv = resp.findResult();
        System.out.println(text);
        ctx.getDB().insert(ctx.getChatId(), text);

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
