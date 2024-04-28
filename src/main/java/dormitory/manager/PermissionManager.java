package dormitory.manager;

import dormitory.db.provider.DBConnectionProvider;
import dormitory.models.RegistrationPermission;

import java.sql.*;

public class PermissionManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public boolean getPermission() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from registration_permission");
            if (resultSet.next()) {
               RegistrationPermission permission = getAnswerFromResultSet(resultSet);
               if (permission.isAllowed()){
                   if (permission.isTimeLeft()){
                       return true;
                   }else {
                       deactivate();
                       return false;
                   }
               }else {
                   return false;
               }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void activate() {
        String query = "UPDATE registration_permission SET is_allowed = 1, deadline = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, RegistrationPermission.insertTimer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deactivate() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("update registration_permission set  is_allowed = -1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private RegistrationPermission getAnswerFromResultSet(ResultSet resultSet) throws SQLException {
        RegistrationPermission rp = new RegistrationPermission();
        rp.setDeadline(resultSet.getDate("deadline"));
        if (resultSet.getInt("is_allowed") == 1) {
            rp.setAllowed(true);
        } else {
            rp.setAllowed(false);
        }
        return rp;
    }
}
