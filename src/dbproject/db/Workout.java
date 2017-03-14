package dbproject.db;

import java.sql.*;

public class Workout extends Model{
	
	private String time;
	private String name;
	private int duration;
	private int condition;
	private int performance;
	private String notes;
	private int id = -1;
	
	
	public String getTime(){
		return this.time;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getDuration(){
		return this.duration;
	}
	
	public int getCondition(){
		return this.condition;
	}
	
	public int getPerformance(){
		return this.performance;
	}
	
	public String getNotes(){
		return this.notes;
	}
	
	public int getId(){
		return this.id;
	}
	
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
	
	public void addExercise(int id){
		try {
            dbConnector.connect();
            Connection conn = dbConnector.getConnection();
            PreparedStatement statement;
            statement = conn.prepareStatement("INSERT INTO ExerciseWorkout('WorkoutID, 'ExerciseID'" +
                                                        "VALUES(?, ?)");
            statement.setInt(1, this.id);
            statement.setInt(2, id);
            statement.executeQuery();
            dbConnector.close();
        } catch(SQLException e) {}
	}
}
