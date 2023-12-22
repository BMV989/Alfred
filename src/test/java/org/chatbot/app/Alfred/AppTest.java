package org.chatbot.app.Alfred;

import static org.chatbot.app.Alfred.TestContext.db;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.chatbot.app.Alfred.telegram.TelegramCommandHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    @BeforeEach
    void setupDB() {
        db.up();
        db.insert(id, "oxxxymiron");
        db.insert(id, "Хан Замай");
        db.insert(id, "Слава КПСС");
        db.insert(id, "Мэйби Бэйби");
        db.insert(id, "Каспийский груз");
    }
    @AfterEach
    void clearDB() {
        db.down();
    }
    @Test
    void testSearchCommand(){
        ctx.setText("/search oxxxymiron");
        TelegramCommandHandler handler = new TelegramCommandHandler(sender);
        handler.handleCommand(ctx);
        assertEquals("""
            OXXXYMIRON — Лига Опасного Интернета (2023)
            oxxxymironofficial
            https://www.youtube.com/watch?v=kkXXrRxmAyw
            2023-08-11T12:55:36Z""",sender.getMsg().getText());
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
    @Test
    void testInlineButtons() {
        ctx.setMessageId(123);
        ctx.setCallbackQueryData("next");
        ctx.getCallbackQueryChatId(id);
        ctx.setChatId(id);
        ctx.setText("/search oxxymiron");
        TelegramCommandHandler handler = new TelegramCommandHandler(sender);
        handler.handleCommand(ctx);
        handler.handleCallbackQuery(ctx);
        assertNotNull(sender.getMsg().getText());
        ctx.setCallbackQueryData("prev");
        assertNotNull(sender.getMsg());
        assertNotNull(sender.getMsg().getReplyMarkup());
        assertNotNull(sender.getMsg().getText());
    }
}