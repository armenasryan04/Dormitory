package dormitory.manager;

import dormitory.db.DBConnectionProvider;
import dormitory.models.ReceptionistRole;
import dormitory.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class URLManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public boolean isAcceptable(String url, ReceptionistRole receptionistRole){
        String sql = "SELECT * FROM url_permission WHERE url = ? and role = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, url);
            statement.setString(2,receptionistRole.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

