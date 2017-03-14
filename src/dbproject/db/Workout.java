package dbproject.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Workout extends Model{
	
	String time;
	String name;
	int duration;
	int condition;
	int performance;
	String notes;
	int id = -1;
	
	public Workout(String name, String time, int dur, int cond, int perf, String notes ){
		this.time = time;
		this.name = name;
		this.duration = dur;
		this.condition = cond;
		this.performance = perf;
		this.notes = notes;
	}
	
	private Workout(String name, String time, int dur, int cond, int perf, String notes, int id ){
		this(name, time, dur, cond, perf, notes);
		this.id = id;
	}

	
	
	public static Workout workoutById(int id){
		 try {
	            dbConnector.connect();
	            Connection conn = dbConnector.getConnection();
	            PreparedStatement statement = conn.prepareStatement("SELECT time, name, duration, condition, performance, notes " +
	                                                                    "FROM Workout" +
	                                                                    "WHERE id = ?");
	            statement.setInt(1, id);
	            ResultSet rs = statement.executeQuery();
	            if(!rs.first())
	                return null;
	            Workout workout = new Workout(rs.getString("name"), rs.getString("time"), rs.getInt("duration"),
	            		rs.getInt("condition"), rs.getInt("performance"), rs.getString("notes"), id);
	            dbConnector.close();
	            return workout;
	        } catch(SQLException e) {}
	        return null;
	}
	
	public static List<Workout> workoutWeek(String weekStart, String weekEnd) {
		try {
			dbConnector.connect();
			Connection conn = dbConnector.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT time, name, duration, condition, performance, notes, id " +
																	"FROM Workout" +
																	"WHERE time >= ? AND time <= ?");
			statement.setString(1, weekStart);
			statement.setString(2, weekEnd);
			ResultSet rs= statement.executeQuery();
			List<Workout> week = new ArrayList<>();
			while(rs.next()) {
				Workout workout = new Workout(rs.getString("name"), rs.getString("time"), rs.getInt("duration"),
						rs.getInt("condition"), rs.getInt("performance"), rs.getString("id"), rs.getInt("id"));
				week.add(workout);
			}
			return week;
		} catch(SQLException e) {}
		return null;
	}
	
	@Override
	public void save() {
		try {
            dbConnector.connect();
            Connection conn = dbConnector.getConnection();
            PreparedStatement statement;
            if(id == -1) {
                statement = conn.prepareStatement("INSERT INTO Workout('time', 'name', 'duration', 'condition', 'performance', 'notes' )" +
                                                        "VALUES(?, ?, ? ,? ,? ,?)");
            } else {
                statement = conn.prepareStatement("UPDATE Workout" +
                                                        "SET time = ?, name = ?, duration = ?, condition = ?, performance = ?, notes = ?" +
                                                        "WHERE id = ?");
                statement.setInt(7, this.id);
            }
            statement.setString(1, this.time);
            statement.setString(2, this.name);
            statement.setInt(3, this.duration);
            statement.setInt(4, this.condition);
            statement.setInt(5, this.performance);
            statement.setString(6, this.notes);
            statement.executeQuery();
			if(id == -1) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if(generatedKeys.first())
					this.id = generatedKeys.getInt(1);
			}
            dbConnector.close();
        } catch(SQLException e) {}
    }
}
