package org.chatbot.app.Alfred;

import static org.chatbot.app.Alfred.TestContext.db;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.chatbot.app.Alfred.telegram.TelegramCommandHandler;
import org.chatbot.app.Alfred.telegram.TelegramContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
        TelegramCommandHandler handler = new TelegramCommandHandler(ctx, sender);
        handler.handleCommand();
        assertEquals(sender.getMsg().getChatId(), id.toString());
        assertNotNull(sender.getMsg().getText());
    }
    @BeforeAll
    static void setupDB() {
        db.up();
    }
    @AfterAll
    static void clearDB() {
        db.down();
    }
    @Test
    void testSearchCommand(){
        ctx.setText("/search oxxxymiron");
        TelegramCommandHandler handler = new TelegramCommandHandler(ctx, sender);
        handler.handleCommand();
        assertEquals("""
            Oxxxymiron feat. 1.Kla$ — 1.Kla$ Pt. 2 (2023)
            oxxxymironofficial
            https://www.youtube.com/watch?v=ZtCspCOOwRE
            2023-08-11T12:55:36Z""", sender.getMsg().getText());
    }
    @Test
    void testHistoryCommand(){
        ctx.setText("/history");
        TelegramCommandHandler handler = new TelegramCommandHandler(ctx, sender);
        handler.handleCommand();
        assertEquals("Your search query history:", sender.getMsg().getText());
        assertNotNull(sender.getMsg().getReplyMarkup());
    }
}
