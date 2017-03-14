package dbproject.db;

/**
 * Created by ela49 on 14.03.2017.
 */
public abstract class Model {
    static DBConnector dbConnector = new DBConnector();

    public abstract void save();

}
