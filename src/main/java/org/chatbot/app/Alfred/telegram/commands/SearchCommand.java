package org.chatbot.app.Alfred.telegram.commands;

import static org.chatbot.app.Alfred.telegram.commands.HistoryCommand.HISTORY_PATH;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;

public class SearchCommand implements Command {
    //TODO: refactor this Search class

    static class Search {
        private final ArrayList<String> links = new ArrayList<>();
        private final String text;
        private final Long chatId;
        public Search(String text, Long chatId) {
            this.text = text;
            this.chatId = chatId;
            try {
                File output = new File(HISTORY_PATH);
                File resources = new File(output.getParent());
                resources.mkdir();
                output.createNewFile();
                FileWriter writer = new FileWriter(output, true);
                writer.write(String.format("%s:%s\n", this.chatId, text));
                writer.flush();
                writer.close();
            }
            catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        public void smartSearch(){
            System.out.println(text);
        }
        public String getResult(){
            if(links.isEmpty()){
                return "Nothing was found for your query :(";
            }
            else{
                StringBuilder ans = new StringBuilder();
                for (String link : links) {
                    ans.append(link).append("\n");
                }
                return ans.toString();
            }
        }
    }
    @Override
    public void execute(MessageSender sender, Context ctx) {
        String key = ctx.getText().split(" ")[0];
        String text =  ctx.getText().split(" ").length > 1 ? ctx.getText()
            .replace(key+" ",
            "") : null;
        Search search = new Search(text, ctx.getChatId());
        search.smartSearch();
        sender.sendMsg(ctx.getChatId(), search.getResult());
    }

    @Override
    public String getCommandName() {
        return "/search";
    }
}
