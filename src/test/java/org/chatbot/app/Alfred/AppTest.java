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
        CommandHandlerTest handler = new CommandHandlerTest(ctx, sender);
        handler.handleCommand();
        assertEquals(sender.getMsg().getChatId(), id.toString());
        assertNotNull(sender.getMsg().getText());
    }
}
