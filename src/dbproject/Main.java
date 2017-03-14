package dbproject;

import dbproject.db.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    DBConnector dbConnector;

    public Main() {
        dbConnector = new DBConnector();
    }

    public void run() {
        try {
            testQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testQuery() throws SQLException {
        dbConnector.connect();

        Connection conn = dbConnector.getConnection();
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE testTable(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(50)" +
                ")");
        statement.execute("INSERT INTO testTable('name') VALUES ('Eivind')");
        ResultSet rs = statement.executeQuery("SELECT * FROM testTable");
        while(rs.next()) {
            System.out.println(rs.getString("id") + ": " + rs.getString("name"));
        }

        dbConnector.close();
    }

    public static void main(String... args) {
        Main main = new Main();
        main.run();
    }
}
