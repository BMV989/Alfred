package org.chatbot.app.Alfred.telegram.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.chatbot.app.Alfred.telegram.types.Command;
import org.chatbot.app.Alfred.telegram.types.Context;
import org.chatbot.app.Alfred.telegram.types.MessageSender;

public class HistoryCommand implements Command {
    public static final String HISTORY_PATH = "./src/main/java/resources/hist.txt";
    // TODO: refactor history class
    static class History {
        private String ans = "";
        public History(Long chatId){
            try{
                File hist = new File(HISTORY_PATH);
                FileReader fr = new FileReader(hist);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null){
                    String id = line.split(":")[0];
                    if (id.equals(chatId.toString())){
                        ans+= line.split(":")[1] + "\n";
                    }
                    line = reader.readLine();
                }
                fr.close();
                reader.close();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        public String getAns(){
            return ans;
        }
    }

    @Override
    public void execute(MessageSender sender, Context ctx) {
        sender.sendMsg(ctx.getChatId(),"Your search query history:");
        History history = new History(ctx.getChatId());
        sender.sendMsg(ctx.getChatId(), history.getAns());
    }

    @Override
    public String getCommandName() {
        return "/history";
    }
}
