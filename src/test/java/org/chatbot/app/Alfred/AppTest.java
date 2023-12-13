package org.chatbot.app.Alfred;

import static org.chatbot.app.Alfred.TestContext.db;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.chatbot.app.Alfred.telegram.TelegramCommandHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AppTest {

    private static final Long id = 249088829L;
    private  final String user = "@AmazingType";
    private final TestMessageSender sender = new TestMessageSender();
    private  final TestContext ctx = new TestContext(id, user);

    private final ArrayList<String> expected = new ArrayList<>(){{add("Каспийский груз");
        add("Мэйби Бэйби");
        add("Слава КПСС");
        add("Хан Замай");
        add("oxxxymiron");
    }};

    @ParameterizedTest
    @ValueSource(strings = {"/start","/info","/help","dsdasda"})
    void testBaseCommands(String command){
        ctx.setText(command);
        TelegramCommandHandler handler = new TelegramCommandHandler(sender);
        handler.handleCommand(ctx);
        assertEquals(sender.getMsg().getChatId(), id.toString());
        assertNotNull(sender.getMsg().getText());
    }
    @BeforeAll
    static void setupDB() {
        db.up();
        db.insert(id, "oxxxymiron");
        db.insert(id, "Хан Замай");
        db.insert(id, "Слава КПСС");
        db.insert(id, "Мэйби Бэйби");
        db.insert(id, "Каспийский груз");
    }
    @AfterAll
    static void clearDB() {
        db.down();
    }
    @Test
    void testSearchCommand(){
        ctx.setText("/search oxxxymiron");
        TelegramCommandHandler handler = new TelegramCommandHandler(sender);
        handler.handleCommand(ctx);
        assertEquals("""
            Oxxxymiron feat. 1.Kla$ — 1.Kla$ Pt. 2 (2023)
            oxxxymironofficial
            https://www.youtube.com/watch?v=ZtCspCOOwRE
            2023-08-11T12:55:36Z""", sender.getMsg().getText());
    }
    @Test
    void testHistoryCommand(){
        ctx.setText("/history");
        TelegramCommandHandler handler = new TelegramCommandHandler(sender);
        handler.handleCommand(ctx);
        List<String> fiveQueries = db.getLastFiveQueries(id);
        assertTrue(fiveQueries.equals(expected));
        assertEquals("Your search query history:", sender.getMsg().getText());
        assertNotNull(sender.getMsg().getReplyMarkup());
    }
}