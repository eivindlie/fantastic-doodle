package dbproject.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Exercise extends Model {
    String name;
    String description;
    int id = -1;

    public Exercise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private Exercise(String name, String description, int id) {
        this(name, description);
        this.id = id;
    }

    public static Exercise getById(int id) {
        try {
            dbConnector.connect();
            Connection conn = dbConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT name, description " +
                                                                    "FROM Exercise" +
                                                                    "WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(!rs.first())
                return null;
            Exercise exercise = new Exercise(rs.getString("name"), rs.getString("description"), id);
            dbConnector.close();
            return exercise;
        } catch(SQLException e) {}
        return null;
    }

    public List<Exercise> getAll() {
        try {
            dbConnector.connect();
            Connection conn = dbConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT name, description, id" +
                                                                    "FROM Exercise");
            ResultSet rs = statement.executeQuery();
            List<Exercise> results = new ArrayList<>();
            while(rs.next()) {
                Exercise exercise = new Exercise(rs.getString("name"), rs.getString("description"), rs.getInt("id"));
                results.add(exercise);
            }

            dbConnector.close();
            return results;
        } catch(SQLException e) {}
        return new ArrayList<>();
    }

    @Override
    public void save() {
        try {
            dbConnector.connect();
            Connection conn = dbConnector.getConnection();
            PreparedStatement statement;
            if(id == -1) {
                statement = conn.prepareStatement("INSERT INTO Exercise('name', 'description')" +
                                                        "VALUES(?, ?)");
            } else {
                statement = conn.prepareStatement("UPDATE Exercise" +
                                                        "SET name = ?, description = ?" +
                                                        "WHERE id = ?");
                statement.setInt(3, this.id);
            }
            statement.setString(1, this.name);
            statement.setString(2, this.description);
            statement.executeQuery();
            dbConnector.close();
        } catch(SQLException e) {}
    }

}
