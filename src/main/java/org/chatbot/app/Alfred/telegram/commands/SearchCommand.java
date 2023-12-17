package org.chatbot.app.Alfred.telegram.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;
import org.chatbot.app.Alfred.telegram.types.MusicService;
import org.chatbot.app.Alfred.youtube.Items;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

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
                sender.sendMsgWithInlineMarkup(ctx.getChatId(), otv[0].getSnippet().getTitle().replace("&quot;", "\"") +
                    "\n" + otv[0].getSnippet().getChannelTitle().replace("&quot;", "\"") + "\n" +
                    "https://www.youtube.com/watch?v=" + otv[0].getId().getVideoId()
                    + "\n" + otv[0].getSnippet().getPublishTime(), getInlineKeyboardMarkup());
        }
    }
    public static InlineKeyboardMarkup getInlineKeyboardMarkup() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("<");
        inlineKeyboardButton1.setCallbackData("prev");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText(">");
        inlineKeyboardButton2.setCallbackData("next");
        rowInline.add(inlineKeyboardButton1);
        rowInline.add(inlineKeyboardButton2);
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;

    }

    @Override
    public String getCommandName() {
        return "/search";
    }
}
