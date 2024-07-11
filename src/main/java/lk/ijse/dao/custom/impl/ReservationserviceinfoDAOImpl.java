package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ReservationserviceinfoDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.ReservationserviceinfoDTO;
import lk.ijse.entity.Reservationserviceinfo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class ReservationserviceinfoDAOImpl implements ReservationserviceinfoDAO {
    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Reservationserviceinfo searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Reservationserviceinfo entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO reservation_service_info VALUES(?,?)", entity.getReservationID(), entity.getServiceID());
    }

    @Override
    public boolean update(Reservationserviceinfo entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Reservationserviceinfo searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Reservationserviceinfo> getAll() throws SQLException, ClassNotFoundException {
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

    @Override
    public boolean saveRSI(List<Reservationserviceinfo> reservationserviceinfo) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO reservation_service_info VALUES(?,?)", reservationserviceinfo.get(0).getReservationID(), reservationserviceinfo.get(0).getServiceID());
    }
}
