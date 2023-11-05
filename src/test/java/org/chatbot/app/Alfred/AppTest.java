package org.chatbot.app.Alfred;

import static org.chatbot.app.Alfred.telegram.commands.HistoryCommand.HISTORY_PATH;
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
    private final TestMessageSender sender = new TestMessageSender();
    private  final TestContext ctx = new TestContext(id, user);

    @ParameterizedTest
    @ValueSource(strings = {"/start","/info","/help","/search Bebra","/history", "dsdasda"})
    void testBaseCommands(String command){
        ctx.setText(command);
        TelegramCommandHandler handler = new TelegramCommandHandler(ctx, sender);
        handler.handleCommand();
        assertEquals(sender.getMsg().getChatId(), id.toString());
        assertNotNull(sender.getMsg().getText());
    }

    @Test
    void testCommandSearch(){
        ctx.setText("/search");
        TelegramCommandHandler handler = new TelegramCommandHandler(ctx, sender);
        handler.handleCommand();
        assertEquals(sender.getMsg().getText(), "Nothing was found for your query :(");
    }

    @Test
    void testCommandHistory(){
        StringBuilder ans = new StringBuilder();
        try{
            File hist = new File(HISTORY_PATH);
            File resources = new File(hist.getParent());
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
        ctx.setText("/history");
        TelegramCommandHandler handler = new TelegramCommandHandler(ctx, sender);
        handler.handleCommand();
        assertEquals(sender.getMsg().getText(), ans.toString());
    }
}
