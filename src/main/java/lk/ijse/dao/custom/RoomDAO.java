package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.ReservationroominfoDTO;
import lk.ijse.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room> {
    String getRoomStatus(String roomId) throws SQLException, ClassNotFoundException;

    boolean updateRoom(List<ReservationroominfoDTO> reservationroominfo) throws SQLException, ClassNotFoundException;
}
