package dormitory.manager;


import dormitory.db.DBConnectionProvider;
import dormitory.models.Room;
import dormitory.models.Receptionist;
import dormitory.models.Student;
import dormitory.models.StudentStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    RoomManager roomManager = new RoomManager();

    public List<Student> getAllActive() {

        List<Student> students = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from student where status = 'ACTIVE' order by name asc ");
            while (resultSet.next()) {
                students.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> getAllArchive() {
        List<Student> students = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from student where status = 'ARCHIVE' order by name asc ");
            while (resultSet.next()) {
                students.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> getByNameOrSurnameActive(String search) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE   UPPER(name) LIKE CONCAT('%', UPPER(?), '%') OR UPPER(surname) LIKE CONCAT('%', UPPER(?), '%')";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + search + "%");
            statement.setString(2, "%" + search + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (getFromResultSet(resultSet).getStudentStatus().equals(StudentStatus.ACTIVE)) {
                    students.add(getFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> getByNameOrSurnameArchive(String search) {
        List<Student> students = new ArrayList<>();
        String sql = "select * from (SELECT * FROM student WHERE  (UPPER(name) LIKE CONCAT('%', UPPER(?), '%') OR UPPER(surname) LIKE CONCAT('%', UPPER(?), '%')))";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, search);
            statement.setString(2, search);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (getFromResultSet(resultSet).getStudentStatus().equals(StudentStatus.ARCHIVE)) {
                    students.add(getFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getById(int id) {
        Student student = new Student();
        String sql = "SELECT * FROM student WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Student getByEmailOrId(String email, int id) {
        Student student = new Student();
        String sql = "SELECT * FROM student WHERE id = ? or email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }    public Student getByEmail(String email) {
        Student student = new Student();
        String sql = "SELECT * FROM student WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public int getStudentsCount() {
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM student");
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getActiveStudentsNumber() {
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM student where status = 'ACTIVE'");
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getArchiveStudentsNumber() {
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM student where status = 'ARCHIVE'");
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    public Student addToDB(Student student) {
        String sql = "insert  into student(name,surname,email,phone_num,date,room_id,receptionist_id,id) values (?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getPhoneNum());
            preparedStatement.setDate(5, (Date) student.getDate());
            preparedStatement.setInt(6, student.getRoom().getId());
            preparedStatement.setInt(7, student.getReceptionist().getId());
            preparedStatement.setInt(8, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void statusToActive(int id, int roomId, String email, Date date, Receptionist receptionist) {
        String updateSql = "UPDATE student SET status = 'ACTIVE' , room_id = ?, date = ? , receptionist_id = ?, email = ?  WHERE id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            updateStatement.setInt(1, roomId);
            updateStatement.setDate(2, date);
            updateStatement.setInt(3, receptionist.getId());
            updateStatement.setString(4, email);
            updateStatement.setInt(5, id);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkStatusToChange() {
        String updateSql = "UPDATE student SET status = 'ARCHIVE' WHERE id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            List<Student> students = getAllActive();
            for (Student student : students) {
                if ("0d 0h".equals(student.getDaysUntil(student.getDate()))) {
                    updateStatement.setInt(1, student.getId());
                    updateStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Student getFromResultSet(ResultSet resultSet) throws SQLException {
        Room room = roomManager.getById(resultSet.getInt("room_id"));
        Student student = Student.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .phoneNum(resultSet.getString("phone_num"))
                .date(resultSet.getDate("date"))
                .email(resultSet.getString("email"))
                .room(room)
                .studentStatus(StudentStatus.valueOf(resultSet.getString("status")))
                .build();
        return student;
    }
}
