package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Reservation;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ReservationDAO extends CrudDAO<Reservation> {
    String getCurrentId() throws SQLException, ClassNotFoundException;

    Map<String, String> getWeddingEvents() throws SQLException, ClassNotFoundException;

    List<String> getDistinctReservationIds() throws SQLException, ClassNotFoundException;
}
