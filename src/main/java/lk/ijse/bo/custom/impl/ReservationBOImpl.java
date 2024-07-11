package lk.ijse.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.PlaceReservationDTO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.ReservationpackageinfoDTO;
import lk.ijse.dto.ReservationserviceinfoDTO;
import lk.ijse.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    ReservationpackageinfoDAO reservationpackageinfoDAO = (ReservationpackageinfoDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONPACKAGEINFO);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    ReservationroominfoDAO reservationroominfoDAO = (ReservationroominfoDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONROOMINFO);
    ReservationserviceinfoDAO reservationserviceinfoDAO = (ReservationserviceinfoDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONSERVICEINFO);

    @Override
    public String getCurrentReservationId() throws SQLException, ClassNotFoundException {
        return reservationDAO.getCurrentId();
    }

    @Override
    public Map<String, String> getWeddingEvents() throws SQLException, ClassNotFoundException {
        return reservationDAO.getWeddingEvents();
    }

    @Override
    public List<String> getReservationIds() throws SQLException, ClassNotFoundException {
        return reservationDAO.getIds();
    }

    @Override
    public ReservationDTO searchByReservationId(String id) throws SQLException, ClassNotFoundException {
        Reservation reservation = reservationDAO.searchById(id);
        if (reservation == null) {
            return null;
        }
        return new ReservationDTO(
                reservation.getReservationID(), reservation.getCheckInDate(), reservation.getCheckOutDate(), reservation.getReservationDate(),
                reservation.getDuration(), reservation.getNumberofGuests(), reservation.getStatus());
    }

    @Override
    public boolean saveReservation(ReservationDTO entity) throws SQLException, ClassNotFoundException {
        return reservationDAO.save(new Reservation(entity.getReservationID(), entity.getCheckInDate(), entity.getCheckOutDate(), entity.getReservationDate(),
                entity.getDuration(), entity.getNumberofGuests(), entity.getStatus()));
    }

    @Override
    public boolean updateReservation(ReservationDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextIdReservation() throws SQLException, ClassNotFoundException {
        return reservationDAO.getNextId();
    }

    @Override
    public ReservationDTO searchReservationByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<ReservationDTO> getAllReservation() throws SQLException, ClassNotFoundException {
        ObservableList<Reservation> all = reservationDAO.getAll();
        ObservableList<ReservationDTO> reservations = javafx.collections.FXCollections.observableArrayList();
        for (Reservation reservation : all) {
            reservations.add(new ReservationDTO(reservation.getReservationID(), reservation.getCheckInDate(), reservation.getCheckOutDate(), reservation.getReservationDate(),
                    reservation.getDuration(), reservation.getNumberofGuests(), reservation.getStatus()));
        }
        return reservations;
    }

    @Override
    public boolean DeleteReservation(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
        return reservationDAO.getDistinctDepartments();
    }

    @Override
    public List<String> getDistinctReservationIds() throws SQLException, ClassNotFoundException {
        return reservationDAO.getDistinctReservationIds();
    }

    public List<String> getRoomIdsByReservationId(String reservationId) throws SQLException, ClassNotFoundException {
        return reservationroominfoDAO.getRoomIdsByReservationId(reservationId);
    }

    public boolean placereservation(PlaceReservationDTO reservation) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isReservationSaved = reservationDAO.save(reservation.getReservation());
            System.out.println("isReservationSaved: " + isReservationSaved);
            if (isReservationSaved) {
                // Convert DTO to entity
                List<Reservationpackageinfo> reservationPackageInfoList = new ArrayList<>();
                for (ReservationpackageinfoDTO dto : reservation.getReservationpackageinfo()) {
                    reservationPackageInfoList.add(new Reservationpackageinfo(
                            dto.getReservationID(), dto.getPackageID(), dto.getCustomerID()));
                }
                boolean isPackageInfoSaved = reservationpackageinfoDAO.save(reservationPackageInfoList);
                System.out.println("isPackageInfoSaved: " + isPackageInfoSaved);
                if (isPackageInfoSaved) {
                    boolean isRoomUpdated = roomDAO.updateRoom(reservation.getReservationroominfo());
                    System.out.println("isRoomUpdated: " + isRoomUpdated);
                    if (isRoomUpdated) {
                        boolean isPaymentInfoSaved = paymentDAO.save(new Payment(reservation.getReservationpaymentinfo()));
                        System.out.println("isPaymentInfoSaved: " + isPaymentInfoSaved);
                        if (isPaymentInfoSaved) {
                            // Convert DTO to entity
                            List<Reservationserviceinfo> reservationServiceInfoList = new ArrayList<>();
                            for (ReservationserviceinfoDTO dto : reservation.getReservationserviceinfo()) {
                                reservationServiceInfoList.add(new Reservationserviceinfo(
                                        dto.getReservationID(), dto.getServiceID()));
                            }
                            boolean isServiceInfoSaved = reservationserviceinfoDAO.saveRSI(reservationServiceInfoList);
                            System.out.println("isServiceInfoSaved: " + isServiceInfoSaved);
                            if (isServiceInfoSaved) {
                                connection.commit();
                                System.out.println("Reservation placed successfully.");
                                return true;
                            } else {
                                System.out.println("Failed to save service info.");
                            }
                        } else {
                            System.out.println("Failed to save payment info.");
                        }
                    } else {
                        System.out.println("Failed to save room info or update room.");
                    }
                } else {
                    System.out.println("Failed to save package info.");
                }
            } else {
                System.out.println("Failed to save reservation.");
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
