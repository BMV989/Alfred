package org.chatbot.app.Alfred;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

public class AppTest {

    @Test
    public void testingHelp() {
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(myOut);
        System.setOut(ps);
        System.setIn(new ByteArrayInputStream("/help\n/exit\n".getBytes()));
        App.main(new String[0]);
        System.setIn(System.in);
        System.setOut(System.out);
        String outputText = myOut.toString();
        String out = outputText.substring(outputText.indexOf("List"),
            outputText.indexOf("and time") + "and time".length()).trim();
        String expectedOutput = """
            List of commands:
            /exit --- quit Alfred
            /help --- show help
            /flip --- flip coin
            /time --- show date and time""";
        assertEquals(expectedOutput, out);
    }

    @Test
    public void testingDefault() {
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(myOut);
        System.setOut(ps);
        System.setIn(new ByteArrayInputStream("//\n/exit\n".getBytes()));
        App.main(new String[0]);
        System.setIn(System.in);
        System.setOut(System.out);
        String outputText = myOut.toString();
        String out = outputText.substring(outputText.indexOf("There's"),
            outputText.indexOf("for help") + "for help".length()).trim();
        String expectedOutput = "There's no such command!\n/help for help";
        assertEquals(expectedOutput, out);
    }

    @Test
    public void testingGreet() {
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(myOut);
        System.setOut(ps);
        System.setIn(new ByteArrayInputStream("/exit\n".getBytes()));
        App.main(new String[0]);
        System.setIn(System.in);
        System.setOut(System.out);
        String outputText = myOut.toString();
        String out = outputText.substring(0, outputText.indexOf("you?") + "you?".length());
        String expectedOutput = String.format("Hello, %s!%sHow can I help you?",
            System.getProperty("user.name"), System.lineSeparator());
        assertEquals(expectedOutput, out);
    }
}
