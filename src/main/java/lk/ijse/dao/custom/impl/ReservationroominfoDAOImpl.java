package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ReservationroominfoDAO;
import lk.ijse.entity.Reservationroominfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationroominfoDAOImpl implements ReservationroominfoDAO {
    public  List<String> getRoomIdsByReservationId(String reservationId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT RoomID FROM reservation_room_info WHERE ReservationID = ?", reservationId);
        List<String> roomIds = new ArrayList<>();
        while (rst.next()) {
            roomIds.add(rst.getString("RoomID"));
        }
        return roomIds;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Reservationroominfo searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Reservationroominfo entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO reservation_room_info VALUES(?,?)", entity.getReservationID(), entity.getRoomID());

    }

    @Override
    public boolean update(Reservationroominfo entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Reservationroominfo searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Reservationroominfo> getAll() throws SQLException, ClassNotFoundException {
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
