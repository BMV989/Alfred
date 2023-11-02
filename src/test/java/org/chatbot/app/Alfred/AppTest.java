package org.chatbot.app.Alfred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.chatbot.app.Alfred.telegram.TelegramCommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AppTest {

    private final Long id = 249088829L;
    private  final String user = "@AmazingType";

    @ParameterizedTest
    @ValueSource(strings = {"/start","/info","/help","/search Bebra","/history", "dsdasda"})
    void testBaseCommands(String command){
        TelegramCommandHandler handler = new TelegramCommandHandler(id,
            command, user, true);
        handler.handleCommand();
        assertEquals(handler.getMessage().getChatId(), id.toString());
        assertNotNull(handler.getMessage().getText());
    }

    @Test
    void testCommandSearch(){
        String name = "oxxxymiron";
        TelegramCommandHandler handler = new TelegramCommandHandler(id,
            "/search " + name, user, true);
        handler.handleCommand();
        assertEquals(handler.getMessage().getText(), "Nothing was found for your query :(");
    }

    @Test
    void testCommandHistory(){
        StringBuilder ans = new StringBuilder();
        try{
            File hist = new File("./src/main/java/resources/hist.txt");
            File resources = new File("./src/main/java/resources");
            resources.mkdir();
            hist.createNewFile();
            FileReader fr = new FileReader(hist);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null){
                String fileId = line.split(":")[0];
                if (fileId.equals(id.toString())){
                    ans.append(line.split(":")[1]).append("\n");
                }
                line = reader.readLine();
            }
            fr.close();
            reader.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        TelegramCommandHandler handler = new TelegramCommandHandler(id,
            "/history", user, true);
        handler.handleCommand();
        assertEquals(handler.getMessage().getText(), ans.toString());
    }
}
