package lk.ijse.dao.custom;


import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Reservationpackageinfo;

import java.sql.SQLException;
import java.util.List;

public interface ReservationpackageinfoDAO extends SuperDAO {
    boolean save(List<Reservationpackageinfo> reservationpackageinfo) throws SQLException, ClassNotFoundException;
}
