package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;

import lk.ijse.entity.Reservationserviceinfo;

import java.sql.SQLException;
import java.util.List;

public interface ReservationserviceinfoDAO extends CrudDAO<Reservationserviceinfo> {
    boolean saveRSI(List<Reservationserviceinfo> reservationserviceinfo) throws SQLException, ClassNotFoundException;
}
