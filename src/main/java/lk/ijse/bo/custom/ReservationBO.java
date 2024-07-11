package lk.ijse.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.dto.PlaceReservationDTO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReservationBO extends SuperBO {
    String getCurrentReservationId() throws SQLException, ClassNotFoundException;

    Map<String, String> getWeddingEvents() throws SQLException, ClassNotFoundException;

    List<String> getReservationIds() throws SQLException, ClassNotFoundException;

    ReservationDTO searchByReservationId(String id) throws SQLException, ClassNotFoundException;

    boolean saveReservation(ReservationDTO entity) throws SQLException, ClassNotFoundException;

    boolean updateReservation(ReservationDTO entity) throws SQLException, ClassNotFoundException;

    String getNextIdReservation() throws SQLException, ClassNotFoundException;

    ReservationDTO searchReservationByPhone(String phone) throws SQLException, ClassNotFoundException;

    ObservableList<ReservationDTO> getAllReservation() throws SQLException, ClassNotFoundException;

    boolean DeleteReservation(String id) throws SQLException, ClassNotFoundException;

    List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException;

    List<String> getDistinctReservationIds() throws SQLException, ClassNotFoundException ;

    List<String> getRoomIdsByReservationId(String reservationId) throws SQLException, ClassNotFoundException;

    boolean placereservation(PlaceReservationDTO reservation) throws SQLException;

}
