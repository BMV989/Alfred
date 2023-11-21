package org.chatbot.app.Alfred;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.chatbot.app.Alfred.telegram.commands.HistoryCommand.HISTORY_PATH;

public class TestSqliteDB {
    private final String url;
    public TestSqliteDB() {
        this.url = String.format("jdbc:sqlite:%s", HISTORY_PATH);
    }
    public List<String> getLastFiveQueries(Long chatId) {
        List<String> ans = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = conn.prepareStatement("""
                select query from test where chat_id = ? order by rowid desc limit 5""");
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
