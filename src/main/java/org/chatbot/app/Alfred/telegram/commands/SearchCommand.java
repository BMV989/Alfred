package org.chatbot.app.Alfred.telegram.commands;

import java.io.IOException;
import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;
import org.chatbot.app.Alfred.telegram.types.MusicService;
import org.chatbot.app.Alfred.youtube.Items;

public class SearchCommand implements Command {

    @Override
    public void execute(MessageSender sender, Context ctx) throws IOException {
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
        resp.setQuery(text);
        Items[] otv = resp.findResult().getItems();
        ctx.getDB().insert(ctx.getChatId(), text);

        if (otv.length == 0){
            sender.sendMsg(ctx.getChatId(),"Sry, nothing was found for now :(");
        }
        else{
            sender.sendMsg(ctx.getChatId(), header);
            ctx.setOtv(otv);
            for (Items items : otv) {
                sender.sendMsg(ctx.getChatId(), items.getSnippet().getTitle().replace("&quot;", "\"") +
                    "\n" + items.getSnippet().getChannelTitle().replace("&quot;", "\"") + "\n" +
                    "https://www.youtube.com/watch?v=" + items.getId().getVideoId()
                    + "\n" + otv[0].getSnippet().getPublishTime());
            }
        }
    }

    @Override
    public String getCommandName() {
        return "/search";
    }
}
