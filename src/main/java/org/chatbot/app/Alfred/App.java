package org.chatbot.app.Alfred;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.printf("Hello, %s!%nHow can I help you?%n", System.getProperty("user.name"));
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.print("> ");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "/exit" -> exit = true;
                case "/help" -> System.out.println("""
                    List of commands:
                    /exit --- quit Alfred
                    /help --- show help
                    /flip --- flip coin
                    /time --- show date and time""");
                case "/flip" -> System.out.printf("Flipping coin... %s!%n",
                    Math.random() > 0.5 ? "Heads" : "Tails");
                case "" -> {
                }
                case "/time" -> {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uu HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println(dtf.format(now));
                }
                default -> System.out.println("There's no such command!\n/help for help");
            }
        }
    }
}
