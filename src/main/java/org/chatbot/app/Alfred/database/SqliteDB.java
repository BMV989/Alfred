package org.chatbot.app.Alfred.database;

import static org.chatbot.app.Alfred.telegram.commands.HistoryCommand.HISTORY_PATH;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteDB {
    private final String url = String.format("jdbc:sqlite:%s", HISTORY_PATH);
    public SqliteDB() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void up() {
       try {
           Connection conn = DriverManager.getConnection(url);
           Statement statement = conn.createStatement();
           statement.execute("""
               create table search_history(
               chat_id int,
               query text not null
               );""");
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }
    public void down() {
        File db = new File(HISTORY_PATH);
        if (db.delete()) {
            System.out.printf("db: %s has been deleted%n", db.getName());
        } else {
            System.err.println("db hasn't been deleted");
        }
    }
    public void insert(Long chatId, String query) {
        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = conn.prepareStatement("""
                insert into search_history(chat_id, query) values(?, ?);""");
            preparedStatement.setLong(1, chatId);
            preparedStatement.setString(2, query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> getLastFiveQueries(Long chatId) {
        List<String> ans = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = conn.prepareStatement("""
                select query from search_history where chat_id = ? order by rowid desc limit 5""");
            preparedStatement.setLong(1, chatId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ans.add(resultSet.getString("query"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
