package org.chatbot.app.Alfred;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    String user = System.getProperty("user.name");
    String greetings = String.format("Hello, %s!", user);
    System.out.println(greetings);
    System.out.println("How can I help you?");
    Scanner scanner = new Scanner(System.in);
    session: while (true) {
      System.out.print("> ");
      String userInput = scanner.nextLine();
      // TODO: come up with the idea of the chatbot
      switch (userInput) {
        case "/exit":
          break session;
        case "/help":
          System.out.println("List of commands:");
          System.out.println("/exit --- quit Alfred");
          System.out.println("/help --- show help");
          System.out.println("/flip --- flip coin");
          System.out.println("/time --- show date and time");
          break;
        case "/flip":
          System.out.print("Flipping coin... ");
          System.out.println(Math.random() > 0.5 ? "Heads!" : "Tails!");
          break;
        case "":
          break;
        case "/time":
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uu HH:mm:ss");
          LocalDateTime now = LocalDateTime.now();
          System.out.println(dtf.format(now));
          break;
        default:
          System.out.println("There's no such command!");
          System.out.println("/help for help");
          break;
      }
    }
  }
}
