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
    @ValueSource(strings = {"/start","/info","/help","dsdasda"})
    void testBaseCommands(String command){
        ctx.setText(command);
        CommandHandlerTest handler = new CommandHandlerTest(ctx, sender);
        handler.handleCommand();
        assertEquals(sender.getMsg().getChatId(), id.toString());
        assertNotNull(sender.getMsg().getText());
    }
    @Test
    void testSearchCommand(){
        ctx.setText("/search oxxxymiron");
        CommandHandlerTest handler = new CommandHandlerTest(ctx, sender);
        handler.handleCommand();
        assertEquals(sender.getMsg().getText(),"Oxxxymiron feat. 1.Kla$ — 1.Kla$ Pt. 2 (2023)\n" +
                "oxxxymironofficial\n" +
                "https://www.youtube.com/watch?v=ZtCspCOOwRE\n" +
                "2023-08-11T12:55:36Z");
    }
    @Test
    void testHistoryCommand(){
        ctx.setText("/history");
        CommandHandlerTest handler = new CommandHandlerTest(ctx, sender);
        handler.handleCommand();
        assertEquals(sender.getMsg().getText(),"Your search query history:\n" +
                "/search test /search Мэйби Бейби /search Слава КПСС /search Bebra /search oxxxymiron ");
    }
}
