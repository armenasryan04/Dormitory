package dormitory.manager;


import dormitory.db.DBConnectionProvider;
import dormitory.models.Receptionist;
import dormitory.models.ReceptionistRole;

import java.sql.*;

public class ReceptionistManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void changeNameSurnameById(int id, String name, String surname) {
        String sql = "update receptionist set name = ? , surname = ? where id = " + id;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeEmailById(int id, String email) {
        String sql = "update receptionist set email = ? where id = " + id;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changePasswordById(int id, String password) {
        String sql = "update receptionist set password = ? where id = " + id;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Receptionist addToDb(Receptionist receptionist) {
        if (getByEmail(receptionist.getEmail()).equals(null)) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO receptionist(name,surname,email,password,role) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, receptionist.getName());
                statement.setString(2, receptionist.getSurname());
                statement.setString(3, receptionist.getEmail());
                statement.setString(4, receptionist.getPassword());
                statement.setString(5, receptionist.getReceptionistRole().name());
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    receptionist.setId(resultSet.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return receptionist;
        }
        return null;
    }

    public Receptionist getById(int id) {
        Receptionist receptionist = new Receptionist();
        String sql = "select * from receptionist where id = " + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                receptionist = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {

        }
        return receptionist;
    }

    public Receptionist getByEmailAndPassword(String email, String password) {
        Receptionist receptionist = new Receptionist();
        String sql = "SELECT * from receptionist WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                receptionist = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receptionist;
    }

    public Receptionist getByEmail(String email) {
        Receptionist receptionist = new Receptionist();
        String sql = "SELECT * from receptionist WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                receptionist = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receptionist;
    }

    public void deleteByEmailAndPassword(String email, String password) {
        String sql = "delete from  receptionist where email = ? and password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Receptionist getFromResultSet(ResultSet resultSet) throws SQLException {
        Receptionist receptionist = Receptionist.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .receptionistRole(ReceptionistRole.valueOf(resultSet.getString("role")))
                .build();
        return receptionist;
    }
}
