package org.chatbot.app.Alfred.telegram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class History {
    private String ans = "";

    public static final String HISTORY_PATH = "./src/main/java/resources/hist.txt";
    public History(Long chatId){
        try{
            File hist = new File(HISTORY_PATH);
            FileReader fr = new FileReader(hist);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null){
                String id = line.split(":")[0].toString();
                if (id.equals(chatId.toString())){
                    ans+= line.split(":")[1].toString() + "\n";
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
