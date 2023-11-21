package org.chatbot.app.Alfred.telegram.commands;

import java.util.ArrayList;
import java.util.List;
import org.chatbot.app.Alfred.database.SqliteDB;
import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class HistoryCommand implements Command {
    public static final String HISTORY_PATH = "src/main/resources/history.db";
    private final SqliteDB db = new SqliteDB();
    @Override
    public void execute(MessageSender sender, Context ctx) {

        sender.sendMsgWithMarkup(ctx.getChatId(), "Your search query history:",
            getHistoryKeyboardMarkup(ctx.getChatId()));

    }
    private ReplyKeyboardMarkup getHistoryKeyboardMarkup(Long chatId) {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        for (String button : db.getLastFiveQueries(chatId)) {
            KeyboardRow row = new KeyboardRow();
            row.add(String.format("/search %s", button));
            keyboard.add(row);
        }
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
    @Override
    public String getCommandName() {
        return "/history";
    }
}
