package dbproject.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {

    public final String type;
    public final String url;
    public final String username;
    public final String password;

    public DBConfig(String path) throws IOException {
        InputStream is = null;
        Properties prop = null;
        try {
             prop = new Properties();

            is = getClass().getClassLoader().getResourceAsStream(path);

            if(is != null) {
                prop.load(is);
            } else {
                throw new FileNotFoundException("Property file " + path + " not found");
            }
        } finally {
            if(is != null)
                is.close();
        }
        type = prop.getProperty("type");
        url = prop.getProperty("url");
        username = prop.getProperty("username");
        password = prop.getProperty("password");
    }

}
