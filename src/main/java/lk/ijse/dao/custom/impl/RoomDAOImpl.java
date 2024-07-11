package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.ReservationroominfoDTO;
import lk.ijse.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    public  String getRoomStatus(String roomId) throws SQLException, ClassNotFoundException {
       String status = "Unavailable";
        ResultSet rst = SQLUtil.execute("SELECT Status FROM room WHERE RoomID = ?", roomId);
        if (rst.next()) {
            status= rst.getString("Status");
        }
        return status;
    }

    @Override
    public boolean updateRoom(List<ReservationroominfoDTO> reservationroominfo) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("UPDATE room SET Status = 'Booked' WHERE RoomID = ?", reservationroominfo.get(0).getRoomID());
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT RoomID FROM room WHERE Status = 'Available'");
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString(1));
        }
        return ids;
    }

    @Override
    public Room searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Room entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Room entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE room SET Status = 'Available' WHERE RoomID = ?", entity.getRoomID());
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Room searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Room> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
