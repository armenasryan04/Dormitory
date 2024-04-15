package dormitory.db.config;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {

    @Getter
    private String  dbUrl;
    @Getter
    private String username;
    @Getter
    private String password;

    public DBConfig() {
        try (FileInputStream input = new FileInputStream("C:\\Users\\User\\IdeaProjects\\dormitory\\config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            dbUrl = prop.getProperty("db.url");
            username = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
