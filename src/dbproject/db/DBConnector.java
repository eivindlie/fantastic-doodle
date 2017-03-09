package dbproject.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private DBConfig dbConfig;
    private Connection conn = null;

    public DBConnector() {
        try {
            dbConfig = new DBConfig("db.properties");
        } catch (IOException e) {
            System.out.println("Could not load db config file.");
            System.exit(1);
        }
    }

    public void connect() {
        try {
            if(dbConfig.type.equals("sqlite")) {
                conn = DriverManager.getConnection(dbConfig.url);
            } else if(dbConfig.type.equals("mysql")) {
                conn = DriverManager.getConnection(dbConfig.url, dbConfig.username, dbConfig.password);
            }
        } catch(SQLException e) {
            System.out.println("Could not establish connection to database: " + e.getMessage());
        }
    }

    public void close() {
        if(conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public Connection getConnection() throws IllegalStateException {
        if(conn == null)
            throw new IllegalStateException("You must call connect() before getting the connection!");
        return conn;
    }

}
