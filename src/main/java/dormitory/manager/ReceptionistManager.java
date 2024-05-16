package dormitory.manager;


import dormitory.db.provider.DBConnectionProvider;
import dormitory.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceptionistManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    public List<Receptionist> getAllAdmins() {
        List<Receptionist> receptionists = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from receptionist where role = 'ADMIN' order by name asc ");
            while (resultSet.next()) {
                receptionists.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receptionists;
    }   public List<Receptionist> getAllInactives() {
        List<Receptionist> receptionists = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from receptionist where role = 'INACTIVE' order by name asc ");
            while (resultSet.next()) {
                receptionists.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receptionists;
    }
 public List<Receptionist> getAllRegistrants() {
        List<Receptionist> receptionists = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from receptionist where role = 'REGISTRANT' order by name asc ");
            while (resultSet.next()) {
                receptionists.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receptionists;
    }

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
    public void ActivateById(int id,Date employmentDate) {
        String updateSql = "update receptionist set role = 'ADMIN' ,employment_date = ? where  id = " + id;
        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            updateStatement.setDate(1, employmentDate);
            updateStatement.executeUpdate();
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
        if (getByEmail(receptionist.getEmail()).getId() == 0) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO receptionist(name,surname,birthday,phone_num,email,password,about_experience,gender) VALUES(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, receptionist.getName());
                statement.setString(2, receptionist.getSurname());
                statement.setDate(3, (Date) receptionist.getBirthday());
                statement.setString(4,receptionist.getPhone());
                statement.setString(5, receptionist.getEmail());
                statement.setString(6, receptionist.getPassword());
                statement.setString(7,receptionist.getExperienceInformation());
                statement.setString(8, receptionist.getGender().name());
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

    public List<Receptionist> getByNameOrSurnameInactive(String search) {
        List<Receptionist> receptionists = new ArrayList<>();
        String sql = "select * from (SELECT * FROM receptionist WHERE  (UPPER(name) LIKE CONCAT('%', UPPER(?), '%') OR UPPER(surname) LIKE CONCAT('%', UPPER(?), '%'))) as alias_name";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, search);
            statement.setString(2, search);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (getFromResultSet(resultSet).getReceptionistRole().equals(ReceptionistRole.INACTIVE)) {
                    receptionists.add(getFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receptionists;
    }
    public void removeAllRegistrants() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE  from receptionist where role = 'REGISTRANT'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deActivateById(int id, Date resignationDay) {
        String updateSql = "update receptionist set role = 'INACTIVE' ,resignoration_date = ? where  id = " + id;
        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            updateStatement.setDate(1, resignationDay);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Receptionist> getByNameOrSurnameRegistrants(String search) {
        List<Receptionist> receptionists = new ArrayList<>();
        String sql = "select * from (SELECT * FROM receptionist WHERE  (UPPER(name) LIKE CONCAT('%', UPPER(?), '%') OR UPPER(surname) LIKE CONCAT('%', UPPER(?), '%'))) as alias_name";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, search);
            statement.setString(2, search);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (getFromResultSet(resultSet).getReceptionistRole().equals(ReceptionistRole.REGISTRANT)) {
                    receptionists.add(getFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receptionists;
    }

    public List<Receptionist> getByNameOrSurnameAdmin(String search) {
        List<Receptionist> receptionists = new ArrayList<>();
        String sql = "select * from (SELECT * FROM receptionist WHERE  (UPPER(name) LIKE CONCAT('%', UPPER(?), '%') OR UPPER(surname) LIKE CONCAT('%', UPPER(?), '%'))) as alias_name";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, search);
            statement.setString(2, search);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (getFromResultSet(resultSet).getReceptionistRole().equals(ReceptionistRole.ADMIN)) {
                    receptionists.add(getFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receptionists;
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
e.printStackTrace();
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


    private Receptionist getFromResultSet(ResultSet resultSet) throws SQLException {
        Receptionist receptionist = Receptionist.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .birthday(resultSet.getDate("birthday"))
                .phone(resultSet.getString("phone_num"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .employmentDate(resultSet.getDate("employment_date"))
                .resignationDate(resultSet.getDate("resignation_date"))
                .experienceInformation(resultSet.getString("about_experience"))
                .receptionistRole(ReceptionistRole.valueOf(resultSet.getString("role")))
                .gender(Gender.valueOf(resultSet.getString("gender")))
                .controlCode(resultSet.getString("control_code"))
                .build();
        return receptionist;
    }


}
