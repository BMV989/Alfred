package org.chatbot.app.Alfred.database;

import static org.chatbot.app.Alfred.telegram.commands.HistoryCommand.HISTORY_PATH;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class SqliteDB {
    private final String url;
    public SqliteDB(String path) {
        this.url = String.format("jdbc:sqlite:%s", path);
    }
    public SqliteDB() {
        this.url = String.format("jdbc:sqlite:%s", HISTORY_PATH);
    }
    public void up() {
        String dir = new File(url.split(":")[2]).getParent();
        (new File(dir)).mkdir();
       try {
           Connection conn = DriverManager.getConnection(url);
           Statement statement = conn.createStatement();
           statement.execute("""
               create table search_history(
               chat_id int,
               query text not null
               );""");
           statement.close();
           conn.close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }
    public void down() {
        File db = new File(url.split(":")[2]);
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
            preparedStatement.close();
            conn.close();
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
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
