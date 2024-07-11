package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationDAOImpl implements ReservationDAO {


    public  String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT ReservationID FROM reservation ORDER BY ReservationID DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString("ReservationID");
        } else {
            return null;
        }
    }

    public  Map<String, String> getWeddingEvents() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT PackageID, Name FROM package WHERE Name LIKE '%Wedding%'");
        Map<String, String> eventMap = new HashMap<>();
        while (resultSet.next()) {
            String packageId = resultSet.getString("PackageID");
            String packageName = resultSet.getString("Name");
            eventMap.put(packageName, packageId);
        }
        return eventMap;
    }

    @Override
    public List<String> getDistinctReservationIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT ReservationID FROM reservation");
        List<String> reservationIds = new ArrayList<>();
        while (resultSet.next()) {
            reservationIds.add(resultSet.getString("ReservationID"));
        }
        return reservationIds;
    }


    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT DISTINCT ReservationID FROM reservation_room_info ORDER BY ReservationID");
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString("ReservationID"));
        }
        return ids;
    }

    @Override
    public Reservation searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Reservation entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO reservation VALUES(?,?,?,?,?,?,?)", entity.getReservationID(), entity.getCheckInDate(), entity.getCheckOutDate(), entity.getReservationDate(), entity.getDuration(), entity.getNumberofGuests(), entity.getStatus());
    }

    @Override
    public boolean update(Reservation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT ReservationID FROM reservation ORDER BY ReservationID DESC LIMIT 1");
        if (rst.next()) {
            String lastId = rst.getString("ReservationID");
            int numericPart = Integer.parseInt(lastId.substring(3));
            numericPart++;
            return String.format("RES%03d", numericPart);
        } else {
            return "RES001";
        }
    }

    @Override
    public Reservation searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM reservation");
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        while (rst.next()) {
            reservations.add(new Reservation(
                    rst.getString("ReservationID"),
                    rst.getDate("CheckInDate"),
                    rst.getDate("CheckOutDate"),
                    rst.getDate("ReservationDate"),
                    rst.getString("Duration"),
                    rst.getInt("NumberofGuests"),
                    rst.getString("Status")
            ));
        }
        return reservations;
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
