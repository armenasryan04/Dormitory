package dormitory.db.provider;

import dormitory.db.config.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private static final DBConnectionProvider INSTANCE = new DBConnectionProvider();
    private static final DBConfig dbConfig = new DBConfig();
    private Connection connection;
    private static final String DB_URL = dbConfig.getDbUrl();
    private static final String USERNAME =dbConfig.getUsername();
    private static final String PASSWORD = dbConfig.getPassword();

    private DBConnectionProvider() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static DBConnectionProvider getInstance() {
        return INSTANCE;
    }
}
