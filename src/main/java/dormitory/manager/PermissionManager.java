package dormitory.manager;

import dormitory.db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PermissionManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public boolean getPermission() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from registration_permission");
            if (resultSet.next()) {
               return getAnswerFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void changer() {
        if (getPermission()) {
            deactivate();
        } else {
            activate();
        }
    }

    private void activate() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("update registration_permission set  is_allowed = 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deactivate() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("update registration_permission set  is_allowed = -1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean getAnswerFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.getInt("is_allowed") == 1) {
            return true;
        } else {
            return false;
        }
    }
}
