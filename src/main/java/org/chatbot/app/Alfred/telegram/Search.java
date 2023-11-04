package org.chatbot.app.Alfred.telegram;

import static org.chatbot.app.Alfred.telegram.History.HISTORY_PATH;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Search {
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
