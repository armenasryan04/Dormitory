package dormitory.manager;



import dormitory.db.provider.DBConnectionProvider;
import dormitory.models.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();
    public List<Room> getAll(){
        List<Room> dormitories = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
           ResultSet resultSet = statement.executeQuery("select * from dormitory");
           while (resultSet.next()){
               dormitories.add(getFromResultSet(resultSet));
           }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dormitories;
    }
    public List<Room> getOnlyFreeRoomsByFloorOrRoom(int floor, int roomNum) {
        List<Room> freeDormitories = new ArrayList<>();
        String sql = "select * from dormitory where floor = ? or room_num = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,floor);
            statement.setInt(2,roomNum);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Room room = getFromResultSet(resultSet);
                if (isFree(room.getId())) {
                    freeDormitories.add(room);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return freeDormitories;
    }
    public List<Room> getOnlyFreeRooms() {
        List<Room> freeDormitories = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from dormitory ");
            while (resultSet.next()) {
                Room room = getFromResultSet(resultSet);
                if (isFree(room.getId())) {
                    freeDormitories.add(room);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return freeDormitories;
    }

    public boolean isFree(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select 1 from  student where status = 'ACTIVE' AND room_id = " + id);
            if (resultSet.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Room getById(int id) {
        Room room = new Room();
        String sql = "SELECT * FROM dormitory WHERE room_id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                room = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    private Room getFromResultSet(ResultSet resultSet) throws SQLException {
        Room room = Room.builder()
                .id(resultSet.getInt("room_id"))
                .floor(resultSet.getInt("floor"))
                .roomNum(resultSet.getInt("room_num")).build();
        return room;
    }
}
