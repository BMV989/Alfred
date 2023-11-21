package org.chatbot.app.Alfred.Commands;

import org.chatbot.app.Alfred.TestSqliteDB;
import org.chatbot.app.Alfred.database.SqliteDB;
import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class HistoryCommandTest implements Command {
    public static final String HISTORY_PATH = "src/main/resources/history.db";
    private final TestSqliteDB db = new TestSqliteDB();

    private String ans = "";
    @Override
    public void execute(MessageSender sender, Context ctx) {
        getHistoryKeyboardMarkup(ctx.getChatId());
        sender.sendMsg(ctx.getChatId(), "Your search query history:\n" + ans);

    }
    private void getHistoryKeyboardMarkup(Long chatId) {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        for (String button : db.getLastFiveQueries(chatId)) {
            KeyboardRow row = new KeyboardRow();
            row.add(String.format("/search %s", button));
            keyboard.add(row);
        }
        keyboardMarkup.setKeyboard(keyboard);
        for (int i = 0; i < keyboard.size(); i++){
            this.ans += keyboard.get(i).get(0).getText();
            this.ans += " ";
        }
    }
    @Override
    public String getCommandName() {
        return "/history";
    }
}
