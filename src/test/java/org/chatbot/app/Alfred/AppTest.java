package org.chatbot.app.Alfred;

import org.chatbot.app.Alfred.telegram.TelegramBot;
import org.chatbot.app.Alfred.telegram.TelegramCommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest {

    Long id = 249088829L;
    String user = "@AmazingType";

    @ParameterizedTest
    @ValueSource(strings = {"/start","/info","/help","/search Bebra","/history", "dsdasda"})
    void testBaseCommands(String command){
        TelegramCommandHandler handler = new TelegramCommandHandler(id, command, user);
        handler.isTest(true);
        handler.handleCommand();
        assertEquals(handler.getMessage().getChatId(), id.toString());
        assertNotNull(handler.getMessage().getText());
    }

    @Test
    void testCommandSearch(){
        String name = "oxxxymiron";
        TelegramCommandHandler handler = new TelegramCommandHandler(id, "/search " + name, user);
        handler.isTest(true);
        handler.handleCommand();
        assertEquals(handler.getMessage().getText(), "Nothing was found for your query :(");
    }

    @Test
    void testCommandHistory(){
        String ans = "";
        try{
            File hist = new File("./src/main/java/resources/hist.txt");
            FileReader fr = new FileReader(hist);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null){
                String fileId = line.split(":")[0].toString();
                if (fileId.equals(id.toString())){
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

        TelegramCommandHandler handler = new TelegramCommandHandler(id, "/history", user);
        handler.isTest(true);
        handler.handleCommand();
        assertEquals(handler.getMessage().getText(), ans);
    }
}
