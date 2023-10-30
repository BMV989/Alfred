package org.chatbot.app.Alfred.telegram;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Search {
    // for future
    //private String [] links;
    ArrayList<String> links = new ArrayList<>();
    //
    private String text;
    Search(String text){
        this.text = text;
        try {
            File output = new File("D:/intellij/code/Alfred/src/main/java/org/chatbot/app/Alfred/resources/hist.txt");
            FileWriter writer = new FileWriter(output, true);
            writer.write(text + "\n");
            writer.flush();
            writer.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void smart_search(){
        System.out.println(text);
    }
    public String get_result(){
        if(links.size() == 0){
            return "Nothing was found for your query :(";
        }
        else{
            String ans = "";
            for(int i = 0; i < links.size(); i++){
                ans += links.get(i) + "\n";
            }
            return ans;
        }
    }
}
