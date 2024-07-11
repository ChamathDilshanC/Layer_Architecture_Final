package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Reservationroominfo;

import java.sql.SQLException;
import java.util.List;

public interface ReservationroominfoDAO extends CrudDAO<Reservationroominfo> {
     List<String> getRoomIdsByReservationId(String reservationId) throws SQLException, ClassNotFoundException;
}
